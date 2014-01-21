package com.kulturhusetpalladium.palladium;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	public static String url = "http://www.kulturhusetpalladium.se";
	Button start;
	Button program;
	Button bilder;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        
	        start= (Button) findViewById (R.id.start);
	        program= (Button) findViewById (R.id.program);
	        bilder= (Button) findViewById (R.id.gallery);
	        
	        start.setOnClickListener(new OnClickListener() {
        	 	@Override
	       		public void onClick(View arg0)
        	 	{

              	  try
       	         {
              		  Intent intent = new Intent(MainActivity.this, Pagecontroller.class);
       	             
       	           intent .putExtra("URL", url+"/palladium.html");	 
       	           
       	           
       	             startActivity(intent);
       	         }
       	         catch (Exception e)
       	         {
       	             e.printStackTrace();
       	         }
	       		}
       		});
	        program.setOnClickListener(new OnClickListener() {
        	 	@Override
	       		public void onClick(View arg0)
        	 	{
        	 		  try
            	         {
                   		  Intent intent = new Intent(MainActivity.this,  Pagecontroller.class);
            	             
            	           intent .putExtra("URL", url+"/program.html");	 
            	           
            	           
            	             startActivity(intent);
            	         }
            	         catch (Exception e)
            	         {
            	             e.printStackTrace();
            	         }
	       			
	       		}
       		});
	        bilder.setOnClickListener(new OnClickListener() {
        	 	@Override
	       		public void onClick(View arg0)
        	 	{
        	 		  try
            	         {
                   		  Intent intent = new Intent(MainActivity.this, Menulist.class);
            	             
                   		  intent .putExtra("URL", url+"/galleri.html");
            	           
            	           
            	             startActivity(intent);
            	         }
            	         catch (Exception e)
            	         {
            	             e.printStackTrace();
            	         }
	       			
	       		}
       		});
}}

