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
package com.cloudbees.eclipse.ui.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;

import com.cloudbees.eclipse.ui.AuthStatus;
import com.cloudbees.eclipse.ui.CloudBeesUIPlugin;

public abstract class CBTreeAction extends Action implements CBTreeContributor {

  private boolean authrequired;
  
  public CBTreeAction(boolean authrequired) {
    super();
    this.authrequired=authrequired;
  }
  
  public void contributeTo(IMenuManager menuManager) {
    menuManager.add(this);
  }

  public void contributeTo(IToolBarManager toolBarManager) {
    toolBarManager.add(this);
  }
  /*
  @Override
  public boolean isEnabled() {
    if (!super.isEnabled()) {
      return false;
    }
    
  }*/

  final public void selectionChanged(IAction action, ISelection selection) {
      action.setEnabled((!authrequired || CloudBeesUIPlugin.getDefault().getAuthStatus()==AuthStatus.OK ) && super.isEnabled());
  }

  final public boolean isEnabled() {
    return (!authrequired || CloudBeesUIPlugin.getDefault().getAuthStatus()==AuthStatus.OK) && super.isEnabled();
  }
  
}
