package com.example.abc;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
//import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class add extends Activity implements OnClickListener{
	private Button add;
	private EditText name,password;
	private TextView data;
	private String fordata;

	MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add);
        
        name = (EditText) findViewById(R.id.add);
        add = (Button) findViewById(R.id.add1);
        add.setOnClickListener(this);   
        
        Intent intent = getIntent();                      //获取前一页面数据
        fordata = intent.getStringExtra("extra_data");
       
    }
	@Override
	public void onClick(View v) {
	
		dbHelper=new MyDatabaseHelper(this,"user.db",null,2);
		SQLiteDatabase db=dbHelper.getWritableDatabase();
//		db.execSQL("create table user("+"id interger,"+"num int,"+"name text,"+"password int,"+"sex text)");
//		ContentValues values=new ContentValues();
//		db.execSQL("create table "+name.getText().toString()+"("+"id interger,"+"num int,"+"name text,"+"password int,"+"sex text)");
		Cursor cursor = db.query("user",new String[]{"num"},"num = ?", new String[]{name.getText().toString()}, null, null, null,null);
		int result=0;
		String result1=null;
		if(cursor.moveToFirst()){
			do{
				result=cursor.getInt(cursor.getColumnIndex("num"));	
//				result1=cursor.getString(cursor.getColumnIndex("name"));
			}while(cursor.moveToNext());
		}
//		String.valueOf(result);
		cursor.close();
		
		ContentValues values=new ContentValues();
		values.put("num", result);
//		values.put("name",result1);
		db.insert(fordata, null, values);
		
	//	String CREATE="create table "+ name +" ("+"id interger,"+"num int,"+"name text,"+"password int,"+"sex text)";
	//	db.execSQL("create table "+ name +" ("+"id interger,"+"num int,"+"name text,"+"password int,"+"sex text)");
		Toast.makeText(add.this, 
             	"添加成功！", Toast.LENGTH_SHORT).show();
	}
}
