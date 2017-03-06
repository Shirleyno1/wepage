package com.pdmi.app.wepage.model;

import java.util.List;

/**
 * 工作室列表
 * Created by guoxiuli on 2017/2/20.
 */
public class StudioList{

  /**
   * code : 0
   * id : 15573
   * name : 工作室
   * alias : gzs
   * link : /json/gzs/
   * childCount : 13
   * list : [{"id":15574,"subName":"麻辣财经","alias":"gzs_mlcj","link":"/json/gzs/mlcj/","childCount":0},{"id":15575,"subName":"学习大国","alias":"gzs_xxdg","link":"/json/gzs/xxdg/","childCount":0},{"id":15576,"subName":"新地平线","alias":"gzs_xdpx","link":"/json/gzs/xdpx/","childCount":0},{"id":15577,"subName":"半亩方塘","alias":"gzs_bmft","link":"/json/gzs/bmft/","childCount":0},{"id":15578,"subName":"2050","alias":"gzs_2050","link":"/json/gzs/2050/","childCount":0},{"id":15579,"subName":"一秒世界","alias":"gzs_ymsj","link":"/json/gzs/ymsj/","childCount":0},{"id":15580,"subName":"冷观察","alias":"gzs_lgc","link":"/json/gzs/lgc/","childCount":0},{"id":15581,"subName":"一本政经","alias":"gzs_ybzj","link":"/json/gzs/ybzj/","childCount":0},{"id":15582,"subName":"文艺九局","alias":"gzs_wyjj","link":"/json/gzs/wyjj/","childCount":0},{"id":15583,"subName":"智勇新闻","alias":"gzs_zyxw","link":"/json/gzs/zyxw/","childCount":0},{"id":15584,"subName":"碰碰词","alias":"gzs_ppc","link":"/json/gzs/ppc/","childCount":0},{"id":15585,"subName":"侠客岛","alias":"gzs_xkd","link":"/json/gzs/xkd/","childCount":0},{"id":15646,"subName":"最山水","alias":"gzs_zss","link":"/json/gzs/zss/","childCount":0}]
   */

  private int code;

  private int id;
  private String name;
  private String alias;
  private String link;
  private int childCount;
  private List<StudioItem> list;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
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

  public List<StudioItem> getList() {
    return list;
  }

  public void setList(List<StudioItem> list) {
    this.list = list;
  }

  @Override
  public String toString() {
    return "StudioList{" +
        "code=" + code +
        ", id=" + id +
        ", name='" + name + '\'' +
        ", alias='" + alias + '\'' +
        ", link='" + link + '\'' +
        ", childCount=" + childCount +
        ", list=" + list +
        '}';
  }
}
