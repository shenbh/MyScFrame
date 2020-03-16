package app.shenbh.myscframe.ui.ninephoto;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.shenbh.scframe.widget.photo.NinePhotoAddLayout;
import com.shenbh.scframe.widget.photo.NinePhotoItem;

import app.shenbh.myscframe.R;
import app.shenbh.myscframe.base.BaseScMvpActivity;
import butterknife.BindView;

/**
 * 九宫格添加图片
 *
 * @author shenbh
 * @date 2019/1/15
 * @e-mail 93234929@qq.com
 * 维护者
 */
public class NinePhotoAddActivity
        extends BaseScMvpActivity<NinePhotoAddContract.View, NinePhotoAddPresenter>
        implements NinePhotoAddContract.View {

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.nine_photo_add_layout)
    NinePhotoAddLayout mNinePhotoAddLayout;

    @Override
    public void setImmersion() {
        getImmersion().setImmersionDarkFont(mToolbar, true);
    }

    @Override
    protected int setLayoutResId() {
        return R.layout.activity_nine_photo_add;
    }

    @NonNull
    @Override
    public NinePhotoAddPresenter createPresenter() {
        return new NinePhotoAddPresenter(mContext);
    }

    @Override
    protected void onCreateMvp() {
        setImmersion();
        mToolbarTitle.setText("九宫格添加图片");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mNinePhotoAddLayout.setListener(new NinePhotoAddLayout.onItemClickListener() {
            @Override
            public void onClickImage(View view, NinePhotoItem ninePhotoItem, int position) {
                showToast("点击了已添加的图片布局");
            }

            @Override
            public void onClickAddImage(View view, NinePhotoItem ninePhotoItem, int position) {
                showToast("点击了添加图片的布局");
            }
        });
    }

}
