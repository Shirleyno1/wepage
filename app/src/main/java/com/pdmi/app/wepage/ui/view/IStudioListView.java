package com.pdmi.app.wepage.ui.view;

import com.pdmi.app.wepage.model.StudioItem;
import com.pdmi.app.wepage.model.StudioList;

/**
 * Created by guoxiuli on 2017/2/16.
 */

public interface IStudioListView extends IView{
  void setStudioList(StudioList studios);
  void subscribe(StudioItem item);
}
