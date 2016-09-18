package feel.life.wonderful.library_switchbutton;

import android.content.res.TypedArray;
import android.graphics.PointF;

/**
 * Created by queda on 16-9-18.
 */
public class SwitchButtonConfig {

    private int mBgClosedColor;
    private int mBgOpenedColor;
    private int mCircleColor;
    private float mCircleRadius;
    private float mCircleShadowRadius;
    private float mBgWidth;
    private float mBgHeight;

    private PointF mLeftCirclePoint;
    private PointF mRightCirclePoint;

    private static final int DEFAULT_BG_OPENED_COLOR = 0xf85b50;
    private static final int DEFAULT_BG_CLOSED_COLOR = 0xa9a9a9;
    private static final int DEFAULT_CIRCLE_COLOR = 0x82aeff;
    private static final float DEFAULT_CIRCLE_RADIUS = 30;
    private static final float DEFAULT_CIRCLE_SHADOW_RADIUS = 6;
    private static final float DEFAULT_BG_WIDTH = 120;
    private static final float DEFAULT_BG_HEIGHT = 90;

    public SwitchButtonConfig() {
    }

    public SwitchButtonConfig(TypedArray typedArray) {
        mBgOpenedColor = typedArray.getColor(R.styleable.SwitchButton_switchButtonBgColorOpened, DEFAULT_BG_OPENED_COLOR);
        mBgClosedColor = typedArray.getColor(R.styleable.SwitchButton_switchButtonBgColorClosed, DEFAULT_BG_CLOSED_COLOR);
        mCircleColor = typedArray.getColor(R.styleable.SwitchButton_switchButtonCircleColor, DEFAULT_CIRCLE_COLOR);
        mCircleRadius = typedArray.getDimension(R.styleable.SwitchButton_switchButtonCircleRadius, DEFAULT_CIRCLE_RADIUS);
        mCircleShadowRadius = typedArray.getDimension(R.styleable.SwitchButton_switchButtonCircleShadowRadius, DEFAULT_CIRCLE_SHADOW_RADIUS);
        mBgWidth = typedArray.getDimension(R.styleable.SwitchButton_switchButtonBgWidth, DEFAULT_BG_WIDTH);
        mBgHeight = typedArray.getDimension(R.styleable.SwitchButton_switchButtonBgHeight, DEFAULT_BG_HEIGHT);

        mLeftCirclePoint = new PointF();
        mRightCirclePoint = new PointF();
    }

    public void onMeasure(int width, int height) {
        mLeftCirclePoint.set(mBgHeight / 2, mBgHeight / 2);
        mRightCirclePoint.set(mBgWidth - mBgHeight / 2, mBgHeight / 2);
    }

    public PointF getmLeftCirclePoint() {
        return mLeftCirclePoint;
    }

    public PointF getmRightCirclePoint() {
        return mRightCirclePoint;
    }

    public int getmBgClosedColor() {
        return mBgClosedColor;
    }

    public void setmBgClosedColor(int mBgClosedColor) {
        this.mBgClosedColor = mBgClosedColor;
    }

    public int getmBgOpenedColor() {
        return mBgOpenedColor;
    }

    public void setmBgOpenedColor(int mBgOpenedColor) {
        this.mBgOpenedColor = mBgOpenedColor;
    }

    public int getmCircleColor() {
        return mCircleColor;
    }

    public void setmCircleColor(int mCircleColor) {
        this.mCircleColor = mCircleColor;
    }

    public float getmCircleRadius() {
        return mCircleRadius;
    }

    public void setmCircleRadius(float mCircleRadius) {
        this.mCircleRadius = mCircleRadius;
    }

    public float getmCircleShadowRadius() {
        return mCircleShadowRadius;
    }

    public void setmCircleShadowRadius(float mCircleShadowRadius) {
        this.mCircleShadowRadius = mCircleShadowRadius;
    }

    public float getmBgWidth() {
        return mBgWidth;
    }

    public void setmBgWidth(float mBgWidth) {
        this.mBgWidth = mBgWidth;
    }

    public float getmBgHeight() {
        return mBgHeight;
    }

    public void setmBgHeight(float mBgHeight) {
        this.mBgHeight = mBgHeight;
    }
}
