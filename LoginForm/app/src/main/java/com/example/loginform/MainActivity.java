package com.example.loginform;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rbtnLoginByUserName = (RadioButton) findViewById(R.id.rbtnLoginByUserName);
                String DbUser, DbPassword;
                if (rbtnLoginByUserName.isChecked()) {
                    DbUser = "GaryMK";
                    DbPassword = "123456";
                } else {
                    DbUser = "GaryMK@sample.com";
                    DbPassword = "123456";
                }
                EditText txtUserName = (EditText) findViewById(R.id.txtUserName);
                EditText txtPassword = (EditText) findViewById(R.id.txtPassword);
                TextView txtResult = (TextView) findViewById(R.id.txtResult);
                if (txtUserName.getText().toString().equals(DbUser)) {
                    if (txtPassword.getText().toString().equals(DbPassword)) {
                        txtResult.setText("登录成功");
                        Toast toast = Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    } else {
                        //密码错误
                        //txtResult.setText("密码有误");
                        //Toast.makeText( MainActivity.this,"密码错误",Toast.LENGTH_LONG).show();
                        ToastTemplateShow("密码有误");
                    }
                } else {
                    //用户名不存在
                    ToastShow("用户名不存在");
                }
            }
        });

        final Button btnReg = (Button) findViewById(R.id.btnReg);
        btnReg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){

                CustomDialog customDialog = new CustomDialog(MainActivity.this, new CustomDialog.OnCustomDialogListener() {
                    @Override
                    public void btnConfirmLicenseClicked(boolean isConfirm) {
                        if (isConfirm) {
                            ToastShow("感谢您成为我们的新用户。");
                        }
                        else {
                            ToastShow("只有接受用户协议，才能注册新用户。");
                        }
                    }
                });
                customDialog.show();

                /*
                AlertDialog alert = new AlertDialog.Builder(MainActivity.this).create();
                alert.setTitle("用户协议确认");
                alert.setIcon(R.drawable.googleplay);
                alert.setMessage("注册新用户需接受用户协议的约束，请您认真查阅用户协议内容，并选择是否同意接受用户协议。");
                alert.setButton(AlertDialog.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.popup_content, null, false);
                        PopupWindow window = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
                        window.setTouchable(true);
                        //window.showAsDropDown(btnReg, 0, 0, Gravity.BOTTOM);
                        window.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
                    }
                });
                alert.setButton(AlertDialog.BUTTON_NEGATIVE, "不同意", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastShow("只有接受用户协议，才能注册新用户。");
                    }
                });
                alert.show();
                 */
            }
        });
    }

    void ToastShow(String Content){
        Toast toast = Toast.makeText(MainActivity.this, Content, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout layout = (LinearLayout)toast.getView();
        ImageView img = new ImageView(getApplicationContext());
        img.setImageResource(R.drawable.googleplay);
        layout.addView(img,0);
        toast.show();
    }
    void ToastTemplateShow(String Content){
        Toast toast = Toast.makeText(MainActivity.this, Content, Toast.LENGTH_LONG);
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.toast_template, null);
        TextView txtToastContent = (TextView)view.findViewById(R.id.txtToastContent);
        txtToastContent.setText(Content);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0,0);
        toast.show();
    }
}
