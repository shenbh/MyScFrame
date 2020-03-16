package app.shenbh.myscframe.ui.flowlayout;

import android.support.annotation.Nullable;
import android.view.View;

import com.shenbh.scframe.widget.flowlayout.BaseMultiItemTagFlowAdapter;
import com.shenbh.scframe.widget.flowlayout.BaseTagFlowViewHolder;

import java.util.List;

import app.shenbh.myscframe.R;

/**
 * @author shenbh
 * @date 2019/4/12
 * @e-mail 93234929@qq.com
 * 维护者
 */
public class FlowLayoutAdapter extends BaseMultiItemTagFlowAdapter<FlowLayoutItem, BaseTagFlowViewHolder> {


    public FlowLayoutAdapter() {
        super();
        addItemType(1, R.layout.item_flow_layout3);
        addItemType(2, R.layout.item_flow_layout4);
        setCheckedStateViewId(1,R.id.flow_item_tv);
        setCheckedStateViewId(2,R.id.flow_item_tv);
    }

    @Override
    public void setNewData(@Nullable List<FlowLayoutItem> data) {
        super.setNewData(data);
    }

    @Override
    protected void convert(BaseTagFlowViewHolder helper, FlowLayoutItem item) {
        int type = helper.getViewType();
        switch (type) {
            case 1:
                helper.setText(R.id.flow_item_tv, item.getMsg());
                break;
            case 2:
                helper.setText(R.id.flow_item_tv, item.getMsg());
                break;
            default:
                break;
        }
    }


    @Override
    protected boolean setDefSelected(FlowLayoutItem flowLayoutItem, int position) {
        return "Android".equals(flowLayoutItem.getMsg());
    }

    @Override
    protected void onSelected(View view, int position) {
    }

    @Override
    protected void unSelected(View view, int position) {
    }
}
