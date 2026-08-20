package TiaLU.Component.Node.Com.Map;

import TiaLU.Component.Node.Node;
import TiaLU.Storage.Texture.TextureSet;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import static TiaLU.API.TiaAPI.Rend;

public class TileMap extends Node {

    protected TileSet TileData;
    protected String FileName;
    protected double Size;
    protected TextureSet texture;

    public TileMap(double X, double Y, double Size, float R, TextureSet textureset, String FileName) {
        super("TileMap");
        SetCord(X, Y, R);
        this.FileName = FileName;
        texture = textureset;
        TileData = Loading();
        this.Size = Size;
    }

    @Override
    public void Draw() {
        for (int HEI = 0; HEI < TileData.Map.length; HEI++) {
            for (int WID = 0; WID < TileData.Map[0].length; WID++) {
                Rend.draw(texture.GetTexture(TileData.Map[HEI][WID]), GetCordAbsol().x + (WID * Size), GetCordAbsol().y + (HEI * Size), Size, Size, GetOrigin().x, GetOrigin().y, GetCordAbsol().rotate);
            }
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void Update(double delta) {

    }

    public void MergeTextures() {

    }

    public void MergeTextures(int X, int Y) {

    }

    public TileSet Loading() {
        FileHandle file = Gdx.files.internal(FileName);
        Json json = new Json();
        return json.fromJson(TileSet.class, file);
    }

}

class TileSet {
    String name;
    int[][] Map;
    int Size;
}
