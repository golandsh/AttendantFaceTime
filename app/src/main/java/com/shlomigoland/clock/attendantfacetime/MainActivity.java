package com.shlomigoland.clock.attendantfacetime;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback, ZXingScannerView.ResultHandler {

    private static final String TAG = "MainActivity";

    private Context mContext;
    private RelativeLayout mLoRelMain;
    private Button mBtnScan;

    private ZXingScannerView mScannerView;

    private SurfaceView topSurfaceView;
    //private SurfaceView bottomSurfaceView;
    private SurfaceHolder topSurfaceHolder;
    //private SurfaceHolder bottomSurfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        initComponents();
        initComponentListeners();
        initSurfaceViews();
    }

    private void initSurfaceViews() {
        /*bottomSurfaceView = (SurfaceView)findViewById(R.id.bottomSurfaceView);
        bottomSurfaceHolder = bottomSurfaceView.getHolder();
        bottomSurfaceHolder.addCallback(this);*/
        topSurfaceView = (SurfaceView)findViewById(R.id.topSurfaceView);
        topSurfaceView.setZOrderOnTop(true);
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
        /*try {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes

            startActivityForResult(intent, 0);

        } catch (Exception e) {

            Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
            startActivity(marketIntent);

        }*/
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
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

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void handleResult(Result result) {
        mScannerView.stopCamera();
        mScannerView.setActivated(false);
    }
}
