package com.kulturhusetpalladium.palladium;
import java.io.IOException;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;



import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Pagecontroller extends Activity {
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		
	
		 new PageTask().execute(); 
	      
    }
    
private class PageTask extends AsyncTask<String, Void, String> {
	

  @Override
  protected String doInBackground(String... params) {
    String result ="";
   
    Document doc;
    String url = getIntent().getExtras().getString("URL");
    
    try {
    	doc = Jsoup.connect(url).timeout(10*1000).get();
    
    	
    	Element page = doc.select("#main-bottom").first();
    	 //modifierar källkoden
    	  Document html = Jsoup.parse(page.html());
    	 
    	  result=html.outerHtml();  
    	  result=result.replace("Filer",  MainActivity.url+"/Filer");
    	  
    } catch (IOException e) {
      e.printStackTrace();
    }
    Log.v("test", result );
    return result;   
  } 


  @Override
  protected void onPostExecute(String result) {   
	  setContentView(R.layout.webview);
	  if(result.equals("")){
 		 Toast toast = Toast.makeText(getApplicationContext(), "Anslutnings problem!", Toast.LENGTH_SHORT);
           
 		 toast.setGravity(Gravity.CENTER, 0, 0);
               toast.show();
         
  		}
		WebView mWebView; 
	  
	  mWebView = (WebView)findViewById(R.id.webView);
	  //webbläsarens inställningar
      WebSettings webSettings = mWebView.getSettings();                           
      webSettings.setJavaScriptEnabled(true);
      webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
     
      webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    
      webSettings.setLoadWithOverviewMode(true);
    
      webSettings.setSupportMultipleWindows(true);
      webSettings.setLoadsImagesAutomatically(true);
      webSettings.setLightTouchEnabled(true);
      webSettings.setDomStorageEnabled(true);
     
      mWebView.setInitialScale(100);
     
      mWebView.setScrollbarFadingEnabled(false);
      mWebView.clearHistory();
      mWebView.clearFormData();
      mWebView.clearCache(true);
      mWebView.setFocusableInTouchMode(false);
      mWebView.setFocusable(false);
      mWebView.setWebViewClient(new WebViewClient(){
    	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
    	       
    	    	
    	    	//Länkar öppnas i standard webbläsare och inte i appen
    	    	
    	    	if(url != null && url.startsWith("http://")) {
    	            view.getContext().startActivity(
    	                new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    	            return true;
    	        } 
    	    	else {
    	            return true;
    	        }
    	    }
    	});
      
      //Laddar CSS från applikationens asset mapp
	  mWebView.loadDataWithBaseURL("file:///android_asset/","<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;' name='viewport' /><link href='photoswipe.css' type='text/css' rel='stylesheet' />"+result, "text/html", "utf-8", null);
	  
    
    
    
  }


}

	

}
