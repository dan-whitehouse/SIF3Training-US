/*
 * ProviderServletContext.java
 * Created: 09/03/2014
 *
 * Copyright 2014 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License 
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package persist.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import persist.common.HibernateUtil;


/**
 * This class is to initialise the provider at startup and clean up resources at shutdown.
 * 
 * @author Joerg Huber
 *
 */
public class R1ServletContext implements ServletContextListener
{
	protected final Logger logger = Logger.getLogger(getClass());
	
   	/*
   	 * (non-Javadoc)
   	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
   	 */
    public void contextInitialized(ServletContextEvent servletCtxEvent) 
    {
    	boolean allOK = true;
		logger.info("Initialise R1 Provider: start");
		logger.debug("Initialise R1 DB Connection Pool....");
		if (HibernateUtil.getSessionFactory() == null)
		{
			logger.error("Failed to initialise R1 DB connection pool.");
			allOK = false;
		}
		
		logger.info("Initialise R1 Provider sucessful: "+allOK);
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent servletCtxEvent) 
    {
        logger.info("Shutdown R1 Provider...");

        logger.debug("Release R1 DB Connections....");
        HibernateUtil.shutdown();
        logger.info("Shutdown R1 Provider: done.");
    }
}
