package app.shenbh.myscframe.ui.recyclerview.provider;

import android.widget.Toast;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;

import app.shenbh.myscframe.R;
import app.shenbh.myscframe.ui.recyclerview.NormalMultipleEntity;

/**
 * @author shenbh
 * @date 2019/3/31
 * @e-mail 93234929@qq.com
 * 维护者
 */
public class Item2Provider extends BaseItemProvider<NormalMultipleEntity, BaseViewHolder> {

    @Override
    public int viewType() {
        return NormalMultipleEntity.TYPE_2;
    }

    @Override
    public int layout() {
        return R.layout.item_top_adsorption_2;
    }

    @Override
    public void convert(BaseViewHolder helper, NormalMultipleEntity data, int position) {
        helper.setText(R.id.content_tv, data.content);
    }

    @Override
    public void onClick(BaseViewHolder helper, NormalMultipleEntity data, int position) {
        Toast.makeText(mContext, "Item2Provider " + data.content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, NormalMultipleEntity data, int position) {
        Toast.makeText(mContext, "longClick", Toast.LENGTH_SHORT).show();
        return true;
    }
}
