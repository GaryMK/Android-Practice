package com.example.loginform;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;

public class CustomDialog extends Dialog {
    public CustomDialog(@NonNull Context context,OnCustomDialogListener customDialogListener) {
        super(context);
        this.customDialogListener = customDialogListener;
    }

    public interface OnCustomDialogListener{
        public void btnConfirmLicenseClicked(boolean isConfirm);
    }
    private OnCustomDialogListener customDialogListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);

        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        this.getWindow().setAttributes(lp);

        Button btnConfirmLicense = (Button)findViewById(R.id.btnConfirmLicense);
        btnConfirmLicense.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CheckBox cbConfirm = (CheckBox)findViewById(R.id.cbConfirmLicense);
                customDialogListener.btnConfirmLicenseClicked(cbConfirm.isChecked());
            }
        });
    }
}
