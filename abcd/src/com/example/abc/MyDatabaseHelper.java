package com.example.abc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper{
	
  public static final String CREATE="create table user("+"id interger,"+"num int,"+"name text,"+"password int,"+"sex text)";
  private Context myContext;

  public MyDatabaseHelper (Context context, String name, CursorFactory factory, int version){
	  super(context,name,factory,version);
	  myContext=context;    
  }

  @Override  
  public void onCreate(SQLiteDatabase db) {  

     db.execSQL(CREATE);
     Toast.makeText(myContext, "Create succeeded", Toast.LENGTH_SHORT).show();
  }  
    
  @Override  
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
         // TODO Auto-generated method stub  
         db.execSQL("drop table if exists user");  
         onCreate(db);  
  }
}
