package com.example.abc;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
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

public class create extends Activity implements OnClickListener {
	private Button queren;
	private EditText name,password;
	private TextView data;

	MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.create);
        
        name = (EditText) findViewById(R.id.name1);
        password = (EditText) findViewById(R.id.password1);
        data = (TextView) findViewById(R.id.textView2);
        data.setText("×¢²áÒ³Ãæ");
        queren = (Button) findViewById(R.id.zhuce);
        queren.setOnClickListener(this);     
    }
	@Override
	public void onClick(View v) {
	
		dbHelper=new MyDatabaseHelper(this,"user.db",null,2);
		SQLiteDatabase db=dbHelper.getWritableDatabase();
//		db.execSQL("create table user("+"id interger,"+"num int,"+"name text,"+"password int,"+"sex text)");
//		ContentValues values=new ContentValues();

		db.execSQL("create table "+name.getText().toString()+"("+"id interger,"+"num int,"+"name text,"+"password int,"+"sex text)");

		ContentValues values=new ContentValues();
		values.put("num", name.getText().toString());
		values.put("password",password.getText().toString());
		db.insert("user", null, values);
		db.insert(name.getText().toString(), null, values);
		
	//	String CREATE="create table "+ name +" ("+"id interger,"+"num int,"+"name text,"+"password int,"+"sex text)";
	//	db.execSQL("create table "+ name +" ("+"id interger,"+"num int,"+"name text,"+"password int,"+"sex text)");
		Toast.makeText(create.this, 
             	"¹§Ï²Äã×¢²á³É¹¦,ÇëµÇÂ¼£¡", Toast.LENGTH_SHORT).show();	
	}
}