package TiaLU.Component.Node.Com;

import TiaLU.Component.Node.Node;
import java.util.*;


public class Camera extends Node {

    private static List<Camera> camera = new ArrayList<>();
    private Runnable run = () -> TiaLU.Render.Camera.camera.Position = GetCordAbsol();
    private int CameraID;

    public Camera(String name) {
        super(name);
    }

    public Camera(String name, double x, double y, float r) {
        super(name, x, y, r);
    }

    public void init() {
        camera.add(this);
        CameraID = camera.size() - 1;
    }

    @Override
    public void Update(double delta) {

    }

    @Override
    public void Draw() {
        run.run();
    }
}
