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
package com.cloudbees.eclipse.core.forge.api;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;

import com.cloudbees.eclipse.core.util.Utils;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class ForgeInstance implements Comparable<ForgeInstance>, IAdaptable {

  public static enum TYPE {
    SVN, GIT, CVS
  }

  public static enum STATUS {
    UNKNOWN("Not Configured"), SYNCED("Configured"), SKIPPED("Skipped");

    private final String label;

    private STATUS(final String label) {
      this.label = label;
    }

    public String getLabel() {
      return this.label;
    }
  }

  public String url;
  public String user;
  public String account;
  @Expose(deserialize = false, serialize = false)
  public transient String password;
  public TYPE type;
  public STATUS status = STATUS.UNKNOWN;
  @Expose(deserialize = false, serialize = false)
  public transient Throwable lastException;

  private Map<String, Object> adaptables = new HashMap<String, Object>();

  /** Only for Gson!!! */
  public ForgeInstance() {
  }

  public ForgeInstance(final String url, final String user, final String password, final TYPE type) {
    this.url = url;
    this.user = user;
    this.password = password;
    this.type = type;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
    result = prime * result + ((this.url == null) ? 0 : this.url.hashCode());
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
    if (!(obj instanceof ForgeInstance)) {
      return false;
    }
    ForgeInstance other = (ForgeInstance) obj;
    if (this.type != other.type) {
      return false;
    }
    if (this.url == null) {
      if (other.url != null) {
        return false;
      }
    } else if (!this.url.equals(other.url)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return this.url + " (" + this.status.getLabel().toLowerCase() + ")";
  }

  /**
   * <p>
   * Encodes a list of ForgeInstances to one base64 USASCII String. Internally it's kept in json format.
   * </p>
   * <p>
   * Possible use cases include storing this information in preferences.
   * </p>
   * 
   * @param instances
   * @return
   */
  public final static String encode(final List<ForgeInstance> instances) {
    Type type = new TypeToken<List<ForgeInstance>>() {
    }.getType();
    String json = Utils.createGson().toJson(instances, type);
    return Utils.toB64(json);
  }

  /**
   * <p>
   * Decodes a string to an array of ForgeInstance from base64 string.
   * </p>
   * <p>
   * Possible use cases include storing this information in preferences.
   * </p>
   * 
   * @param encodedInstances
   * @return
   */
  public final static List<ForgeInstance> decode(final String encodedInstances) {
    Type type = new TypeToken<List<ForgeInstance>>() {
    }.getType();
    String json = Utils.fromB64(encodedInstances);
    List<ForgeInstance> ret = Utils.createGson().fromJson(json, type);
    if (ret == null) {
      return new ArrayList<ForgeInstance>();
    }
    return ret;
  }

  @Override
  public int compareTo(final ForgeInstance o) {
    int w = this.type.name().compareTo(o.type.name());
    if (w!=0) {
      return w;
    }
    return this.url.compareToIgnoreCase(o.url);
  }

  public String getTypeName() {
    if (type == null) {
      return "";
    }
    return type.name();
  }

  public void addAdaptable(Class adapter, Object af) {
    adaptables.put(adapter.getName(), af);
  }

  public Object getAdapter(Class adapter) {
    if (adapter != null) {
      Object ret = adaptables.get(adapter.getName());
      return ret;
    }
    return null;
  }

}
