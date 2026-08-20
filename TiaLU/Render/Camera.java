/*
 * 这是摄像机的主要组成
 * 它的功能特别简单FarthestVisibility是摄像机的显示范围超过的直接不显示
 * CameraPosition是摄像机的位置虽然这些是静态变量但是你可以通过修改制作摄像机节点
 */
//A0.1

package TiaLU.Render;

public class Camera {

    public static BaseCamera camera = new Came();
}

class Came extends BaseCamera {

    @Override
    public float VertexTransX(float VertexX) {
        return VertexX;
    }

    @Override
    public float VertexTransY(float VertexY) {
        return VertexY;
    }
}