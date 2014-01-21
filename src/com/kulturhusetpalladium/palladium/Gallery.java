package com.kulturhusetpalladium.palladium;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;




import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;




import android.util.Log;
import android.view.Gravity;

import android.webkit.WebSettings;
import android.webkit.WebView;


import android.widget.Toast;

public class Gallery extends Activity {
	WebView mWebView = null;
	 ArrayList<String> array_list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 setContentView(R.layout.loading);
		
		array_list = new ArrayList<String>();
		
		
			
		       
		      
		       
		       
		       new galleryTask().execute(); 
			      
    }
    
private class galleryTask extends AsyncTask <String, Void, String> {
	

  protected String doInBackground(String... params) {
  
    String image = "";
    String result= "";
   
    Document doc;
    String url = getIntent().getExtras().getString("URL");
    Log.v("url", url);
    try {
    	doc = Jsoup.connect(url).timeout(10*1000).get();
     
      Elements links = doc.select("div#main-bottom img");
      
      for (int i=0;i< links.size();i++) {
      
        	 
        	  image= links.get(i).attr("abs:src");
        	
        	
        		  
        		 
        	 result +="<li><a href='"+image+"'><img src='"+image+"'></a></li>";
        	
     
      }    
   

    } catch (IOException e) {
      e.printStackTrace();
   
    }
    Log.v("tag", result);
    return result;   
  } 


  protected void onPostExecute(String result) {   
	  setContentView(R.layout.webview);
	  if(result.equals("")){
	 		 Toast toast = Toast.makeText(getApplicationContext(), "Anslutnings problem!", Toast.LENGTH_SHORT);
	           
	 		 toast.setGravity(Gravity.CENTER, 0, 0);
	               toast.show();
	         
	  		}
	//webbläsarens inställningar
	  mWebView = (WebView)findViewById(R.id.webView);
      WebSettings webSettings = mWebView.getSettings();                           
      webSettings.setJavaScriptEnabled(true);
      webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
     
 
    
      webSettings.setLoadWithOverviewMode(true);
     
      webSettings.setSupportMultipleWindows(true);
      webSettings.setLoadsImagesAutomatically(true);
      webSettings.setLightTouchEnabled(true);
      webSettings.setDomStorageEnabled(true);
     
      mWebView.setInitialScale(150);
     
      mWebView.setScrollbarFadingEnabled(false);
      mWebView.clearHistory();
      mWebView.clearFormData();
      mWebView.clearCache(true);
    
      
    //Laddar javascript (photoswipe) och CSS dessa filer går att hitta i applikationens asset mapp
     
	  mWebView.loadDataWithBaseURL("file:///android_asset/", "<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;' name='viewport' /><link href='file:///android_asset/photoswipe.css' type='text/css' rel='stylesheet' /><script type='text/javascript' src='klass.min.js'></script><script type='text/javascript' src='code.photoswipe-3.0.5.min.js'></script><script type='text/javascript'>(function(window, PhotoSwipe){document.addEventListener('DOMContentLoaded', function(){ var options = {}, instance = PhotoSwipe.attach( window.document.querySelectorAll('#Gallery a'), options ); }, false); }(window, window.Code.PhotoSwipe));</script></head><body><ul id='Gallery' class='gallery'>" + result +"</ul></body>", "text/html", "utf-8", null);
	  
	


  }



  
		
	

}





}