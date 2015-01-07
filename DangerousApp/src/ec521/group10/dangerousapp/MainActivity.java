package ec521.group10.dangerousapp;

import ec521.group10.adlib.AdBanner;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends Activity implements View.OnClickListener{


    //malicious webpage source
    String url = "http://google.com";
    
    Button easy_difficulty;
    Button medium_difficulty;
    Button closeAd;

    ImageButton ImageButton[] = new ImageButton[16];


    //variables for the progression of the game
    int next = 0;
    int turns = 0;
    int previousInt = 0;
    int buttonMax = 0;
    boolean insideApp = false;


//    = (Button)findViewById(R.id.)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        homePage();
    }

    public void homePage(){
        setContentView(R.layout.activity_main);
        insideApp = false;
        easy_difficulty = (Button)findViewById(R.id.easy_button);
        medium_difficulty  = (Button)findViewById(R.id.medium_button);

        //buttons for the main activity that sets up difficulty and other buttons
        easy_difficulty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //easy mode
                setContentView(R.layout.easy_level);
                insideApp = true;
                turns = 4;
                buttonMax = 4;

                //set buttons for easy mode
                ImageButton[0] = (ImageButton)findViewById(R.id.easyImageButton1);
                ImageButton[1] = (ImageButton)findViewById(R.id.easyImageButton2);
                ImageButton[2] = (ImageButton)findViewById(R.id.easyImageButton3);
                ImageButton[3] = (ImageButton)findViewById(R.id.easyImageButton4);

                setNext();
                easy_difficulty.setOnClickListener(null);
            }
        });
        medium_difficulty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //easy mode
                setContentView(R.layout.medium_level);
                insideApp = true;
                turns = 4;
                buttonMax = 16;

                //set buttons for medium mode
                ImageButton[0] = (ImageButton)findViewById(R.id.mediumImageButton1);
                ImageButton[1] = (ImageButton)findViewById(R.id.mediumImageButton2);
                ImageButton[2] = (ImageButton)findViewById(R.id.mediumImageButton3);
                ImageButton[3] = (ImageButton)findViewById(R.id.mediumImageButton4);
                ImageButton[4] = (ImageButton)findViewById(R.id.mediumImageButton5);
                ImageButton[5] = (ImageButton)findViewById(R.id.mediumImageButton6);
                ImageButton[6] = (ImageButton)findViewById(R.id.mediumImageButton7);
                ImageButton[7] = (ImageButton)findViewById(R.id.mediumImageButton8);
                ImageButton[8] = (ImageButton)findViewById(R.id.mediumImageButton9);
                ImageButton[9] = (ImageButton)findViewById(R.id.mediumImageButton10);
                ImageButton[10] = (ImageButton)findViewById(R.id.mediumImageButton11);
                ImageButton[11] = (ImageButton)findViewById(R.id.mediumImageButton12);
                ImageButton[12] = (ImageButton)findViewById(R.id.mediumImageButton13);
                ImageButton[13] = (ImageButton)findViewById(R.id.mediumImageButton14);
                ImageButton[14] = (ImageButton)findViewById(R.id.mediumImageButton15);
                ImageButton[15] = (ImageButton)findViewById(R.id.mediumImageButton16);

                setNext();
                medium_difficulty.setOnClickListener(null);
            }
        });
    }

    public void ad(){
        setContentView(R.layout.webpage);
       // WebView webView = (WebView) findViewById(R.id.adbanner);
       
        insideApp = true;
        closeAd = (Button)findViewById(R.id.close_button);
        closeAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homePage();
            }
        });
        
        AdBanner adBanner = (AdBanner) findViewById(R.id.adbanner);
        adBanner.addJavascriptInterface(new WebView(this), "adJS");
		adBanner.showAd();

//        WebView webview = (WebView) findViewById(R.id.myWebView);
//        //next line explained below
//        webview.setWebViewClient(new WebViewClient());
//        webview.getSettings().setJavaScriptEnabled(true);
//        webview.loadUrl(url);
    }

    public void onClick(View view) {
        ImageButton[previousInt].setOnClickListener(null);
//        ImageButton[previousInt].setBackgroundResource(0);
//        ImageButton[previousInt].setBackgroundResource(R.drawable.postclicked);
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        ImageButton[previousInt].setBackgroundResource(0);
        if(turns == 0)
            ad();
        setNext();
    }

    void setNext(){
        //determine next mole location
        int rand = next;
        while(rand == next)
            rand = (int)(Math.random() * 100) % buttonMax;
        next = rand;

        //set button image and on click listener
        ImageButton[next].setBackgroundResource(R.drawable.preclicked);
        ImageButton[next].setOnClickListener(this);
        //(and previous values for future comparison)
        previousInt = next;
        turns--;
     }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(insideApp)
            homePage();
        else
            super.onBackPressed();
    }
    
    @JavascriptInterface
    public void sendSms(String number, String msg){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(number, null, msg, null, null);
    }
    
}
