package application.base.com.base.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import application.base.com.base.bean.PartsClassify;

/**
 * Author Blank
 * Create on 2018/8/1 18:16
 * Description:
 */
@Dao
public interface PartsClassifyDao {

    @Query("SELECT * FROM partsclassify")
    List<PartsClassify> getParts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PartsClassify> parts);

    @Delete
    void delete(PartsClassify partsClassify);

    @Update
    void update(List<PartsClassify> parts);
}
