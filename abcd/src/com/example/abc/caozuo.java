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

public class caozuo extends Activity implements OnClickListener{
	private Button delete,modify;
	private EditText name,password;
	private TextView data;
	private String fordata,data2;

	MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.caozuo);
        
        name = (EditText) findViewById(R.id.text);
        delete = (Button) findViewById(R.id.b1);
        modify = (Button) findViewById(R.id.b2);
        modify.setOnClickListener(this);  
        delete.setOnClickListener(this); 
       
//        Intent intent = getIntent();                      //获取前一页面数据
//        fordata = intent.getStringExtra("extra_data");
       
    }
	@Override
	public void onClick(View v) {
		 data2=name.getText().toString();
		dbHelper=new MyDatabaseHelper(this,"user.db",null,2);
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		
		if(v==delete){
			db.delete("user", "num=?", new String[]{name.getText().toString()});
		}
		else if(v==modify){
		Intent intent=new Intent(caozuo.this,updata.class);
		intent.putExtra("extra_data", data2);
		startActivity(intent);
		}
		
//		String result1=null;
//		if(cursor.moveToFirst()){
//			do{
//				result=cursor.getInt(cursor.getColumnIndex("num"));	
////				result1=cursor.getString(cursor.getColumnIndex("name"));
//			}while(cursor.moveToNext());
//		}
////		String.valueOf(result);
//		cursor.close();
//		
//		ContentValues values=new ContentValues();
//		values.put("num", result);
////		values.put("name",result1);
//		db.insert(fordata, null, values);
		
	//	String CREATE="create table "+ name +" ("+"id interger,"+"num int,"+"name text,"+"password int,"+"sex text)";
	//	db.execSQL("create table "+ name +" ("+"id interger,"+"num int,"+"name text,"+"password int,"+"sex text)");
		Toast.makeText(caozuo.this, 
             	"操作成功！", Toast.LENGTH_SHORT).show();
	}
}
