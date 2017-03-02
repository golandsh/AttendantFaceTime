package com.shlomigoland.clock.attendantfacetime;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Context mContext;
    private RelativeLayout mLoRelMain;
    private Button mBtnScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        initComponents();
        initComponentListeners();
    }

    private void initComponents(){
        setContentView(R.layout.activity_main);

        mLoRelMain = (RelativeLayout)findViewById(R.id.activity_main);
        mBtnScan = (Button)findViewById(R.id.btnMainScan);
    }

    private void initComponentListeners() {
        mBtnScan.setOnClickListener(OnClickScan);
    }

    private View.OnClickListener OnClickScan = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startScan();
        }
    };

    private void startScan(){
        try {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes

            startActivityForResult(intent, 0);

        } catch (Exception e) {

            Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
            startActivity(marketIntent);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {

            if (resultCode == RESULT_OK) {
                String contents = data.getStringExtra("SCAN_RESULT");
            }
            if(resultCode == RESULT_CANCELED) {

            }
        }
    }
}
