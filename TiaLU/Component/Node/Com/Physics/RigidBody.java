package TiaLU.Component.Node.Com.Physics;

import TiaLU.Component.Node.Com.BaseEntity;
import TiaLU.Physics.Body;
import TiaLU.Physics.PhysBeh;
import TiaLU.Storage.Dire2;
import TiaLU.Storage.Dire2R;
import com.badlogic.gdx.graphics.Texture;

public abstract class RigidBody extends BaseEntity implements PhysBeh {

    protected Dire2R Vector = new Dire2R();

    protected Dire2R Coordinates = new Dire2R();

    Body body;

    Dire2 WidAndHei;

    public RigidBody(String name, double x, double y, double width, double height, float r, Texture texture, float Mass) {
        super(name, x, y, width, height, r, texture);
        WidAndHei = new Dire2(width, height);
        body = new Body(GetCordAbsol(), this);
    }

    @Override
    public void NodeInit() {
        Sce.GetPhysWorld().AddBody(body);
        super.NodeInit();
    }

    @Override
    public void SetCord(double x, double y) {
        WidAndHei.SetDire(width, height);
        Coordinates.SetDire(x, y);
    }

    @Override
    public void SetCord(double x, double y, float rotate) {
        WidAndHei.SetDire(width, height);
        Coordinates.SetDire(x, y, rotate);
    }

    @Override
    public void SetCord(Dire2R coordinates) {
        WidAndHei.SetDire(width, height);
        Coordinates.AddDire(coordinates);
    }

    @Override
    public void AddCord(double x, double y) {
        WidAndHei.SetDire(width, height);
        Coordinates.AddDire(x, y);
    }

    @Override
    public void AddCord(double x, double y, float rotate) {
        WidAndHei.SetDire(width, height);
        Coordinates.AddDire(x, y, rotate);
    }

    @Override
    public void AddCord(Dire2R coordinates) {
        WidAndHei.SetDire(width, height);
        Coordinates.AddDire(coordinates);
    }

    @Override
    public void update(double delta) {
        super.update(delta);
        Coordinates.AddDire(Vector);
    }

    @Override
    public void Delete() {
        Sce.GetPhysWorld().Delete(body.GetID());
        super.Delete();
    }

    @Override
    public void PhysUpdate(double delta) {
        super.SetCord(Coordinates);
    }//注意这个是物理世界类使用的禁止在Update可能导致错误
}