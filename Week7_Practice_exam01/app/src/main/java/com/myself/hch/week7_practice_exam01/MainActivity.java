package com.myself.hch.week7_practice_exam01;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private Button openBtn;
    private Handler mHandler;
    EditText urlInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webview_layout);
        urlInput = (EditText) findViewById(R.id.editText_url);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

        webView.addJavascriptInterface(new JavaScriptMethods(), "sample");

        webView.loadUrl("file:///android_asset/sample.html");

        openBtn = (Button) findViewById(R.id.confirmBtn);
        openBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("http://" + urlInput.getText().toString());
            }
        });

        mHandler = new Handler();
    }

    public class JavaScriptMethods{
        public JavaScriptMethods(){

        }
        @android.webkit.JavascriptInterface
        public void clickOnFace(){
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("javascript:changFace()");
                }
            });
        }
    }
}
