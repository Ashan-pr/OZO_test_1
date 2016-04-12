package com.example.ashan.ozo_test_1;

import android.app.Activity;

/**
 * Created by Ashan on 3/4/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class webview_java extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  //removes title bar from activity
        setContentView(R.layout.webview_xml);

        final WebView wv=(WebView)findViewById(R.id.webView);

        Button btnBack,btnForward,btnRefresh,btnMain;
        btnBack=(Button)findViewById(R.id.btnBack);
        btnForward=(Button)findViewById(R.id.btnForward);
        btnRefresh=(Button)findViewById(R.id.btnRefresh);
        btnMain=(Button)findViewById(R.id.btnMainMenu);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wv.canGoBack())
                    wv.goBack();
            }
        });

        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wv.canGoForward())
                    wv.goForward();
            }
        });

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainMen=new Intent(webview_java.this,MainActivity.class);
                startActivity(MainMen);
                wv.clearFormData();
                wv.clearHistory();
                wv.clearMatches();
            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wv.reload();
            }
        });

        String url;


        if(getIntent().getExtras().containsKey("TutorialsP"))
            url=getIntent().getExtras().getString("TutorialsP");
        else if(getIntent().getExtras().containsKey("Wiki"))
            url="file:///android_asset/www/wiki_start.html";
           // url=getIntent().getExtras().getString("Wiki");
        else if(getIntent().getExtras().containsKey("Moodle"))
            url=getIntent().getExtras().getString("Moodle");
        else if(getIntent().getExtras().containsKey("Stack"))
            url=getIntent().getExtras().getString("Stack");
        else {
            url="<html><body> error</body></html>";
        }

        wv.getSettings().setLoadsImagesAutomatically(true);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                // do your javascript injection here, remember "javascript:" is needed to recognize this code is javascript
               if (getIntent().getExtras().containsKey("Moodle")) {
                    wv.loadUrl("javascript:document.body.style.color='blue';" +
                            "document.getElementsByClassName('headermain')[0].style.color='blue';" +
                            "var parnt=document.getElementsByClassName('text_to_html')[0];" +
                            "var child=parnt.firstChild;" +
                            "parnt.removeChild(child);" +

                            "var child2=document.getElementsByClassName('text_to_html')[0];" +
                            "var parnt2=child2.parentNode;" +
                            "parnt2.removeChild(child2);" +

                            "var parnt3=document.getElementsByClassName('signuppanel')[0];" +
                            "parnt3.innerHTML='';" +
                            "var parnt4=document.getElementById('page-footer');" +
                            "parnt4.removeChild(parnt4.childNodes[1]);");

                } else if (getIntent().getExtras().containsKey("Stack")) {
                    wv.loadUrl("javascript:var gr_child=document.getElementById('mainbar');" +
                            "var child=gr_child.parentNode;" +
                            "var parent=child.parentNode;" +
                            "parent.removeChild(child);");
                }
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // this method is needed to ignore SSL certificate errors if you are visiting https website
                handler.proceed();
            }
        });

            wv.loadUrl(url);

        }

    }
