package com.example.abc;

public class Msg {

    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;
    public String content;
    public int type;

    public Msg(String content, int type){
    this.content = content;
    this.type = type;
    }

    public String getContent(){
          return content;  //��ʾ��Ϣ�����ݡ�
    }

    public int getType(){
          return type; //��ʾ��Ϣ�����ͣ�����������ѡֵ��TYPE_RECEIVED, TYPE_SENT�� 
    }
}

