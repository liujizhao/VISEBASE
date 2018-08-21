package application.base.com.base.http;

/**
 *
 *
 * @param <T>
 */
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
public class BaseResponse<T>{

    private BaseResponseHead head = new BaseResponseHead();
    private BaseResponseBody<T> body = new BaseResponseBody<>();

    public boolean isSuccess() {
        return head != null && head.status.equalsIgnoreCase("success");
    }

    public T getInfo(){
        return body.baseInfo;
    }

    public BaseResponseHead getHead(){
        return head;
    }

    public static class BaseResponseHead {

        /**
         * head : {"timeStamp":"2018-07-03 10:03:06","requestType":"01","errCode":"000","errMsg":"请使用修理厂账户进行登录","status":"FAILED"}
         * body : {"baseInfo":{"status":"07"}}
         */

        /**
         * timeStamp : 2018-07-03 10:03:06
         * requestType : 01
         * errCode : 000
         * errMsg : 请使用修理厂账户进行登录
         * status : FAILED
         */

        public String timeStamp;
        public String requestType;
        public String errCode;
        public String errMsg;
        public String status;

    }

    public static class BaseResponseBody<T> {
        public T baseInfo;
    }
}
