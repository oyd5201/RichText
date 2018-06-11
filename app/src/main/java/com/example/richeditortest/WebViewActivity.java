package com.example.richeditortest;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * 开启第三方网页
 */
public class WebViewActivity extends AppCompatActivity {

    private String contextURL;
    private WebView mWebview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initView();
        initData();
    }

    private void initView() {
        contextURL = getIntent().getStringExtra("contextURL");
        mWebview = (WebView) findViewById(R.id.webview);
    }


    protected void initData() {
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setSupportZoom(false);
        mWebview.getSettings().setBuiltInZoomControls(false);
        mWebview.getSettings().setDisplayZoomControls(false);
        mWebview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); //取消滚动条白边效果
        mWebview.setWebChromeClient(new WebChromeClient());
        mWebview.setWebViewClient(new WebViewClient());
        mWebview.getSettings().setDefaultTextEncodingName("UTF-8");
        mWebview.getSettings().setBlockNetworkImage(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebview.getSettings().setMixedContentMode(mWebview.getSettings()
                    .MIXED_CONTENT_ALWAYS_ALLOW);  //注意安卓5.0以上的权限
        }
        mWebview.loadDataWithBaseURL(null, getNewContent(contextURL), "text/html", "UTF-8", null);
    }

    private String getNewContent(String htmltext) {

        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width", "100%").attr("height",
                    "auto");
        }

        return doc.toString();
    }

    @Override
    protected void onDestroy() {
        if (mWebview != null) {
            mWebview.clearHistory();
            mWebview = null;
        }
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            if (mWebview.canGoBack()) {
                mWebview.clearHistory();
                mWebview.loadDataWithBaseURL(null, getNewContent(contextURL),
                        "text/html", "UTF-8", null);
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }


}
