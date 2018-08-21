package application.base.com.base;

import com.androidlibrary.App;
import com.androidlibrary.common.ViseConfig;
import com.androidlibrary.http.ViseHttp;
import com.androidlibrary.http.core.ApiCookie;
import com.androidlibrary.http.interceptor.HttpLogInterceptor;
import com.androidlibrary.http.interceptor.NoCacheInterceptor;
import com.androidlibrary.http.mode.HttpHeaders;
import com.androidlibrary.http.mode.MediaTypes;
import com.vise.utils.assist.SSLUtil;

import java.io.File;
import java.util.HashMap;

import okhttp3.Cache;

public class BaseApp extends App {

    @Override
    public void onCreate() {
        super.onCreate();

        HashMap<String, String> globalHeaders = new HashMap<>();
//        globalHeaders.put("User-Agent", getUserAgent(this));//用户代理
        HashMap<String, String> globalParams = new HashMap<>();
        globalParams.put("appType", "Android");//app类型
        globalParams.put("appId", "10001");//app标识
        ViseHttp.init(this);
        ViseHttp.CONFIG()
                //配置请求主机地址
                .baseUrl("https://www.jiaanpei.cn/")
                //配置全局请求头
                .globalHeaders(globalHeaders)
                //配置全局请求参数
                .globalParams(globalParams)
                //配置读取超时时间，单位秒
                .readTimeout(10)
                //配置写入超时时间，单位秒
                .writeTimeout(20)
                //配置连接超时时间，单位秒
                .connectTimeout(10)
                //配置请求失败重试次数
                .retryCount(3)
                //配置请求失败重试间隔时间，单位毫秒
                .retryDelayMillis(300)
                //配置是否使用cookie
                .setCookie(true)
                //配置自定义cookie
                .apiCookie(new ApiCookie(this))
                //配置是否使用OkHttp的默认缓存
                .setHttpCache(true)
                //配置OkHttp缓存路径
                .setHttpCacheDirectory(new File(ViseHttp.getContext().getCacheDir(), ViseConfig.CACHE_HTTP_DIR))
                //配置自定义OkHttp缓存
                .httpCache(new Cache(new File(ViseHttp.getContext().getCacheDir(), ViseConfig.CACHE_HTTP_DIR), ViseConfig.CACHE_MAX_SIZE))
                //配置自定义离线缓存
                .cacheOffline(new Cache(new File(ViseHttp.getContext().getCacheDir(), ViseConfig.CACHE_HTTP_DIR), ViseConfig.CACHE_MAX_SIZE))
                //配置自定义在线缓存
                .cacheOnline(new Cache(new File(ViseHttp.getContext().getCacheDir(), ViseConfig.CACHE_HTTP_DIR), ViseConfig.CACHE_MAX_SIZE))
//                配置开启Gzip请求方式，需要服务器支持 request 数据会乱码
//                .postGzipInterceptor()
                //配置应用级拦截器
                .interceptor(new HttpLogInterceptor()
                        .setLevel(HttpLogInterceptor.Level.BODY))
                //配置网络拦截器
                .networkInterceptor(new NoCacheInterceptor())
//                配置主机证书验证
//                .hostnameVerifier(new SSLUtil.UnSafeHostnameVerifier("http://192.168.1.100/"))
                //配置SSL证书验证
                .SSLSocketFactory(SSLUtil.getSslSocketFactory(null, null, null));
    }
}
