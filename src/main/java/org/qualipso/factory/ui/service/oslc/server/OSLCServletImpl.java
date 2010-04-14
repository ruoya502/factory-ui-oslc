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
package org.qualipso.factory.ui.service.oslc.server;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.qualipso.factory.Factory;
import org.qualipso.factory.FactoryException;
import org.qualipso.factory.service.oslc.OSLCService;
import org.qualipso.factory.ui.service.oslc.client.iserver.OSLCServlet;
import org.qualipso.factory.ui.service.oslc.client.iserver.OSLCServletException;
import org.qualipso.factory.ui.service.oslc.client.model.Results;

import com.google.gwt.rpc.server.RpcServlet;

/**
 * OSLC servlet.
 * 
 * @author Huihui Yang <yhh.ruoya@gmail.com>
 * @date 14 April 2010
 */
@SuppressWarnings("serial")
public class OSLCServletImpl extends RpcServlet implements OSLCServlet {

	private static Log logger = LogFactory.getLog(OSLCServletImpl.class);
	private OSLCService oslcService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			oslcService = (OSLCService) Factory.findService(OSLCService.SERVICE_NAME);
		} catch (FactoryException e) {
			logger.error("unabel to get oslc service", e);
			throw new ServletException(e);
		}
	}

	@Override
	public Results getLicensesResult(String svnPath,String userName,String password) throws OSLCServletException{
		logger.debug("getLicensesResult service called in the servlet...");	
		org.qualipso.factory.service.oslc.Results results = null;
		try {
			results = oslcService.getLicensesResult(svnPath, userName, password);
		} catch (Exception e) {
			logger.debug("unable to get information of license:", e);
			throw new OSLCServletException(e.getMessage());
		}
		return ResultsFromQualiPSoResults(results);
	}
	
	public static Results ResultsFromQualiPSoResults(org.qualipso.factory.service.oslc.Results results){
		Results localResults = new Results();
		localResults.setAllCount(results.getAllCount());
		localResults.setConfGblCount(results.getConfGblCount());
		localResults.setConflictingFiles(results.getConflictingFiles());
		localResults.setConfRefCount(results.getConfRefCount());
		localResults.setCopyRightedFiles(results.getCopyRightedFiles());
		localResults.setCopyRightHolders(results.getCopyRightHolders());
		localResults.setDisLicensesCount(results.getDisLicensesCount());
		localResults.setLicenseFileCount(results.getLicenseFileCount());
		localResults.setMissingLicenseFiles(results.getMissingLicenseFiles());
		localResults.setSrcCount(results.getSrcCount());
		localResults.setUncertainLicensesFiles(results.getUncertainLicensesFiles());
		//localResults.setUnknownCount(results.getUnknownCount());
		
		return localResults;
	}

}
