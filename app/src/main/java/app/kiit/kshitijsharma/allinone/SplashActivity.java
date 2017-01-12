package app.kiit.kshitijsharma.allinone;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        db= openOrCreateDatabase("CampusInfo",MODE_PRIVATE,null);

        class Mythread extends Thread{
            @Override
            public void run() {
                try {
                    if (db!=null){
                    Thread.sleep(2000);
                    Intent i= new Intent(SplashActivity.this,FirstpageActivity.class);
                    startActivity(i);}
                    //finish();
                } catch (InterruptedException e) {
                    Toast.makeText(SplashActivity.this,"EXCEPTION",Toast.LENGTH_SHORT).show();
                }
            }
        }
        Mythread my= new Mythread();
        my.start();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }
}

