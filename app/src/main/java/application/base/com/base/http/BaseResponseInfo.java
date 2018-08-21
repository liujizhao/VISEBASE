package application.base.com.base.http;

import java.io.Serializable;

/**
 * 包名：application.base.com.base.response
 * 时间：2018/8/17
 * 描述：网络请求结果 基类
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author liujizhao
 */
public class BaseResponseInfo implements Serializable {

    /**
     * status : 07
     */

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
