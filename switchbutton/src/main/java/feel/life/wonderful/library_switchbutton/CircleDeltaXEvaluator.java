package feel.life.wonderful.library_switchbutton;

import android.animation.TypeEvaluator;

/**
 * @author : xiaoxiaoda
 *         date: 16-9-26
 *         email: daque@hustunique.com
 */
public class CircleDeltaXEvaluator implements TypeEvaluator<Float> {

  @Override
  public Float evaluate(float fraction, Float startValue, Float endValue) {
    if (startValue > endValue) {
      return (startValue - endValue) * fraction;
    } else if (startValue < endValue){
      return (endValue - startValue) * fraction;
    } else {
      return startValue;
    }
  }

}
