package application.base.com.base.bean;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Author Blank
 * Create on 2018/7/31 15:42
 * Description:
 */
@Entity
public class PartsClassifyShop implements Serializable {


    /**
     * peiLedger : 车架系统
     * peiJmc : 后牵引钩总成
     * peiJmcId : 3401051
     */
    @NonNull
    @PrimaryKey()
    private String peiJmcId;
    private String peiLedger;
    private String peiJmc;

    public String getPeiLedger() {
        return peiLedger;
    }

    public void setPeiLedger(String peiLedger) {
        this.peiLedger = peiLedger;
    }

    public String getPeiJmc() {
        return peiJmc;
    }

    public void setPeiJmc(String peiJmc) {
        this.peiJmc = peiJmc;
    }

    public String getPeiJmcId() {
        return peiJmcId;
    }

    public void setPeiJmcId(String peiJmcId) {
        this.peiJmcId = peiJmcId;
    }
}
