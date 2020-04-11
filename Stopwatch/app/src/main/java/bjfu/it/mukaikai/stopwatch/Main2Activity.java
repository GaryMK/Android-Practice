package bjfu.it.mukaikai.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView textView = findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                System.out.println();
            }
        });
    }
}
