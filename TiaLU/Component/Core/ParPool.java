package TiaLU.Component.Core;

import TiaLU.Component.PAR.BaseParticle;

public class ParPool {
    public BaseParticle[] Pool;

    public ParPool(int size) {
        Pool = new BaseParticle[size];
    }

    public void SetSize(int size) {
        Pool = new BaseParticle[size];
    }

    public void AddSta(BaseParticle Par) {
        for (int i = 0; i < Pool.length; i++) {
            if (Pool[i] == null) {
                Pool[i] = Par;
                Pool[i].SetID(i);
                break;
            }
        }
    }

    public void CoverSta(BaseParticle[] PoolPar) {
        Pool = PoolPar;
    }

    public void DeleteSta(int ID) {
        if (Pool[ID] != null) {
            Pool[ID] = null;
        }
    }
}
