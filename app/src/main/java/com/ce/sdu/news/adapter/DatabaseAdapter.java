package com.ce.sdu.news.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rauan on 09.02.2017.
 */

public class DatabaseAdapter {
    public static final String DATABASE_NAME = "ApiDB";
    public static final String DATABASE_TABLE = "News";
    public static final String ID = "_id";
    public static final String DATE = "_date";
    public static final String TITLE = "_title";
    public static final String SUMMARY = "_summary";
    public static final String IMAGE = "_image";
    private final Context mycontext;
    private DBHelper myhelper;
    private SQLiteDatabase mydatabase;

    public DatabaseAdapter(Context mycontext) {
        this.mycontext = mycontext;
    }
    private static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null ,1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(
                    "CREATE TABLE "+DATABASE_TABLE+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                            +DATE + " TEXT NOT NULL, "+TITLE+" TEXT NOT NULL, "+SUMMARY+" TEXT NOT NULL);"
            );
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }
    public DatabaseAdapter connect(){
        myhelper = new DBHelper(mycontext);
        mydatabase = myhelper.getWritableDatabase();
        return this;
    }
    public void insertData(String date,String title,String sum){
        ContentValues cv = new ContentValues();
        cv.put(DATE,date);
        cv.put(TITLE,title);
        cv.put(SUMMARY,sum);
        mydatabase.insert(DATABASE_TABLE,null,cv);
    }
    public  void updateData(String date,String title,String sum,String ids){
        ContentValues cv = new ContentValues();
        cv.put(DATE,date);
        cv.put(TITLE,title);
        cv.put(SUMMARY,sum);
        Log.d("upData",ids);
        mydatabase.update(DATABASE_TABLE,cv,ID+" = "+ids,null);
    }
    public void deleteData(String ids){
        mydatabase.delete(DATABASE_TABLE,"_id = "+ids,null);
    }
    public List<String []> getData(){
        String []columns = new String[]{ID,DATE,TITLE,SUMMARY};
        Cursor c = mydatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        ArrayList<String []> arrayList=new ArrayList();
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            String []str=new String[4];
            str[0]=c.getString(c.getColumnIndex(ID));
            str[1]=c.getString(c.getColumnIndex(DATE));
            str[2]=c.getString(c.getColumnIndex(TITLE));
            str[3]=c.getString(c.getColumnIndex(SUMMARY));
            arrayList.add(str);
        }
        c.close();
        Collections.reverse(arrayList);
        return arrayList;
    }
    public void disconnect(){
        myhelper.close();
    }
}
