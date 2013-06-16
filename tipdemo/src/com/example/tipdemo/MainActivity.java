package com.example.tipdemo;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar1);  
        final TextView seekBarValue = (TextView)findViewById(R.id.seekBarValue);  
		
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){  
        	  
        @Override  
        public void onProgressChanged(SeekBar seekBar, int progress,  
        	     boolean fromUser) {  
        	    // TODO Auto-generated method stub  
        	    seekBarValue.setText(String.valueOf(progress)); 
        	    
        	    
        	    
        	    EditText input_num = (EditText) findViewById(R.id.input_num);  
        	    Toast toast = Toast.makeText(getApplicationContext(), "please input", Toast.LENGTH_SHORT);
        	    
        	    if ( input_num.getText().length() < 0  ) {
           	    Double money =  Double.parseDouble(input_num.getText().toString());
       	        Double tip =  ( money * progress ) / 100 ;
       	        setResult(tip);
       	        toast.cancel();
        	    } else {
        	    	toast.show();      	    	
        	    }
        	    
       }  
        	  
        @Override  
        public void onStartTrackingTouch(SeekBar seekBar) {  
        	    // TODO Auto-generated method stub  
       }  
        	  
        @Override  
        public void onStopTrackingTouch(SeekBar seekBar) {  
        	    // TODO Auto-generated method stub  
         }  
        });  
        
        
		return true;
	}

     public void onTen(View v){
    	 EditText input_num = (EditText) findViewById(R.id.input_num);  
    	 
    	 Toast toast = Toast.makeText(getApplicationContext(), "please input", Toast.LENGTH_SHORT);
    	
		if ( input_num.getText().length() < 0 ) {
    	 int tiprate = Integer.parseInt(v.getTag().toString());
    	    Double money =  Double.parseDouble(input_num.getText().toString());	   
	        Double tip = ( money * tiprate ) / 100;
	        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar1);
	        seekBar.setProgress(tiprate);
	        toast.cancel();
	        setResult(tip);
    	 } else {
    		 toast.show();
    	 }
    	 
	    }
    

     private void setResult(Double money){
         TextView resultText = (TextView)findViewById(R.id.tipresult);
         
         resultText.setText(String.valueOf(money));
     }
     
}
