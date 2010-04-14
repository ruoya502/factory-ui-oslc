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

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Content panel.
 * 
 * @author Huihui Yang <yhh.ruoya@gmail.com>
 * @date 14 April 2010
 */
public class ContentView extends Composite {

    interface ContentViewUiBinder extends UiBinder<SimplePanel, ContentView> {}
    private static ContentViewUiBinder uiBinder = GWT.create(ContentViewUiBinder.class);

    @UiField
    SimplePanel contentPanel;
    
    protected Widget currentView = null;
    
    public ContentView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public void changeContentView(final Widget view) {
        assert view != null;
        
        if (currentView != null) {
            contentPanel.remove(currentView);
        }
        currentView = view;
        contentPanel.add(view);
    }

}