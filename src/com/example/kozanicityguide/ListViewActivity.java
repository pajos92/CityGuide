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

			tvPlaceType.setText("Αξιοθέατα");
			myPlaces.add(new Place(
					"Ιστ. Λαογραφικό Μουσείο",
					R.drawable.museum,
					PlaceType.LANDMARK,
					40.3015423,
					21.7849702,
					"Το Ιστορικολαογραφικό Μουσείο Φυσικής Ιστορίας είναι δημιούργημα του Συνδέσμου Γραμμάτων και Τεχνών Νομού Κοζάνης. Οι συλλογές του Μουσείου μεταστεγάστηκαν από την ημέρα της ιδρύσεώς του τέσσερις φορές σε διάφορα μισθωμένα κτίρια. Η κατάσταση αυτή δεν μπορούσε να συνεχιστεί γιαυτό ο Σύνδεσμος μερίμνησε και ανήγειρε ιδιόκτητο κτίριο, κατάλληλο για το σκοπό αυτό. Σε οικόπεδο που παραχώρησε ομόφωνα το Δημοτικό Συμβούλιο Κοζάνης στην οδό Ίωνος Δραγούμη όπου ήταν τα παλιά λουτρά, με δωρεές ιδιωτών, οργανισμών και συλλόγων και την οικονομική αρωγή της Πολιτείας, ο Σύνδεσμος ανήγειρε με αυτεπιστασία στην περίοδο 19801983 τετραώροφο κτίριο με υπόστεγο και υπόγειους βοηθητικούς χώρους. Τα εγκαίνια του Μουσείου έγιναν την 10η Οκτωβρίου 1987."));
			myPlaces.add(new Place(
					"Μουσείο Συγχ.Τοπ. Ιστορίας",
					R.drawable.museum,
					PlaceType.LANDMARK,
					40.3001359,
					21.7835427,
					"Εγκαινιάστηκε την Τετάρτη 20 Σεπτεμβρίου 2006, το Μουσείο Σύγχρονης Τοπικής Ιστορίας που ίδρυσε ο Δήμος Κοζάνης και η Εταιρία Διάσωσης Ιστορικών Αρχείων σε συνεργασία με τις τοπικές αντιστασιακές οργανώσεις. Εδώ και αρκετές δεκαετίες η προηγούμενη γενιά έπαψε να μεταδίδει στην επόμενη τη γνώση και τη στάση της για τον κόσμο. Ένας πολιτισμικός παγκοσμιοποιημένος οδοστρωτήρας πλήττει καίρια την πρόσληψη της ιστορικής γνώσης και επομένως τη δημιουργία της συνείδησης. Δε γνωρίζουμε, ιδίως οι νέοι, τη νεότερη και σύγχρονη ιστορία του τόπου μας, την τρομακτική για τις επιπτώσεις της δεκαετία του 1940 και κυρίως τα κατορθώματα των αγωνιστών της Εθνικής Αντίστασης στη Δυτική Μακεδονία, αλλά και τις συμφορές του λαού. "));
			myPlaces.add(new Place(
					"Αρχαιολογικό Μουσείο",
					R.drawable.museum,
					PlaceType.LANDMARK,
					40.3029495,
					21.7850523,
					"Το Μουσείο στεγάζεται σε νεοκλασικό κτίριο, γνωστό ως Παναγιωτίδειο Αρχοντικό δωρεά του Δήμου Κοζάνης στην Αρχαιολογική Υπηρεσία.Έχουν γίνει εργασίες αποκατάστασης του κτιρίου και προσωρινά είναι μη επισκέψιμο.Στις συλλογές του Μουσείου περιλαμβάνονται ευρήματα προϊστορικών μέχρι παλαιοχριστιανικών χρόνων από διάφορες θέσεις του νομού Κοζάνης."));

		} else if (getPlaceGiven().equals("bars")) {

			tvPlaceType.setText("Διασκέδαση");
			myPlaces.add(new Place(
					"Chorus",
					R.drawable.wine,
					PlaceType.BAR,
					40.3023857,
					21.7873442,
					"Δεν χρειάζεται να είσαι Σούρδος για να ξέρεις πώς λέγεται το rock-bar στα κοζανίτικα: «Chorus»! Ο Αργύρης έφτιαξε εδώ έναν χώρο με αυθεντικά rock αισθητική και πλούσια κάβα με ετικέτες μπύρας ποιότητας, όπου η επιλογή είναι μάλλον δύσκολη υπόθεση. Συχνά μάλιστα διοργανώνονται θεματικές βραδιές με blues ή jazz μουσική, ενώ γύρω στην άνοιξη βγαίνουν και τραπεζάκια έξω."));
			myPlaces.add(new Place(
					"Gallery",
					R.drawable.wine,
					PlaceType.BAR,
					40.3015423,
					21.7849702,
					"Οι λάτρεις της ροκ ΅ουσικής σε αυτό το bar, απέναντι από τον πεζόδρο΅ο Ειρήνης, θα βρείτε το στέκι σας."));
			myPlaces.add(new Place(
					"Grand Cafe",
					R.drawable.wine,
					PlaceType.BAR,
					40.3011216,
					21.7876017,
					"Από τα πρώτα cafe-bars που σε περιμένουν φεύγοντας από τη Νίκης είναι το Grand Cafe, στον πεζόδρομο της Ειρήνης. Δεν είναι μονάχα η άνεσή του, που σε κερδίζει (το εμβαδόν του είναι 300 τ.μ.), αλλά και το σοφιστικέ ύφος. Το καλοκαίρι μάλιστα είναι ανοιχτό και το roof garden, για να πίνεις το ποτό σου με θέα την πλατεία και το καμπαναριό."));

		} else if (getPlaceGiven().equals("food")) {

			tvPlaceType.setText("Εστίαση");
			myPlaces.add(new Place(
					"Τρυποκάρυδος",
					R.drawable.restaurant,
					PlaceType.FOOD,
					40.30161,
					21.786953,
					"Το Beer Bar Restaurant ΤΡΥΠΟΚΑΡΥΔΟΣ λειτουργεί από τον Μάρτιο του 2002 σε ένα ξεχωριστό, ζεστό και φιλικό χώρο. Αποτελεί μια εναλλακτική πρόταση διασκέδασης που συνδυάζει ποτό, φαγητό και μουσική. Προσφέρει μεγάλη ποικιλία σε μπύρες και κρασιά, συνδυάζοντάς τα με ξεχωριστές γαστρονομικές προτάσεις βασισμένες στις γεύσεις της περιοχής μας. Τα πιάτα μας ετοιμάζονται από προσεγμένα φρέσκα υλικά αρίστης ποιότητος. Ένας ξεχωριστός τρόπος διασκέδασης που απευθύνεται σε όλους."));
			myPlaces.add(new Place(
					"STRADA",
					R.drawable.restaurant,
					PlaceType.FOOD,
					40.301588,
					21.787188,
					"Είναι το cafe-bar-restaurant στο κέντρο της Κοζάνης που άνοιξε νέους δρόμους στις γαστρονομικές σας αναζητήσεις στην πόλη. Εξαιρετικές προτάσεις μεσογειακής κουζίνας, εκλεκτή λίστα κρασιών και μεγάλη ποικιλία σε καφέ προσφέρονται κάτω από μια πρωτότυπη οροφή από... βιβλία! Σε συνδυασμό με τα ιδιαίτερα διακοσμητικά στοιχεία, το Strada σάς προκαλεί να ζήσετε μια εμπειρία υψηλής... αισθητικής!"));
			myPlaces.add(new Place(
					"Tip Top",
					R.drawable.restaurant,
					PlaceType.FOOD,
					40.3012585,
					21.7876578,
					"Η πρώτη πίτσα χωρίς καμπύλες στην Κοζάνη. Απολαύστε το φαγητό σας στο νέο μας χώρο. Αναλαμβάνουμε party γενεθλίων.."));

		} else if (getPlaceGiven().equals("hotels")) {

			tvPlaceType.setText("Διαμονή");
			myPlaces.add(new Place(
					"Αλιάκμων",
					R.drawable.hotel,
					PlaceType.HOTEL,
					40.299665,
					21.791666,
					"Το Aliakmon Hotel βρίσκεται σε απόσταση 200μ. από το κέντρο της Κοζάνης. Πρόκειται για ένα καλαίσθητο ξενοδοχείο, κοντά σε σημεία ενδιαφέροντος, καταστήματα και εστιατόρια. Προσφέρει σνακ μπαρ, δωρεάν Wi-Fi σε όλους τους χώρους και δωρεάν ιδιωτικό χώρο στάθμευσης."));
			myPlaces.add(new Place(
					"Anesis",
					R.drawable.hotel,
					PlaceType.HOTEL,
					40.298520,
					21.789982,
					"Το οικογενειακά διοικούμενο Anesis βρίσκεται σε απόσταση 200μ. από το κέντρο της Κοζάνης και διαθέτει κλιματιζόμενα δωμάτια με δωρεάν Wi-Fi και μπαλκόνι με θέα. Ο σταθμός των τρένων και των λεωφορείων και το αεροδρόμιο της Κοζάνης απέχουν 700μ. και 4χλμ. αντίστοιχα."));
			myPlaces.add(new Place(
					"Elena Hotel",
					R.drawable.hotel,
					PlaceType.HOTEL,
					40.3078875,
					21.7842247,
					"Το Elena Hotel απέχει 500μ. από το κέντρο της Κοζάνης. Προσφέρει κομψά και κλιματιζόμενα δωμάτια με δωρεάν Wi-Fi. Διαθέτει εστιατόριο, μπαρ και δωρεάν χώρο στάθμευσης.Τα ζεστής ατμόσφαιρας δωμάτια του Elena είναι εξοπλισμένα με δορυφορική τηλεόραση, θυρίδα ασφαλείας και μίνι μπαρ. Το ιδιωτικό μπάνιο περιλαμβάνει ντους και στεγνωτήρα μαλλιών."));

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
						+ clicked.getDistanceInMeters() + "m μακριά.";
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
