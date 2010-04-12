package org.qualipso.factory.ui.service.oslc.client.view;

import org.qualipso.factory.ui.service.oslc.client.presenter.OSLCPresenter;
import org.qualipso.factory.ui.service.oslc.client.resources.OSLCResources;
import org.qualipso.factory.ui.service.oslc.client.view.CreateOSLCCheckerView.CreateOSLCCheckerWidgetUiBinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

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
	 
	 @UiField
	 Grid ConflictingFiles;
	 
	 @UiField
	 Grid MissingLicenseFiles;
	
	 @UiField
	 Grid UncertainLicensesFiles;
	 
	 @UiFactory
	 public Grid initGrid() {
		 return new Grid(1, 2);
	 }
	 
	 @UiField
	 Button backButton;

	 @UiField
	 TextArea numbersDisplay; 

	public DisplayResultsView(OSLCPresenter presenter) {
		GWT.log("Display Results View!", null);
		startLoading();
		initWidget(uiBinder.createAndBindUi(this));
		resources.style().ensureInjected();
        this.presenter = presenter;
        numbersDisplay.setReadOnly(true);
        
        ConflictingFiles.setWidget(0, 0,new HTML("name"));
        ConflictingFiles.setWidget(0, 1,new HTML("path"));
        MissingLicenseFiles.setWidget(0, 0,new HTML("name"));
        MissingLicenseFiles.setWidget(0, 1,new HTML("path"));
        UncertainLicensesFiles.setWidget(0, 0,new HTML("name"));
        UncertainLicensesFiles.setWidget(0, 1,new HTML("path"));
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
	
	public void setNumbersDisplayValue(String content){
		numbersDisplay.setText(content);
	}
	
	@UiHandler("backButton")
	void onBackButtonClick(ClickEvent e) {
		presenter.loadCreateProjectView(null);
	}
	
	public void startLoading() {
		//newProjectLoadingLabel.addStyleName(resources.style().iconLoader());
	}
	
	public void stopLoading() {
		//newProjectLoadingLabel.removeStyleName(resources.style().iconLoader());
	}
}
