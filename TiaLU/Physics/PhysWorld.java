package TiaLU.Physics;

import TiaLU.Interface.RunInt;
import TiaLU.Physics.JPhysicsLib.main.library.collision.Arbiter;
import TiaLU.Storage.Dire2R;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import java.util.Arrays;

public class PhysWorld implements RunInt {

    public final double StandardBarometric = 101.325;
    public final double StandardGravity = 9.80665;

    public Dire2R Gravity;
    public double Barometric;
    Texture Line;
    public boolean Debug;

    private Body[] body;

    public PhysWorld() {
        Gravity = new Dire2R(0, StandardGravity);
        Barometric = StandardBarometric;
        Pixmap TLine = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        TLine.setColor(0.6f, 0.6f, 0.6f, 1);
        TLine.fill();
        Line = new Texture(TLine);
        Debug = false;
        body = new Body[20];
    }

    @Override
    public void draw() {
        if (Debug) {
            for (Body i : body) {
                if (i != null) {
                    //render.draw(Line, i.boundbox.GetX(), i.boundbox.GetY(), i.boundbox.GetWidth(), 0.01);                                 // 底边
                    //render.draw(Line, i.boundbox.GetX(), i.boundbox.GetY() + i.boundbox.GetHeight(), i.boundbox.GetWidth(), 0.01);     // 顶边
                    //render.draw(Line, i.boundbox.GetX(), i.boundbox.GetY(), 0.01, i.boundbox.GetHeight());                                 // 左边
                    //render.draw(Line, i.boundbox.GetX() + i.boundbox.GetWidth(), i.boundbox.GetY(), 0.01, i.boundbox.GetHeight());      // 右边
                }
            }
        }
    }

    int Num;

    @Override
    public void update(double delta) {
        Num = 0;
        for (Body i : body) {
            if (i != null) {
                i.physbeh.CollisionDetection();
                Num++;
            }
        }

        for (int i = 0, ii = 0; i <= Num && i < body.length; i++, ii++) {
            if (body[i] == null) {
                ii--;
                continue;
            }
            body[i].physbeh.PhysUpdate(delta);
        }
    }

    public void AddBody(Body bod) {
        for (int i = 0; i < body.length; i++) {
            if (body[i] == null) {
                body[i] = bod;
                body[i].ID = i;
                return;
            }
        }
        Expansion(body.length + 10);
    }

    public void Delete(int ID) {
        int i = Math.clamp(ID, 0, body.length -1);
        if (body[i] == null) return;
        body[i] = null;
    }

    protected void Expansion(int value) {
        if (body.length >= value) return;
        body = Arrays.copyOf(body, value);
    }

    public Body GetBody(int ID) {
        return body[Math.clamp(ID, 0, body.length - 1)];
    }

    public int GetLength() {
        return body.length;
    }

}
