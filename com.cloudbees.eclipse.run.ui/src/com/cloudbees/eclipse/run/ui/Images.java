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
package com.cloudbees.eclipse.run.ui;

public interface Images {

  String ICONS = "icons";
  String ICONS16 = ICONS + "/16x16";

  String CLOUDBEES_WIZ_ICON_PATH = ICONS + "/cb_wiz_icon.png";
  String CLOUDBEES_WIZ_ICON = Images.class.getSimpleName() + ".cb_wiz_icon.png";

  String CLOUDBEES_ICON_16x16_PATH = ICONS16 + "/cb_plain.png";
  String CLOUDBEES_ICON_16x16 = Images.class.getSimpleName() + ".cb_plain.png";

  String CLOUDBEES_TOMCAT_ICON = Images.class.getSimpleName() + ".tomcat_icon_16_trans.png";
  String CLOUDBEES_TOMCAT_ICON_PATH = ICONS16 + "/tomcat_icon_16_trans.png";

  String CLOUDBEES_FOLDER = Images.class.getSimpleName() + ".cb_folder_cb.png";
  String CLOUDBEES_FOLDER_PATH = ICONS16 + "/cb_folder_cb.png";

  String CLOUDBEES_REFRESH = Images.class.getSimpleName() + ".refresh.png";
  String CLOUDBEES_REFRESH_PATH = ICONS16 + "/refresh.png";
}
