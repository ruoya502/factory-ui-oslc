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
package org.qualipso.factory.ui.service.oslc.client.view;

import org.qualipso.factory.ui.service.oslc.client.presenter.OSLCPresenter;
import org.qualipso.factory.ui.service.oslc.client.resources.OSLCResources;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
/**
 * Dispaly Result View.
 * 
 * @author Huihui Yang <yhh.ruoya@gmail.com>
 * @date 14 April 2010
 */


public class DisplayResultsView extends Composite {

	interface DisplayResultsUiBinder extends UiBinder<Widget, DisplayResultsView> {}
	private static DisplayResultsUiBinder uiBinder = GWT.create(DisplayResultsUiBinder.class);


	private OSLCPresenter presenter;
	
	

	@UiField
	public OSLCResources resources;
	
	@UiField
	Label newProjectLoadingLabel;
	
	 @UiField
	 public TabLayoutPanel tabPanel;
	 
	 Grid ConflictingFiles = new Grid(1,2);
	 
	 Grid MissingLicenseFiles= new Grid(1,2);
	
	 Grid UncertainLicensesFiles= new Grid(1,2);
	 

	 
	 @UiField
	 ScrollPanel scrolPanelMissingLicenseFiles;
	 
	 @UiField
	 ScrollPanel scrolPanelUncertainLicensesFiles;
	 
	 @UiField
	 ScrollPanel scrolPanelConflictingFiles;
	 
	 @UiField
	 Button backButton;
		
	 @UiField
	 TextBox allFilesText;
	
	 @UiField
	 TextBox sourceFilesText;
		
	 @UiField
	 TextBox distinctLicensesText;
		
	 @UiField
	 TextBox conflictsReferenceText;

	 @UiField
	 TextBox conflictsGlobalText;
		
	 @UiField
	 TextBox copyrightHoldersText;
	 
	 @UiField
	 TextBox copyrightedFilesText;
	 
	private Label info1 = new Label("There is no Files which are Missing Licenses here!");
	private Label info2 = new Label("There is no Files which are Uncertain License here!");
	private Label info3 = new Label("There is no Files which are Conflicting License here!");
	 
	public DisplayResultsView(OSLCPresenter presenter) {
		GWT.log("Display Results View!", null);
		//startLoading();
		
		initWidget(uiBinder.createAndBindUi(this));
		resources.style().ensureInjected();
        this.presenter = presenter;

        //set style of info 
        info1.addStyleName(resources.style().fieldlabel());
        info2.addStyleName(resources.style().fieldlabel());
        info3.addStyleName(resources.style().fieldlabel());
       
        this.setGridStyle();// set the style of grid panel
        this.setTextReadOnly();//set the number text is read only
        
        //add the info to scrolPanel
        scrolPanelMissingLicenseFiles.add(info1);
        scrolPanelUncertainLicensesFiles.add(info2);
        scrolPanelConflictingFiles.add(info3);
        
	}
	
	public void InsertConflictingFiles(String path){	
	    String[] items =  path.split("/");
	    String name = items[items.length - 1];
		int row = ConflictingFiles.getRowCount();
		
		ConflictingFiles.insertRow(row);
		ConflictingFiles.setWidget(row, 0,new HTML(name));
		ConflictingFiles.setWidget(row, 1,new HTML(path));
	}
	
	public void InsertMissingLicenseFiles(String path){	
	    String[] items = path.split("/");
	    String name = items[items.length - 1];
		int row = MissingLicenseFiles.getRowCount();
		
		MissingLicenseFiles.insertRow(row);
		MissingLicenseFiles.setWidget(row, 0,new HTML(name));
		MissingLicenseFiles.setWidget(row, 1,new HTML(path));
	}
	
	public void InsertUncertainLicensesFiles(String path){	
	    String[] items =  path.split("/");
	    String name = items[items.length - 1];
		int row = UncertainLicensesFiles.getRowCount();
		
		UncertainLicensesFiles.insertRow(row);
		UncertainLicensesFiles.setWidget(row, 0,new HTML(name));
		UncertainLicensesFiles.setWidget(row, 1,new HTML(path));
		
	}
	
	
	@UiHandler("backButton")
	void onBackButtonClick(ClickEvent e) {
		presenter.loadCreateProjectView(null);
	}
	
	public void setGridStyle(){
		
		this.ConflictingFiles.setWidth("580px");
		this.ConflictingFiles.setBorderWidth(1);
		this.ConflictingFiles.setCellSpacing(1);

		this.UncertainLicensesFiles.setWidth("580px");
		this.UncertainLicensesFiles.setBorderWidth(1);
		this.UncertainLicensesFiles.setCellSpacing(1);
		
		this.MissingLicenseFiles.setWidth("580px");
		this.MissingLicenseFiles.setBorderWidth(1);
		this.MissingLicenseFiles.setCellSpacing(1);
		
	}
	
	public void setTextReadOnly(){
		allFilesText.setReadOnly(true);
		sourceFilesText.setReadOnly(true);
		distinctLicensesText.setReadOnly(true);
		conflictsReferenceText.setReadOnly(true);
		conflictsGlobalText.setReadOnly(true);
		copyrightHoldersText.setReadOnly(true);
		copyrightedFilesText.setReadOnly(true);
	}
	
	public void startLoading() {
		newProjectLoadingLabel.addStyleName(resources.style().iconLoader());
	}
	
	public void stopLoading() {
		newProjectLoadingLabel.removeStyleName(resources.style().iconLoader());
	}
	
	
	public String getAllFilesText() {
		return allFilesText.getText();
	}

	public void setAllFilesText(String allFiles) {
		this.allFilesText.setText(allFiles);
	}

	public String getDistinctLicenses() {
		return distinctLicensesText.getText();
	}

	public void setDistinctLicensesText(String distinctLicenses) {
		this.distinctLicensesText.setText(distinctLicenses);
	}

	public String getConflictsReferenceText() {
		return conflictsReferenceText.getText();
	}

	public void setConflictsReferenceText(String conflictsReference) {
		this.conflictsReferenceText.setText(conflictsReference);
	}

	public String getConflictsGlobalText() {
		return conflictsGlobalText.getText();
	}

	public void setConflictsGlobalText(String conflictsGlobal) {
		this.conflictsGlobalText.setText(conflictsGlobal);
	}

	public String getCopyrightHoldersText() {
		return copyrightHoldersText.getText();
	}

	public void setCopyrightHoldersText(String copyrightHolders) {
		this.copyrightHoldersText.setText(copyrightHolders);
	}

	public String getCopyrightedFilesText() {
		return copyrightedFilesText.getText();
	}

	public void setCopyrightedFilesText(String copyrightedFiles) {
		this.copyrightedFilesText.setText(copyrightedFiles);
	}
	
	public String getSourceFilesText() {
		return sourceFilesText.getText();
	}

	public void setSourceFilesText(String sourceFiles) {
		this.sourceFilesText.setText(sourceFiles);
	}
	public Grid getConflictingFiles() {
		return ConflictingFiles;
	}

	public void setConflictingFiles(Grid conflictingFiles) {
		ConflictingFiles = conflictingFiles;
	}

	public Grid getMissingLicenseFiles() {
		return MissingLicenseFiles;
	}

	public void setMissingLicenseFiles(Grid missingLicenseFiles) {
		MissingLicenseFiles = missingLicenseFiles;
	}

	public Grid getUncertainLicensesFiles() {
		return UncertainLicensesFiles;
	}

	public void setUncertainLicensesFiles(Grid uncertainLicensesFiles) {
		UncertainLicensesFiles = uncertainLicensesFiles;
	}

	public ScrollPanel getScrolPanelMissingLicenseFiles() {
		return scrolPanelMissingLicenseFiles;
	}

	public void setScrolPanelMissingLicenseFiles(ScrollPanel scrolPanelMissingLicenseFiles) {
		this.scrolPanelMissingLicenseFiles = scrolPanelMissingLicenseFiles;
	}

	public ScrollPanel getScrolPanelUncertainLicensesFiles() {
		return scrolPanelUncertainLicensesFiles;
	}

	public void setScrolPanelUncertainLicensesFiles(ScrollPanel scrolPanelUncertainLicensesFiles) {
		this.scrolPanelUncertainLicensesFiles = scrolPanelUncertainLicensesFiles;
	}

	public ScrollPanel getScrolPanelConflictingFiles() {
		return scrolPanelConflictingFiles;
	}

	public void setScrolPanelConflictingFiles(ScrollPanel scrolPanelConflictingFiles) {
		this.scrolPanelConflictingFiles = scrolPanelConflictingFiles;
	}
	public Label getInfo1() {
		return info1;
	}

	public void setInfo1(Label info1) {
		this.info1 = info1;
	}

	public Label getInfo2() {
		return info2;
	}

	public void setInfo2(Label info2) {
		this.info2 = info2;
	}

	public Label getInfo3() {
		return info3;
	}

	public void setInfo3(Label info3) {
		this.info3 = info3;
	}
}
