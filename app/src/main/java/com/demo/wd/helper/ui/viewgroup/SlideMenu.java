package com.demo.wd.helper.ui.viewgroup;

import android.animation.FloatEvaluator;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;

import com.demo.wd.helper.R;
import com.demo.wd.helper.factory.NewsPagerFactory;
import com.demo.wd.helper.pager.news.HomePager;


/**
 * Created by wd on 2016/4/23.
 */
public class SlideMenu extends FrameLayout implements View.OnClickListener {

    private View mMainView;
    private View mMenuView;
    private View mMenuContent;
    private int mMainWidth;
    private int mMainHeight;
    private int mMenuWidth;
    private int mMenuHeight;
    private int mDragRange;
    private ViewDragHelper mViewDragHelper;
    private FloatEvaluator mFloatEvaluator;
    private View mIvHead;
    private float mDownX;
    private float mDownY;
    private long mDownTime;
    private int touchSlop;//

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_head) {
            open();
        }
//        if (v.getId() == R.id.layout_main) {
//            if (mState == SlideState.Open) {
//                close();
//            }
//        }
    }

    private enum SlideState {
        Open, Close;
    }

    private SlideState mState = SlideState.Close;

    public SlideMenu(Context context) {
        this(context, null);
    }

    public SlideMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        mViewDragHelper = ViewDragHelper.create(this, mCallback);
        mFloatEvaluator = new FloatEvaluator();
        touchSlop = ViewConfiguration.getTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMainView = findViewById(R.id.layout_main);
        mMenuView = findViewById(R.id.layout_menu);
        mMenuContent = findViewById(R.id.menu_content);

        mIvHead = findViewById(R.id.iv_head);

        mIvHead.setOnClickListener(this);
//        mMainView.setOnClickListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMainWidth = mMainView.getMeasuredWidth();
        mMainHeight = mMainView.getMeasuredHeight();
        mMenuWidth = mMenuView.getMeasuredWidth();
        mMenuHeight = mMenuView.getMeasuredHeight();

        mDragRange = mMenuContent.getMeasuredWidth();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mMainView.layout(0, 0, mMainWidth, mMainHeight);
        mMenuView.layout(-mMenuWidth, 0, -mMenuWidth + mMenuWidth, mMenuHeight);
    }


//    @Override
//    public boolean onInterceptHoverEvent(MotionEvent event) {
//        if (mState == SlideState.Open) {
//            return mViewDragHelper.shouldInterceptTouchEvent(event);
//        }
//        return super.onInterceptHoverEvent(event);
//    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mState == SlideState.Open) {
            if (ev.getRawX() > mDragRange) {
                return true;
            }
            return mViewDragHelper.shouldInterceptTouchEvent(ev);
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return super.onInterceptHoverEvent(event);
    }

    @Override
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);

        if (mState == SlideState.Open) {
            //手动设置main点击事件
            float rawX = event.getRawX();
            if (rawX > mDragRange) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mDownX = event.getRawX();
                        mDownY = event.getRawY();
                        mDownTime = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_UP:
                        //1.计算按下抬起的距离
                        float xOffset = event.getRawX() - mDownX;
                        float yOffset = event.getRawY() - mDownY;
                        float distance = (float) Math.sqrt(xOffset * xOffset + yOffset * yOffset);
                        //2.计算按下抬起的时间
                        long duration = System.currentTimeMillis() - mDownTime;
                        if (duration < 400 && distance < touchSlop) {
                            close();
                        }
                        break;
                }
                return true;
            }
        }
        return false;
    }

    private ViewDragHelper.Callback mCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == mMainView || child == mMenuView;
        }

        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return mDragRange;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            //限制mainView
            if (child == mMainView) {
                //限制left的最大值
                if (left > mDragRange) {
                    left = mDragRange;
                }
                //限制left的最小值
                else if (left < -mDragRange) {
                    left = -mDragRange;
                }
            } else if (child == mMenuView) {
                //限制left的最大值
                if (left > -mMenuWidth + mDragRange) {
                    left = -mMenuWidth + mDragRange;
                }
                //限制left的最小值
                else if (left < -mMenuWidth) {
                    left = -mMenuWidth;
                }
            }
            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            if (changedView == mMainView) {
                //main固定住
                mMainView.layout(0, 0, mMainWidth, mMainHeight);

                //让menu动
                int newLeft = mMenuView.getLeft() + dx;
                if (newLeft > -mMenuWidth + mDragRange) {
                    newLeft = -mMenuWidth + mDragRange;
                } else if (newLeft < -mMenuWidth) {
                    newLeft = -mMenuWidth;
                }
                mMenuView.layout(newLeft, 0, newLeft + mMenuWidth, mMenuView.getBottom());
            }

            //1.计算mainView滑动的百分比
            float fraction = (mMenuView.getLeft() + mMenuWidth) * 1f / mDragRange;
            //2.根据滑动的百分比执行一些列的伴随动画
            executeAnim(fraction);

        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (mMenuView.getLeft() < -mMenuWidth + mDragRange / 2) {
                //应该关闭
                close();
            } else {
                //应该打开
                open();
            }
        }
    };

    private void open() {
        mState = SlideState.Open;
        HomePager pager = (HomePager) NewsPagerFactory.createPager(0);
        pager.stopRoll();
        mViewDragHelper.smoothSlideViewTo(mMenuView, -mMenuWidth + mDragRange, 0);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    private void close() {
        mState = SlideState.Close;
        HomePager pager = (HomePager) NewsPagerFactory.createPager(0);
        pager.startRoll();
        mViewDragHelper.smoothSlideViewTo(mMenuView, -mMenuWidth, 0);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private void executeAnim(float fraction) {
        ViewCompat.setAlpha(mMainView, mFloatEvaluator.evaluate(fraction, 1.0f, 0.3f));
    }

}
