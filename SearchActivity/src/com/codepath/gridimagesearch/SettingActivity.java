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
import android.widget.TextView;

public class SettingActivity extends Activity {
	protected static final String TAG = null;
	String[] imageSize;
	String[] resource;
	String imagez_choose;
	String color_filter_choose;
	String image_type_choose;
	Spinner SpinnerS;
	Spinner SpinnerZ;
	Spinner SpinnerF;
	Bundle bundle = new Bundle();
	int  imagez_choose_index,color_filter_index,image_type_index;
	
	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		bundle = getIntent().getExtras();
		
		renderImageSize();
		renderColorFilter();
		renderImageType();
		renderSiteFilter();
		
	
	}

	public void renderImageSize() {
		SpinnerS = (Spinner) findViewById(R.id.spinner_image_size);
		imageSize = getResources().getStringArray(R.array.imagezlist);		
		ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this,R.array.imagezlist, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		SpinnerS.setAdapter(adapter);
		bundle.getInt("imagez_choose_index");
		adapter.notifyDataSetChanged();   
		SpinnerS.setSelection(bundle.getInt("imagez_choose_index"));
		SpinnerS.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
						
			            @Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
							//   //  Toast.makeText(MainActivity.this, "±z¿ï¾Ü"+adapterView.getSelectedItem().toString(), Toast.LENGTH_LONG).show()
							// TODO Auto-generated method stub
							imagez_choose = arg0.getSelectedItem().toString();
							imagez_choose_index =  arg2;
							Log.d("DEBUG",arg0.getSelectedItem().toString());							
						}
						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub
							
						}
        });	
	}
	
	
	public void renderColorFilter() {
	    SpinnerS = (Spinner) findViewById(R.id.spinner_color_filter);
	   		ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this,R.array.imgcolorlist, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		SpinnerS.setAdapter(adapter);
		adapter.notifyDataSetChanged();   
		SpinnerS.setSelection(bundle.getInt("color_filter_index"));

		
		SpinnerS.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
							color_filter_choose = arg0.getSelectedItem().toString();
							color_filter_index =  arg2;
							Log.d("DEBUG",arg0.getSelectedItem().toString());
							String s = String.valueOf(arg2); 
							Log.d("DEBUG data zzzz  : ", s);
							
						}
						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub
							
						}
        });	
        
        
	}
	
	public void renderImageType() {
		SpinnerS = (Spinner) findViewById(R.id.spinner_image_type);
			
			ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this,R.array.imgtypelist, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			SpinnerS.setAdapter(adapter);
			adapter.notifyDataSetChanged();   
			SpinnerS.setSelection(bundle.getInt("image_type_index"));
			SpinnerS.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
							@Override
							public void onItemSelected(AdapterView<?> arg0,
									View arg1, int arg2, long arg3) {
								// TODO Auto-generated method stub
								image_type_choose = arg0.getSelectedItem().toString();
								image_type_index =  arg2;
								Log.d("DEBUG",arg0.getSelectedItem().toString());							
							}
							@Override
							public void onNothingSelected(AdapterView<?> arg0) {
								// TODO Auto-generated method stub
								
							}
	        });	
	        
	        
		}
	
	public void renderSiteFilter() {
		String tmp = bundle.getString("site_filter");
		EditText etSiteFilter = (EditText) findViewById(R.id.etSiteFilter);
		etSiteFilter.setText(tmp);
		
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
		bundle.putInt("imagez_choose_index", imagez_choose_index);
		bundle.putString("color_filter_choose", color_filter_choose );
		bundle.putInt("color_filter_index", color_filter_index);
		bundle.putString("image_type_choose", image_type_choose );
		bundle.putInt("image_type_index", image_type_index);
		bundle.putString("site_filter", etSiteFilter.getText().toString() );
		bundle.putBoolean("hassetting", true );
		
		
		//imagte_type_choose
		Intent intent = new Intent();
		intent.putExtras(bundle);
		intent.setClass(getApplicationContext(), SearchActivity.class);
		setResult(RESULT_OK, intent); 
		finish();
		//startActivity(intent);
		
	}
	
}
