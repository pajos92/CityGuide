package com.example.kozanicityguide;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailedInfoActivity extends ActionBarActivity implements
		View.OnClickListener {

	private Place place = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detailed_info);
		setPlace(ListViewActivity.myPlaces.get(getIntent().getIntExtra("place",
				0)));

		TextView name = (TextView) findViewById(R.id.tvPlaceNameDetailed);
		ImageView im = (ImageView) findViewById(R.id.ivPlaceImage);
		TextView description = (TextView) findViewById(R.id.tvDescription);
		Button bMap = (Button) findViewById(R.id.bMapView);

		bMap.setOnClickListener(this);

		name.setText(getPlace().getName());
		im.setBackgroundResource(getPlace().getIconId());
		description.setText(getPlace().getDescription());
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		// execute a geo uri
		case R.id.bMapView:
			Intent geoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:<"
					+ getPlace().getLatitude() + ">,<"
					+ getPlace().getLongitude() + ">?q=<"
					+ getPlace().getLatitude() + ">,<"
					+ getPlace().getLongitude() + ">("
					+ getPlace().getType().toString() + "+"
					+ getPlace().getName() + ")"));
			startActivity(geoIntent);
			break;
		default:

			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detailed_info, menu);
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

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

}
