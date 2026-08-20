package TiaLU.Component.Core;

import TiaLU.Component.Node.Node;
import TiaLU.Interface.RunInt;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NodeStorage implements RunInt {

    protected Node[] savnode;

    public NodeStorage(int size) {
        savnode = new Node[size];
        PossiblyAQuote = new ArrayList<>();
    }

    public void SetSize(int size) {
        savnode = new Node[size];
        PossiblyAQuote = new ArrayList<>();
        NodeNumber = 0;
    }

    public void CoverSta(NodeStorage Storage) {
        this.savnode = Storage.savnode;
        NodeNumber = Storage.NodeNumber;
        FrontNull = 0;
        for (Node node : savnode) {
            if (node != null) NodeNumber++;
        }
    }

    public void DeleteSta(int ID) {
        savnode[ID] = null;
        FrontNull = Math.min(FrontNull, ID);
        PossiblyAQuote.remove(Integer.valueOf(ID));
        TemporaryNodeCount--;
        NodeNumber--;
    }

    @Override
    public void draw() {
        for (int i = 0, ii = 0; ii <= NodeNumber && i < savnode.length; i++, ii++) {
            if (savnode[i] == null) {
                ii--;
                continue;
            }
            DrawRun(i);
            savnode[i].draw();
        }
    }

    @Override
    public void update(double delta) {
        TemporaryNodeCount = 0;
        for (int i = 0, ii = 0; ii <= NodeNumber && i < savnode.length; i++, ii++) {
            if (savnode[i] == null) {
                ii--;
                continue;
            }
            UpdRun(i);
            savnode[i].update(delta);
        }
        PossiblyAQuote.removeIf(i -> {
            if (i < 0 || i >= savnode.length) return true;
            if (savnode[i] == null) {
                DeleteSta(i);
                return true;
            }
            return false;
        });
        if (TemporaryNodeCount > 0) {
            ExtNum = TemporaryNodeCount + ExtendedNumber;
        } else {
            ExtNum = ExtendedNumber;
            //Downsize();
        }
    }

    //A0.1

    protected int NodeNumber = 0;
    protected int FrontNull = 0;
    protected int TemporaryNodeCount = 0;
    protected int ExtendedNumber = 50;
    protected int ExtNum = 0;
    protected List<Integer> PossiblyAQuote;

    public int GetLength() {
        return savnode.length;
    }

    public void AddNode(Node node) {
        AddNode(node, false);
    }

    public void AddNode(Class<? extends Node> node, Object... para) {
        AddNode(node, false, para);
    }

    protected int AddNode(Node node, boolean value) {
        boolean ii = value;
        int iii = 0;
        for (int i = FrontNull; i < savnode.length; i++) {
            if (savnode[i] == null) {
                if (!ii) {
                    addNo(node, i);
                    ii = true;
                    iii = i;
                } else {
                    FrontNull = i;
                    return iii;
                }
            }
        }
        Expansion(savnode.length + ExtNum);
        return AddNode(node, ii);
    }

    protected int AddNode(Class<? extends Node> node, boolean value, Object... para) {
        boolean ii = value;
        int iii = 0;
        for (int i = FrontNull; i < savnode.length; i++) {
            if (savnode[i] == null) {
                if (!ii) {
                    addNo(node, i, para);
                    ii = true;
                    iii = i;
                } else {
                    FrontNull = i;
                    return iii;
                }
            }
        }
        Expansion(savnode.length + ExtNum);
        return AddNode(node, ii, para);
    }

    protected void addNo(Class<? extends Node> node, int i, Object... para) {
        try {
            savnode[i] = node.getConstructor().newInstance(para);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        savnode[i].SetID(i);
        AddNoRun(i);
        savnode[i].NodeInit();
        TemporaryNodeCount++;
        NodeNumber++;
    }

    protected void addNo(Node node, int i) {
        savnode[i] = node;
        savnode[i].SetID(i);
        AddNoRun(i);
        savnode[i].NodeInit();
        TemporaryNodeCount++;
        NodeNumber++;
        PossiblyAQuote.add(i);
    }

    protected void Expansion(int value) {
        if (savnode.length >= value) return;
        savnode = Arrays.copyOf(savnode, value);
    }

    protected void Downsize() {
        int i = 0;
        for (Node node : savnode) {
            i += (node == null) ? 0 : 1;
        }
        savnode = Arrays.copyOf(savnode, i);
    }

    protected void Compress() {
        int Vacancy = FrontNull;
        int Available;
    }

    protected void UpdRun(int ID) {}
    protected void DrawRun(int ID) {}

    protected void AddNoRun(int ID) {}
}