package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void BtnBmiCalc_Clicked(View v) {
        EditText editHeight = (EditText) findViewById(R.id.editHeight);
        EditText editWeight = (EditText) findViewById(R.id.editWeight);
        TextView txtResult = (TextView) findViewById(R.id.txtResult);
        Double height = Double.parseDouble(editHeight.getText().toString());
        Double weight = Double.parseDouble(editWeight.getText().toString());
        /*
            体质指数（BMI）=体重（kg）÷身高^2（m）
            最理想的体重指数是22， BMI指数为18.5～23.9时属正常，具体分类如下：
            过轻：低于18.5
            正常：18.5-23.9
            过重：24-27
            肥胖：28-32
            非常肥胖, 高于32
        */
        Double bmi = weight / (height * height);
        RadioButton rbtnStdWho = (RadioButton) findViewById(R.id.rbtnStdWho);
        RadioButton rbtnStdAsia = (RadioButton) findViewById(R.id.rbtnStdAsia);
        RadioButton rbtnStdChn = (RadioButton) findViewById(R.id.rbtnStdChn);
        if (rbtnStdWho.isChecked()) {
            if (bmi < 18.5) {
                txtResult.setText("BMI:" + bmi.toString() + " 体重：过轻 ");
            }
            else if (bmi <= 24.9) {
                txtResult.setText("BMI:" + bmi.toString() + "体重： 正常");
            }
            else if (bmi <= 29.9) {
                txtResult.setText("BMI:" + bmi.toString() + "体重： 过重");
            }
            else if (bmi <= 34.9) {
                txtResult.setText("BMI:" + bmi.toString() + "体重： 肥胖");
            }
            else {
                txtResult.setText("BMI:" + bmi.toString() + "体重： 非常肥胖");
            }
        }
        else if (rbtnStdAsia.isChecked()) {
            if (bmi < 18.5) {
                txtResult.setText("BMI:" + bmi.toString() + " 体重：过轻 ");
            }
            else if (bmi <= 22.9) {
                txtResult.setText("BMI:" + bmi.toString() + "体重： 正常");
            }
            else if (bmi <= 24.9) {
                txtResult.setText("BMI:" + bmi.toString() + "体重： 过重");
            }
            else if (bmi <= 29.9) {
                txtResult.setText("BMI:" + bmi.toString() + "体重： 肥胖");
            }
            else {
                txtResult.setText("BMI:" + bmi.toString() + "体重： 非常肥胖");
            }
        }
        else if (rbtnStdChn.isChecked()) {
            if (bmi < 18.5) {
                txtResult.setText("BMI:" + bmi.toString() + " 体重：过轻 ");
            }
            else if (bmi <= 23.9) {
                txtResult.setText("BMI:" + bmi.toString() + "体重： 正常");
            }
            else if (bmi <= 27.9) {
                txtResult.setText("BMI:" + bmi.toString() + "体重： 过重");
            }
            else {
                txtResult.setText("BMI:" + bmi.toString() + "体重： 非常肥胖");
            }
        }
        else {
            txtResult.setText("请选择BMI标准");
        }
    }
}
