package TiaLU.Component.Node;

import TiaLU.Component.Core.NodeStorage;
import TiaLU.Interface.RunInt;
import TiaLU.Scene.BaseSce;
import TiaLU.Storage.Dire2;
import TiaLU.Storage.Dire2R;

public abstract class Node implements RunInt {

    public final String name;

    private Dire2R Cord;
    private Dire2R RelaCord;
    private Dire2 Origin;

    public BaseSce Sce;

    protected int ID = -1;
    protected Node ParentNode;
    protected NodeStorage ChildNode = new NodeStorage(1) {

        @Override
        public void AddNoRun(int ID) {
            savnode[ID].Sce = Node.this.Sce;
            savnode[ID].ParentNode = Node.this;
        }

        @Override
        public void UpdRun(int ID) {
            savnode[ID].CoordinateCalculation(Node.this.Cord, Node.this.Origin);
        }
    };

    public Node(String name) {
        this.name = name;
        Cord = new Dire2R();
        RelaCord = new Dire2R();
        Origin = new Dire2();
    }

    public Node(String name, double x, double y, float rotate) {
        this.name = name;
        Cord = new Dire2R();
        RelaCord = new Dire2R(x, y, rotate);
        Origin = new Dire2();
    }

    public Node(String name, double x, double y, float rotate, double originX, double originY) {
        this.name = name;
        Cord = new Dire2R();
        RelaCord = new Dire2R(x, y, rotate);
        Origin = new Dire2(originX, originY);
    }

    @Override
    public void update(double delta) {
        TCR.SetDire(RelaCord);
        TO.SetDire(Origin);
        ChildNode.update(delta);
        Update(delta);
    }

    @Override
    public void draw() {
        ChildNode.draw();
        Draw();
    }

    public void SetID(int ID) {
        this.ID = ID;
    }

    public void NodeInit() {
        UpdNodeRel();
        init();
    }

    public void SetCord(double x, double y) {
        RelaCord.SetDire(x, y);
    }

    public void SetCord(double x, double y, float rotate) {
        RelaCord.SetDire(x, y, rotate);
    }

    public void SetCord(Dire2R coordinates) {
        RelaCord.SetDire(coordinates);
    }

    public void AddCord(double x, double y) {
        RelaCord.AddDire(x, y);
    }

    public void AddCord(Dire2R coordinates) {
        RelaCord.AddDire(coordinates);
    }

    public void AddCord(double x, double y, float rotate) {
        RelaCord.AddDire(x, y, rotate);
    }

    public void SetOrigin(double x, double y) { Origin.SetDire(x, y); }

    public Dire2R GetCordAbsol() { return Cord; }//绝对坐标的输出

    private Dire2R TCR = new Dire2R();

    public Dire2R GetCordRelat() { return TCR; }//相对坐标的输出

    private Dire2 TO = new Dire2();

    public Dire2 GetOrigin() { return TO; }

    public void CoordinateCalculation(Dire2R relativecoordinates, Dire2 origin) {
        Cord.SetDire(relativecoordinates.x + RelaCord.x, relativecoordinates.y + RelaCord.y, relativecoordinates.rotate + RelaCord.rotate);
        Origin.SetDire(origin);
    }

    public void CoordinateCalculation(Dire2R relativecoordinates) {
        Cord.SetDire(relativecoordinates.x + RelaCord.x, relativecoordinates.y + RelaCord.y, relativecoordinates.rotate + RelaCord.rotate);
    }

    public Dire2R CoordCalcu(Dire2R relativecoordinates) {
        return new Dire2R(relativecoordinates.x + RelaCord.x, relativecoordinates.y + RelaCord.y, relativecoordinates.rotate + RelaCord.rotate);
    }

    protected abstract void init();

    protected abstract void Update(double delta);

    protected abstract void Draw();

    public void Delete() {
        NodeRel.run();
    }

    public void DeleteNode(int ID) {
        ChildNode.DeleteSta(ID);
    }

    public void AddChildNode(Node node) {
        ChildNode.AddNode(node);
    }

    public String GetName() {
        return name;
    }

    public int GetID() {
        return ID;
    }

    private Runnable NodeRel;

    public void UpdNodeRel() {
        NodeRel = (ParentNode == null) ? () -> Sce.DelNode(ID) : () -> ParentNode.DeleteNode(ID);
    }

    //A0.1
    public void AddNode(Node node) {
        Sce.SceNode.AddNode(node);
    }

}
