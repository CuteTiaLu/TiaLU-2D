package TiaLU.Scene.UI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.List;

public class GUITexture {
    public List<Texture> Textures;
    public BitmapFont font;
    public Skin skin;
    public GUITexture(Texture... textures) {
        Textures = List.of(textures);
    }
}