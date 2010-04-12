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
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Project creation view.
 * 
 * @author Jerome Blanchard <jayblanc@gmail.com>
 * @date 16 December 2009
 */
public class CreateOSLCCheckerView extends Composite {
	
	interface CreateOSLCCheckerWidgetUiBinder extends UiBinder<Widget, CreateOSLCCheckerView> {}
	private static CreateOSLCCheckerWidgetUiBinder uiBinder = GWT.create(CreateOSLCCheckerWidgetUiBinder.class);
  
	private OSLCPresenter presenter;
	

	@UiField
	OSLCResources resources;
	
	@UiField
	Label newProjectMessageLabel; 
	
	@UiField
	Label newProjectLoadingLabel;
	
	@UiField
	TextBox newSVNPathTextBox;
	
	@UiField
	Label newSVNPathLegendLabel;
	
	@UiField
	TextBox newUserNameTextBox;
	
	@UiField
	Label newUserNameLegendLabel;
	
	@UiField
	PasswordTextBox newPWDTextBox;
	
	@UiField
	Label newPWDLegendLabel;
	
	@UiField
	Button OSLCButton;
	
	 
	public CreateOSLCCheckerView(OSLCPresenter presenter) {
		initWidget(uiBinder.createAndBindUi(this));
		resources.style().ensureInjected();
		this.presenter = presenter;
		newProjectMessageLabel.setVisible(false);	
	}

	//UI Popups
	public void showErrorPopupPanel(String message) {
		PopupPanel errorPopup = new PopupPanel();
		errorPopup.setAnimationEnabled(true);
		errorPopup.setGlassEnabled(true);
		errorPopup.setTitle("An error occured");
		Label label = new Label();
		label.setText(message);
		errorPopup.setWidget(label);
		errorPopup.show();
	}
	
	public void setSVNPathLabelMessage(String message) {
		newSVNPathLegendLabel.setText(message);
	}
	
	public void setSVNPathTextBoxValue(String path) {
		newSVNPathTextBox.setValue(path);
	}
	
	
	public void displayMessage(String message) {
		newProjectMessageLabel.setText(message);
		blindDown(newProjectMessageLabel.getElement());		
	}
	
	public void hideMessage() {
		blindUp(newProjectMessageLabel.getElement());
	}
	
	public void startLoading() {
		newProjectLoadingLabel.addStyleName(resources.style().iconLoader());
	}
	
	public void stopLoading() {
		newProjectLoadingLabel.removeStyleName(resources.style().iconLoader());
	}
	
	
	private Timer suggestPathTimer = new Timer() {
		@Override
		public void run() {
			presenter.suggestPathFromName(newUserNameTextBox.getValue());
		};
	};


	//Events handling
//	@UiHandler("newSVNPathTextBox")
//	void onPathKeyPressed(KeyUpEvent event) {
//		//checkFreePathTimer.cancel();
//		if (!newSVNPathTextBox.getValue().equals("")) {
//			presenter.checkIfPathIsValid(newSVNPathTextBox.getValue());
//			//checkFreePathTimer.schedule(1200);
//		} else {
//			setSVNPathLabelMessage("");
//			//resetSVNPathLabelStyle();
//		}
//	}

	@UiHandler("newUserNameTextBox")
	void onNameKeyPressed(KeyUpEvent event) {
		//GWT.log("key Predded! ", null);
		suggestPathTimer.cancel();
		if (!newUserNameTextBox.getValue().equals("") && newSVNPathTextBox.getValue().equals("")) {
			suggestPathTimer.schedule(1200);
		}
	}

	@UiHandler("OSLCButton")
	void onOSLCClick(ClickEvent e) {
		GWT.log("Create Click! ", null);
		presenter.getLicensesResult(newSVNPathTextBox.getValue(), newUserNameTextBox.getValue(), newPWDTextBox.getValue());
	}

	//External js effects	
	private final native void blindDown(Element element) /*-{
		new $wnd.Effect.BlindDown(element, {duration : 0.6});
	}-*/;

	private final native void blindUp(Element element) /*-{
		new $wnd.Effect.BlindUp(element, {duration : 0.6});
	}-*/;

	
}
