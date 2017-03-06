package com.pdmi.app.wepage.ui.view;

import com.pdmi.app.wepage.model.Studio;

/**
 * Created by guoxiuli on 2017/2/16.
 */

public interface IStudioView extends IView{
   void setStudioData(Studio studio);
   void subscribe(boolean isSubscribed);
   void setState(boolean isSubscribed);
}
