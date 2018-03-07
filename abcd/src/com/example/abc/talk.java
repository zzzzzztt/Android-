package com.example.abc;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class talk extends Activity  implements OnClickListener{
		private TextView name;
		private Button send;
		private EditText text;
		private List<Msg>msgList = new ArrayList<Msg>();
		private ListView listview;
		
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.talk);
	        
	        Intent intent = getIntent();                      //数据传输
	        String data = intent.getStringExtra("extra_data");
	        Log.d("SecondActivity", data);
	        name = (TextView)findViewById(R.id.textView2);
	        name.setText(data);
	 
	        send = (Button) findViewById(R.id.send);
	        text = (EditText) findViewById(R.id.text);
	        send.setOnClickListener(this); 
      
	        initMsg();	        
	        MsgAdapter adapter = new MsgAdapter(talk.this, 
	        		R.layout.msg_item, msgList);
	        ListView  listview = (ListView)findViewById(R.id.msg_list_view);
	        listview.setAdapter(adapter);
	          
	 }	        
	        private void initMsg(){  //初始化几条数据用于在ListView中显示。
	            Msg msg1 = new Msg("你好，我是安卓机器人!", Msg.TYPE_RECEIVED);
	            msgList.add(msg1);
	            Msg msg2 = new Msg("^_^!", Msg.TYPE_SENT);
	            msgList.add(msg2);
	           
	        }
	    	@Override
			public void onClick(View v) {
				
				Msg msg3 = new Msg(text.getText().toString(), Msg.TYPE_SENT);
	            msgList.add(msg3);
	            MsgAdapter adapter = new MsgAdapter(talk.this, 
		        		R.layout.msg_item, msgList);
		        ListView  listview = (ListView)findViewById(R.id.msg_list_view);
		        listview.setAdapter(adapter);
	            				
			} 	       
	 }


