package TiaLU.Storage.Texture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureSet {
    private Texture[] textures;

    public TextureSet(String... FileName) {
        if (FileName == null) return;
        textures = new Texture[FileName.length];

        for (int i = 0; i < FileName.length; i++) {
            if (!FileName[i].endsWith(".png") && !FileName[i].endsWith(".jpg")) {
                System.out.println(" \"" + FileName[i] + "\" 可能不是图片文件");
                continue;
            }
            textures[i] = new Texture(Gdx.files.internal(FileName[i]));
        }
    }

    public Texture GetTexture(int ID) {
        return textures[Math.clamp(ID, 0, textures.length - 1)];
    }
}

/* 这个可以放一堆图片和瓦片
 *
 */