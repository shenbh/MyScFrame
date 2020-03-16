package app.shenbh.myscframe.ui.http;

import android.content.Context;

import com.shenbh.scframe.api.ScHttpManager;
import com.shenbh.scframe.framework.v1.support.impl.MvpBasePresenter;
import com.shenbh.scframe.rxjava.RxSchedulers;
import com.shenbh.scframe.rxjava.RxSubscriber;
import com.shenbh.scframe.utils.json.JsonAnalysis;
import com.shenbh.scframe.utils.log.ScLog;
import com.google.gson.reflect.TypeToken;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zhouyou.http.callback.CallClazzProxy;

import java.util.List;

import app.shenbh.myscframe.api.GankApiResult;
import app.shenbh.myscframe.config.Constant;
import app.shenbh.myscframe.model.GankResult;
import app.shenbh.myscframe.model.ResultsBean;

/**
 * @author shenbh
 * @date 2019/3/21
 * @e-mail 93234929@qq.com
 * 维护者
 */
public class RequestJobGetPresenter extends MvpBasePresenter<RequestJobGetContract.View> {


    public RequestJobGetPresenter(Context context) {
        super(context);
    }

    public void onObservable(int type) {
        switch (type) {
            case 0:
                onObservable();
                break;
            case 1:
                onObservable1();
                break;
            case 2:
                onObservable2();
                break;
            default:
                break;
        }
    }

    private void onObservable() {
        ScHttpManager.get(Constant.WELFARE + "/10/1",
                new CallClazzProxy<GankApiResult<List<ResultsBean>>, List<ResultsBean>>(new TypeToken<List<ResultsBean>>() {
                }.getType()) {
                })
                .compose(RxSchedulers.request((RxAppCompatActivity) getView()))
                .subscribe(new RxSubscriber<List<ResultsBean>>(getView()) {
                    @Override
                    public void onRxNext(List<ResultsBean> resultsBeans) {
                        ScLog.debug("   onObservable :: " + resultsBeans.toString());
                        getView().nexObservable(0);
                    }

                    @Override
                    public void onRxError(Throwable error) {
                        ScLog.debug(error.getMessage());
                        getView().nexObservable(0);
                    }
                });
    }

    private void onObservable1() {
        ScHttpManager.get(Constant.WELFARE + "/10/1")
                .compose(RxSchedulers.request((RxAppCompatActivity) getView()))
                .subscribe(new RxSubscriber<String>(getView()) {
                    @Override
                    public void onRxNext(String gankApiResult) {
                        GankResult gankResult = JsonAnalysis.getInstance().fromJson(gankApiResult, GankResult.class);
                        ScLog.debug("   onObservable1 :: " + gankResult.toString());
                        getView().nexObservable(1);
                    }

                    @Override
                    public void onRxError(Throwable error) {
                        ScLog.debug(error.getMessage());
                        getView().nexObservable(1);
                    }
                });
    }

    private void onObservable2() {
        ScHttpManager.get(Constant.WELFARE + "/10/1",
                new CallClazzProxy<GankApiResult<String>, String>(new TypeToken<String>() {
                }.getType()) {
                })
                .compose(RxSchedulers.request((RxAppCompatActivity) getView()))
                .subscribe(new RxSubscriber<String>(getView()) {
                    @Override
                    public void onRxNext(String stringApiResult) {
                        GankResult gankResult = JsonAnalysis.getInstance().fromJson(stringApiResult, GankResult.class);
                        ScLog.debug("   onObservable2 :: " + gankResult.toString());
                        getView().nexObservable(2);
                    }

                    @Override
                    public void onRxError(Throwable error) {
                        ScLog.debug(error.getMessage());
                        getView().nexObservable(2);
                    }
                });
    }

    @Override
    public void destroy() {

    }
}