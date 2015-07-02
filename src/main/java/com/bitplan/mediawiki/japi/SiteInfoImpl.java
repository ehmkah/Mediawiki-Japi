/**
 * Copyright (C) 2015 BITPlan GmbH
 *
 * Pater-Delp-Str. 1
 * D-47877 Willich-Schiefbahn
 *
 * http://www.bitplan.com
 * 
 * This source is part of
 * https://github.com/WolfgangFahl/Mediawiki-Japi
 * and the license for Mediawiki-Japi applies
 * 
 */
package com.bitplan.mediawiki.japi;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import com.bitplan.mediawiki.japi.api.General;
import com.bitplan.mediawiki.japi.api.Ns;

/**
 * SiteInfo and namespace handling
 * @author wf
 *
 */
public class SiteInfoImpl implements SiteInfo{
  /**
   * Logging may be enabled by setting debug to true
   */
  protected static java.util.logging.Logger LOGGER = java.util.logging.Logger
      .getLogger("com.bitplan.mediawiki.japi");
  protected String mediawikiVersion;
  
  protected General generalSiteInfo;
  protected Map<String,Ns> namespaces;
  protected Map<String,Ns> namespacesByCanonicalName;
  protected Map<Integer,Ns> namespacesById;
  
  /**
   * initalize me
   * @param general
   * @param list 
   */
  public SiteInfoImpl(General general, List<Ns> list) {
    this.generalSiteInfo=general;
    initNameSpaces(general,list);
  }

  /**
   * initialize the NameSpaces from the given namespaceList
   * @param general
   * @param namespaceList
   */
  protected void initNameSpaces(General general, List<Ns> namespaceList) {
    namespaces=new LinkedHashMap<String,Ns>();
    namespacesById=new LinkedHashMap<Integer,Ns>();
    namespacesByCanonicalName=new LinkedHashMap<String,Ns>();
    for (Ns namespace:namespaceList) {
      namespaces.put(namespace.getValue(), namespace);
      namespacesById.put(namespace.getId(), namespace);
      String canonical=namespace.getCanonical();
      namespacesByCanonicalName.put(canonical,namespace);
    }
  }

  /**
   * @return the siteInfo
   */
  public General getGeneral() {
    return generalSiteInfo;
  }

  /**
   * @param siteInfo the siteInfo to set
   */
  public void setGeneral(General siteInfo) {
    this.generalSiteInfo = siteInfo;
  }

  /**
   * get the Namespaces for this wiki
   * @return
   * @throws Exception
   */
  public Map<String,Ns> getNamespaces() throws Exception {
    if (namespaces==null) {
      getGeneral();
    }
    return namespaces;
  }
  
  /**
   * get the Namespaces by canonical name for this wiki
   * @return
   * @throws Exception
   */
  public Map<String,Ns> getNamespacesByCanonicalName() throws Exception {
    if (namespacesByCanonicalName==null) {
      getGeneral();
    }
    return namespacesByCanonicalName;
  }
  
  /**
   * get the Namespaces for this wiki
   * @return
   * @throws Exception
   */
  public Map<Integer,Ns> getNamespacesById() throws Exception {
    if (namespacesById==null) {
      getGeneral();
    }
    return namespacesById;
  }

  /**
   * map the given namespace to the target wiki
   * @param ns
   * @param targetWiki
   * @return the namespace name for the target wiki
   * @throws Exception 
   */
  public String mapNamespace(String ns, SiteInfo targetWiki) throws Exception {
    Map<String, Ns> sourceMap = this.getNamespaces();
    Map<Integer, Ns> targetMap = targetWiki.getNamespacesById();
    Ns sourceNs = sourceMap.get(ns);
    if (sourceNs==null) {
      LOGGER.log(Level.WARNING,"can not map unknown namespace "+ns);
      return ns;
    }
    Ns targetNs=targetMap.get(sourceNs.getId());
    if (targetNs==null) {
      LOGGER.log(Level.WARNING,"missing namespace "+sourceNs.getValue()+" id:"+sourceNs.getId()+" canonical:"+sourceNs.getCanonical());
      return ns;
    }
    return targetNs.getValue();
  }

  @Override
  public String getLang() {
    String lang=this.getGeneral().getLang();
    return lang;
  }

  @Override
  public String getVersion() throws Exception {
    String mediawikiVersion = this.getGeneral().getGenerator();
    return mediawikiVersion;
  }
}