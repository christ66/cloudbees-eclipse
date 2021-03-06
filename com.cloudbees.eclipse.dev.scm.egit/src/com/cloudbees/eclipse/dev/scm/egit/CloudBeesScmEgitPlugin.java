/*******************************************************************************
 * Copyright (c) 2013 Cloud Bees, Inc.
 * All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 	Cloud Bees, Inc. - initial API and implementation 
 *******************************************************************************/
package com.cloudbees.eclipse.dev.scm.egit;

import org.eclipse.jsch.core.IJSchService;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import com.cloudbees.eclipse.core.Logger;

public class CloudBeesScmEgitPlugin extends AbstractUIPlugin {

  public static final String PLUGIN_ID = "com.cloudbees.eclipse.dev.scm.egit"; //$NON-NLS-1$
  
  private static CloudBeesScmEgitPlugin plugin;
  private ServiceTracker tracker;
  private Logger logger;
  
  /*
   * (non-Javadoc)
   * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(final BundleContext bundleContext) throws Exception {
    super.start(bundleContext);
    plugin = this;
    this.logger = new Logger(getLog());
    tracker = new ServiceTracker(
        getBundle().getBundleContext(),
        IJSchService.class.getName(),
         null);
     tracker.open();
     
  }

  /*
   * (non-Javadoc)
   * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(final BundleContext bundleContext) throws Exception {
    plugin = null;
    super.stop(bundleContext);
    tracker.close();
    logger = null;
  }

  public IJSchService getJSchService() {
    return (IJSchService)tracker.getService();
  }
  
  public static CloudBeesScmEgitPlugin getDefault() {
    return plugin;
  }
  
  public Logger getLogger() {
    return this.logger;
  }

}
