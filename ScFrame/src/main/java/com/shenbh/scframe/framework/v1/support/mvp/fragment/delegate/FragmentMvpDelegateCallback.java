package com.shenbh.scframe.framework.v1.support.mvp.fragment.delegate;

import com.shenbh.scframe.framework.v1.support.MvpPresenter;
import com.shenbh.scframe.framework.v1.support.MvpView;
import com.shenbh.scframe.framework.v1.support.mvp.MvpCallback;

/**
 * 扩展目标接口 针对不同的模块，目标接口有差异
 *
 * @author shenbh
 * @date 2018/1/17
 * @e-mail 93234929@qq.com
 * 维护者
 */
public interface FragmentMvpDelegateCallback<V extends MvpView, P extends MvpPresenter<V>>
        extends MvpCallback<V, P> {

}
