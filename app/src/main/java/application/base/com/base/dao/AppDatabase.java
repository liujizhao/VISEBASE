package application.base.com.base.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import application.base.com.base.bean.PartsClassify;
import application.base.com.base.bean.PartsClassifyShop;
import application.base.com.base.response.User;

/**
 * Author Blank
 * Create on 2018/7/31 15:42
 * Description:
 */
@Database(entities = {User.class, PartsClassify.class, PartsClassifyShop.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final Object sLock = new Object();
    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE =
                        Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "xlc.db").allowMainThreadQueries()
                                /*.addMigrations(MIGRATION_1_2)*/.build();
            }
            return INSTANCE;
        }
    }

    public abstract UserDao userDao();

    public abstract PartsClassifyDao partsClassifyDao();

//    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
////            database.execSQL("");
//        }
//    };

    public abstract PartsClassifyShopDao partsClassifyShopDao();
}