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
package com.cloudbees.eclipse.dev.core.junit;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.eclipse.jdt.internal.junit.model.TestRunSession;
import org.junit.Test;

public class JUnitReportSupportTest {

  @Test
  public void testImportJenkins() throws Exception {
    InputStream report = JUnitReportSupport.class.getResourceAsStream("testReport.xml");
    TestRunSession sess = JUnitReportSupport.importJenkinsTestRunSession(null, null, report);
    assertEquals(3, sess.getTotalCount());
    assertEquals(0, sess.getFailureCount());
    assertEquals(0, sess.getErrorCount());
  }

  @Test
  public void testImportJenkins2() throws Exception {
    InputStream report = JUnitReportSupport.class.getResourceAsStream("testReport2.xml");
    TestRunSession sess = JUnitReportSupport.importJenkinsTestRunSession(null, null, report);
    assertEquals(7788, sess.getTotalCount());
    assertEquals(0, sess.getFailureCount());
    assertEquals(0, sess.getErrorCount());
  }

  @Test
  public void testImportJenkins3() throws Exception {
    InputStream report = JUnitReportSupport.class.getResourceAsStream("testReport3.xml");
    TestRunSession sess = JUnitReportSupport.importJenkinsTestRunSession(null, null, report);
    assertEquals(43, sess.getTotalCount());
    assertEquals(0, sess.getFailureCount());
    assertEquals(0, sess.getErrorCount());
  }

  @Test
  public void testImportJenkins4() throws Exception {
    InputStream report = JUnitReportSupport.class.getResourceAsStream("testReport4.xml");
    TestRunSession sess = JUnitReportSupport.importJenkinsTestRunSession(null, null, report);
    assertEquals(519, sess.getTotalCount());
    assertEquals(2, sess.getFailureCount());
    assertEquals(0, sess.getErrorCount());
  }

  @Test
  public void testImportJenkins5() throws Exception {
    InputStream report = JUnitReportSupport.class.getResourceAsStream("testReport5.xml");
    TestRunSession sess = JUnitReportSupport.importJenkinsTestRunSession(null, null, report);
    assertEquals(4, sess.getTotalCount());
    assertEquals(2, sess.getFailureCount());
    assertEquals(0, sess.getErrorCount());
  }

}
