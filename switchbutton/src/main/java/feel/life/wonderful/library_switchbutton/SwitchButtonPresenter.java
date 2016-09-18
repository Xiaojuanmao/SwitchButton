package feel.life.wonderful.library_switchbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.TranslateAnimation;

/**
 * Created by queda on 16-9-18.
 */
public class SwitchButtonPresenter implements ISwitchButtonPresenter {

    private Paint mBgPaint;
    private Paint mCirclePaint;

    private float mDeltaX = 0; // 拖拽偏移量x
    private float mMaxLeftDelta = 0; // 向左滑动最大偏移量
    private float mMaxRightDelta = 0; // 向右滑动最大偏移量
    private float mCurrentCircleX;

    private SwitchButtonConfig mConfig;
    private ISwitchButtonView mView;

    public SwitchButtonPresenter(ISwitchButtonView view) {
        mView = view;
    }

    public void initialize(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwitchButton);
        mConfig = new SwitchButtonConfig(typedArray);
        typedArray.recycle();
        initPaints();
        // 用来根据当前状态纠正圆圈的位置
    }

    private void initPaints() {
        int bgColor;
        mBgPaint = new Paint();
        mBgPaint.setAntiAlias(true);
        switch (mView.getState()) {
            case CLOSED:
                bgColor = mConfig.getmBgClosedColor();
                break;

            case OPENED:
                bgColor = mConfig.getmBgOpenedColor();
                break;
            default:
                bgColor = mConfig.getmBgClosedColor();
                break;
        }
        mBgPaint.setColor(bgColor);

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(mConfig.getmCircleColor());

    }

    @Override
    public void onMeasure(int width, int height) {
        mConfig.onMeasure(width, height);
    }

    @Override
    public void onDraw(Canvas canvas) {
        int bgHeight = (int) mConfig.getmBgHeight();
        int bgWidth = (int) mConfig.getmBgWidth();
        drawBackground(canvas, bgWidth, bgHeight);
        drawCircle(canvas);
    }

    /**
     * 手指按下的时候，计算能够左右滑动的最大偏移量
     */
    @Override
    public void onFingerDown() {
        SwitchButton.STATE state = mView.getState();
        switch (state) {
            case CLOSED:
                mMaxLeftDelta = 0;
                mMaxRightDelta = mConfig.getmRightCirclePoint().x - mConfig.getmLeftCirclePoint().x;
                mCurrentCircleX = mConfig.getmLeftCirclePoint().x;
                break;

            case OPENED:
                mMaxLeftDelta = mConfig.getmLeftCirclePoint().x - mConfig.getmRightCirclePoint().x;
                mMaxRightDelta = 0;
                mCurrentCircleX = mConfig.getmRightCirclePoint().x;
                break;
        }
    }

    @Override
    public void onFingerMove(float deltaX) {
        int retval = Float.compare(0, deltaX);
        if (retval > 0) {
            // deltaX 小于0
            if (deltaX < mMaxLeftDelta) {
                mDeltaX = mMaxLeftDelta;
            } else {
                mDeltaX = deltaX;
            }
        } else if (retval < 0) {
            // deltaX 大于0
            if (deltaX > mMaxRightDelta) {
                mDeltaX = mMaxRightDelta;
            } else {
                mDeltaX = deltaX;
            }
        } else {
            // 等于0
            mDeltaX = 0;
        }
        remixBackgroundColor();
    }

    @Override
    public void onFingerUp(float deltaX) {
        // 判断当前偏移量应该自动向开关的哪边进行回弹
        float rate = calculateRate(deltaX);
        if (rate < 0.5f) {
            // close回弹

        } else {
            // open回弹

        }
    }

    /**
     * 根据当前偏移量算出背景的混合颜色
     */
    private void remixBackgroundColor() {
        int closedColor = mConfig.getmBgClosedColor();
        int openedColor = mConfig.getmBgOpenedColor();

        int ocr = Color.red(openedColor);
        int ocg = Color.green(openedColor);
        int ocb = Color.blue(openedColor);
        int ccr = Color.red(closedColor);
        int ccg = Color.green(closedColor);
        int ccb = Color.blue(closedColor);

        // 计算透明度比率
        float rate = calculateRate(mDeltaX);
        int color_all;

        int color_r = remix(ccr, ocr, rate);
        int color_g = remix(ccg, ocg, rate);
        int color_b = remix(ccb, ocb, rate);
        color_all = ((color_r << 16) + (color_g << 8) + color_b) | (0xff000000);

        mBgPaint.setColor(color_all);
    }

    private int remix(int a, int b, float rate) {
        return (int) (a * (1 - rate) + b * rate);
    }

    /**
     * 计算当前小圆圈所在的位置
     *
     * @param value
     * @return
     */
    private float calculateRate(float value) {
        float rate;
        if (value > 0) {
            if (value == mMaxRightDelta) {
                rate = 1.0f;
            } else {
                rate = value / mMaxRightDelta;
            }
        } else {
            if (value == mMaxLeftDelta) {
                rate = 0.0f;
            } else {
                rate = value / mMaxLeftDelta;
            }
        }
        return rate;
    }

    private void drawBackground(Canvas canvas, int bgWidth, int bgHeight) {
        canvas.drawCircle(mConfig.getmLeftCirclePoint().x, mConfig.getmLeftCirclePoint().y, bgHeight / 2, mBgPaint);
        canvas.drawRect(bgHeight / 2, 0, bgWidth - bgHeight / 2, bgHeight, mBgPaint);
        canvas.drawCircle(mConfig.getmRightCirclePoint().x, mConfig.getmRightCirclePoint().y, bgHeight / 2, mBgPaint);
    }

    private void drawCircle(Canvas canvas) {
        float circleX;
        SwitchButton.STATE state = mView.getState();
        switch (state) {
            case CLOSED:
                circleX = mConfig.getmLeftCirclePoint().x;
                break;

            case OPENED:
                circleX = mConfig.getmRightCirclePoint().x;
                break;

            case DRAGGED:
                circleX = mCurrentCircleX + mDeltaX;
                break;
            default:
                circleX = mConfig.getmLeftCirclePoint().x;
                break;
        }
        canvas.drawCircle(circleX, mConfig.getmLeftCirclePoint().y, mConfig.getmCircleRadius(), mCirclePaint);
    }


}
