package TiaLU.Component.Node.Com;

import TiaLU.Component.Node.Node;
import com.badlogic.gdx.graphics.Texture;

public abstract class BaseEntity extends Node {

    public Texture texture;
    public double height, width;

    public BaseEntity(String name, double x, double y, double width, double height, float r, Texture texture) {
        super(name, x, y, r, width * 0.5, height * 0.5);
        this.texture = texture;
        this.height = height;
        this.width = width;
    }

}