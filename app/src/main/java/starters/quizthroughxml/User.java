package starters.quizthroughxml;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.beans.IndexedPropertyChangeEvent;

/**
 * Created by Murad on 12/3/2017.
 */
@Entity(tableName = "user", indices = {@Index(value = "Name", unique = true)})
public class User{
    @PrimaryKey(autoGenerate = true)
    private int uid;


    @ColumnInfo(name = "Name")
    private String name;

    @ColumnInfo(name = "Password")
    private String password;

    @ColumnInfo(name = "Score")
    private int score;

    public int getUid(){

        return uid;
    }

    public void setUid(int uid){

        this.uid = uid;
    }

    public String getName(){

        return name;
    }
    public void setName(String name){

        this.name = name;
    }

    public String getPassword(){

        return password;
    }

    public void setPassword(String password){

        this.password = password;
    }

    public int getScore(){

        return score;
    }

    public void setScore(int score){

        this.score = score;
    }


}


