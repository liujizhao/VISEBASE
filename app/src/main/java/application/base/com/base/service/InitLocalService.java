package application.base.com.base.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.vise.log.ViseLog;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import application.base.com.base.bean.PartsClassify;
import application.base.com.base.bean.PartsClassifyShop;
import application.base.com.base.dao.AppDatabase;
import application.base.com.base.dao.PartsClassifyDao;
import application.base.com.base.dao.PartsClassifyShopDao;

/**
 * Author Blank
 * Create on 2018/8/1 16:06
 * Description: 读取本地数据到数据库，只执行一次
 */
public class InitLocalService extends Service {

    private static final String TAG = InitLocalService.class.getSimpleName();

    private static String readLocalJson(Context context, String fileName) {
        String resultString = "";
        try {
            InputStream inputStream = context.getResources().getAssets().open(fileName);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            resultString = new String(buffer, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    private static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(s, clazz);

        return Arrays.asList(arr);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        int partsClassifySize = AppDatabase.getInstance(this).partsClassifyDao().getParts().size();
        if (partsClassifySize == 0) {
            ViseLog.d("第一次加载乘用车配件数据");
            readPartsToDB();
        }
        partsClassifySize = AppDatabase.getInstance(this).partsClassifyDao().getParts().size();
        ViseLog.d("乘用车配件数量：" + partsClassifySize);

        int partsClassifyShopSize = AppDatabase.getInstance(this).partsClassifyShopDao().getShopParts().size();
        if (partsClassifyShopSize == 0) {
            ViseLog.e("第一次加载商用车配件数据");
            readShopPartsToDB();
        }
        partsClassifyShopSize = AppDatabase.getInstance(this).partsClassifyShopDao().getShopParts().size();
        ViseLog.d("商用车配件数量：" + partsClassifyShopSize);
    }

    /**
     * 读取乘用车配件数据到数据库里
     */
    private void readPartsToDB() {
        // 四大分类数据
        String partsClassifyJsonStr = readLocalJson(getBaseContext(), "parts_classify.json");
        List<PartsClassify> partsClassifyList = stringToArray(partsClassifyJsonStr, PartsClassify[].class);
        PartsClassifyDao partsClassifyDao = AppDatabase.getInstance(this).partsClassifyDao();
        partsClassifyDao.insertAll(partsClassifyList);
    }

    /**
     * 读取商用车赔金数据到数据库里
     */
    private void readShopPartsToDB() {
        // 四大分类数据
        String partsClassifyShopJsonStr = readLocalJson(getBaseContext(), "parts_classify_shop.json");
        List<PartsClassifyShop> partsClassifyShopList = stringToArray(partsClassifyShopJsonStr, PartsClassifyShop[].class);
        PartsClassifyShopDao partsClassifyShopDao = AppDatabase.getInstance(this).partsClassifyShopDao();
        partsClassifyShopDao.insertAll(partsClassifyShopList);

    }

}
