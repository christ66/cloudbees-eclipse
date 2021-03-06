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
package com.cloudbees.eclipse.run.core.launchconfiguration;

import com.cloudbees.eclipse.run.core.CBRunCoreActivator;

public interface CBLaunchConfigurationConstants {

  String ID_CB_LAUNCH = "com.cloudbees.eclipse.run.core.launchconfiguration.launchConfigurationType";
  String ID_CB_DEPLOY_LAUNCH = "com.cloudbees.eclipse.run.core.launchcloudconfiguration.launchConfigurationType";
  String ATTR_CB_PROJECT_NAME = CBRunCoreActivator.PLUGIN_ID + ".projectName";
  String ATTR_CB_WST_FLAG = CBRunCoreActivator.PLUGIN_ID + ".wstFlag";
  String ATTR_CB_LAUNCH_BROWSER = CBRunCoreActivator.PLUGIN_ID + ".browser";
  String ATTR_CB_LAUNCH_CUSTOM_ID = CBRunCoreActivator.PLUGIN_ID + ".customId";
  String ATTR_CB_LAUNCH_WAR_PATH = CBRunCoreActivator.PLUGIN_ID + ".warPath";
  String ATTR_CB_PORT = CBRunCoreActivator.PLUGIN_ID + ".port";
  String ATTR_CB_DEBUG_PORT = CBRunCoreActivator.PLUGIN_ID + ".debugPort";
  String ATTR_CB_LOCAL_LAUNCH = CBRunCoreActivator.PLUGIN_ID + ".localLaunch";
  
  public static final String COM_CLOUDBEES_ECLIPSE_WST = "com.cloudbees.eclipse.wst";
  public static final String DO_NOTHING = "DO_NOTHING";

}
