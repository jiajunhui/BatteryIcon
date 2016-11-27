package com.xapp.jjh.xui.bean;

import com.xapp.jjh.xui.R;
import java.io.Serializable;

/**
 * Created by Taurus on 2016/8/23.
 */
public class BaseMenuItem implements Serializable {
    private final int DEFAULT_ITEM_HEIGHT_DP = 50;
    private final int DEFAULT_ITEM_PART_LINE_HEIGHT_DP = 1;
    private final int DEFAULT_ITEM_PART_LINE_Color = 0x55000000;
    private final int DEFAULT_ITEM_TEXT_COLOR = 0xffffff;
    private final int DEFAULT_ITEM_TEXT_SIZE_SP = 13;
    private final int DEFAULT_ITEM_BACKGROUND_DRAWABLE = R.drawable.selector_default_menu_item;
    private int iconId = -1;
    private String itemText;

    public BaseMenuItem() {
    }

    public BaseMenuItem(int iconId, String itemText) {
        this.iconId = iconId;
        this.itemText = itemText;
    }

    public int getItemHeightDP(){
        return DEFAULT_ITEM_HEIGHT_DP;
    }

    public int getItemPartLineHeightDP(){
        return DEFAULT_ITEM_PART_LINE_HEIGHT_DP;
    }

    public int getItemPartLineColor(){
        return DEFAULT_ITEM_PART_LINE_Color;
    }

    public int getItemTextColor(){
        return DEFAULT_ITEM_TEXT_COLOR;
    }

    public int getItemTextSizeSP(){
        return DEFAULT_ITEM_TEXT_SIZE_SP;
    }

    public int getItemBackgroundDrawable(){
        return DEFAULT_ITEM_BACKGROUND_DRAWABLE;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

}
