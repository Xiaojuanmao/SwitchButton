package feel.life.wonderful.library_switchbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author : xiaoxiaoda
 *         date: 16-9-4
 *         email: daque@hustunique.com
 */

public class SwitchButton extends View {

  private int mBgColor;
  private int mCircleColor;
  private float mCircleRadius;
  private float mCircleShadowRadius;
  private float mBgWidth;
  private float mBgHeight;

  private static final int DEFAULT_BG_COLOR = 0xf85b50;
  private static final int DEFAULT_CIRCLE_COLOR = 0x82aeff;
  private static final float DEFAULT_CIRCLE_RADIUS = 30;
  private static final float DEFAULT_CIRCLE_SHADOW_RADIUS = 6;
  private static final float DEFAULT_BG_WIDTH = 120;
  private static final float DEFAULT_BG_HEIGHT = 90;

  public SwitchButton(Context context) {
    this(context, null);
  }

  public SwitchButton(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public SwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {
    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwitchButton);
    mBgColor = typedArray.getColor(R.styleable.SwitchButton_switchButtonBgColor, DEFAULT_BG_COLOR);
    mCircleColor = typedArray.getColor(R.styleable.SwitchButton_switchButtonBgColor, DEFAULT_CIRCLE_COLOR);
    mCircleRadius = typedArray.getDimension(R.styleable.SwitchButton_switchButtonCircleRadius, DEFAULT_CIRCLE_RADIUS);
    mCircleShadowRadius = typedArray.getDimension(R.styleable.SwitchButton_switchButtonCircleShadowRadius, DEFAULT_CIRCLE_SHADOW_RADIUS);
    mBgWidth = typedArray.getDimension(R.styleable.SwitchButton_switchButtonBgWidth, DEFAULT_BG_WIDTH);
    mBgHeight = typedArray.getDimension(R.styleable.SwitchButton_switchButtonBgHeight, DEFAULT_BG_HEIGHT);
    typedArray.recycle();
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int width;
    int height;

    int widthMode = MeasureSpec.getMode(widthMeasureSpec);
    int widthSize = MeasureSpec.getSize(widthMeasureSpec);

    if (widthMode == MeasureSpec.EXACTLY) {
      width = widthSize;
    } else {

    }

    int heightMode = MeasureSpec.getMode(heightMeasureSpec);
    int heightSize = MeasureSpec.getSize(heightMeasureSpec);

  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
  }


}
