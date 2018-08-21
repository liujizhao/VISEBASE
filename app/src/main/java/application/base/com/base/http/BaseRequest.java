package application.base.com.base.http;

import com.androidlibrary.util.TimeUtil;

import java.util.UUID;

/**
 * 包名：application.base.com.base.response
 * 时间：2018/8/17
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author liujizhao
 */
public class BaseRequest<R> {

    private BaseRequestHead head = new BaseRequestHead();
    private BaseRequestBody<R> body = new BaseRequestBody<>();

    public void setRequestType(String requestType) {
        this.head.requestType = requestType;
    }

    public void setRequestSource(String requestSource) {
        this.head.requestSource = requestSource;
    }

    public void setTimeStamp(String timeStamp) {
        this.head.timeStamp = timeStamp;
    }

    public void setTransactionNo(String transactionNo) {
        this.head.transactionNo = transactionNo;
    }

    public void setUserCode(String userCode) {
        this.head.userCode = userCode;
    }

    public void setPassWord(String passWord) {
        this.head.passWord = passWord;
    }

    public void setRequestBaseInfo(R baseInfo) {
        body.baseInfo = baseInfo;
    }

    static class BaseRequestHead {

        /**
         * 请求类型
         */
        String requestType;

        /**
         * 请求来源   1：微信；
         * 2：IOS;
         * 3：Android
         * 4：windows
         * 5：APP
         */
        String requestSource = "3";

        /**
         * 请求时间 Date yyyy-MM-dd HH:mm:ss
         */
        String timeStamp = TimeUtil.INSTANCE.getDateToString();

        /**
         * 请求流水号 Date yyyy-MM-dd HH:mm:ss
         */
        String transactionNo = UUID.randomUUID().toString();

        /**
         * 请求者的身份标识，传值“defLossAPP”
         */
        String userCode = "rblp";

        /**
         * 请求者的身份密码，传值“1234”
         */
        String passWord = "1234";

    }

    static class BaseRequestBody<R> {
        R baseInfo;
    }
}
