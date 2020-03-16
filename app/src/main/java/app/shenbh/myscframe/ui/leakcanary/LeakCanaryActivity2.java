package app.shenbh.myscframe.ui.leakcanary;

import com.blankj.utilcode.util.FragmentUtils;

import app.shenbh.myscframe.R;
import app.shenbh.myscframe.base.BaseScActivity;

public class LeakCanaryActivity2 extends BaseScActivity {

    @Override
    protected int setLayoutResId() {
        return R.layout.activity_leak_canary2;
    }

    @Override
    protected void onCreate() {
        getImmersion();
        FragmentUtils.add(getSupportFragmentManager(),
                LeakCanaryFragment.newInstance(),
                R.id.fragments,
                "LeakCanaryFragment");
    }
}
