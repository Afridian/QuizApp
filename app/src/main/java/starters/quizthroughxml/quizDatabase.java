package starters.quizthroughxml;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Murad on 12/5/2017.
 */

@Database(entities = {User.class}, version = 1)
public abstract class quizDatabase extends RoomDatabase {

    public abstract UserDao userDao();

}
