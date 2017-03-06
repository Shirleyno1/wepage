package com.pdmi.app.wepage.model;

import io.realm.RealmObject;

/**
 * Created by guoxiuli on 2017/2/21.
 */

public class StudioItem extends RealmObject {
  /**
   * id : 15656
   * subName : 麻辣财经
   * alias : gzsapp_mlcj
   * description : 《麻辣财经》是由点石财金制作的一档财经评论类电视脱口秀节目。节目以麻辣的方式，评论财经事件、现象。主要以视频网站的传播为主。其犀利的语言，独特的见解，深的网友喜欢。
   * logoFile :
   * link : /json/gzsapp/mlcj/
   * childCount : 0
   */

  private int id;
  private String subName;
  private String alias;
  private String description;
  private String logoFile;
  private String link;
  private int childCount;
  private boolean hasSubscribed;

  public boolean isHasSubscribed() {
    return hasSubscribed;
  }

  public void setHasSubscribed(boolean hasSubscribed) {
    this.hasSubscribed = hasSubscribed;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getSubName() {
    return subName;
  }

  public void setSubName(String subName) {
    this.subName = subName;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLogoFile() {
    return logoFile;
  }

  public void setLogoFile(String logoFile) {
    this.logoFile = logoFile;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public int getChildCount() {
    return childCount;
  }

  public void setChildCount(int childCount) {
    this.childCount = childCount;
  }

  @Override
  public String toString() {
    return "StudioItem{" +
        "id=" + id +
        ", subName='" + subName + '\'' +
        ", alias='" + alias + '\'' +
        ", description='" + description + '\'' +
        ", logoFile='" + logoFile + '\'' +
        ", link='" + link + '\'' +
        ", childCount=" + childCount +
        ", hasSubscribed=" + hasSubscribed +
        '}';
  }
}

