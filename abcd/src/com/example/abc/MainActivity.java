package com.example.abc;

import android.os.Bundle;
import android.app.Activity;
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

public class MainActivity extends Activity implements OnClickListener {
	private Button submit,cancel,create;
	private EditText name,password;
	private String data1,data2,user1;
	private TextView user;
	static String data,q;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        Intent intent = getIntent();                      //���ݴ���
        data = intent.getStringExtra("extra_data");
        Log.d("MainActivity", data);
        user = (TextView)findViewById(R.id.user);
        if(data.equals("a"))user1="�û���¼";
        else if(data.equals("b"))user1="����Ա��¼";        
        user.setText(user1);
        
        submit = (Button) findViewById(R.id.button1);
        cancel = (Button) findViewById(R.id.button2);
        create = (Button) findViewById(R.id.button3);
        name = (EditText) findViewById(R.id.edit_text1);
        password = (EditText) findViewById(R.id.edit_text2);
        submit.setOnClickListener(this);    
        cancel.setOnClickListener(this); 
        create.setOnClickListener(this); 

    }

	@Override
	public void onClick(View v) {
	if(v==submit){
		data2=name.getText().toString();
		data1=password.getText().toString();
		
		MyDatabaseHelper dbHelper;
		dbHelper=new MyDatabaseHelper(this,"user.db",null,2);
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		Cursor cursor = db.query("user",new String[]{"password"},"num = ?", new String[]{data2}, null, null, null,null);
		int result=0;
		if(cursor.moveToFirst()){
			do{
				result=cursor.getInt(cursor.getColumnIndex("password"));	
			}while(cursor.moveToNext());
		}
		q=String.valueOf(result);
		cursor.close();
		
		if(data1.equals(q)){
			if(data2.equals("admin")){             //����Ա��½��ת
				Intent intent = new Intent(MainActivity.this, admin.class); 
				intent.putExtra("extra_data", data2);
		        startActivity(intent);
			}
			else{                                 //��ͨ�û�
		Intent intent = new Intent(MainActivity.this, SecondActivity.class);  
		intent.putExtra("extra_data", data2);
        startActivity(intent);
        }}
		else{Toast.makeText(MainActivity.this, 
             	"�������", Toast.LENGTH_SHORT).show();}
	}
	else if(v==cancel){
        	Intent intent = new Intent(MainActivity.this, login.class);   
            startActivity(intent);
        }
	else if(v==create){
			if(data.equals("b"))
			{Toast.makeText(MainActivity.this, 
	             	"���ǹ���Ա��ע�ᣡ", Toast.LENGTH_SHORT).show();
			}
			else if(data.equals("a"))
			{
        	Intent intent = new Intent(MainActivity.this, create.class);   
            startActivity(intent);
			}
        }
       }		
	}   

