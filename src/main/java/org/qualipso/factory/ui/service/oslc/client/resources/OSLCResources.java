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
package org.qualipso.factory.ui.service.oslc.client.resources;

import org.qualipso.factory.ui.service.oslc.client.resources.css.Style;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * Project resources.
 * 
 * @author Jerome Blanchard <jayblanc@gmail.com>
 * @date 16 December 2009
 */
public interface OSLCResources extends ClientBundle {

    @Source("org/qualipso/factory/ui/service/oslc/client/resources/css/style.css")
    Style style();

    @Source("org/qualipso/factory/ui/service/oslc/client/resources/img/loader.gif")
    ImageResource iconLoader();

    @Source("org/qualipso/factory/ui/service/oslc/client/resources/img/accept.png")
    ImageResource iconGood();

    @Source("org/qualipso/factory/ui/service/oslc/client/resources/img/error.png")
    ImageResource iconWrong();

    @Source("org/qualipso/factory/ui/service/oslc/client/resources/img/required.png")
    ImageResource iconRequired();

    @Source("org/qualipso/factory/ui/service/oslc/client/resources/img/project.png")
    ImageResource iconProject();

    @Source("org/qualipso/factory/ui/service/oslc/client/resources/img/error-big.png")
    ImageResource iconError();

    @Source("org/qualipso/factory/ui/service/oslc/client/resources/img/add.png")
    ImageResource iconAdd();

    @Source("org/qualipso/factory/ui/service/oslc/client/resources/img/delete.png")
    ImageResource iconDelete();

    @Source("org/qualipso/factory/ui/service/oslc/client/resources/img/reload.png")
    ImageResource iconRefresh();

    @Source("org/qualipso/factory/ui/service/oslc/client/resources/img/update.png")
    ImageResource iconUpdate();

}
