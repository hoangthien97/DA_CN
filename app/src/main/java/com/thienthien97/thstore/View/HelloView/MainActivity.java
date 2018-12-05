package com.thienthien97.thstore.View.HelloView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.thienthien97.thstore.R;
import com.thienthien97.thstore.View.TrangChu.TrangChuActivity;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(toolbar);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);

                }catch (Exception e){

                }
                Intent trangchu = new Intent(MainActivity.this, TrangChuActivity.class);
                startActivity(trangchu);
            }
        });

        thread.start();

    }
}
