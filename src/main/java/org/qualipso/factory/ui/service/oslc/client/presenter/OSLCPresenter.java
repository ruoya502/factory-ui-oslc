/*
 * Qualipso Factory UI
 * Copyright (C) 2006-2010 INRIA
 * http://www.inria.fr
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation. See the GNU
 * Lesser General Public License in LGPL.txt for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 *
 * Initial authors :
 *
 *  Jérôme Blanchard / INRIA
 * Christophe Bouthier / INRIA
 * Pascal Molli / Nancy Université
 * Gérald Oster / Nancy Université
 */
package org.qualipso.factory.ui.service.oslc.client.presenter;

import org.qualipso.factory.ui.service.oslc.client.iserver.OSLCServlet;
import org.qualipso.factory.ui.service.oslc.client.iserver.OSLCServletAsync;
import org.qualipso.factory.ui.service.oslc.client.model.Results;
import org.qualipso.factory.ui.service.oslc.client.view.ContentView;
import org.qualipso.factory.ui.service.oslc.client.view.CreateOSLCCheckerView;
import org.qualipso.factory.ui.service.oslc.client.view.DisplayResultsView;
import org.qualipso.factory.ui.service.oslc.client.view.ErrorProjectView;
import org.qualipso.factory.ui.shared.OpenParts.client.OPBinder;
import org.qualipso.factory.ui.shared.OpenParts.client.OPParams;
import org.qualipso.factory.ui.shared.OpenParts.client.OPShell; 
import org.qualipso.factory.ui.shared.OpenParts.client.annotations.OPServiceName;
import org.qualipso.factory.ui.shared.OpenParts.client.annotations.OPViewFactory;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * OSLC.
 * 
 * @author Huihui Yang <yhh.ruoya@gmail.com>
 * @date 14 April 2010
 */
@OPServiceName("oslc")
public class OSLCPresenter implements EntryPoint {
    
    public interface OSLCBinder extends OPBinder<OSLCPresenter> {}
    public final static OSLCBinder binder = GWT.create(OSLCBinder.class);

    private final OSLCServletAsync oslcServlet = GWT.create(OSLCServlet.class);

    private CreateOSLCCheckerView OSLCView = null;
    private DisplayResultsView displayResultsView = null;
    private ContentView contentView = null;
    
    private ErrorProjectView errorView = null;
    private PopupPanel popup = null;

    public static final String Select_Files_Conflicting = "conflictingFiles";
    public static final String Select_Files_Uncertaion = "UncertainLicensesFiles";
    public static final String Select_Files_MissingLicense = "MissingLicenseFiles";
    
    private String currentPath = "/";

    protected Widget currentSlot;
    
    /**
     * Entry point, method called when the module is loaded. Create the tree.
     * 
     * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
     */
    @Override
    public void onModuleLoad() {
        GWT.log("Loaded OSLC UI ", null);
        binder.bindPart(this);
        contentView = new ContentView();
        binder.notifyLoadingCompleted();
    }

    @OPViewFactory(resourceName = "oslc", actionName = OPShell.ACTION_CREATE)
    public Widget loadCreateProjectView(OPParams params) {
    	OSLCView = new CreateOSLCCheckerView(this);
    	contentView.changeContentView(OSLCView);
        return contentView;
    }

    @OPViewFactory(resourceName = "oslc", actionName = OPShell.ACTION_DISPLAY)
    public Widget loadDisplayProjectView(OPParams params) {
    	 displayResultsView = new DisplayResultsView(this);	 
    	 contentView.changeContentView(displayResultsView);
    	 return contentView;
    }
      
    public Results getLicensesResult(String svnPath,String userName,String password){
    	OSLCView.startLoading();	
    	//loadDisplayProjectView(null);
    	 GWT.log("begin to call the service asyncCallBack!", null);
        oslcServlet.getLicensesResult(svnPath, userName, password, new AsyncCallback<Results>() {
            @Override
            public void onSuccess(Results results) {
            	OSLCView.stopLoading();
            	 GWT.log("Analyse Results!", null);
            	 loadDisplayProjectView(null);
            	 loadDisplayData(results);//Fill in  results
            }

            @Override
            public void onFailure(Throwable ex) {
            	 GWT.log("Error!", null);
            	 OSLCView.displayMessage("Failed to Connect with WebService!");
            	 OSLCView.stopLoading();
            	 OSLCView.displayMessage(ex.getMessage());
            }
        });
		return null;
    }

    public void showErrorPopup(String message, String trace) {
        errorView = new ErrorProjectView(this);
        errorView.setTitle("An error has occured");
        errorView.setMessage(message);
        errorView.setStacktrace(trace);
        popup = new PopupPanel();
        popup.setStyleName(errorView.resources.style().errorpopup());
        popup.setAnimationEnabled(true);
        popup.setGlassEnabled(true);
        popup.setWidget(errorView);
        popup.center();
        popup.show();
    }

    public void hideErrorPopup() {
        popup.hide();
    }

    public void centerErrorPopup() {
        popup.center();
    }
    
    public String getParentPath(String path) {
        return path.substring(0, path.lastIndexOf('/'));
    }

    
    public void loadDisplayData(Results results){
    	paddingTextArea(results);
    	paddingGrid(results);
    }
    
    public void paddingTextArea(Results results){
    	
        displayResultsView.setAllFilesText(""+results.getAllCount());
        displayResultsView.setSourceFilesText(""+results.getSrcCount());
        displayResultsView.setDistinctLicensesText(""+results.getDisLicensesCount());
        displayResultsView.setConflictsReferenceText(""+results.getConfRefCount());
        displayResultsView.setConflictsGlobalText(""+results.getConfGblCount());
        displayResultsView.setCopyrightedFilesText(""+results.getCopyRightedFiles());
        displayResultsView.setCopyrightHoldersText(""+results.getCopyRightHolders());
    }
    /**
     * 
     * @param results
     * Fill in the grid in OSLCView with files in results
     */
    public void paddingGrid(Results results){
    	String files1 = results.getConflictingFiles();
    	if(files1.length() != 0){
    		String[] filePath1 = files1.split(",");
    		displayResultsView.getConflictingFiles().setWidget(0, 0,new HTML("name"));
    		displayResultsView.getConflictingFiles().setWidget(0, 1,new HTML("path"));
    		displayResultsView.getScrolPanelConflictingFiles().remove(displayResultsView.getInfo3());
    		displayResultsView.getScrolPanelConflictingFiles().add(displayResultsView.getConflictingFiles());
    		
    		for(int i = 0;i < filePath1.length;i ++){
        		String path = filePath1[i];
        		displayResultsView.InsertConflictingFiles(path);
        	}
    	}
    	
    	String files2 = results.getUncertainLicensesFiles();
    	if(files2.length() != 0){
    		String[] filePath2 = files2.split(",");
    		displayResultsView.getUncertainLicensesFiles().setWidget(0, 0,new HTML("name"));
    		displayResultsView.getUncertainLicensesFiles().setWidget(0, 1,new HTML("path"));
    		
    		displayResultsView.getScrolPanelUncertainLicensesFiles().remove(displayResultsView.getInfo2());
    		displayResultsView.getScrolPanelUncertainLicensesFiles().add(displayResultsView.getUncertainLicensesFiles());
    		for(int i = 0;i < filePath2.length;i ++){
        		String path = filePath2[i];
        		displayResultsView.InsertUncertainLicensesFiles(path);
        	}
    	}
    	
    	String files3 = results.getMissingLicenseFiles();
    	if(files3.length() != 0){
    		String[] filePath3 = files3.split(",");
    		displayResultsView.getMissingLicenseFiles().setWidget(0, 0,new HTML("name"));
    		displayResultsView.getMissingLicenseFiles().setWidget(0, 1,new HTML("path"));
    		displayResultsView.getScrolPanelMissingLicenseFiles().remove(displayResultsView.getInfo1());
    		displayResultsView.getScrolPanelMissingLicenseFiles().add(displayResultsView.getMissingLicenseFiles());
    		for(int i = 0;i < filePath3.length;i ++){
        		String path = filePath3[i];
        		displayResultsView.InsertMissingLicenseFiles(path);
        	}
    	}
    }
}
