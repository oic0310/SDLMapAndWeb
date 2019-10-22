package net.masaya3.sdldisplaysample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import static com.smartdevicelink.security.SdlSecurityBase.getContext;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView1 = (WebView) findViewById(R.id.webView2);
        webView1.setWebViewClient(new WebViewClient());
        webView1.loadUrl("https://youtu.be/qwGujjx1h2k");
        //webView1.zoomBy(0.5f);
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                String msg = String.format("Touch: %d %d", motionEvent.getX(), motionEvent.getY());

                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //If we are connected to a module we want to start our SdlService
        if(BuildConfig.TRANSPORT.equals("MULTI") || BuildConfig.TRANSPORT.equals("MULTI_HB")) {
            SdlReceiver.queryForConnectedService(this);
        }else if(BuildConfig.TRANSPORT.equals("TCP")) {
            Intent proxyIntent = new Intent(this, SdlService.class);
            startService(proxyIntent);
        }
    }
}
