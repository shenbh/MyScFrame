package com.caihan.myscframe;import com.caihan.scframe.framework.ScApplication;import com.caihan.scframe.utils.json.JsonAnalysis;import com.squareup.leakcanary.LeakCanary;import com.squareup.leakcanary.RefWatcher;/** *  * * @author caihan * @date 2018/4/22 * @e-mail 93234929@qq.com * 维护者  */public class MyApp extends ScApplication {    public static final String TAG = "MyScFrame";    private RefWatcher mRefWatcher;    @Override    public void onCreate() {        super.onCreate();        initLeakCanary();        initLogUtils(true, TAG);        initUtilCodeSpUtils(TAG);        JsonAnalysis.getInstance().setJsonType(JsonAnalysis.PARSER_TYPE_FAST_JSON);    }    private void initLeakCanary() {        if (LeakCanary.isInAnalyzerProcess(this)) {            return;        }        mRefWatcher = LeakCanary.install(this);    }    public RefWatcher getRefWatcher() {        return mRefWatcher;    }}