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
package com.cloudbees.eclipse.run.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.ant.core.AntRunner;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.osgi.framework.Bundle;

public class CBRunCoreScripts {

  /**
   * Copies the sample-webapp project to user workspace directory
   * 
   * @param workspacePath
   *          user workspace path
   * @param projectName
   *          name of the project
   * @throws IOException
   * @throws CoreException
   */
  public static void executeCopySampleWebAppScript(String workspacePath, String projectName) throws IOException,
      CoreException {

    Map<String, String> properties = new HashMap<String, String>();
    properties.put("workspacePath", workspacePath);
    properties.put("projectName", projectName);

    Bundle bundle = CBRunCoreActivator.getContext().getBundle();
    Path path = new Path("scripts/copy-project.xml");
    String scriptLocation = FileLocator.toFileURL(FileLocator.find(bundle, path, null)).getFile();

    AntRunner antRunner = new AntRunner();
    antRunner.setBuildFileLocation(scriptLocation);
    antRunner.setExecutionTargets(new String[] { "copy-sample-webapp" });
    antRunner.addUserProperties(properties);

    antRunner.run();
  }

}
