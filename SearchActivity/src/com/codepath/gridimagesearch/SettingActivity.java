package com.codepath.gridimagesearch;


import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class SettingActivity extends Activity {
	protected static final String TAG = null;
	String[] imageSize;
	String[] resource;
	String imagez_choose;
	String color_filter_choose;
	String imagte_type_choose;
	Spinner SpinnerS;
	Spinner SpinnerZ;
	Spinner SpinnerF;
	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		renderImageSize();
		renderColorFilter();
		renderImageType();
	}

	public void renderImageSize() {
		SpinnerS = (Spinner) findViewById(R.id.spinner_image_size);
		imageSize = getResources().getStringArray(R.array.imagezlist);		
		ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this,R.array.imagezlist, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		SpinnerS.setAdapter(adapter);
		
		SpinnerS.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
							//   //  Toast.makeText(MainActivity.this, "您選擇"+adapterView.getSelectedItem().toString(), Toast.LENGTH_LONG).show()
							// TODO Auto-generated method stub
							imagez_choose = arg0.getSelectedItem().toString();
							Log.d("DEBUG",arg0.getSelectedItem().toString());							
						}
						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub
							
						}
        });	
	}
	
	
	public void renderColorFilter() {
	SpinnerZ = (Spinner) findViewById(R.id.spinner_color_filter);
		
		ArrayAdapter<?> adapterZ = ArrayAdapter.createFromResource(this,R.array.imgcolorlist, android.R.layout.simple_spinner_item);
		adapterZ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		SpinnerZ.setAdapter(adapterZ);
		
		SpinnerZ.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
							//   //  Toast.makeText(MainActivity.this, "您選擇"+adapterView.getSelectedItem().toString(), Toast.LENGTH_LONG).show()
							// TODO Auto-generated method stub
							color_filter_choose = arg0.getSelectedItem().toString();
							Log.d("DEBUG",arg0.getSelectedItem().toString());							
						}
						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub
							
						}
        });	
        
        
	}
	
	public void renderImageType() {
		SpinnerZ = (Spinner) findViewById(R.id.spinner_image_type);
			
			ArrayAdapter<?> adapterZ = ArrayAdapter.createFromResource(this,R.array.imgtypelist, android.R.layout.simple_spinner_item);
			adapterZ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			SpinnerZ.setAdapter(adapterZ);
			
			SpinnerZ.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
							@Override
							public void onItemSelected(AdapterView<?> arg0,
									View arg1, int arg2, long arg3) {
								//   //  Toast.makeText(MainActivity.this, "您選擇"+adapterView.getSelectedItem().toString(), Toast.LENGTH_LONG).show()
								// TODO Auto-generated method stub
								imagte_type_choose = arg0.getSelectedItem().toString();
								Log.d("DEBUG",arg0.getSelectedItem().toString());							
							}
							@Override
							public void onNothingSelected(AdapterView<?> arg0) {
								// TODO Auto-generated method stub
								
							}
	        });	
	        
	        
		}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting, menu);
		return true;
	}

	public void backMainActivity(View v) {
		
		EditText etSiteFilter = (EditText) findViewById(R.id.etSiteFilter);  
		
		Bundle bundle = new Bundle();
		bundle.putString("imagez_choose", imagez_choose);
		bundle.putString("color_filter_choose", color_filter_choose );
		bundle.putString("imagte_type_choose", imagte_type_choose );
		bundle.putString("site_filter", etSiteFilter.getText().toString() );
		bundle.putString("hassetting", "true" );
		
		
		
		//imagte_type_choose
		
		Intent intent = new Intent();
		intent.putExtras(bundle);
		intent.setClass(getApplicationContext(), SearchActivity.class);
		startActivity(intent);
		
	}
	
}
