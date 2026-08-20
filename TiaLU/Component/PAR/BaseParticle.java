package TiaLU.Component.PAR;

import TiaLU.Interface.RunInt;

public abstract class BaseParticle implements RunInt {
    private String name;
    private int ID;
    private boolean IDbo = false;

    public BaseParticle(String name) {
        this.name = name;
    }

    public void SetID(int ID) {
        if (!IDbo) {
            this.ID = ID;
            IDbo = true;
        } else {
            throw new RuntimeException("<ID> Can't be reassigned");
        }
    }

    public String GetName() {
        return name;
    }

    public int GetID() {
        return ID;
    }


}