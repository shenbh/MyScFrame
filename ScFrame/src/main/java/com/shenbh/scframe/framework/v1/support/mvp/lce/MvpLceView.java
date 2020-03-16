package com.shenbh.scframe.framework.v1.support.mvp.lce;


import com.shenbh.scframe.framework.v1.support.MvpView;

/**
 * 作者：shenbh
 * 创建时间：2017/10/25
 * 邮箱：93234929@qq.com
 * 实现功能：LCE设计->定义回调接口
 * 备注：
 */
public interface MvpLceView<D> extends MvpView {

    /**
     * 显示Loading页面
     * 一个页面分为两种类型
     * 一种：下拉刷新组件类型->自带加载页面
     * 一种：需要添加加载页面
     *
     * @param pullToRefresh 是否是下拉刷新组件
     */
    void showLoadingLayout(boolean pullToRefresh);

    /**
     * 显示Content页面
     */
    void showContentLayout();

    /**
     * 显示Error页面
     */
    void showErrorLayout();

    /**
     * 绑定数据
     *
     * @param data
     */
    void bindData(D data);

    /**
     * 加载数据
     *
     * @param pullToRefresh
     */
    void loadData(boolean pullToRefresh);

}
