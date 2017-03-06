package com.pdmi.app.wepage.model;

import java.util.List;

/**
 * Created by guoxiuli on 2017/2/21.
 */

public class IndexItem {

  /**
   * id : 15656
   * subName : 麻辣财经
   * alias : gzsapp_mlcj
   * logoFile :
   * link : /json/gzsapp/mlcj/
   * articleList : [{"articleId":"557449","title":"麻辣财经：ST慧球闹剧揭秘，信息披露岂能儿戏？","url":"http://original.hubpd.com/c/2017-01-13/557449.shtml","logoFile":"http://original.hubpd.com/upload/resources/image/2017/01/13/51349_500x500.jpg","author":"谢卫群","source":"人民日报中央厨房记者","publishDate":"2017-01-13"},{"articleId":"557456","title":"麻辣财经：\u201c食盐专营\u201d松绑，市场反映平静","url":"http://original.hubpd.com/c/2017-01-12/557456.shtml","logoFile":"http://original.hubpd.com/upload/resources/image/2017/01/12/51232_500x500.jpg","author":"朱剑红&#32;赵展慧","source":"人民日报中央厨房记者","publishDate":"2017-01-12"},{"articleId":"557455","title":"麻辣财经：新能源汽车，骗补漏洞堵得住吗?","url":"http://original.hubpd.com/c/2017-01-11/557455.shtml","logoFile":"http://original.hubpd.com/upload/resources/image/2017/01/11/51208_500x500.jpg","author":"王政&#32;李丽辉","source":"人民日报中央厨房记者","publishDate":"2017-01-11"}]
   */

  private int id;
  private String subName;
  private String alias;
  private String logoFile;
  private String link;
  private List<ArticleItem> articleList;

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

  public List<ArticleItem> getIndexItemList() {
    return articleList;
  }

  public void setIndexItemList(List<ArticleItem> articleList) {
    this.articleList = articleList;
  }

}
