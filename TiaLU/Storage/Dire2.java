package TiaLU.Storage;

public class Dire2 {
    public double x = 0;
    public double y = 0;

    public Dire2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Dire2() {}

    public Dire2(Dire2 dire2) {
        this(dire2.x, dire2.y);
    }

    public void SetDire(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void SetDire(Dire2 dire2) {
        SetDire(dire2.x, dire2.y);
    }

    public void AddDire(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void AddDire(Dire2 dire2) {
        AddDire(dire2.x, dire2.y);
    }

    public Dire2 GetValueAdded(Dire2 dire2) {
        return new Dire2(x + dire2.x, y + dire2.y);
    }
}