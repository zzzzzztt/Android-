package com.example.abc;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity {
	private TextView id,cha;
	private Button chazhao;
	private String name,data;

	private List<Letter> letterlist = new ArrayList<Letter>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.second_layout);
               
        Intent intent = getIntent();                      //获取前一页面数据
        data = intent.getStringExtra("extra_data");
        Log.d("SecondActivity", data);
        id = (TextView)findViewById(R.id.textView2);
        id.setText(data);
        
        chazhao=(Button)findViewById(R.id.tianjia1);       //添加好友
        chazhao.setOnClickListener(new OnClickListener(){
            public void onClick(View v){   
            	Intent intent = new Intent(SecondActivity.this, add.class);
            	intent.putExtra("extra_data", data);
                startActivity(intent);
                initLetter();
           }
 });
 
        initLetter();                                             //适配器
        LetterAdapter adapter = new LetterAdapter(SecondActivity.this, 
        		R.layout.letter_item, letterlist);
        ListView  listview = (ListView)findViewById(R.id.list_view);
        listview.setAdapter(adapter);
        
        listview.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  Letter letter = letterlist.get(position);
                  name=letter.getLetterName();
                  Intent intent = new Intent(SecondActivity.this, talk.class); 
                  intent.putExtra("extra_data", name);
                  startActivity(intent);
            }
      });
   }
    
    private void initLetter(){
    	MyDatabaseHelper dbHelper;
		dbHelper=new MyDatabaseHelper(this,"user.db",null,2);
    	SQLiteDatabase db=dbHelper.getWritableDatabase();
    	   
    		Cursor cursor = db.query(data,new String[]{"num"},null, null, null, null, null,null);
   
		String x;		
		if(cursor.moveToFirst()){
			do{
				x=cursor.getString(cursor.getColumnIndex("num"));
//				y=cursor.getInt(cursor.getColumnIndex("num"));
		        letterlist.add( new Letter(x,R.drawable.bb));
				
			}while(cursor.moveToNext());
		}
		cursor.close();
    	}
    }
   

