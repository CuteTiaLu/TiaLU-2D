package TiaLU.Scene;

import TiaLU.Component.Core.NodeStorage;
import TiaLU.Component.Core.ParPool;
import TiaLU.Component.Node.Node;
import TiaLU.Interface.RunInt;
import TiaLU.Physics.PhysWorld;
import TiaLU.Storage.Dire2;
import TiaLU.Storage.Dire2R;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class BaseSce implements RunInt {

    public final String Name;
    protected int ID = -1;
    public NodeStorage SceNode;
    public ParPool ScePar;
    public Dire2R SceOff = new Dire2R(0, 0, 0);

    protected Stage stage;

    public BaseSce(String name, int ObjSize, int ParSize) {
        this.Name = name;
        stage = new Stage();
        SceNode = new NodeStorage(Math.max(1, ObjSize)) {
            @Override
            public void AddNoRun(int ID) {
                savnode[ID].Sce = BaseSce.this;
            }

            @Override
            public void UpdRun(int ID) {
                savnode[ID].CoordinateCalculation(SceOff);
            }
        };
        ScePar = new ParPool(Math.max(1, ParSize));
    }

    public void SceInit() {
        init();
    }

    public void SceDraw() {
        draw();
        SceNode.draw();
        stage.draw();
        physworld.draw();
    }

    public void SceUpd(double delta) {
        stage.act();
        update(delta);
        SceNode.update(delta);
        physworld.update(delta);
    }

    public abstract void init();

    public abstract void Loading();

    public abstract void CreateNode();

    public int GetID() {
        return ID;
    }

    //A0.1物理效果

    protected PhysWorld physworld = new PhysWorld();

    //A0.1新功能

    public void DelNode(int ID) {
        SceNode.DeleteSta(ID);
    }//删除物体

    public void DelPAR(int ID) {
        ScePar.DeleteSta(ID);
    }//删除粒子

    public PhysWorld GetPhysWorld() {
        return physworld;
    }

    public void addNode(Node node) {SceNode.AddNode(node);}
}