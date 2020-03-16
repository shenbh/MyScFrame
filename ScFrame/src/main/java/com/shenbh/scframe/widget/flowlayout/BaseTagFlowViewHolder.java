package com.shenbh.scframe.widget.flowlayout;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * @author shenbh
 * @date 2019/4/12
 * @e-mail 93234929@qq.com
 * 维护者
 */
public class BaseTagFlowViewHolder {

    /**
     * Views indexed with their IDs
     */
    private final SparseArray<View> views;
    private final LinkedHashSet<Integer> childClickViewIds;//需要添加点击事件的子View
    private final LinkedHashSet<Integer> itemChildLongClickViewIds;//需要添加点击事件的子View
    private final HashSet<Integer> nestViews;//需要添加两种点击事件的子View
    public final View itemView;
    private BaseTagFlowAdapter adapter;
    private int position = -1;
    private int viewType = BaseTagFlowAdapter.DEFAULT_VIEW_TYPE;

    public BaseTagFlowViewHolder(final View view) {
        this.itemView = view;
        this.views = new SparseArray<>();
        this.childClickViewIds = new LinkedHashSet<>();
        this.itemChildLongClickViewIds = new LinkedHashSet<>();
        this.nestViews = new HashSet<>();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    /**
     * Will set the text of a TextView.
     *
     * @param viewId The view id.
     * @param value  The text to put in the text view.
     * @return The BaseViewHolder for chaining.
     */
    public BaseTagFlowViewHolder setText(@IdRes int viewId, CharSequence value) {
        TextView view = getView(viewId);
        view.setText(value);
        return this;
    }

    public BaseTagFlowViewHolder setText(@IdRes int viewId, @StringRes int strId) {
        TextView view = getView(viewId);
        view.setText(strId);
        return this;
    }

    /**
     * Will set the image of an ImageView from a resource id.
     *
     * @param viewId     The view id.
     * @param imageResId The image resource id.
     * @return The BaseTagFlowViewHolder for chaining.
     */
    public BaseTagFlowViewHolder setImageResource(@IdRes int viewId, @DrawableRes int imageResId) {
        ImageView view = getView(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    /**
     * Will set background color of a view.
     *
     * @param viewId The view id.
     * @param color  A color, not a resource id.
     * @return The BaseTagFlowViewHolder for chaining.
     */
    public BaseTagFlowViewHolder setBackgroundColor(@IdRes int viewId, @ColorInt int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * Will set background of a view.
     *
     * @param viewId        The view id.
     * @param backgroundRes A resource to use as a background.
     * @return The BaseTagFlowViewHolder for chaining.
     */
    public BaseTagFlowViewHolder setBackgroundRes(@IdRes int viewId, @DrawableRes int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    /**
     * Will set text color of a TextView.
     *
     * @param viewId    The view id.
     * @param textColor The text color (not a resource id).
     * @return The BaseTagFlowViewHolder for chaining.
     */
    public BaseTagFlowViewHolder setTextColor(@IdRes int viewId, @ColorInt int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }


    /**
     * Will set the image of an ImageView from a drawable.
     *
     * @param viewId   The view id.
     * @param drawable The image drawable.
     * @return The BaseTagFlowViewHolder for chaining.
     */
    public BaseTagFlowViewHolder setImageDrawable(@IdRes int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    /**
     * Add an action to set the image of an image view. Can be called multiple times.
     */
    public BaseTagFlowViewHolder setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    /**
     * Add an action to set the alpha of a view. Can be called multiple times.
     * Alpha between 0-1.
     */
    public BaseTagFlowViewHolder setAlpha(@IdRes int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    /**
     * Set a view visibility to VISIBLE (true) or GONE (false).
     *
     * @param viewId  The view id.
     * @param visible True for VISIBLE, false for GONE.
     * @return The BaseTagFlowViewHolder for chaining.
     */
    public BaseTagFlowViewHolder setGone(@IdRes int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * Set a view visibility to VISIBLE (true) or INVISIBLE (false).
     *
     * @param viewId  The view id.
     * @param visible True for VISIBLE, false for INVISIBLE.
     * @return The BaseTagFlowViewHolder for chaining.
     */
    public BaseTagFlowViewHolder setVisible(@IdRes int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        return this;
    }

    /**
     * Add links into a TextView.
     *
     * @param viewId The id of the TextView to linkify.
     * @return The BaseTagFlowViewHolder for chaining.
     */
    public BaseTagFlowViewHolder linkify(@IdRes int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    /**
     * Apply the typeface to the given viewId, and enable subpixel rendering.
     */
    public BaseTagFlowViewHolder setTypeface(@IdRes int viewId, Typeface typeface) {
        TextView view = getView(viewId);
        view.setTypeface(typeface);
        view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        return this;
    }

    /**
     * Apply the typeface to all the given viewIds, and enable subpixel rendering.
     */
    public BaseTagFlowViewHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    /**
     * Sets the progress of a ProgressBar.
     *
     * @param viewId   The view id.
     * @param progress The progress.
     * @return The BaseTagFlowViewHolder for chaining.
     */
    public BaseTagFlowViewHolder setProgress(@IdRes int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    /**
     * Sets the progress and max of a ProgressBar.
     *
     * @param viewId   The view id.
     * @param progress The progress.
     * @param max      The max value of a ProgressBar.
     * @return The BaseTagFlowViewHolder for chaining.
     */
    public BaseTagFlowViewHolder setProgress(@IdRes int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    /**
     * Sets the range of a ProgressBar to 0...max.
     *
     * @param viewId The view id.
     * @param max    The max value of a ProgressBar.
     * @return The BaseTagFlowViewHolder for chaining.
     */
    public BaseTagFlowViewHolder setMax(@IdRes int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    /**
     * Sets the rating (the number of stars filled) of a RatingBar.
     *
     * @param viewId The view id.
     * @param rating The rating.
     * @return The BaseTagFlowViewHolder for chaining.
     */
    public BaseTagFlowViewHolder setRating(@IdRes int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    /**
     * Sets the rating (the number of stars filled) and max of a RatingBar.
     *
     * @param viewId The view id.
     * @param rating The rating.
     * @param max    The range of the RatingBar to 0...max.
     * @return The BaseTagFlowViewHolder for chaining.
     */
    public BaseTagFlowViewHolder setRating(@IdRes int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    /**
     * Sets the on click listener of the view.
     *
     * @param viewId   The view id.
     * @param listener The on click listener;
     * @return The BaseTagFlowViewHolder for chaining.
     */
    @Deprecated
    public BaseTagFlowViewHolder setOnClickListener(@IdRes int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * add childView id
     *
     * @param viewIds add the child views id can support childview click
     * @return if you use adapter bind listener
     * @link {(adapter.setOnItemChildClickListener(listener))}
     * <p>
     * or if you can use  recyclerView.addOnItemTouch(listerer)  wo also support this menthod
     */
    @SuppressWarnings("unchecked")
    public BaseTagFlowViewHolder addOnClickListener(@IdRes final int ...viewIds) {
        for (int viewId : viewIds) {
            childClickViewIds.add(viewId);
            final View view = getView(viewId);
            if (view != null) {
                if (!view.isClickable()) {
                    view.setClickable(true);
                }
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (adapter.getOnItemChildClickListener() != null) {
                            adapter.getOnItemChildClickListener().onItemChildClick(adapter, v, getPosition());
                        }
                    }
                });
            }
        }
        return this;
    }


    /**
     * set nestview id
     *
     * @param viewIds add the child views id   can support childview click
     * @return
     */
    public BaseTagFlowViewHolder setNestView(@IdRes int ... viewIds) {
        for (int viewId : viewIds) {
            nestViews.add(viewId);
        }
        addOnClickListener(viewIds);
        addOnLongClickListener(viewIds);
        return this;
    }

    /**
     * add long click view id
     *
     * @param viewIds
     * @return if you use adapter bind listener
     * @link {(adapter.setOnItemChildLongClickListener(listener))}
     * <p>
     * or if you can use  recyclerView.addOnItemTouch(listerer)  wo also support this menthod
     */
    @SuppressWarnings("unchecked")
    public BaseTagFlowViewHolder addOnLongClickListener(@IdRes final int ... viewIds) {
        for (int viewId : viewIds) {
            itemChildLongClickViewIds.add(viewId);
            final View view = getView(viewId);
            if (view != null) {
                if (!view.isLongClickable()) {
                    view.setLongClickable(true);
                }
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        return adapter.getOnItemChildLongClickListener() != null &&
                                adapter.getOnItemChildLongClickListener().onItemChildLongClick(adapter, v, getPosition());
                    }
                });
            }
        }
        return this;
    }


    /**
     * Sets the on touch listener of the view.
     *
     * @param viewId   The view id.
     * @param listener The on touch listener;
     * @return The BaseTagFlowViewHolder for chaining.
     */
    @Deprecated
    public BaseTagFlowViewHolder setOnTouchListener(@IdRes int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    /**
     * Sets the listview or gridview's item long click listener of the view
     *
     * @param viewId   The view id.
     * @param listener The item long click listener;
     * @return The BaseTagFlowViewHolder for chaining.
     */
    public BaseTagFlowViewHolder setOnItemLongClickListener(@IdRes int viewId, AdapterView.OnItemLongClickListener listener) {
        AdapterView view = getView(viewId);
        view.setOnItemLongClickListener(listener);
        return this;
    }

    /**
     * Sets the listview or gridview's item selected click listener of the view
     *
     * @param viewId   The view id.
     * @param listener The item selected click listener;
     * @return The BaseTagFlowViewHolder for chaining.
     */
    public BaseTagFlowViewHolder setOnItemSelectedClickListener(@IdRes int viewId, AdapterView.OnItemSelectedListener listener) {
        AdapterView view = getView(viewId);
        view.setOnItemSelectedListener(listener);
        return this;
    }

    /**
     * Sets the on checked change listener of the view.
     *
     * @param viewId   The view id.
     * @param listener The checked change listener of compound button.
     * @return The BaseTagFlowViewHolder for chaining.
     */
    public BaseTagFlowViewHolder setOnCheckedChangeListener(@IdRes int viewId, CompoundButton.OnCheckedChangeListener listener) {
        CompoundButton view = getView(viewId);
        view.setOnCheckedChangeListener(listener);
        return this;
    }

    /**
     * Sets the tag of the view.
     *
     * @param viewId The view id.
     * @param tag    The tag;
     * @return The BaseTagFlowViewHolder for chaining.
     */
    public BaseTagFlowViewHolder setTag(@IdRes int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    /**
     * Sets the tag of the view.
     *
     * @param viewId The view id.
     * @param key    The key of tag;
     * @param tag    The tag;
     * @return The BaseTagFlowViewHolder for chaining.
     */
    public BaseTagFlowViewHolder setTag(@IdRes int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    /**
     * Sets the checked status of a checkable.
     *
     * @param viewId  The view id.
     * @param checked The checked status;
     * @return The BaseTagFlowViewHolder for chaining.
     */
    public BaseTagFlowViewHolder setChecked(@IdRes int viewId, boolean checked) {
        View view = getView(viewId);
        // View unable cast to Checkable
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(checked);
        }
        return this;
    }
    /**
     * Set the enabled state of this view.
     *
     * @param viewId  The view id.
     * @param enable The checked status;
     * @return The BaseTagFlowViewHolder for chaining.
     */
    public BaseTagFlowViewHolder setEnabled(@IdRes int viewId,boolean enable) {
        View view = getView(viewId);
        view.setEnabled(enable);
        return this;
    }

    /**
     * Sets the adapter of a adapter view.
     *
     * @param viewId  The view id.
     * @param adapter The adapter;
     * @return The BaseTagFlowViewHolder for chaining.
     */
    @SuppressWarnings("unchecked")
    public BaseTagFlowViewHolder setAdapter(@IdRes int viewId, Adapter adapter) {
        AdapterView view = getView(viewId);
        view.setAdapter(adapter);
        return this;
    }

    /**
     * Sets the adapter of a adapter view.
     *
     * @param adapter The adapter;
     * @return The BaseViewHolder for chaining.
     */
    protected BaseTagFlowViewHolder setAdapter(BaseTagFlowAdapter adapter) {
        this.adapter = adapter;
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }
}
