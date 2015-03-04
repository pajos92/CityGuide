package com.example.kozanicityguide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewActivity extends ActionBarActivity implements
		LocationListener {

	// list of places to guide
	public static List<Place> myPlaces = new ArrayList<Place>();
	// array adapter for the list view
	private ArrayAdapter<Place> listAdapter = null;
	// place type passed through the last activity (landmarks || bars || food ||
	// hotels )
	private String placeGiven = null;

	// static values for my location - changes onLocationChanged()
	public static double myLatitude = 0;
	public static double myLongitude = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.place_list_view);
		// get place type from extras, to display later
		setPlaceGiven(getIntent().getStringExtra("type"));
		myPlaces.clear();
		// populate some places in the list
		populatePlaceList();
		// enable location service and stuff
		registerLocationListener();
		// apply the adapter on our listview
		populateListView();
		// set some callbacks of the list items
		registerOnClickCallback();
	}

	// input some places - java style
	private void populatePlaceList() {
		TextView tvPlaceType = (TextView) findViewById(R.id.tvPlaceType);
		if (getPlaceGiven().equals("landmarks")) {

			tvPlaceType.setText("���������");
			myPlaces.add(new Place(
					"���. ���������� �������",
					R.drawable.museum,
					PlaceType.LANDMARK,
					40.3015423,
					21.7849702,
					"�� ������������������ ������� ������� �������� ����� ����������� ��� ��������� ��������� ��� ������ ����� �������. �� �������� ��� �������� ��������������� ��� ��� ����� ��� �������� ��� �������� ����� �� ������� ��������� ������. � ��������� ���� ��� �������� �� ���������� ������ � ��������� ��������� ��� �������� ��������� ������, ��������� ��� �� ����� ����. �� �������� ��� ���������� ������� �� �������� ��������� ������� ���� ��� ����� �������� ���� ���� �� ����� ������, �� ������ �������, ���������� ��� �������� ��� ��� ���������� ����� ��� ���������, � ��������� �������� �� ������������ ���� ������� 1980�1983 ���������� ������ �� �������� ��� ��������� ����������� ������. �� �������� ��� �������� ������ ��� 10� ��������� 1987."));
			myPlaces.add(new Place(
					"������� ����.���. ��������",
					R.drawable.museum,
					PlaceType.LANDMARK,
					40.3001359,
					21.7835427,
					"������������� ��� ������� 20 ����������� 2006, �� ������� ��������� ������� �������� ��� ������ � ����� ������� ��� � ������� �������� ��������� ������� �� ���������� �� ��� ������� ������������� ����������. ��� ��� ������� ��������� � ����������� ����� ����� �� ��������� ���� ������� �� ����� ��� �� ����� ��� ��� ��� �����. ���� ������������ ������������������ ������������ ������� ������ ��� �������� ��� ��������� ������ ��� �������� �� ���������� ��� ����������. �� ����������, ����� �� ����, �� ������� ��� �������� ������� ��� ����� ���, ��� ���������� ��� ��� ���������� ��� �������� ��� 1940 ��� ������ �� ����������� ��� ��������� ��� ������� ���������� ��� ������ ���������, ���� ��� ��� �������� ��� ����. "));
			myPlaces.add(new Place(
					"������������ �������",
					R.drawable.museum,
					PlaceType.LANDMARK,
					40.3029495,
					21.7850523,
					"�� ������� ���������� �� ���������� ������, ������ �� ������������� ��������� ����� ��� ����� ������� ���� ������������ ��������.����� ����� �������� ������������� ��� ������� ��� ��������� ����� �� ����������.���� �������� ��� �������� ��������������� �������� ������������ ����� ������������������ ������ ��� �������� ������ ��� ����� �������."));

		} else if (getPlaceGiven().equals("bars")) {

			tvPlaceType.setText("����������");
			myPlaces.add(new Place(
					"Chorus",
					R.drawable.wine,
					PlaceType.BAR,
					40.3023857,
					21.7873442,
					"��� ���������� �� ����� ������� ��� �� ������ ��� ������� �� rock-bar ��� ����������: �Chorus�! � ������� ������� ��� ���� ���� �� ��������� rock ��������� ��� ������� ���� �� �������� ������ ���������, ���� � ������� ����� ������ ������� �������. ����� ������� �������������� ��������� ������� �� blues � jazz �������, ��� ���� ���� ������ �������� ��� ���������� ���."));
			myPlaces.add(new Place(
					"Gallery",
					R.drawable.wine,
					PlaceType.BAR,
					40.3015423,
					21.7849702,
					"�� ������� ��� ��� �������� �� ���� �� bar, �������� ��� ��� �������� �������, �� ������ �� ����� ���."));
			myPlaces.add(new Place(
					"Grand Cafe",
					R.drawable.wine,
					PlaceType.BAR,
					40.3011216,
					21.7876017,
					"��� �� ����� cafe-bars ��� �� ���������� ��������� ��� �� ����� ����� �� Grand Cafe, ���� ��������� ��� �������. ��� ����� ������ � ����� ���, ��� �� �������� (�� ������� ��� ����� 300 �.�.), ���� ��� �� ��������� ����. �� ��������� ������� ����� ������� ��� �� roof garden, ��� �� ������ �� ���� ��� �� ��� ��� ������� ��� �� ����������."));

		} else if (getPlaceGiven().equals("food")) {

			tvPlaceType.setText("�������");
			myPlaces.add(new Place(
					"������������",
					R.drawable.restaurant,
					PlaceType.FOOD,
					40.30161,
					21.786953,
					"�� Beer Bar Restaurant ������������ ���������� ��� ��� ������ ��� 2002 �� ��� ���������, ����� ��� ������ ����. �������� ��� ����������� ������� ����������� ��� ��������� ����, ������ ��� �������. ��������� ������ �������� �� ������ ��� ������, ������������ �� �� ���������� ������������� ��������� ���������� ���� ������� ��� �������� ���. �� ����� ��� ������������ ��� ���������� ������ ����� ������� ���������. ���� ���������� ������ ����������� ��� ����������� �� �����."));
			myPlaces.add(new Place(
					"STRADA",
					R.drawable.restaurant,
					PlaceType.FOOD,
					40.301588,
					21.787188,
					"����� �� cafe-bar-restaurant ��� ������ ��� ������� ��� ������ ����� ������� ���� ������������� ��� ����������� ���� ����. ����������� ��������� ����������� ��������, ������� ����� ������� ��� ������ �������� �� ���� ������������ ���� ��� ��� ��������� ����� ���... ������! �� ��������� �� �� ��������� ������������ ��������, �� Strada ��� �������� �� ������ ��� �������� ������... ����������!"));
			myPlaces.add(new Place(
					"Tip Top",
					R.drawable.restaurant,
					PlaceType.FOOD,
					40.3012585,
					21.7876578,
					"� ����� ����� ����� �������� ���� ������. ��������� �� ������ ��� ��� ��� ��� ����. ������������� party ���������.."));

		} else if (getPlaceGiven().equals("hotels")) {

			tvPlaceType.setText("�������");
			myPlaces.add(new Place(
					"��������",
					R.drawable.hotel,
					PlaceType.HOTEL,
					40.299665,
					21.791666,
					"�� Aliakmon Hotel ��������� �� �������� 200�. ��� �� ������ ��� �������. ��������� ��� ��� ���������� ����������, ����� �� ������ �������������, ����������� ��� ����������. ��������� ���� ����, ������ Wi-Fi �� ����� ���� ������ ��� ������ �������� ���� ����������."));
			myPlaces.add(new Place(
					"Anesis",
					R.drawable.hotel,
					PlaceType.HOTEL,
					40.298520,
					21.789982,
					"�� ������������ ����������� Anesis ��������� �� �������� 200�. ��� �� ������ ��� ������� ��� �������� ������������� ������� �� ������ Wi-Fi ��� �������� �� ���. � ������� ��� ������ ��� ��� ���������� ��� �� ���������� ��� ������� ������� 700�. ��� 4���. ����������."));
			myPlaces.add(new Place(
					"Elena Hotel",
					R.drawable.hotel,
					PlaceType.HOTEL,
					40.3078875,
					21.7842247,
					"�� Elena Hotel ������ 500�. ��� �� ������ ��� �������. ��������� ����� ��� ������������� ������� �� ������ Wi-Fi. �������� ����������, ���� ��� ������ ���� ����������.�� ������ ����������� ������� ��� Elena ����� ����������� �� ���������� ���������, ������ ��������� ��� ���� ����. �� �������� ������ ������������ ����� ��� ���������� �������."));

		} else {
			myPlaces.add(new Place("Error parsing extras", R.drawable.hotel,
					PlaceType.HOTEL, 0, 0, "No extras found"));
		}

	}

	private void registerLocationListener() {

		// Acquire a reference to the system Location Manager
		LocationManager locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
		// Register the listener with the Location Manager to receive location
		// updates via GPS
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				2000, 10, this);
	}

	// register adapter for the listview
	private void populateListView() {
		listAdapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.listPlaces);
		list.setAdapter(listAdapter);
	}

	private void registerOnClickCallback() {

		ListView list = (ListView) findViewById(R.id.listPlaces);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked,
					int position, long id) {

				Place clicked = myPlaces.get(position);

				String message = "" + clicked.getName() + " , "
						+ clicked.getDistanceInMeters() + "m ������.";
				Toast.makeText(ListViewActivity.this, message,
						Toast.LENGTH_SHORT).show();

				Intent details = new Intent(ListViewActivity.this,
						DetailedInfoActivity.class);
				details.putExtra("place", position);
				startActivity(details);
			}
		});
	}

	private class MyListAdapter extends ArrayAdapter<Place> {
		public MyListAdapter() {
			super(ListViewActivity.this, R.layout.item_view, myPlaces);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			// Make sure we have a view to work with (may have been given null)
			View itemView = convertView;
			if (itemView == null) {
				itemView = getLayoutInflater().inflate(R.layout.item_view,
						parent, false);
			}

			// Find the place to work
			Place currentPlace = myPlaces.get(position);

			// Fill the view
			ImageView imageView = (ImageView) itemView
					.findViewById(R.id.place_icon);
			imageView.setImageResource(currentPlace.getIconId());

			// Name:
			TextView tvName = (TextView) itemView.findViewById(R.id.place_name);
			tvName.setText(currentPlace.getName());

			// Distance
			TextView tvDistance = (TextView) itemView
					.findViewById(R.id.place_distance);
			if (myLatitude == 0 || myLongitude == 0) {
				tvDistance.setText("Waiting for GPS..");
			} else {
				currentPlace.setDistanceInMeters(calculateDistance(currentPlace.getLatitude(),currentPlace.getLongitude(), myLatitude, myLongitude));
				tvDistance.setText("" + currentPlace.getDistanceInMeters() + "m");
			}

			return itemView;
		}
	}

	@Override
	public void onLocationChanged(Location location) {
		myLatitude = location.getLatitude();
		myLongitude = location.getLongitude();
		// update distance between me and all locations
		for (Place p : myPlaces) {
			p.setDistanceInMeters(calculateDistance(p.getLatitude(),
					p.getLongitude(), location.getLatitude(),
					location.getLongitude()));
		}
		//sort by comparing the distances
		Collections.sort(myPlaces, placeComparator);
		// notify out list that data have changed
		listAdapter.notifyDataSetChanged();
	}

    public static Comparator<Place> placeComparator = new Comparator<Place>() {
        @Override
        public int compare(Place p1, Place p2) {
            return (int) (p1.getDistanceInMeters() - p2.getDistanceInMeters());
        }
    };

	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(getBaseContext(), "Gps turned off ", Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(getBaseContext(), "Gps turned on ", Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}

	// calculate distance between 2 points
	private int calculateDistance(double lat1, double long1, double lat2,
			double long2) {

		Location loc1 = new Location("some place");
		loc1.setLatitude(lat1);
		loc1.setLongitude(long1);

		Location loc2 = new Location("my current location");
		loc2.setLatitude(lat2);
		loc2.setLongitude(long2);

		int distance = (int) (loc1.distanceTo(loc2));
		return distance;

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_view, menu);
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

	public String getPlaceGiven() {
		return placeGiven;
	}

	public void setPlaceGiven(String placeGiven) {
		this.placeGiven = placeGiven;
	}

}
