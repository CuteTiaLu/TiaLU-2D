package TiaLU.API;

import TiaLU.Scene.BaseSce;

import static TiaLU.API.TiaAPI.SceneMana;

public class SceAPI {
    public static void sceneInit() {
        SceneMana.SceInit();
    }

    public static void add(BaseSce scene) {
        SceneMana.AddScene(scene);
    }

    public static void sceneSet(int size) {
        SceneMana.SceManInit(size);
    }

    public static void enter(String name) {
        SceneMana.SelectScene(name);
    }

    public static void enter(int id) {
        SceneMana.SelectScene(id);
    }
}
