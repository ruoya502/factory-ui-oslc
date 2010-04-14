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
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Project error view.
 * 
 * @author Huihui Yang <yhh.ruoya@gmail.com>
 * @date 14 April 2010
 */
public class ErrorProjectView extends Composite {

	private static MessageBoxUiBinder uiBinder = GWT.create(MessageBoxUiBinder.class);
	interface MessageBoxUiBinder extends UiBinder<Widget, ErrorProjectView> {}

	private OSLCPresenter presenter;
	
	@UiField
	public OSLCResources resources;
	@UiField
	Label titleLabel;
	@UiField
	Label messageLabel;
	@UiField
	Label stacktraceLabel;
	@UiField
	DisclosurePanel stacktracePanel;
	
	public ErrorProjectView(OSLCPresenter presenter) {
		initWidget(uiBinder.createAndBindUi(this));
		resources.style().ensureInjected();
		this.presenter = presenter;
	}
	
	@UiHandler("hideButton")
	public void clickHideButton(ClickEvent ce) {
		presenter.hideErrorPopup();
	}
	
	@UiHandler("stacktracePanel")
	public void openStackTrace(OpenEvent<DisclosurePanel> oe) {
		presenter.centerErrorPopup();
	}
	
	@UiHandler("stacktracePanel")
	public void closeStackTrace(CloseEvent<DisclosurePanel> ce) {
		presenter.centerErrorPopup();
	}
	
	public void setTitle(String title) {
		titleLabel.setText(title);
	}
	
	public void setMessage(String message) {
		messageLabel.setText("message:\r\n" + message);
	}
	
	public void setStacktrace(String stacktrace) {
		if (stacktrace == null || stacktrace.equals("")) {
			stacktracePanel.setVisible(false);
		} else {
			stacktracePanel.setVisible(true);
		}
		stacktraceLabel.setText(stacktrace);
	}

}
