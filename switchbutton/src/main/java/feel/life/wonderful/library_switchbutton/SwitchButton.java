package feel.life.wonderful.library_switchbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
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

  private Paint mBgPaint;
  private Paint mCirclePaint;
  private Paint mShadowPaint;

  private PointF mLeftCirclePoint;
  private PointF mRightCirclePoint;

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
    mCircleColor = typedArray.getColor(R.styleable.SwitchButton_switchButtonCircleColor, DEFAULT_CIRCLE_COLOR);
    mCircleRadius = typedArray.getDimension(R.styleable.SwitchButton_switchButtonCircleRadius, DEFAULT_CIRCLE_RADIUS);
    mCircleShadowRadius = typedArray.getDimension(R.styleable.SwitchButton_switchButtonCircleShadowRadius, DEFAULT_CIRCLE_SHADOW_RADIUS);
    mBgWidth = typedArray.getDimension(R.styleable.SwitchButton_switchButtonBgWidth, DEFAULT_BG_WIDTH);
    mBgHeight = typedArray.getDimension(R.styleable.SwitchButton_switchButtonBgHeight, DEFAULT_BG_HEIGHT);
    typedArray.recycle();

    initPaints();
  }

  private void initPaints() {
    mBgPaint = new Paint();
    mBgPaint.setAntiAlias(true);
    mBgPaint.setColor(mBgColor);

    mCirclePaint = new Paint();
    mCirclePaint.setAntiAlias(true);
    mCirclePaint.setColor(mCircleColor);

    mShadowPaint = new Paint();

    mLeftCirclePoint = new PointF();
    mRightCirclePoint = new PointF();
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int width;
    int height;

    int widthMode = MeasureSpec.getMode(widthMeasureSpec);
    int widthSize = MeasureSpec.getSize(widthMeasureSpec);

    if (widthMode == MeasureSpec.EXACTLY) {
      // 处理match_parent和dimen
      width = widthSize;
    } else {
      // 处理wrap_content

      width = widthSize;
    }

    int heightMode = MeasureSpec.getMode(heightMeasureSpec);
    int heightSize = MeasureSpec.getSize(heightMeasureSpec);

    if (heightMode == MeasureSpec.EXACTLY) {
      height = heightSize;
    } else {

      height = heightSize;
    }

    mLeftCirclePoint.set(mBgHeight / 2, mBgHeight / 2);
    mRightCirclePoint.set(mBgWidth - mBgHeight / 2, mBgHeight / 2);

    setMeasuredDimension(width, height);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    drawBackground(canvas);
    drawCircle(canvas);
  }

  private void drawBackground(Canvas canvas) {
    canvas.drawCircle(mLeftCirclePoint.x, mLeftCirclePoint.y, mBgHeight / 2, mBgPaint);
    canvas.drawRect(mBgHeight / 2, 0, mBgWidth - mBgHeight / 2, mBgHeight, mBgPaint);
    canvas.drawCircle(mRightCirclePoint.x, mRightCirclePoint.y, mBgHeight / 2, mBgPaint);
  }

  private void drawCircle(Canvas canvas) {
    canvas.drawCircle(mLeftCirclePoint.x, mLeftCirclePoint.y, mCircleRadius, mCirclePaint);
  }


}
