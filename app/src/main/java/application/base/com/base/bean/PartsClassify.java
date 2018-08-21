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
public class PartsClassify implements Serializable {


    /**
     * peiLedger : 车身部分
     * peiJzc : 前保险杠
     * peiJzmc : 前保险杠
     * peiJmc : 前保险杠亮条
     * peiJmcId : 41411340
     */

    @NonNull
    @PrimaryKey()
    private String peiJmcId;
    private String peiLedger;
    private String peiJzc;
    private String peiJzmc;
    private String peiJmc;

    public String getPeiLedger() {
        return peiLedger;
    }

    public void setPeiLedger(String peiLedger) {
        this.peiLedger = peiLedger;
    }

    public String getPeiJzc() {
        return peiJzc;
    }

    public void setPeiJzc(String peiJzc) {
        this.peiJzc = peiJzc;
    }

    public String getPeiJzmc() {
        return peiJzmc;
    }

    public void setPeiJzmc(String peiJzmc) {
        this.peiJzmc = peiJzmc;
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
