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
         //   url="file:///android_asset/www/wiki_start.html";
            url=getIntent().getExtras().getString("Wiki");
        else if(getIntent().getExtras().containsKey("Moodle"))
            url="file:///android_asset/www/moodle_login.user.js";
           // url=getIntent().getExtras().getString("Moodle");
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

                if (getIntent().getExtras().containsKey("Stack")) {
                    wv.loadUrl("javascript:var gr_child=document.getElementById('mainbar');" +
                            "var child=gr_child.parentNode;" +
                            "var parent=child.parentNode;" +
                            "parent.removeChild(child);");
                }

                if (url.contains("wikipedia.org/")) {
                    wv.loadUrl("javascript:var url=String(document.URL);" +
                            "if(url.search('www.wiki')!=-1){" +
                            "  var langLi=document.getElementById('js-lang-list-container');" +
                            "  var langLiPa=langLi.parentNode;" +
                            "  langLiPa.removeChild(langLi);" +
                            "}" +
                            "else if(url.search('en.m')!=(-1)){" +
                            "  var section_0=document.getElementById('section_0');" +
                            "  var infobox=document.getElementsByTagName('table')[0];" +
                            "  var firstImage=infobox.getElementsByTagName('img')[0].parentNode;" +
                            "  var thumbInner=document.getElementsByClassName('thumbinner')[0];" +
                            "  var thumbFImage=thumbInner.firstChild;" +
                            "  var firstP=document.getElementsByTagName('p')[0];" +
                            "  document.getElementsByTagName('html')[0].innerHTML='<head></head><body></body>';" +
                            "  var bdy=document.getElementsByTagName('body')[0];" +
                            "  bdy.appendChild(section_0);" +
                            "  if(firstImage==undefined){" +
                            "    bdy.appendChild(thumbFImage);" +
                            "  }" +
                            "  else{" +
                            "    bdy.appendChild(firstImage);" +
                            "  }" +
                            "  bdy.appendChild(firstP);" +
                            "}");
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
