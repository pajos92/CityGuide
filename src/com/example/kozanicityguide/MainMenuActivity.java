package com.example.kozanicityguide;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainMenuActivity extends ActionBarActivity implements
		View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		initializeButtons();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void initializeButtons() {
		ImageButton bLandmarks = (ImageButton) findViewById(R.id.bLandmarks);
		ImageButton bBars = (ImageButton) findViewById(R.id.bBars);
		ImageButton bFood = (ImageButton) findViewById(R.id.bFood);
		ImageButton bHotels = (ImageButton) findViewById(R.id.bHotels);

		bLandmarks.setOnClickListener(this);
		bBars.setOnClickListener(this);
		bFood.setOnClickListener(this);
		bHotels.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent listview = new Intent(MainMenuActivity.this,
				ListViewActivity.class);
		switch (v.getId()) {
		case R.id.bLandmarks:
			listview.putExtra("type", "landmarks");
			MainMenuActivity.this.startActivity(listview);
			break;
		case R.id.bBars:
			listview.putExtra("type", "bars");
			MainMenuActivity.this.startActivity(listview);
			break;
		case R.id.bFood:
			listview.putExtra("type", "food");
			MainMenuActivity.this.startActivity(listview);
			break;
		case R.id.bHotels:
			listview.putExtra("type", "hotels");
			MainMenuActivity.this.startActivity(listview);
			break;
		default:
			break;

		}

	}

}
