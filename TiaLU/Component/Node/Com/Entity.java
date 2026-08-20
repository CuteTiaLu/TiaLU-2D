package TiaLU.Component.Node.Com;

import TiaLU.Component.Node.NodeRun;
import com.badlogic.gdx.graphics.Texture;

import static TiaLU.API.TiaAPI.Rend;

public class Entity extends BaseEntity {

    public final NodeRun update;

    public Entity(String name, double x, double y, double width, double height, float r, Texture texture, NodeRun update) {
        super(name, x, y, width, height, r, texture);
        this.update = update;
    }

    @Override
    public void Draw() {
        Rend.draw(texture, GetCordAbsol().x, GetCordAbsol().y, width, height, GetOrigin().x, GetOrigin().y, GetCordAbsol().rotate);
    }

    @Override
    public void init() {

    }

    @Override
    public void Update(double delta) {
        update.run(this, delta);
    }
}
