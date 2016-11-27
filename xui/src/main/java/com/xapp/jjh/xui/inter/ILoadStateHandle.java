package com.xapp.jjh.xui.inter;

/**
 * Created by Taurus on 16/8/14.
 */
public interface ILoadStateHandle {
    /** you can accord activity page state ,set loading show style*/
    void setPageState(PageState pageState);
    /** show the custom loading dialog*/
    void showLoadingDialog(String message);
    /** close the loading dialog if it has show*/
    void closeLoadingDialog();
}
