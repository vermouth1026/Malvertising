package ec521.group10.adlib;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import android.telephony.SmsManager;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by Haitian on 11/27/2014.
 */
public class AdBanner extends WebView {

    private int lastingTime;
    private boolean enableAdBanner;
    private String url;
    private Handler adHandler;
    private Runnable adRunnable;
    private boolean countingDown;

    @TargetApi(Build.VERSION_CODES.DONUT)
    public AdBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AdBanner, 0, 0);
        boolean enableJavaScript, enableSMS;
        try {
            this.enableAdBanner = a.getBoolean(R.styleable.AdBanner_enableAdBanner, false);
            enableJavaScript = a.getBoolean(R.styleable.AdBanner_enableJavaScript, false);
            enableSMS = a.getBoolean(R.styleable.AdBanner_enableSMS, false);
            this.lastingTime = a.getInt(R.styleable.AdBanner_lastingTime, 30);
            this.url = a.getString(R.styleable.AdBanner_targetURL);
        } finally {
            a.recycle();
        }
        if (!enableAdBanner) {
            this.setVisibility(GONE);
        } else {
            if (enableJavaScript) {
                this.getSettings().setJavaScriptEnabled(true);
                if (enableSMS) {
                    this.addJavascriptInterface(SmsManager.getDefault(), "smsManager");
                }
            }
            this.adHandler = new Handler();
            this.adRunnable = new Runnable() {
                @Override
                public void run() {
                    setVisibility(GONE);
                    countingDown = false;
                }
            };
            this.countingDown = false;
            this.showAd();
        }
    }

    public void showAd() {
        if (enableAdBanner) {
            this.loadUrl(url);
            this.setVisibility(VISIBLE);
            if (!countingDown && lastingTime > 0) {
                adHandler.postDelayed(adRunnable, lastingTime * 1000);
                countingDown = true;
            }
        }
    }
}
