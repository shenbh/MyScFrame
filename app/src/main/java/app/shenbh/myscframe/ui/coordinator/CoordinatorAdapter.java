package app.shenbh.myscframe.ui.coordinator;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import app.shenbh.myscframe.R;

/**
 * @author shenbh
 * @date 2019/3/24
 * @e-mail 93234929@qq.com
 * 维护者
 */
public class CoordinatorAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public CoordinatorAdapter() {
        super(R.layout.item_coordinator);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        helper.setText(R.id.text,item);

    }
}
