package com.shenbh.scframe.rxjava;

import com.shenbh.scframe.framework.v1.support.mvp.BaseView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Rx网络请求数据处理工具类<br/>
 * 结合{@link RxSchedulers#request(RxAppCompatActivity, BaseView)}一起使用,效果最佳
 *
 * @author shenbh
 * @date 2018/2/26
 * @e-mail 93234929@qq.com
 * 维护者
 */
public abstract class RxSubscriber<T> implements Observer<T> {

    private boolean mCancelLoading = true;
    private BaseView mBaseView;

    public RxSubscriber(BaseView baseView) {
        mBaseView = baseView;
        mCancelLoading = true;
    }

    public RxSubscriber(BaseView baseView,boolean cancelLoading) {
        mBaseView = baseView;
        mCancelLoading = cancelLoading;
    }

    @Override
    public void onSubscribe(Disposable d) {
        //开始采用subscribe连接
    }

    @Override
    public void onComplete() {
        //对Complete事件作出响应
//        if (cancelLoading) {
//            mBaseView.dismissRequestLoading();
//        }
    }

    @Override
    public void onError(Throwable e) {
        //对Error事件作出响应
        if (mCancelLoading && mBaseView != null) {
            mBaseView.dismissRequestLoading();
        }
        if (e != null) {
            if (mBaseView != null) {
                mBaseView.onRequestError(e.toString());
            }
            onRxError(e);
        }
    }

    @Override
    public void onNext(T t) {
        //接收到的数据
        if (mCancelLoading && mBaseView != null) {
            mBaseView.dismissRequestLoading();
        }
        onRxNext(t);
    }


    public abstract void onRxNext(T t);

    public abstract void onRxError(Throwable error);
}
