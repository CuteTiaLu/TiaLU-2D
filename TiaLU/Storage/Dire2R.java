package TiaLU.Storage;

public class Dire2R extends Dire2 {
    public float rotate = 0;

    public Dire2R(double x, double y, float rotate) {
        super(x, y);
        this.rotate = rotate;
    }

    public Dire2R() {}

    public Dire2R(double x, double y) {
        this(x, y, 0);
    }

    public Dire2R(Dire2R dire2r) {
        this(dire2r.x, dire2r.y, dire2r.rotate);
    }

    public void SetDire(double x, double y, float rotate) {
        this.x = x;
        this.y = y;
        this.rotate = rotate;
    }

    public void SetDire(Dire2R dire2R) {
        SetDire(dire2R.x, dire2R.y, dire2R.rotate);
    }

    public void AddDire(double x, double y, float rotate) {
        this.x += x;
        this.y += y;
        this.rotate += rotate;
    }

    public void AddDire(Dire2R dire2R) {
        AddDire(dire2R.x, dire2R.y, dire2R.rotate);
    }

    public Dire2R GetValueAdded(Dire2R dire2) {
        return new Dire2R(x + dire2.x, y + dire2.y, rotate + dire2.rotate);
    }
}