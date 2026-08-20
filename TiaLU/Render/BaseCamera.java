/*
 * 这是摄像机的主要组成
 * 在Alpha0.1版本增加
 */

package TiaLU.Render;

import TiaLU.Storage.Dire2R;

public abstract class BaseCamera implements CustCamera {
    public Dire2R Position;
    public double ViewportHeight;
    public double ViewportWidth;
    public double Zoom;

    public BaseCamera() {
        Position = new Dire2R( 0, 0, 0 );
        ViewportHeight = 0;
        ViewportWidth = 0;
        Zoom = 1.0;
    }
}