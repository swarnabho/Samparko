package com.textifly.samparka;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.webkit.JavascriptInterface;

public class JS {
    ProgressDialog dialog;

    private Context context;

    public JS(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void Getlink(){
        dialog = new ProgressDialog(context);
        dialog.setMessage("Loading...");
        dialog.show();
        //Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        },1000);
    }
}
