package bjfu.it.mukaikai.weather.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import bjfu.it.mukaikai.weather.R;

public class LeakActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_act);

        /**
         * 匿名内部类 导致的内存泄露
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                LeakActivity.this.getApplicationContext();
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        /**
         * fix
         */
        new Thread(new staticRunnable());
    }

    private static class staticRunnable implements Runnable{
        @Override
        public void run() {
            // Error
            // LeakActActivity.this.getApplicationContext();
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
