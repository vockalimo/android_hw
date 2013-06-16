package com.codepath.gridimagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.bool;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class SearchActivity extends Activity {
	EditText etQuery;
	GridView gvResults;
	Button btnSearch;
	ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
	ImageResultArrayAdapter imageAdapter;
	Bundle bundle = new Bundle();
	//String[] = array["imagez_choos"];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		setupViews();
		imageAdapter = new ImageResultArrayAdapter(this, imageResults);
		gvResults.setAdapter(imageAdapter);
		gvResults.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View parent, int position, long rowId) {
				Intent i = new Intent(getApplicationContext(), ImageDisplayActivity.class);
				ImageResult imageResult = imageResults.get(position);
				i.putExtra("result", imageResult);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	public void setupViews() {
		etQuery = (EditText) findViewById(R.id.etQuery);
		gvResults = (GridView) findViewById(R.id.gvResults);
		btnSearch = (Button) findViewById(R.id.btnSearch);
		initBundle();
		
		
		
	}

	public void initBundle() {
		bundle.putString("imagez_choose", "aa");
		bundle.putString("imagez_choose", "");
		bundle.putString("color_filter_choose", "" );
		bundle.putString("imagte_type_choose", "" );
		bundle.putString("site_filter", "" );
	}
	
	
	public void onImageSearch(View v) {
		String query = etQuery.getText().toString();
		Toast.makeText(this, "Searching for " + query, Toast.LENGTH_SHORT).show();
		
		String query_str = "";
		
		
		boolean hassetting = false;
		hassetting  = getIntent().getBooleanExtra("hassetting", false);
		if (hassetting == true) {
			bundle = getIntent().getExtras();
		} else {
			initBundle();
		}
	//	Instent i  =  this.getIntent();
		
		if (bundle.getString("imagez_choose") == null) {
			initBundle();
		}
		
		
		if (bundle.getString("imagez_choose") != "")
		{
			query_str = "&imgsz=" + bundle.getString("imagez_choose"); 
		}
		
		if (bundle.getString("color_filter_choose") != "")
		{
			query_str += "&imgcolor=" + bundle.getString("color_filter_choose"); 
		}
		
		if (bundle.getString("imagte_type_choose") != "")
		{
			query_str += "&imgsz=" + bundle.getString("imagte_type_choose"); 
		}
		
		if (bundle.getString("site_filter") != "null")
		{
			query_str += "&as_sitesearch=" + Uri.encode(bundle.getString("site_filter")); 
		}
		
		Log.d("DEBUG", query_str.toString());
		AsyncHttpClient client = new AsyncHttpClient();
		// https://ajax.googleapis.com/ajax/services/search/images?q=Android&v=1.0
		client.get("https://ajax.googleapis.com/ajax/services/search/images?rsz=8&" + "start=" + 0
				+ "&v=1.0&q=" + Uri.encode(query) + query_str , new JsonHttpResponseHandler() {
			@Override
			
			
			public void onSuccess(JSONObject response) {
				JSONArray imageJsonResults = null;
				try {
					imageJsonResults = response.getJSONObject("responseData").getJSONArray(
							"results");
					imageResults.clear();
					imageAdapter.addAll(ImageResult.fromJSONArray(imageJsonResults));
					Log.d("DEBUG", imageResults.toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void chageToOptionViews(View v) {
		Intent i = new Intent(this, SettingActivity.class);
	//	i.putExtra(SearchOptationsIntentKey, this.searchOptations);
		i.putExtras(bundle);
		startActivity(i);
	}
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent i = new Intent(this, SettingActivity.class);
		//	i.putExtras(bundle);
			startActivity(i);
	//	Intent i = new Intent(this, SearchOptionsActivity.class);
	//	i.putExtra(SearchOptationsIntentKey, this.searchOptations);
	//	startActivity(i);
			break;

		}
		return true;
	}
	
}
