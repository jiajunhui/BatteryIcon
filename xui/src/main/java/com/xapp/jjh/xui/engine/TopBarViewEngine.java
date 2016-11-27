package com.xapp.jjh.xui.engine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xapp.jjh.xui.R;
import com.xapp.jjh.xui.bean.BaseMenuItem;
import com.xapp.jjh.xui.config.XUIConfig;
import com.xapp.jjh.xui.inter.ITopBarHandle;
import com.xapp.jjh.xui.inter.ITopBarInterface;
import com.xapp.jjh.xui.inter.MenuType;
import com.xapp.jjh.xui.inter.OnMenuItemClickListener;
import com.xapp.jjh.xui.inter.TopBarListener;
import com.xapp.jjh.xui.utils.CommonUtils;
import com.xapp.jjh.xui.view.divider.FlexibleDividerDecoration;
import com.xapp.jjh.xui.view.divider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/11.
 * ------------------------------------
 */
public class TopBarViewEngine extends FrameLayout implements ITopBarHandle{

    private View mContentView;
    private ITopBarInterface iTopBarInterface;
    private TopBarListener topBarListener;
    private boolean isCustomTopBar = false;
    private MenuType menuType = MenuType.TEXT;
    private FrameLayout loadContainer;
    private boolean isTopBarVisible = true;
    private boolean shadowEnable = true;

    protected final int CENTER_TITLE_DRAWABLE_TYPE_LEFT = 1;
    protected final int CENTER_TITLE_DRAWABLE_TYPE_RIGHT = 2;
    private View topBarView;
    private View mShadowView;

    public TopBarViewEngine(Context context,View contentView, ITopBarInterface iTopBarInterface,TopBarListener topBarListener) {
        super(context);
        this.mContentView = contentView;
        this.iTopBarInterface = iTopBarInterface;
        this.topBarListener = topBarListener;
        setBackgroundColor(Color.TRANSPARENT);
        fillContent();
    }

    private void fillContent() {
        if(iTopBarInterface==null)
            return;
        isCustomTopBar = (iTopBarInterface.getCustomTopBarView()!=null);
        if(isCustomTopBar){
            handleCustomTopBar();
        }else{
            handleDefaultTopBar();
            initDefaultTopBarParams();
            setDefaultTopBarListener();
        }
    }

    /**
     * According to the configuration, dynamic adjustment parameters
     */
    private void initDefaultTopBarParams() {
        View navigationView = getNavigationView();
        int titleMarginLeft = -1;
        int titleMarginRight = -1;
        if(navigationView!=null){
            ViewGroup.LayoutParams params = navigationView.getLayoutParams();
            int width = (int) getDimension(XUIConfig.getTopBarNavigationIconWidth());
            if(width!=params.width){
                titleMarginLeft = width;
                params.width = width;
                navigationView.setLayoutParams(params);
            }
            ((ImageView)navigationView).setImageResource(XUIConfig.getTopBarNavigationIcon());
        }
        View menuView = getMenuView();
        if(menuView!=null){
            if(menuView instanceof ImageView){
                ViewGroup.LayoutParams params = menuView.getLayoutParams();
                int width = (int) getDimension(XUIConfig.getTopBarMenuIconWidth());
                if(width!=params.width){
                    titleMarginRight = width;
                    params.width = width;
                    menuView.setLayoutParams(params);
                }
            }else if(menuView instanceof TextView){
                ((TextView)menuView).setTextColor(getColor(XUIConfig.getTopBarMenuTextColor()));
                ((TextView)menuView).setTextSize(TypedValue.COMPLEX_UNIT_SP,XUIConfig.getTopBarMenuTextSize());
            }
        }
        View titleView = getTitleView();
        if(titleView!=null){
            ((TextView)titleView).setTextColor(getColor(XUIConfig.getTopBarTitleTextColor()));
            ((TextView)titleView).setTextSize(TypedValue.COMPLEX_UNIT_SP,XUIConfig.getTopBarTitleTextSize());
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) titleView.getLayoutParams();
            if(titleMarginLeft!=-1){
                params.setMargins(titleMarginLeft,0,params.rightMargin,0);
            }
            if(titleMarginRight!=-1){
                params.setMargins(params.leftMargin,0,titleMarginRight,0);
            }
            titleView.setLayoutParams(params);
        }
    }

    /**
     * set the default top bar view listener
     */
    private void setDefaultTopBarListener() {
        findViewById(R.id.iv_navigation).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(topBarListener!=null){
                    topBarListener.onNavigationClick();
                }
            }
        });
        findViewById(R.id.tv_toolbar_title).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(topBarListener!=null){
                    topBarListener.onTitleClick();
                }
            }
        });
        findViewById(R.id.menu_icon).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(topBarListener!=null){
                    topBarListener.onMenuClick();
                }
            }
        });
        findViewById(R.id.menu_text).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(topBarListener!=null){
                    topBarListener.onMenuClick();
                }
            }
        });
    }

    public View getLoadContainer(){
        return loadContainer;
    }

    /**
     * package content layout
     */
    private void handleDefaultTopBar() {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(params);
        topBarView = iTopBarInterface.getDefaultTopBarView();
        topBarView.setBackgroundColor(getColor(XUIConfig.getTopBarBgColor()));
        topBarView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, (int) getDefaultTopBarHeight()));
        addView(topBarView);

        FrameLayout.LayoutParams userViewParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        userViewParams.topMargin = (int) getContentTopMargin();
        addView(mContentView, userViewParams);
        initLoadContainer((int) getContentTopMargin());
        addView(loadContainer);

        handleTopBarShadow();
    }

    @SuppressLint("NewApi")
    private void handleTopBarShadow() {
        if(shadowEnable){
            getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    mShadowView = new View(getContext());
                    LayoutParams shadowParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) getShadowHeight());
                    shadowParams.topMargin = (int) getDefaultTopBarHeight();
                    mShadowView.setLayoutParams(shadowParams);
                    mShadowView.setBackgroundResource(R.drawable.shape_shadow);
                    addView(mShadowView);
                }
            });
        }
    }

    @SuppressLint("NewApi")
    public void setShadowVisible(final boolean state){
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if(mShadowView!=null){
                    if(state && shadowEnable){
                        mShadowView.setVisibility(View.VISIBLE);
                    }else{
                        mShadowView.setVisibility(View.GONE);
                    }
                }
            }
        });

    }

    private void handleCustomTopBar(){
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(params);
        topBarView = iTopBarInterface.getCustomTopBarView();
        topBarView.setBackgroundColor(getColor(XUIConfig.getTopBarBgColor()));
        topBarView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, (int) getDefaultTopBarHeight()));
        addView(topBarView);

        FrameLayout.LayoutParams userViewParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        userViewParams.topMargin = (int) getContentTopMargin();
        addView(mContentView, userViewParams);
        //add load container
        initLoadContainer((int) getContentTopMargin());
        addView(loadContainer);

        handleTopBarShadow();
    }

    public void initLoadContainer(int height){
        loadContainer = new FrameLayout(getContext());
        FrameLayout.LayoutParams loadContainerParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        loadContainerParams.topMargin = height;
        loadContainer.setBackgroundColor(Color.TRANSPARENT);
        loadContainer.setLayoutParams(loadContainerParams);
    }

    @Override
    public <T extends View> T findTopBarViewById(int id) {
        View view = findViewById(id);
        if(view == null)
            return null;
        return (T) view;
    }

    @Override
    public View getTopBarView() {
        return topBarView;
    }

    private float getContentTopMargin(){
        return getDefaultTopBarHeight();
    }

    private float getDefaultTopBarHeight(){
        return getContext().getResources().getDimension(XUIConfig.getTopBarHeight());
    }

    private float getShadowHeight(){
        return getContext().getResources().getDimension(R.dimen.shadow_height);
    }

    private int getColor(int colorResId){
        return getContext().getResources().getColor(colorResId);
    }

    private float getDimension(int dimen){
        return getContext().getResources().getDimension(dimen);
    }

    @Override
    public void setShadowEnable(boolean enable){
        this.shadowEnable = enable;
        setShadowVisible(enable);
    }

    @Override
    public boolean isTopBarVisible() {
        return isTopBarVisible;
    }

    @Override
    public void setTopBarColor(int color) {
        if(topBarView!=null){
            topBarView.setBackgroundColor(color);
        }
    }

    @Override
    public void setTopBarVisible(boolean visible) {
        if(topBarView!=null && mContentView!=null){
            isTopBarVisible = visible;
            topBarView.setVisibility(visible?View.VISIBLE:View.GONE);
            setShadowVisible(visible);
            LayoutParams params = (LayoutParams) mContentView.getLayoutParams();
            params.topMargin = visible? (int) getDefaultTopBarHeight() :0;
            mContentView.setLayoutParams(params);
        }
    }

    @Override
    public View getNavigationView() {
        if(isCustomTopBar){
            return null;
        }
        return findViewById(R.id.iv_navigation);
    }

    @Override
    public View getMenuView() {
        if(isCustomTopBar)
            return null;
        if(menuType == MenuType.ICON){
            return findViewById(R.id.menu_icon);
        }else if(menuType == MenuType.TEXT){
            return findViewById(R.id.menu_text);
        }
        return null;
    }

    @Override
    public void setTopBarTitle(String title) {
        if(!isCustomTopBar){
            ((TextView)findViewById(R.id.tv_toolbar_title)).setText(title);
        }
    }

    @Override
    public void setTitleDrawable(int drawableType, int drawId) {
        if(isCustomTopBar)
            return;
        Drawable drawable= getResources().getDrawable(drawId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        switch (drawableType){
            case CENTER_TITLE_DRAWABLE_TYPE_LEFT:
                ((TextView)getTitleView()).setCompoundDrawables(drawable,null,null,null);
                break;

            case CENTER_TITLE_DRAWABLE_TYPE_RIGHT:
                ((TextView)getTitleView()).setCompoundDrawables(null,null,drawable,null);
                break;

            default:
                break;
        }
    }

    @Override
    public void setNavigationIcon(int resId) {
        if(!isCustomTopBar){
            ((ImageView)getNavigationView()).setImageResource(resId);
        }
    }

    @Override
    public void setNavigationVisible(boolean visible) {
        if(!isCustomTopBar){
            getNavigationView().setVisibility(visible?View.VISIBLE:View.GONE);
        }
    }

    @Override
    public View getTitleView() {
        if(isCustomTopBar){
            return null;
        }
        return findViewById(R.id.tv_toolbar_title);
    }

    @Override
    public void setMenuEnable(boolean enable) {
        if(!isCustomTopBar){
            findViewById(R.id.menu_icon).setVisibility(enable?View.VISIBLE:View.GONE);
            findViewById(R.id.menu_text).setVisibility(enable?View.VISIBLE:View.GONE);
        }
    }

    @Override
    public void setMenuType(MenuType type, int resId) {
        if(isCustomTopBar)
            return;
        menuType = type;
        if(type == MenuType.ICON){
            findViewById(R.id.menu_text).setVisibility(View.GONE);
            findViewById(R.id.menu_icon).setVisibility(View.VISIBLE);
            ((ImageView)findViewById(R.id.menu_icon)).setImageResource(resId);
        }else if(type == MenuType.TEXT){
            findViewById(R.id.menu_icon).setVisibility(View.GONE);
            findViewById(R.id.menu_text).setVisibility(View.VISIBLE);
            setMenuText(getContext().getString(resId));
        }
    }

    @Override
    public void showMenuList(List<? extends BaseMenuItem> list, final OnMenuItemClickListener onMenuItemClickListener){
        if(list == null || (list!=null && list.size()<=0))
            return;
        final int size = list.size();
        BaseMenuItem item = list.get(0);
        int itemHeightDP = item.getItemHeightDP();
        int itemLineHeightDP = item.getItemPartLineHeightDP();
        int itemLineColor = item.getItemPartLineColor();
        int popHeightDP = size*itemHeightDP + (itemLineHeightDP*(size - 1));
        int heightPx = CommonUtils.dip2px(getContext(),popHeightDP);
        View contentView = View.inflate(getContext(),R.layout.layout_menu,null);
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recycler);
        ViewGroup.LayoutParams params = recyclerView.getLayoutParams();
        params.height = heightPx;
        recyclerView.setLayoutParams(params);
        final PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        HorizontalDividerItemDecoration.Builder horizontalBuilder = new HorizontalDividerItemDecoration.Builder(getContext());
        final int itemSize = CommonUtils.dip2px(getContext(),itemLineHeightDP);
        horizontalBuilder.color(itemLineColor);
        horizontalBuilder.sizeProvider(new FlexibleDividerDecoration.SizeProvider() {
            @Override
            public int dividerSize(int position, RecyclerView parent) {
                if(position == size - 1)
                    return 0;
                return itemSize;
            }
        });
        recyclerView.addItemDecoration(horizontalBuilder.build());
        MenuListAdapter adapter = new MenuListAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(BaseMenuItem menuItem, int position) {
                if(popupWindow!=null && popupWindow.isShowing()){
                    popupWindow.dismiss();
                }
                if(onMenuItemClickListener!=null){
                    onMenuItemClickListener.onMenuItemClick(menuItem,position);
                }
            }
        });
        popupWindow.update();
        int[] location = new int[2];
        getMenuView().getLocationOnScreen(location);
        popupWindow.setAnimationStyle(R.style.menu_pop);
        popupWindow.showAtLocation(getMenuView(), Gravity.NO_GRAVITY,location[0],location[1]);
    }

    @Override
    public void setMenuText(String menuText) {
        if(isCustomTopBar)
            return;
        if(menuType == MenuType.TEXT){
            final TextView menuView = ((TextView)findViewById(R.id.menu_text));
            menuView.setText(menuText);
            menuView.post(new Runnable() {
                @Override
                public void run() {
                    int width = menuView.getWidth();
                    View titleView = getTitleView();
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) titleView.getLayoutParams();
                    params.rightMargin = width;
                    titleView.setLayoutParams(params);
                }
            });
        }
    }

    @Override
    public void setMenuIcon(int icon) {
        if(isCustomTopBar)
            return;
        if(menuType == MenuType.ICON){
            ((ImageView)findViewById(R.id.menu_icon)).setImageResource(icon);
        }
    }

    public class MenuListAdapter extends RecyclerView.Adapter<MenuItemHolder>{

        private List<? extends BaseMenuItem> mList = new ArrayList<>();
        private OnMenuItemClickListener onMenuItemClickListener;

        public MenuListAdapter(List<? extends BaseMenuItem> list){
            this.mList = list;
        }

        public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
            this.onMenuItemClickListener = onMenuItemClickListener;
        }

        @Override
        public MenuItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MenuItemHolder(View.inflate(getContext(),R.layout.item_menu,null));
        }

        @Override
        public void onBindViewHolder(MenuItemHolder holder, final int position) {
            final BaseMenuItem item = mList.get(position);
            int backDrawable = item.getItemBackgroundDrawable();
            int textColor = item.getItemTextColor();
            int textSizeSP = item.getItemTextSizeSP();
            int itemHeightDP = item.getItemHeightDP();
            holder.rl_item_container.setBackgroundResource(backDrawable);
            ViewGroup.LayoutParams params = holder.rl_item_container.getLayoutParams();
            params.height = CommonUtils.dip2px(getContext(),itemHeightDP);
            holder.rl_item_container.setLayoutParams(params);
            holder.tv_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSizeSP);
            holder.tv_text.setTextColor(textColor);
            holder.tv_text.setText(item.getItemText());
            if(item.getIconId()!=-1){
                holder.iv_icon.setImageResource(item.getIconId());
            }
            if(onMenuItemClickListener!=null){
                holder.itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onMenuItemClickListener.onMenuItemClick(item,position);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

    public static class MenuItemHolder extends RecyclerView.ViewHolder{

        RelativeLayout rl_item_container;
        ImageView iv_icon;
        TextView tv_text;

        public MenuItemHolder(View itemView) {
            super(itemView);
            rl_item_container = (RelativeLayout) itemView.findViewById(R.id.rl_item_container);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tv_text = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }


}
