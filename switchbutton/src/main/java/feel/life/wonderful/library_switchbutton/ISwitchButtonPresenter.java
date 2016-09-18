package feel.life.wonderful.library_switchbutton;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

/**
 * Created by queda on 16-9-18.
 */
public interface ISwitchButtonPresenter {

    void initialize(Context context, AttributeSet attrs);

    void onMeasure(int width, int height);

    void onDraw(Canvas canvas);

    void onFingerDown();

    void onFingerMove(float deltaX);

    void onFingerUp(float deltaX);
}
