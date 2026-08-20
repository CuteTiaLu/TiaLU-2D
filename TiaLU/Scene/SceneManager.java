package TiaLU.Scene;

import com.badlogic.gdx.Gdx;

import java.util.Objects;

public class SceneManager {

    protected BaseSce[] Scenes;
    private int Select = 0;

    private static SceneManager scenemanager;

    private SceneManager() {}

    public static SceneManager SetSceneManager() {
        if (scenemanager == null) scenemanager = new SceneManager();
        return scenemanager;
    }

    public void SceManInit(int size) {//初始化场景管理器
        int minSize = Math.max(size, 1);// 最少1个  At least 1
        Select = 0;
        Scenes = new BaseSce[minSize];
    }

    public void SceInit() {//初始化现有的场景
        int Size = 0;
        for (BaseSce Sce : Scenes) {
            if (Sce == null) continue;
            SceneInit(Sce);
            Size += 1;
        }
        if (Scenes == null || (Scenes[Select] == null && Size == 0)) {
            Sce sce = new Sce("默认场景");
            AddScene(sce);//防止类似我自己的人忘记创建
            for (BaseSce Sce : Scenes) {
                if (Sce == null) continue;
                SelectScene(Sce.GetID());
                SceneInit(Sce);
                break;
            }
        } else if (Scenes[Select] == null && !(Size == 0)) {
            for (BaseSce Sce : Scenes) {
                if (Sce == null) continue;
                SelectScene(Sce.GetID());
                SceneInit(Sce);
                break;
            }
        }
    }

    private void SceneInit(BaseSce scene) {//内部的初始化场景
        scene.SceInit();
        scene.CreateNode();
    }

    public void AddScene(BaseSce scene) {//添加场景
        for (int i = 0; i < Scenes.length; i++) {
            if (Scenes[i] == null) {
                Scenes[i] = scene;
                Scenes[i].ID = i;
                return;
            }
        }
    }

    public void DelScene(int ID) {//删除场景
        int max = 0;
        for (BaseSce Sce : Scenes) {
            max += (Sce != null) ? 1 : 0;
        }
        ID = (ID < max) ? Math.max(ID, 0) : max - 1;
        Scenes[ID] = null;
    }

    public BaseSce GetScene(int ID) {
        return Scenes[ID];
    }//获取指定ID的场景

    public BaseSce GetScene() {
        return Scenes[Select];
    }//获取当前场景

    public BaseSce GetScene(String name) {
        for (BaseSce Sce : Scenes) {
            if (Sce == null) continue;
            if (Objects.equals(name, Sce.Name)) {
                return Scenes[Sce.GetID()];
            }
        }
        return Scenes[0];
    }//获取指定名字的场景

    public void SelectScene(int Sel) {//根据数字ID切换场景
        int max = 0;
        for (BaseSce Sce : Scenes) {
            max += (Sce != null) ? 1 : 0;
        }
        Sel = (Sel < max) ? Math.max(Sel, 0) : max - 1;
        if (Scenes[Sel] != null) {
            Select = Sel;
            Gdx.input.setInputProcessor(Scenes[Sel].stage);
            Scenes[Select].Loading();
        }
    }

    public void SelectScene(String Sel) {//根据名字切换场景， ！注意重复的名字会导致问题
        for (BaseSce Sce : Scenes) {
            if (Sce == null) continue;
            if (Objects.equals(Sel, Sce.Name)) {
                SelectScene(Sce.GetID());
                return;
            }
        }
    }

    public void SceDraw() {
        Scenes[Select].SceDraw();
    }//当前场景的渲染更新

    public void SceUpdate(double delta) {
        Scenes[Select].SceUpd(delta);
    }//当前场景的运行更新

    //OBJ物体

    public void OBJDelete(int ID) {
        Scenes[Select].SceNode.DeleteSta(ID);
    }//删除当前场景的物体

    public void OBJDelete(int SceID, int ID) {
        Scenes[SceID].SceNode.DeleteSta(ID);
    }//删除指定的场景的物体

    //PAR粒子

    public void PARDelete(int ID) {
        Scenes[Select].ScePar.DeleteSta(ID);
    }//删除当前场景的粒子

    public void PARDelete(int SceID, int ID) {
        Scenes[SceID].ScePar.DeleteSta(ID);
    }//删除指定的场景的粒子
}
