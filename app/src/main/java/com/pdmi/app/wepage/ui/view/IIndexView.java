package com.pdmi.app.wepage.ui.view;

import com.pdmi.app.wepage.model.IndexItem;

import java.util.List;

/**
 * Created by guoxiuli on 2017/2/16.
 */

public interface IIndexView extends IView{
  void setIndexList(List<IndexItem> items);
//  void subscribe(StudioItem item);
}
