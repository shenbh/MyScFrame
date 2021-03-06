package com.shenbh.scframe.widget.photo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.blankj.utilcode.util.ScreenUtils;
import com.shenbh.scframe.R;
import com.shenbh.scframe.utils.imageloader.ScImageLoader;
import com.shenbh.scframe.utils.log.ScLog;
import com.shenbh.scframe.utils.text.ListUtils;
import com.shenbh.scframe.widget.imageview.SquareImageView;
import com.shenbh.scframe.widget.recyclerview.GridDividerItemDecoration;
import com.shenbh.scframe.widget.recyclerview.NoScrollRecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 九宫格展示图片自定义布局
 * 仿朋友圈
 * 当图片数量 = 1时,显示一张大图(不一定是正方形)
 * 当图片数量 = 2,4时,一行显示2个
 * 其余时候一行显示3个
 *
 * @author shenbh
 * @date 2019/1/16
 * @e-mail 93234929@qq.com
 * 维护者
 */
public class NinePhotoLayout extends FrameLayout {

    private Context mContext;

    private int mMaxSize = 9;//最大显示个数
    private int mItemSpanCount = 3;//单行显示个数
    @DrawableRes
    private int mDefDrawableResId = R.drawable.image_nine_photo_def;//默认占位图
    private int mItemWhiteSpacing = 10;//Item间隔默认10dp
    private int mOtherWhiteSpacing = 100;//除去九宫格剩余空白空间默认100dp
    private boolean mShowAsLargeWhenOnlyOne = true;//当只有一张的时候是否展示大图
    private boolean mShowTwoItemSpanCount = true;//当图片数量为2,4时单行显示2个
    @ColorInt
    private int mItemDecorationColor = Color.TRANSPARENT;//默认间隙颜色
    private int mItemWidth;//item的宽度
    private int mItemHeight;//item的高度

    private ImageView mLargeWhenOnlyOneIv;//单独一张且是大图的时候使用
    private NoScrollRecyclerView mNinePhotoRv;//多图的布局
    private GridLayoutManager mGridLayoutManager;
    private NinePhotoAdapter mNinePhotoAdapter;//多图的adapter
    private onItemClickListener mListener;//点击监听事件

    public interface onItemClickListener {

        /**
         * 点击大图
         *
         * @param view
         * @param ninePhotoItem
         */
        void onClickLargeWhenOnlyOneIv(View view, NinePhotoItem ninePhotoItem);

        /**
         * 点击了九宫格图片
         *
         * @param view
         * @param ninePhotoItem
         * @param position
         */
        void onClickNinePhotoIv(View view, NinePhotoItem ninePhotoItem, int position);
    }

    /**
     * 回调监听,默认Adapter中使用
     *
     * @param listener
     */
    public void setListener(onItemClickListener listener) {
        mListener = listener;
    }

    public NinePhotoLayout(Context context) {
        this(context, null);
    }

    public NinePhotoLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NinePhotoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initDefaultAttrs();
        initCustomAttrs(attrs);
        initView();
    }

    private void initDefaultAttrs() {
        mMaxSize = 9;
        mItemSpanCount = 3;
        mDefDrawableResId = R.drawable.image_nine_photo_def;
        mItemWhiteSpacing = 0;
        mOtherWhiteSpacing = 0;
        mShowAsLargeWhenOnlyOne = true;
        mShowTwoItemSpanCount = true;
        mItemWidth = 0;
        mItemHeight = 0;
    }

    private void initCustomAttrs(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.NinePhotoLayout);
        final int N = typedArray.getIndexCount();
        for (int i = 0; i < N; i++) {
            initCustomAttr(typedArray.getIndex(i), typedArray);
        }
        typedArray.recycle();
    }

    private void initCustomAttr(int attr, TypedArray typedArray) {
        if (attr == R.styleable.NinePhotoLayout_ninePhoto_maxSize) {
            mMaxSize = typedArray.getInteger(attr, mMaxSize);
        } else if (attr == R.styleable.NinePhotoLayout_ninePhoto_defDrawable) {
            mDefDrawableResId = typedArray.getResourceId(attr, mDefDrawableResId);
        } else if (attr == R.styleable.NinePhotoLayout_ninePhoto_itemDecorationColor) {
            mItemDecorationColor = typedArray.getColor(attr, mItemDecorationColor);
        } else if (attr == R.styleable.NinePhotoLayout_ninePhoto_itemWhiteSpacing) {
            mItemWhiteSpacing = typedArray.getDimensionPixelSize(attr, mItemWhiteSpacing);
        } else if (attr == R.styleable.NinePhotoLayout_ninePhoto_otherWhiteSpacing) {
            mOtherWhiteSpacing = typedArray.getDimensionPixelSize(attr, mOtherWhiteSpacing);
        } else if (attr == R.styleable.NinePhotoLayout_ninePhoto_showAsLargeWhenOnlyOne) {
            mShowAsLargeWhenOnlyOne = typedArray.getBoolean(attr, mShowAsLargeWhenOnlyOne);
        } else if (attr == R.styleable.NinePhotoLayout_ninePhoto_showTwoItemSpanCount) {
            mShowTwoItemSpanCount = typedArray.getBoolean(attr, mShowTwoItemSpanCount);
        }
    }

    private void initView() {
        mLargeWhenOnlyOneIv = new ImageView(mContext);
        mLargeWhenOnlyOneIv.setClickable(true);
        mLargeWhenOnlyOneIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //大图点击事件
                if (mListener != null) {
                    mListener.onClickLargeWhenOnlyOneIv(v, mNinePhotoAdapter.getItem(0));
                }
            }
        });

        mNinePhotoRv = new NoScrollRecyclerView(mContext);
        mGridLayoutManager = new GridLayoutManager(mContext, mItemSpanCount);
        mNinePhotoRv.setLayoutManager(mGridLayoutManager);
        mNinePhotoRv.addItemDecoration(new GridDividerItemDecoration(mContext,
                mItemWhiteSpacing, mItemDecorationColor));
        mNinePhotoAdapter = new NinePhotoAdapter();
        mNinePhotoAdapter.bindToRecyclerView(mNinePhotoRv);
        mNinePhotoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //多图的点击事件
                if (mListener != null) {
                    NinePhotoItem item = mNinePhotoAdapter.getItem(position);
                    mListener.onClickNinePhotoIv(view, item, position);
                }
            }
        });
        addView(mLargeWhenOnlyOneIv, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.rightMargin = mOtherWhiteSpacing;
        addView(mNinePhotoRv, layoutParams);
        if (isInEditMode()) {
            //用于Preview中展示
            mNinePhotoRv.setVisibility(GONE);
            mLargeWhenOnlyOneIv.setVisibility(VISIBLE);
            mLargeWhenOnlyOneIv.setImageResource(mDefDrawableResId);
        }
    }

    public void setNewData(List<NormalNinePhotoItem> photos,
                           boolean showAsLargeWhenOnlyOne, boolean showTwoItemSpanCount) {
        mShowAsLargeWhenOnlyOne = showAsLargeWhenOnlyOne;
        mShowTwoItemSpanCount = showTwoItemSpanCount;
        setNewData(photos);
    }

    public void setNewData(List<NormalNinePhotoItem> photos) {
        if (ListUtils.isEmpty(photos)) {
            setVisibility(GONE);
        } else {
            setVisibility(VISIBLE);
            if (photos.size() == 1 && mShowAsLargeWhenOnlyOne) {
                //单张并且展示大图
                showLargeImage(photos);
            } else {
                showNinePhoto(photos);
            }
        }
    }

    private void showLargeImage(List<NormalNinePhotoItem> photos) {
        mNinePhotoRv.setVisibility(GONE);
        mNinePhotoAdapter.setNewData(photos);
        mLargeWhenOnlyOneIv.setVisibility(VISIBLE);
        //设置大图的最大尺寸
        int size = getWidth() - mOtherWhiteSpacing;
        ScLog.debug("NinePhotoLayout showLargeImage size = " + size);
        mLargeWhenOnlyOneIv.setMaxWidth(size);
        mLargeWhenOnlyOneIv.setMaxHeight(size);

        ScImageLoader.getInstance()
                .display(mNinePhotoAdapter.getItem(0).getNinePhotoImageUrl(),
                        mDefDrawableResId,
                        mDefDrawableResId,
                        size,
                        size,
                        mLargeWhenOnlyOneIv);
    }

    private void showNinePhoto(List<NormalNinePhotoItem> photos) {
        int size = photos.size();
        if (mShowTwoItemSpanCount && (size == 2 || size == 4)) {
            //展示2格图
            mItemSpanCount = 2;
        } else {
            mItemSpanCount = 3;
        }
        //宽度用于网络请求拿缩略图
        mItemWidth = (ScreenUtils.getScreenWidth() - mOtherWhiteSpacing - (mItemSpanCount - 1) * mItemWhiteSpacing) / mItemSpanCount;
        ScLog.debug("NinePhotoLayout showNinePhoto mItemWidth = " + mItemWidth);
        mGridLayoutManager.setSpanCount(mItemSpanCount);
        mNinePhotoRv.setVisibility(VISIBLE);
        mNinePhotoAdapter.setNewData(photos);
        mLargeWhenOnlyOneIv.setVisibility(GONE);
    }

    public void clearImageView() {
        ScImageLoader.getInstance().clearImageView(mLargeWhenOnlyOneIv);
    }

    private class NinePhotoAdapter extends BaseQuickAdapter<NormalNinePhotoItem, BaseViewHolder> {

        public NinePhotoAdapter() {
            super(R.layout.item_nine_photo_layout);
        }

        @Override
        public void setNewData(List<NormalNinePhotoItem> data) {
            super.setNewData(data);
        }

        @Override
        public void addData(NormalNinePhotoItem data) {
//            super.addData(data);
        }

        @Override
        protected void convert(BaseViewHolder helper, NormalNinePhotoItem item) {
            helper.addOnClickListener(R.id.nine_photo_image_iv);
            SquareImageView squareImageView = helper.getView(R.id.nine_photo_image_iv);
            ScImageLoader.getInstance()
                    .display(item.getNinePhotoImageUrl(),
                            mDefDrawableResId,
                            mDefDrawableResId,
                            mItemWidth,
                            mItemWidth,
                            squareImageView);
        }
    }
}
