package application.base.com.base.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import application.base.com.base.response.User;

/**
 * Author Blank
 * Create on 2018/7/31 15:42
 * Description:
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    User getUser();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);
}