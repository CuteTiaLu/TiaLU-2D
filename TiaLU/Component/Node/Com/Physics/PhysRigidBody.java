package TiaLU.Component.Node.Com.Physics;

import com.badlogic.gdx.graphics.Texture;

import static TiaLU.API.TiaAPI.Rend;

public class PhysRigidBody extends RigidBody {

    public PhysRigidBody(String name, double x, double y, double Width, double Height, float r, Texture texture, float Mass) {
        super(name, x, y, Width, Height, r, texture, Mass);
    }

    @Override
    protected void init() {
    }

    @Override
    protected void Update(double delta) {
        AddCord(1 * delta, 0, 1);
    }

    @Override
    protected void Draw() {
        Rend.draw(texture, GetCordAbsol().x, GetCordAbsol().y, width, height, GetCordAbsol().rotate);
    }

    @Override
    public void BoundingBox() {
    }

    @Override
    public void CollisionDetection() {
        for (int i = 0; i < Sce.GetPhysWorld().GetLength(); i++) {
            if (Sce.GetPhysWorld().GetBody(i) != null) {
                //Sce.GetPhysWorld().GetBody(i).boundbox;
            }
        }
    }

    @Override
    public void DebugDraw() {

    }
}
