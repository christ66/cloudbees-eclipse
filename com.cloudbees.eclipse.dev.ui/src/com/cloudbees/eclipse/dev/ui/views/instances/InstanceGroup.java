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
package com.cloudbees.eclipse.dev.ui.views.instances;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cloudbees.eclipse.core.jenkins.api.JenkinsInstanceResponse;
import com.cloudbees.eclipse.ui.views.ICBGroup;

class InstanceGroup implements ICBGroup {

  private final List<JenkinsInstanceHolder> instances;
  private final String name;
  private final boolean cloudHosted;
  private boolean loading;

  public InstanceGroup(final String name, final boolean cloudHosted) {
    this.instances = new ArrayList<JenkinsInstanceHolder>();
    this.name = name;
    this.cloudHosted = cloudHosted;
    this.loading = true;
  }

  public boolean isCloudHosted() {
    return this.cloudHosted;
  }

  public String getName() {
    return this.name;
  }

  public void setLoading(final boolean loading) {
    this.loading = loading;
  }

  public boolean isLoading() {
    return this.loading;
  }

  public void addChild(final JenkinsInstanceResponse child) {
    this.instances.add(new JenkinsInstanceHolder(this, child));
  }
  
  public void removeChild(final JenkinsInstanceResponse child) {
    JenkinsInstanceHolder holder = null;
    for (JenkinsInstanceHolder h: instances) {
      if (h.getBacking().equals(child)) {
        holder = h;
      }
    }
    if (holder!=null) {
      this.instances.remove(holder);
    }
  }

  public JenkinsInstanceResponse[] getChildren() {
    List<JenkinsInstanceResponse> resp = new ArrayList<JenkinsInstanceResponse>();
    //build view list
    Iterator<JenkinsInstanceHolder> it = this.instances.iterator();
    while (it.hasNext()) {
      JenkinsInstanceHolder holder = it.next();
      resp.add(holder.getBacking());
    }

    return resp.toArray(new JenkinsInstanceResponse[resp.size()]);
  }

  public boolean hasChildren() {
    return this.instances.size() > 0;
  }

  public void clear() {
    this.instances.clear();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (this.name == null ? 0 : this.name.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof InstanceGroup)) {
      return false;
    }
    InstanceGroup other = (InstanceGroup) obj;
    if (this.name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!this.name.equals(other.name)) {
      return false;
    }
    return true;
  }

  @Override
  public int getOrder() {
    return this.cloudHosted ? 3 : 2;
  }

  public static class DevAtCloudJenkinsInstanceGroup extends InstanceGroup {

    public DevAtCloudJenkinsInstanceGroup(String name, boolean cloudHosted) {
      super(name, cloudHosted);
    }

  }

  public static class OnPremiseJenkinsInstanceGroup extends InstanceGroup {

    public OnPremiseJenkinsInstanceGroup(String name, boolean cloudHosted) {
      super(name, cloudHosted);
    }

  }
}
