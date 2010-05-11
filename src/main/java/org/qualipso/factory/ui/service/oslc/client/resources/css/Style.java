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
package org.qualipso.factory.ui.service.oslc.client.resources.css;

import com.google.gwt.resources.client.CssResource;

/**
 * Project css classes methods.
 * 
 * @author Huihui Yang <yhh.ruoya@gmail.com>
 * @date 14 April 2010
 */
public interface Style extends CssResource {
    String iconProject();

    String iconRequired();

    String iconWrong();

    String iconGood();

    String iconLoader();

    String iconError();

    String bodybox();

    String headbox();

    String title();

    String info();

    String message();

    String path();

    String loading();

    String label();

    String content();

    String fieldlabel();

    String fieldlabelrequired();

    String fielddata();

    String fieldlegend();

    String fielderror();

    String fieldgood();

    String fieldsmall();

    String fieldnormal();

    String fieldlarge();

    String textarea();

    String button();

    String actionbutton();

    String actionbuttonrefresh();

    String buttonforward();

    String buttonbackward();

    String createformbox();

    String createinfobox();

    String createfieldbox();

    String createbuttonbox();

    String createfieldlabel();

    String updateformbox();

    String updatetabbox();

    String updatetablabel();

    String updatetablabelactive();

    String updateinfobox();

    String updatefieldbox();

    String updatebuttonbox();

    String updatefieldlabel();

    String displaydatabox();

    String addresourcesbox();
    
    String addresourcepanel();
    
    String displayresourcesbox();

    String displayactionsbox();

    String displaydatalabel();

    String displaydatacontent();

    String displayresourcestitle();

    String errorpopup();

    String errorbox();

    String errortitle();

    String errormessage();

    String errortrace();

    String errorbutton();

    String errorcontrol();
    
    String tabPanel();
    
    String numberLabel();
    
    String numberText();
    
    String tabLayoutPanel();
    
    String tabLayoutPanelContent();
}