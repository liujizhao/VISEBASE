package application.base.com.base.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import application.base.com.base.bean.PartsClassifyShop;

/**
 * Author Blank
 * Create on 2018/8/1 18:16
 * Description:
 */
@Dao
public interface PartsClassifyShopDao {

    @Query("SELECT * FROM partsclassifyshop")
    List<PartsClassifyShop> getShopParts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PartsClassifyShop> parts);

    @Delete
    void delete(PartsClassifyShop partsClassifyShop);

    @Update
    void update(List<PartsClassifyShop> parts);

}
