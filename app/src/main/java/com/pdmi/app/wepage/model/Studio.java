package com.pdmi.app.wepage.model;

import java.util.List;

/**
 * 工作室详情
 * Created by guoxiuli on 2017/2/20.
 */

public class Studio {

  /**
   * code : 0
   * name : 一本政经
   * logoFile :
   * list : [{"articleId":"553855","title":"专家解读：2017，正风反腐七项任务怎么抓 ","artUrl":"/json/c/2017-01-09/553855.json","logoFile":"http://original.hubpd.com/upload/resources/image/2017/01/09/51068_500x500.png","author":"","source":"人民日报中央厨房","publishDate":"2017-01-09"},{"articleId":"553854","title":"中纪委七次全会新闻发布会，你最关心啥 ","artUrl":"/json/c/2017-01-09/553854.json","logoFile":"http://original.hubpd.com/upload/resources/image/2017/01/09/51059_500x500.png","author":"&#32;姜洁","source":"人民日报中央厨房记者","publishDate":"2017-01-09"},{"articleId":"553853","title":"教你读懂中央纪委七次全会公报 ","artUrl":"/json/c/2017-01-08/553853.json","logoFile":"http://original.hubpd.com/upload/resources/image/2017/01/08/51008_500x500.png","author":"姜洁","source":"人民日报中央厨房记者","publishDate":"2017-01-08"}]
   * pageIndex : 1
   * pageSize : 10
   * pageTotal : 3
   * isMore : false
   */

  private int code;
  private String name;
  private String logoFile;
  private String pageIndex;
  private String pageSize;
  private String pageTotal;
  private boolean isMore;
  private String description;
  private List<ArticleItem> list;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLogoFile() {
    return logoFile;
  }

  public void setLogoFile(String logoFile) {
    this.logoFile = logoFile;
  }

  public String getPageIndex() {
    return pageIndex;
  }

  public void setPageIndex(String pageIndex) {
    this.pageIndex = pageIndex;
  }

  public String getPageSize() {
    return pageSize;
  }

  public void setPageSize(String pageSize) {
    this.pageSize = pageSize;
  }

  public String getPageTotal() {
    return pageTotal;
  }

  public void setPageTotal(String pageTotal) {
    this.pageTotal = pageTotal;
  }

  public boolean isIsMore() {
    return isMore;
  }

  public void setIsMore(boolean isMore) {
    this.isMore = isMore;
  }

  public List<ArticleItem> getList() {
    return list;
  }

  public void setList(List<ArticleItem> list) {
    this.list = list;
  }


  @Override
  public String toString() {
    return "Studio{" +
        "code=" + code +
        ", name='" + name + '\'' +
        ", logoFile='" + logoFile + '\'' +
        ", pageIndex='" + pageIndex + '\'' +
        ", pageSize='" + pageSize + '\'' +
        ", pageTotal='" + pageTotal + '\'' +
        ", isMore=" + isMore +
        ", list=" + list +
        '}';
  }
}
