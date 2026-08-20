package TiaLU.Mathematics;

import com.badlogic.gdx.math.MathUtils;

public class TiaMath {

    public static float cos(float value) {
        return CosData[java.lang.Math.clamp((int) (value * 1000), 0, 35999)];
    }

    public static float sin(float value) {
        return SinData[java.lang.Math.clamp((int) (value * 1000), 0, 35999)];
    }

    protected static final float[] CosData = new float[36000];

    protected static final float[] SinData = new float[36000];

    static {
        for (int i = 0; i < 36000; i++) {
            float deg = i * 0.1f;
            SinData[i] = MathUtils.sinDeg(deg);
            CosData[i] = MathUtils.cosDeg(deg);
        }
    }
}
