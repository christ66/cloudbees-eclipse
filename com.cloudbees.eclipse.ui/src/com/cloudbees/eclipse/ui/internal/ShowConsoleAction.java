package com.cloudbees.eclipse.ui.internal;

import org.eclipse.ui.PlatformUI;

import com.cloudbees.eclipse.ui.CBImages;
import com.cloudbees.eclipse.ui.CloudBeesUIPlugin;
import com.cloudbees.eclipse.ui.console.BeesConsole;
import com.cloudbees.eclipse.ui.console.BeesConsoleFactory;
import com.cloudbees.eclipse.ui.views.CBTreeAction;

public class ShowConsoleAction extends CBTreeAction {

  public ShowConsoleAction() {
    super();
    setText("CloudBees SDK Console");
    setToolTipText("CloudBees SDK Console");
    setImageDescriptor(CloudBeesUIPlugin.getImageDescription(CBImages.ICON_16X16_CB_CONSOLE));     
  }

  @Override
  public void run() {
    PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

      public void run() {
        BeesConsoleFactory.openGlobalConsole();
        BeesConsole.moveCaret();
        BeesConsole.focusConsole();
    }});

  }

  public boolean isPopup() {
    return false;
  }

  public boolean isPullDown() {
    return true;
  }

  public boolean isToolbar() {
    return false;
  }
}
