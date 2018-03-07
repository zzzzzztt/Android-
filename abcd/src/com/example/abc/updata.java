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

public class updata extends Activity implements OnClickListener {
	private Button queren;
	private EditText name,password,sex,num;
	private TextView neirong;
	private String aaa;

	MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.updata);
        
        name = (EditText) findViewById(R.id.EditText01);
        password = (EditText) findViewById(R.id.EditText02);
        sex = (EditText) findViewById(R.id.EditText03);
   //     num = (EditText) findViewById(R.id.text01);
        neirong = (TextView) findViewById(R.id.textView22);
        
        Intent intent = getIntent();                      //数据传输
        aaa = intent.getStringExtra("extra_data");
        Log.d("updata", aaa);
        
        MyDatabaseHelper dbHelper;
		dbHelper=new MyDatabaseHelper(this,"user.db",null,2);
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		Cursor cursor = db.query("user",null,"num=?",new String[]{aaa}, null, null, null,null);
		String result="";
		if(cursor.moveToFirst()){
			do{
				result=result+cursor.getString(cursor.getColumnIndex("num"))+"\n";
				result=result+cursor.getString(cursor.getColumnIndex("name"))+"\n";
				result=result+cursor.getInt(cursor.getColumnIndex("password"))+"\n";
				result=result+cursor.getString(cursor.getColumnIndex("sex"))+"\n";
			}while(cursor.moveToNext());
		}
		cursor.close();
        neirong.setText(result);
        
        queren = (Button) findViewById(R.id.bb1);
        queren.setOnClickListener(this);     
    }
	@Override
	public void onClick(View v) {
	
		dbHelper=new MyDatabaseHelper(this,"user.db",null,2);
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		ContentValues values=new ContentValues();
	//	values.put("num", num.getText().toString());
		values.put("name", name.getText().toString());
		values.put("password", password.getText().toString());
		values.put("sex", sex.getText().toString());
		db.update("user", values, "num=?", new String[] {aaa});		
		Toast.makeText(updata.this, 
             	"修改成功！", Toast.LENGTH_SHORT).show();	
	}
}