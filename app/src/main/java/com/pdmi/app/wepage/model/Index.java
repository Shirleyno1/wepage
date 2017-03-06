package com.pdmi.app.wepage.model;

import java.util.List;

/**
 * Created by guoxiuli on 2017/2/21.
 */

public class Index {

  /**
   * code : 0
   * name :
   * logoFile :
   * list : [{"id":15656,"subName":"麻辣财经","alias":"gzsapp_mlcj","logoFile":"","link":"/json/gzsapp/mlcj/","articleList":[{"articleId":"557449","title":"麻辣财经：ST慧球闹剧揭秘，信息披露岂能儿戏？","url":"http://original.hubpd.com/c/2017-01-13/557449.shtml","logoFile":"http://original.hubpd.com/upload/resources/image/2017/01/13/51349_500x500.jpg","author":"谢卫群","source":"人民日报中央厨房记者","publishDate":"2017-01-13"},{"articleId":"557456","title":"麻辣财经：\u201c食盐专营\u201d松绑，市场反映平静","url":"http://original.hubpd.com/c/2017-01-12/557456.shtml","logoFile":"http://original.hubpd.com/upload/resources/image/2017/01/12/51232_500x500.jpg","author":"朱剑红&#32;赵展慧","source":"人民日报中央厨房记者","publishDate":"2017-01-12"},{"articleId":"557455","title":"麻辣财经：新能源汽车，骗补漏洞堵得住吗?","url":"http://original.hubpd.com/c/2017-01-11/557455.shtml","logoFile":"http://original.hubpd.com/upload/resources/image/2017/01/11/51208_500x500.jpg","author":"王政&#32;李丽辉","source":"人民日报中央厨房记者","publishDate":"2017-01-11"}]},{"id":15657,"subName":"学习大国","alias":"gzsapp_xxdg","logoFile":"","link":"/json/gzsapp/xxdg/","articleList":[]},{"id":15658,"subName":"新地平线","alias":"gzsapp_xdpx","logoFile":"","link":"/json/gzsapp/xdpx/","articleList":[]},{"id":15659,"subName":"半亩方塘","alias":"gzsapp_bmft","logoFile":"","link":"/json/gzsapp/bmft/","articleList":[]},{"id":15660,"subName":"2050","alias":"gzsapp_2050","logoFile":"","link":"/json/gzsapp/2050/","articleList":[]},{"id":15661,"subName":"一秒世界","alias":"gzsapp_ymsj","logoFile":"","link":"/json/gzsapp/ymsj/","articleList":[]},{"id":15662,"subName":"冷观察","alias":"gzsapp_lgc","logoFile":"","link":"/json/gzsapp/lgc/","articleList":[]},{"id":15663,"subName":"一本政经","alias":"gzsapp_ybzj","logoFile":"","link":"/json/gzsapp/ybzj/","articleList":[{"articleId":"557459","title":"专家解读：2017，正风反腐七项任务怎么抓 ","url":"http://original.hubpd.com/c/2017-01-09/557459.shtml","logoFile":"http://original.hubpd.com/upload/resources/image/2017/01/09/51068_500x500.png","author":"","source":"人民日报中央厨房","publishDate":"2017-01-09"},{"articleId":"557458","title":"中纪委七次全会新闻发布会，你最关心啥 ","url":"http://original.hubpd.com/c/2017-01-09/557458.shtml","logoFile":"http://original.hubpd.com/upload/resources/image/2017/01/09/51059_500x500.png","author":"&#32;姜洁","source":"人民日报中央厨房记者","publishDate":"2017-01-09"},{"articleId":"557457","title":"教你读懂中央纪委七次全会公报 ","url":"http://original.hubpd.com/c/2017-01-08/557457.shtml","logoFile":"http://original.hubpd.com/upload/resources/image/2017/01/08/51008_500x500.png","author":"姜洁","source":"人民日报中央厨房记者","publishDate":"2017-01-08"}]},{"id":15664,"subName":"文艺九局","alias":"gzsapp_wyjj","logoFile":"","link":"/json/gzsapp/wyjj/","articleList":[]},{"id":15665,"subName":"智勇新闻","alias":"gzsapp_zyxw","logoFile":"","link":"/json/gzsapp/zyxw/","articleList":[]},{"id":15666,"subName":"碰碰词","alias":"gzsapp_ppc","logoFile":"","link":"/json/gzsapp/ppc/","articleList":[]},{"id":15667,"subName":"侠客岛","alias":"gzsapp_xkd","logoFile":"","link":"/json/gzsapp/xkd/","articleList":[]},{"id":15668,"subName":"最山水","alias":"gzsapp_zss","logoFile":"","link":"/json/gzsapp/zss/","articleList":[]}]
   */

  private int code;
  private String name;
  private String logoFile;
  private List<IndexItem> list;

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

  public List<IndexItem> getList() {
    return list;
  }

  public void setList(List<IndexItem> list) {
    this.list = list;
  }

}
