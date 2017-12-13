package starters.quizthroughxml;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Murad on 12/5/2017.
 */

@Dao
public interface UserDao {

    @Insert
   public void insertUser(User user);

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("UPDATE user SET Score=:score WHERE Name=:name")
    public void updateData(int score, String name);



}
