package application.base.com.base.response;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import application.base.com.base.http.BaseResponseInfo;

/**
 * Author Blank
 * Create on 2018/7/3 11:01
 * Description:
 */
@Entity
public class User extends BaseResponseInfo {

    @NonNull
    @PrimaryKey()
    private String userId;

    /**
     * payUser : 1
     * cityName : 杭州市
     * cityCode : 330100
     * provinceCode : 330000
     * companyName : 诚合维修企业(股份有限公司股份有限公司股份有限公司)
     * provinceName : 浙江省
     * isRegisteredComplete : 1
     * bbyOpen : 1
     * status : 01
     */

    private String payUser;
    private String cityName;
    private String cityCode;
    private String provinceCode;
    private String companyName;
    private String provinceName;
    private String isRegisteredComplete;
    private String bbyOpen;
    private String customerService = "ja_1000_9999";
    /**
     * accessToken : 38a57a1cf740474d991cc3991dd5b214
     */

    private String accessToken;

    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    public String getCustomerService() {
        return customerService;
    }

    public void setCustomerService(String customerService) {
        this.customerService = customerService;
    }

    public String getPayUser() {
        return payUser;
    }

    public void setPayUser(String payUser) {
        this.payUser = payUser;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getIsRegisteredComplete() {
        return isRegisteredComplete;
    }

    public void setIsRegisteredComplete(String isRegisteredComplete) {
        this.isRegisteredComplete = isRegisteredComplete;
    }

    public String getBbyOpen() {
        return bbyOpen;
    }

    public void setBbyOpen(String bbyOpen) {
        this.bbyOpen = bbyOpen;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
