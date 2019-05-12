package edu.hzuapps.androidlabs.soft1714080902114;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Find_url_Activity extends AppCompatActivity {
    protected static  final int CHANCE_UI=1;
    protected static final int ERROR=2;
    private static final char CHANGE_UI =1;
    private EditText et_path;
    private ImageView iv;
    private Handler handler=new Handler(){

        @SuppressLint("WrongConstant")
        public void handleMessage(android.os.Message msg){
            if(msg.what==CHANGE_UI){
                Bitmap bitmap=(Bitmap) msg.obj;
                iv.setImageBitmap(bitmap);
            }else if(msg.what==ERROR){
                Toast.makeText(Find_url_Activity.this,"显示有误",0).show();
            }
        };
    };
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_url);
        et_path=(EditText) findViewById(R.id.et_path);
        iv=(ImageView) findViewById(R.id.iv);

    }
    public void click(View view){
        final String path=et_path.getText().toString().trim();
        if(TextUtils.isEmpty(path)){
            Toast.makeText(this,"url不能为空",0).show();
        }else{
            new Thread() {
                private HttpURLConnection conn;
                private Bitmap bitmap;
                public void run() {
                    try{
                        URL url=new URL(path);
                        conn=(HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setConnectTimeout(5000);
                        conn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible;MSIE 6.0;windows NT 5.1;"+"SV1;.NET4.0c;.NET CLR 2.0.50727;" +
                                ".NET CLR 3.0.4506.2152;.NET CLR 3.5.30729;shuame)");
                        int code=conn.getResponseCode();
                            if(code==200){
                                InputStream is=conn.getInputStream();
                                bitmap= BitmapFactory.decodeStream(is);
                                Message msg=new Message();
                                msg.what=CHANCE_UI;
                                msg.obj=bitmap;
                                handler.sendMessage(msg);
                            }else{
                                Message msg=new Message();
                                msg.what=ERROR;
                                handler.sendMessage(msg);
                            }
                    }catch(Exception e){
                        e.printStackTrace();
                        Message msg=new Message();
                        msg.what=ERROR;
                        handler.sendMessage(msg);
                    }
                };
            }.start();
        }
    }






}


