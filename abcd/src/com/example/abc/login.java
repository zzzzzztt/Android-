package com.example.abc;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
//import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends Activity implements OnClickListener {
	private Button button1,button2;
	private String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
 
        button1.setOnClickListener(this);   
        button2.setOnClickListener(this);

    }

	@Override
	public void onClick(View v) {
		if(v==button1){
			user="a";
			Intent intent = new Intent(login.this, MainActivity.class);   
			intent.putExtra("extra_data", user);
			startActivity(intent);
		}
		else if(v==button2){
			user="b";
	        Intent intent = new Intent(login.this, MainActivity.class);   
			intent.putExtra("extra_data", user);
	        startActivity(intent);
		}
		}	
	}
    
