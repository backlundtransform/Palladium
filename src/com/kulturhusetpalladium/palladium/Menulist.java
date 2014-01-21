package com.kulturhusetpalladium.palladium;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;



public class Menulist extends Activity{
	
	
	ListView lista;
	
	ArrayAdapter<String> arrayAdapter;
	 Map<String, String> linklist = new HashMap<String, String>();
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
	
    	setContentView(R.layout.loading);
    	

    	new subTask().execute(); 
  
	}
	

	private class subTask extends AsyncTask<Map<String, String>, Void, Map<String, String>> {


		@Override
	protected Map<String, String> doInBackground(Map<String, String>... params) {

			Document doc;
			String url = getIntent().getExtras().getString("URL");
			try {
				doc = Jsoup.connect(url).timeout(10*1000).get();
				
				



				Elements links = doc.select("ul#detachedSubMenu a");
				String link="";
				 
				 for (int i=0;i< links.size();i++) { 
						link=links.get(i).attr("abs:href");
		
						
		 Log.v("link", link);
		  

		  
						linklist.put(link, links.get(i).text());
		 
					}
				   
				
				} 
			catch (IOException e) {
			e.printStackTrace();
			
			
			}
			return linklist;   
	} 


@Override
protected void onPostExecute(Map<String, String> linklist) {  
	
	
	 if(linklist.isEmpty()){
 		Toast toast = Toast.makeText(getApplicationContext(), "Anslutnings problem!", Toast.LENGTH_SHORT);
           
 		 toast.setGravity(Gravity.CENTER, 0, 0);
               toast.show();
         
  		}

	setContentView(R.layout.linklist);
	lista=(ListView)findViewById(R.id.list);
	   final ArrayList<String> keylist = new ArrayList<String>();
	      ArrayList<String> valuelist = new ArrayList<String>();
	
	      
   for (Map.Entry<String,String> entry : linklist.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
		
			keylist.add(key);
			valuelist.add(value);
	 
	}

 
	
	arrayAdapter = new ArrayAdapter<String>(Menulist.this,android.R.layout.simple_list_item_1,valuelist);
	lista.setAdapter(arrayAdapter); 


	lista.setOnItemClickListener(new OnItemClickListener() {

@Override
public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	

		
		try

		{
  
			
			
		
			Intent intent = new Intent(Menulist.this, Gallery.class);
			intent.putExtra("URL", keylist.get(position));
			startActivity(intent);
			
            
            
			 
       	 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

   
   
   
   
   
}

});
}


	
	}
	




	
  
 
  
  }