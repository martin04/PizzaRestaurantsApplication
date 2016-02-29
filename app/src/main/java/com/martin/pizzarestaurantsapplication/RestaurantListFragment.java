package com.martin.pizzarestaurantsapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.martin.pizzarestaurantsapplication.adapters.RestaurantRecViewAdapter;
import com.martin.pizzarestaurantsapplication.models.Restaurant;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RestaurantListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RestaurantListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RestaurantListFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener, OnFetchPlacesListener{

    public static final String TAG = "RestaurantListFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private GoogleApiClient mGoogleApiClient;
    private RecyclerView lstRestaurants;
    private SharedPreferences sharedPreferencesCompat;
    private boolean dataSync; //FALSE-no data syn , TRUE - data sync

    public RestaurantListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RestaurantListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RestaurantListFragment newInstance(String param1, String param2) {
        RestaurantListFragment fragment = new RestaurantListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        readSettings();
        if(dataSync) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addApi(Places.GEO_DATA_API)
                    .addApi(Places.PLACE_DETECTION_API)
                    .enableAutoManage(getActivity(), this)
                    .build();
        }else{
            Toast.makeText(getActivity(), "Data sync is disabled. Please turn on to refresh list!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_restaurant_list, container, false);

        lstRestaurants = (RecyclerView)v.findViewById(R.id.lstRestaurants);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,1);//vertical = 1, horizontal = 0;

        lstRestaurants.setHasFixedSize(true);
        lstRestaurants.setLayoutManager(manager);

        if(dataSync){
            new GetLikelyHoodAsync(this).execute(mGoogleApiClient);
        }

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPlacesFetched(List<Restaurant> lstPlaces) {
        RestaurantRecViewAdapter adapter = new RestaurantRecViewAdapter(lstPlaces);
        lstRestaurants.setAdapter(adapter);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(getActivity(), "Connection cannot be established with Google Services", Toast.LENGTH_LONG).show();
    }

    /**
     * Function that will read users settings
     */
    private void readSettings() {
        sharedPreferencesCompat = PreferenceManager.getDefaultSharedPreferences(getActivity());
        dataSync = sharedPreferencesCompat.getBoolean(SettingsActivity.KEY_DATA_SYNC, false);
    }


    class GetLikelyHoodAsync extends AsyncTask<GoogleApiClient, Void, Void> implements ResultCallback<PlaceLikelihoodBuffer>{

        private OnFetchPlacesListener listener;
        private List<Restaurant> tmpPlaces;

        public GetLikelyHoodAsync(OnFetchPlacesListener listener){
            this.listener = listener;
            tmpPlaces = new ArrayList<>();
        }

        @Override
        protected Void doInBackground(GoogleApiClient... params) {
            try {
                PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi.getCurrentPlace(mGoogleApiClient, null);
                result.setResultCallback(this);

            }catch (SecurityException ex){
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

        }


        @Override
        public void onResult(PlaceLikelihoodBuffer placeLikelihoods) {
            for (PlaceLikelihood placeLikelihood : placeLikelihoods) {
                Restaurant tmpRest = new Restaurant();
                tmpRest.setName(placeLikelihood.getPlace().getName().toString());

                LatLng tmpLatLng = placeLikelihood.getPlace().getLatLng();
                tmpRest.setLatitude(tmpLatLng.latitude);
                tmpRest.setLongitude(tmpLatLng.longitude);

                Location myLocation = new Location("Martin's location");
                myLocation.setLatitude(42.00095);
                myLocation.setLongitude(21.4147421);
                Location placeLocation = new Location("Place location");
                placeLocation.setLatitude(tmpLatLng.latitude);
                placeLocation.setLongitude(tmpLatLng.longitude);

                tmpRest.setDistance(myLocation.distanceTo(placeLocation));

                tmpPlaces.add(tmpRest);
            }
            placeLikelihoods.release();

            if(tmpPlaces != null && tmpPlaces.size() > 0){
                listener.onPlacesFetched(tmpPlaces);
            }
        }
    }
}
