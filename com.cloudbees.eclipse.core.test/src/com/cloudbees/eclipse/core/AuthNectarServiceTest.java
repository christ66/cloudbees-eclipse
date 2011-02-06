package com.cloudbees.eclipse.core;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.cloudbees.eclipse.core.domain.NectarInstance;
import com.cloudbees.eclipse.core.nectar.api.NectarInstanceResponse;

public class AuthNectarServiceTest {


  @Test
  public void testSSOAuth() throws CloudBeesException {
    NectarInstance ni = new NectarInstance("grandsomstate", "https://grandomstate.ci.cloudbees.com",
        "ahti@codehoop.com", "xxx", true, true);

    NectarService s = new NectarService(ni);

    NectarInstanceResponse vs = s.getInstance(null);

    assertTrue(vs.views.length > 0);

  }

}
