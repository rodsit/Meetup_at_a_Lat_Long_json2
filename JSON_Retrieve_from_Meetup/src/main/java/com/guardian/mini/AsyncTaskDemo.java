package com.guardian.mini;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
public class AsyncTaskDemo extends ListActivity {
	ArrayList<String> listWebUrls;
	ProgressDialog m_ProgressDialog = null;
	private static String[] items = { "Joseph", "George", "Mary", "Antony", "Albert",
			"Michel", "John", "Abraham", "Mark", "Savior", "Kristopher",
			"Thomas", "Williams", "Assisi", "Sebastian", "Aloysius", "Alex", "Daniel",
			"Anto", "Alexandar", "Brito", "Robert", "Jose",
			"Paul", "Peter" }; // using guard list now

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
/*		m_ProgressDialog = ProgressDialog.show(AsyncTaskDemo.this,    
	              "Please wait...", "Retrieving data ...", true);
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_checked, new ArrayList()));

		new AddStringTask().execute(); */
	}

	class AddStringTask extends AsyncTask<Void, String, Void> {
		@Override
		protected Void doInBackground(Void... unused) {
			// fetchTwitterPublicTimeline(); 
			ArrayList<String> newlistItems = new ArrayList<String>();
			newlistItems = fetchTwitterPublicTimeline();
			
			 String []strArray = new String[newlistItems.size()];
			 newlistItems.toArray(strArray);
	/*		 for (int i = 0;i<newlistItems.size();i++){ // dont need this loop, its for sys out
			 //System.out.println("strArray is " +i + " "+ strArray[i]);
			 } */
		 
			for (String item : strArray) {
				publishProgress(item);
				SystemClock.sleep(100);
			}
			return (null);
		}
// from another
		   public ArrayList<String> fetchTwitterPublicTimeline()
		    {
		        ArrayList<String> listItems = new ArrayList<String>();
		        listWebUrls = new ArrayList<String>();
// url = http://api.meetup.com/groups.json/?key=46284e6710702b3a3e3860f4a806b71&radius=25.0&topic=Music&order=members&offset=0&format=json&page=200&lat=53.47630045776367&fields=&lon=-2.24110000149011612
		        try {
		            URL twitter = new URL(
"http://api.meetup.com/groups.json/?key=46284e6710702b3a3e3860f4a806b71&radius=25.0&topic=Music&order=members&offset=0&format=json&page=200&lat=51.49630045776367&fields=&lon=-0.17110000149011612");
		            URLConnection tc = twitter.openConnection();
		            BufferedReader in = new BufferedReader(new InputStreamReader(
		                    tc.getInputStream()));
		 // see http://stackoverflow.com/questions/5865192/parsing-json-response-from-url-in-android
		 // http://suggest.bizrate.com/app/search?countryCode=US&numResult=10&callback=BIZRATE.Suggest.callback&keyword=iphone&format=json           
		 // String myUrlResult = ****
		    /*        JSONObject j = jo.getJSONObject("results");
		            JSONArray ja =  j.getJSONArray("suggestions") */
		    StringBuilder sb = new StringBuilder();
		            String line;
		            while ((line = in.readLine()) != null) {
		            	
		            	sb.append(line + "\n");
		            	
		           //     JSONArray ja = new JSONArray(line);
		            }
		        //    JSONObject j2 =  new JSONObject(sb.toString()); // j2.getJSONObject(sb.toString());
		         //   JSONArray ja2 =  j2.getJSONArray("suggestions");
		      //      //System.out.println("value is "+sb.toString());
		            
		            // http://stackoverflow.com/questions/4585162/jsonexception-on-trying-to-create-jsonarray
		 // String task = "{'menu': { 'id': 'file', 'value': 'File', 'popup': { 'menuitem': [ {'value': 'New', 'onclick': 'CreateNewDoc()'}, {'value': 'Open', 'onclick': 'OpenDoc()'}, {'value': 'Close', 'onclick': 'CloseDoc()'}] }}}";
		 // JSONArray ja =  j2.getJSONObject("menu").getJSONObject("popup").getJSONArray("menuitem");
		 /*
		{"A":
		          { "A1":"A1_value" ,"A2":"A2_value","sub":
		              { "sub1":[ {"sub1_attr":"sub1_attr_value" }]}
		          }
		}";
		  */
		 /*           
		   String test = "{\"A\":{\"A1\":\"A1_value\" ,\"A2\":\"A2_value\",\"sub\":{\"sub1\":[ {\"sub1_attr\":\"sub1_attr_value\" }]}}}";
		   JSONObject jObject = new JSONObject(test);
		   JSONObject menuObject = jObject.getJSONObject("A");
		   String attributeId1 = menuObject.getString("A1");
		   //System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA1 value == " +attributeId1);
		   String attributeId2 = menuObject.getString("A2");
		   //System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA2 value == " +attributeId2);
		   JSONObject popupObject = menuObject.getJSONObject("sub");
		   JSONArray menuitemArray = popupObject.getJSONArray("sub1");
		   //System.out.println("sub1_attr = " + menuitemArray.getJSONObject(0).getString("sub1_attr").toString());
		*/
		            /* - here is the guardian response
		            {
		            	  "response":{
		            	    "status":"ok",
		            	    "userTier":"free",
		            	    "total":1361206,
		            	    "startIndex":1,
		            	    "pageSize":10,
		            	    "currentPage":1,
		            	    "pages":136121,
		            	    "orderBy":"newest",
		            	    "results":[{
		            	      "id":"world/2011/aug/03/hosni-mubarak-way-trial",
		            	      "sectionId":"world",
		            	      "sectionName":"World news",
		            	      "webPublicationDate":"2011-08-03T07:11:00+01:00",
		            	      "webTitle":"Hosni Mubarak on way to face trial, says Egyptian official",
		            	      "webUrl":"http://www.guardian.co.uk/world/2011/aug/03/hosni-mubarak-way-trial",
		            	      "apiUrl":"http://content.guardianapis.com/world/2011/aug/03/hosni-mubarak-way-trial"
		            	    },{
		            	      "id":"public-leaders-network/2011/aug/03/pfi-promises-rhetoric-reality",
		            	      "sectionId":"public-leaders-network",
		            	      "sectionName":"Public Leaders Network",
		            	      "webPublicationDate":"2011-08-03T07:00:04+01:00",
		            	      "webTitle":"PFI promises are more rhetoric than reality",
		            	      "webUrl":"http://www.guardian.co.uk/public-leaders-network/2011/aug/03/pfi-promises-rhetoric-reality",
		            	      "apiUrl":"http://content.guardianapis.com/public-leaders-network/2011/aug/03/pfi-promises-rhetoric-reality"
		            	    },{
		            	      "id":"money/2011/aug/03/home-rental-costs-hit-record-highs",
		            	      "sectionId":"money",
		            	      "sectionName":"Money",
		            	      "webPublicationDate":"2011-08-03T07:00:03+01:00",
		            	      "webTitle":"Rents soar to record levels",
		            	      "webUrl":"http://www.guardian.co.uk/money/2011/aug/03/home-rental-costs-hit-record-highs",
		            	      "apiUrl":"http://content.guardianapis.com/money/2011/aug/03/home-rental-costs-hit-record-highs"
		            	    },{
		            	      "id":"global-development/poverty-matters/2011/aug/03/africa-drought-uganda-no-somalia",
		            	      "sectionId":"global-development",
		            	      "sectionName":"Global development",
		            	      "webPublicationDate":"2011-08-03T07:00:02+01:00",
		            	      "webTitle":"East Africa drought: Uganda has problems, but it is no Somalia | Ben Jones",
		            	      "webUrl":"http://www.guardian.co.uk/global-development/poverty-matters/2011/aug/03/africa-drought-uganda-no-somalia",
		            	      "apiUrl":"http://content.guardianapis.com/global-development/poverty-matters/2011/aug/03/africa-drought-uganda-no-somalia"
		            	    },{
		            	      "id":"money/2011/aug/03/virginia-wallis-redemption-charges",
		            	      "sectionId":"money",
		            	      "sectionName":"Money",
		            	      "webPublicationDate":"2011-08-03T07:00:01+01:00",
		            	      "webTitle":"Angry and confused over change in mortgage redemption charges",
		            	      "webUrl":"http://www.guardian.co.uk/money/2011/aug/03/virginia-wallis-redemption-charges",
		            	      "apiUrl":"http://content.guardianapis.com/money/2011/aug/03/virginia-wallis-redemption-charges"
		            	    },{
		            	      "id":"money/2011/aug/03/virginia-wallis-house-flats-mortgage",
		            	      "sectionId":"money",
		            	      "sectionName":"Money",
		            	      "webPublicationDate":"2011-08-03T07:00:00+01:00",
		            	      "webTitle":"I want to convert flats back into a single house \u2013 what mortgage should I go for?",
		            	      "webUrl":"http://www.guardian.co.uk/money/2011/aug/03/virginia-wallis-house-flats-mortgage",
		            	      "apiUrl":"http://content.guardianapis.com/money/2011/aug/03/virginia-wallis-house-flats-mortgage"
		            	    },{
		            	      "id":"world/2011/aug/03/china-calls-us-debt-manage",
		            	      "sectionId":"world",
		            	      "sectionName":"World news",
		            	      "webPublicationDate":"2011-08-03T06:45:36+01:00",
		            	      "webTitle":"China calls on US to manage its debt 'responsibly' from now on",
		            	      "webUrl":"http://www.guardian.co.uk/world/2011/aug/03/china-calls-us-debt-manage",
		            	      "apiUrl":"http://content.guardianapis.com/world/2011/aug/03/china-calls-us-debt-manage"
		            	    },{
		            	      "id":"technology/blog/2011/aug/03/google-sergey-brin-2000-tv-show",
		            	      "sectionId":"technology",
		            	      "sectionName":"Technology",
		            	      "webPublicationDate":"2011-08-03T06:40:00+01:00",
		            	      "webTitle":"It's 2000: can you spot the real Sergey Brin on the quizshow?",
		            	      "webUrl":"http://www.guardian.co.uk/technology/blog/2011/aug/03/google-sergey-brin-2000-tv-show",
		            	      "apiUrl":"http://content.guardianapis.com/technology/blog/2011/aug/03/google-sergey-brin-2000-tv-show"
		            	    },{
		            	      "id":"environment/2011/aug/03/garden-birds-avian-pox-virus",
		            	      "sectionId":"environment",
		            	      "sectionName":"Environment",
		            	      "webPublicationDate":"2011-08-03T06:00:02+01:00",
		            	      "webTitle":"UK garden birds hit by avian pox virus",
		            	      "webUrl":"http://www.guardian.co.uk/environment/2011/aug/03/garden-birds-avian-pox-virus",
		            	      "apiUrl":"http://content.guardianapis.com/environment/2011/aug/03/garden-birds-avian-pox-virus"
		            	    },{
		            	      "id":"voluntary-sector-network/2011/aug/03/charity-sector-management-recruitments",
		            	      "sectionId":"voluntary-sector-network",
		            	      "sectionName":"Voluntary Sector Network",
		            	      "webPublicationDate":"2011-08-03T06:00:02+01:00",
		            	      "webTitle":"Demand for interim managers in charity sector remains high, says recruitment specialist",
		            	      "webUrl":"http://www.guardian.co.uk/voluntary-sector-network/2011/aug/03/charity-sector-management-recruitments",
		            	      "apiUrl":"http://content.guardianapis.com/voluntary-sector-network/2011/aug/03/charity-sector-management-recruitments"
		            	    }]
		            	  }
		            	}             
		 */
		 // here is what you want
		   JSONObject jObject2 = new JSONObject(sb.toString());
	//	   JSONObject menuObject2 = jObject2.getJSONObject("response");
//		   String attributeId12 = menuObject2.getString("status");
		   //System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA1 value == " +attributeId12);
	//	   String attributeId22 = menuObject2.getString("userTier");
		   //System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA2 value == " +attributeId22);
		//   String attribute3 = menuObject2.getString("results");
		   //System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC2 value == " +attribute3);
		   JSONArray ja4 =  jObject2.getJSONArray("results");
/*		   //System.out.println("sub1_attrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr = " + ja4.getJSONObject(0).getString("sectionId").toString()+"\n\n");
		   //System.out.println("sub1_attssssssssssssssssssssssssssssssssssssssssssssssss = " + ja4.getJSONObject(0).getString("sectionName").toString()+"\n\n");
		   //System.out.println("sub1_atttttttttttttttttttttttttttttttttttttttttttttttttt = " + ja4.getJSONObject(1).getString("sectionId").toString()+"\n\n");
		   //System.out.println("sub1_attuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu = " + ja4.getJSONObject(1).getString("sectionName").toString()+"\n\n");
		   //System.out.println("llllllllllllllllllllllllllllllllllllllllllllllllllllllllength = " + ja4.length()+"\n\n");
*/
		   for (int i=0;i<ja4.length();i++){
			 listItems.add(ja4.getJSONObject(i).getString("description").toString());
			 listWebUrls.add(ja4.getJSONObject(i).getString("photo_url").toString()); 
//		//System.out.println("llllllllllllllllllllllllllllllllllllllllllllllllllllllllength = " + ja4.getJSONObject(i).getString("webTitle").toString()+"\n\n"); 
		 }
		 // convert listitems to String array
		 String []strArray = new String[ja4.length()];
		 String []urlsArray = new String[ja4.length()];
		 listItems.toArray(strArray);
		 listWebUrls.toArray(urlsArray);
/*		 for (int i = 0;i<ja4.length();i++){
		 //System.out.println("strArray is " +i + " "+ strArray[0]);
		 } */
		 
		// String attributeId3 = popupObject3.getString("sectionId");
		// //System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA1 value == " +attributeId3);  
		   
		//   JSONArray menuitemArray2 = popupObject2.getJSONArray("sectionId");
		//   //System.out.println("sub1_attr = " + menuitemArray2.getJSONObject(0).getString("sub1_attr").toString());  

		              } catch (MalformedURLException e) {
		            		            e.printStackTrace();
		        } catch (IOException e) {
		           		            e.printStackTrace();
		        } catch (Exception e) {
		            		            e.printStackTrace();
		        }
		        return listItems;
		    }
		@Override
		protected void onProgressUpdate(String... item) { 
		//	Toast.makeText(AsyncTaskDemo.this, "Done one", Toast.LENGTH_SHORT).show();
			((ArrayAdapter) getListAdapter()).add(item[0]);
		}

		@Override
		protected void onPostExecute(Void unused) {
			m_ProgressDialog.dismiss();
			setSelection(3); // dunno if this does anything?
		//	Toast.makeText(AsyncTaskDemo.this, "Done", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String myUrl = listWebUrls.get(position).toString();
       	Intent intent = new Intent(AsyncTaskDemo.this,WebViews.class);
        intent.putExtra("key1", myUrl);
        startActivity(intent);
	    CheckedTextView check = (CheckedTextView)v;
	    check.setChecked(!check.isChecked());
	//    Toast.makeText(AsyncTaskDemo.this, "cicked"+position+myUrl, Toast.LENGTH_SHORT).show();
	}
	@Override
	protected void onStart() {
//	 Toast.makeText(getApplicationContext(), " onPause starter ",Toast.LENGTH_LONG).show();
		super.onStart();
		// have to do this because 1) screen orientation crashes it. 2) After a long stop it gets list from cache which is
		// not what we want cos guardian always changes their stories
		m_ProgressDialog = ProgressDialog.show(AsyncTaskDemo.this,    
	              "Please wait...", "Retrieving data ...", true);
		setListAdapter(new ArrayAdapter<String>(this,
				R.layout.simple_list_bill, new ArrayList()));
		new AddStringTask().execute();	
}
	
}