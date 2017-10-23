package com.designhumanist.faragojanos.weaterforecast.ui.statelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.designhumanist.faragojanos.weaterforecast.R;

public class StateLayout extends LinearLayout {
    private LayoutInflater inflater;
    public View errorLayout;
    public View emptyLayout;
    public View loadingLayout;
    public View offlineLayout;
    public View contentLayout;

    public LayoutState getState() {
        return state;
    }

    private LayoutState state;
    private StateListener listener;

    public StateLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public StateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public StateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        inflater = LayoutInflater.from(context);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StateLayout);
            try {
                int errorLayoutReference = a.getResourceId(R.styleable.StateLayout_errorLayout, 0);

                if (errorLayoutReference != 0) {
                    errorLayout = inflater.inflate(errorLayoutReference, this, false);
                }


                int emptyLayoutReference = a.getResourceId(R.styleable.StateLayout_emptyLayout, 0);

                if (emptyLayoutReference != 0) {
                    emptyLayout = inflater.inflate(emptyLayoutReference, this, false);
                }

                int loadingLayoutReference = a.getResourceId(R.styleable.StateLayout_loadingLayout, 0);

                if (loadingLayoutReference != 0) {
                    loadingLayout = inflater.inflate(loadingLayoutReference, this, false);
                }

                int offlineLayoutReference = a.getResourceId(R.styleable.StateLayout_offlineLayout, 0);

                if (offlineLayoutReference != 0) {
                    offlineLayout = inflater.inflate(offlineLayoutReference, this, false);
                }


            } finally {
                a.recycle();
            }
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() != 1)
            throw new RuntimeException("StateLayout must have just one child!");
        contentLayout = getChildAt(0);
        if (errorLayout != null) {
            addView(errorLayout);
        }
        if (emptyLayout != null) {
            addView(emptyLayout);
        }

        if (loadingLayout != null) {
            addView(loadingLayout);
        }

        if (offlineLayout != null) {
            addView(offlineLayout);
        }
        setState(LayoutState.Content);
    }

    public void setState(LayoutState state) {
        this.state = state;

        contentLayout.setVisibility(View.GONE);

        if (errorLayout != null) {
            errorLayout.setVisibility(View.GONE);
        }
        if (emptyLayout != null) {
            emptyLayout.setVisibility(View.GONE);
        }

        if (loadingLayout != null) {
            loadingLayout.setVisibility(View.GONE);
        }

        if (offlineLayout != null) {
            offlineLayout.setVisibility(View.GONE);
        }


        switch (state) {
            case Loading:
                if (loadingLayout == null) {
                    throw new RuntimeException("Loading layout is undedined!");
                }
                loadingLayout.setVisibility(View.VISIBLE);
                break;
            case Error:
                if (errorLayout == null) {
                    throw new RuntimeException("Error layout is undedined!");
                }

                errorLayout.setVisibility(View.VISIBLE);
                break;
            case Empty:
                if (emptyLayout == null) {
                    throw new RuntimeException("Empty layout is undedined!");
                }

                emptyLayout.setVisibility(View.VISIBLE);
                break;
            case Offline:
                if (offlineLayout == null) {
                    throw new RuntimeException("Empty layout is undedined!");
                }

                offlineLayout.setVisibility(View.VISIBLE);
                break;
            case Content:
                contentLayout.setVisibility(View.VISIBLE);
                break;
        }

        if (listener != null) {
            listener.onStateChanged(state);
        }

    }

    public StateListener getStateListener() {
        return listener;
    }

    public void setStateListener(StateListener listener) {
        this.listener = listener;
    }
}