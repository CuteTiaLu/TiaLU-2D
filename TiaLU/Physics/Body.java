package TiaLU.Physics;

import TiaLU.Storage.Dire2R;

public class Body {

    protected int ID;

    protected Dire2R dire;//获取引用也就是内存地址

    protected PhysBeh physbeh;//获取运行引用

    public Body(Dire2R dire, PhysBeh physbeh) {
        this.dire = dire;
        this.physbeh = physbeh;
    }

    public int GetID() {
        return ID;
    }

}
