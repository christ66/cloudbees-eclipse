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
package com.cloudbees.eclipse.dev.ui.views.jobs;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.ui.console.IHyperlink;
import org.eclipse.ui.console.IPatternMatchListenerDelegate;
import org.eclipse.ui.console.PatternMatchEvent;
import org.eclipse.ui.console.TextConsole;

import com.cloudbees.eclipse.ui.CloudBeesUIPlugin;

public class UrlConsoleTracker implements IPatternMatchListenerDelegate {

	private TextConsole console;

	public void connect(final TextConsole console) {
		this.console = console;
	}

	public void disconnect() {
		// ignore
	}

  public void matchFound(final PatternMatchEvent event) {
    try {
      int offset = event.getOffset();
      int length = event.getLength();
      final String url = this.console.getDocument().get(offset, length);
      IHyperlink link = new IHyperlink() {
        public void linkEntered() {
        }

        public void linkExited() {
        }

        public void linkActivated() {
          CloudBeesUIPlugin.getDefault().openWithBrowser(url);
        }
      };
      this.console.addHyperlink(link, offset, length);
    } catch (BadLocationException e) {
    }
  }

}
