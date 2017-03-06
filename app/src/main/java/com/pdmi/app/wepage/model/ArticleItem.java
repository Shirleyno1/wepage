package com.pdmi.app.wepage.model;

import io.realm.RealmObject;

/**
 * Created by guoxiuli on 2017/2/21.
 */

public class ArticleItem extends RealmObject{

  /**
   * articleId : 557449
   * title : 麻辣财经：ST慧球闹剧揭秘，信息披露岂能儿戏？
   * url : http://original.hubpd.com/c/2017-01-13/557449.shtml
   * logoFile : http://original.hubpd.com/upload/resources/image/2017/01/13/51349_500x500.jpg
   * author : 谢卫群
   * source : 人民日报中央厨房记者
   * publishDate : 2017-01-13
   */

  private String articleId;
  private String title;
  private String url;
  private String logoFile;
  private String author;
  private String source;
  private String publishDate;
  private boolean hasRead;

  public boolean isHasRead() {
    return hasRead;
  }

  public void setHasRead(boolean hasRead) {
    this.hasRead = hasRead;
  }

  public String getArticleId() {
    return articleId;
  }

  public void setArticleId(String articleId) {
    this.articleId = articleId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getLogoFile() {
    return logoFile;
  }

  public void setLogoFile(String logoFile) {
    this.logoFile = logoFile;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(String publishDate) {
    this.publishDate = publishDate;
  }

  @Override
  public String toString() {
    return "ArticleItem{" +
        "articleId='" + articleId + '\'' +
        ", title='" + title + '\'' +
        ", url='" + url + '\'' +
        ", logoFile='" + logoFile + '\'' +
        ", author='" + author + '\'' +
        ", source='" + source + '\'' +
        ", publishDate='" + publishDate + '\'' +
        '}';
  }
}
