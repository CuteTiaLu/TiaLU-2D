package TiaLU.API;

import TiaLU.Render.Camera;
import TiaLU.Render.GraphicsRenderingModule.Render;
import TiaLU.Scene.SceneManager;

public class TiaAPI {

    private TiaAPI() {}

    public static final Render Rend = new Render();

    private static Camera[] CameraGroup;
    private static int CameraSelect;

    static SceneManager SceneMana = SceneManager.SetSceneManager();

    public static void Update(double delta) {SceneMana.SceUpdate(delta);}

    public static void Draw() {SceneMana.SceDraw();}

    public static class Main {
    }
}
