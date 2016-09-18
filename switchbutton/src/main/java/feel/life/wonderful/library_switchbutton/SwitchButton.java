package feel.life.wonderful.library_switchbutton;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author : xiaoxiaoda
 *         date: 16-9-4
 *         email: daque@hustunique.com
 */

public class SwitchButton extends View implements ISwitchButtonView {

  enum STATE {
    CLOSED(0),
    OPENED(1),
    DRAGGED(2);

    private int state;

    STATE(int state) {
      this.state = state;
    }

    public int getState() {
      return state;
    }
  }

  private STATE mState = STATE.CLOSED;

  private PointF mFingerDown = new PointF();

  private ISwitchButtonPresenter mPresenter;

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
    mPresenter = new SwitchButtonPresenter(this);
    mPresenter.initialize(context, attrs);
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

    mPresenter.onMeasure(width, height);

    setMeasuredDimension(width, height);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    mPresenter.onDraw(canvas);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    int action = event.getAction();
    switch (action) {
      case MotionEvent.ACTION_DOWN:
        mFingerDown.set(event.getX(), event.getY());
        mPresenter.onFingerDown();
        break;

      case MotionEvent.ACTION_UP:
        mPresenter.onFingerUp(event.getX() - mFingerDown.x);
        break;

      case MotionEvent.ACTION_MOVE:
        if (mState != STATE.DRAGGED) {
          mState = STATE.DRAGGED;
        }
        mPresenter.onFingerMove(event.getX() - mFingerDown.x);
        break;

    }
    invalidate();
    return true;
  }

  @Override
  public STATE getState() {
    return mState;
  }

  @Override
  public void setState(STATE state) {
    mState = state;
  }

}
