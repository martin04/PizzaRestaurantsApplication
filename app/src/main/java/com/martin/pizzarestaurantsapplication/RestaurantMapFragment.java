package com.martin.pizzarestaurantsapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.martin.pizzarestaurantsapplication.models.Restaurant;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RestaurantMapFragment.OnFragmentMapInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RestaurantMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RestaurantMapFragment extends Fragment implements OnMapReadyCallback {

    public static final String TAG = "RestaurantMapFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private GoogleMap map;
    private List<Restaurant> lst;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentMapInteractionListener mListener;

    public RestaurantMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RestaurantMapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RestaurantMapFragment newInstance(String param1, String param2) {
        RestaurantMapFragment fragment = new RestaurantMapFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_restaurant_map, container, false);

        Restaurant r1 = new Restaurant("Dal Fufo Galija", 41.991632, 21.4227177, 1.56);
        Restaurant r2 = new Restaurant("Mexican Restaurant \"Amigos\"", 41.9929128, 21.428114, 1.56);
        Restaurant r3 = new Restaurant("Bella Vista - Lounge bar & Restaurant", 41.9951693, 21.4319664, 1.56);
        Restaurant r4 = new Restaurant("Pelister", 41.9951693, 21.4319664, 1.56);
        Restaurant r5 = new Restaurant("Squeeze Me", 41.9970685, 21.4282868, 1.56);
        Restaurant r6 = new Restaurant("Sushi Co", 41.9995376, 21.4235793, 1.56);
        Restaurant r7 = new Restaurant("Idadija Restaurant", 42.0013537, 21.4203743, 1.56);
        Restaurant r8 = new Restaurant("Skopski Merak", 42.0011807, 21.4204159, 1.56);
        Restaurant r9 = new Restaurant("Chardak Restaurant - Center", 42.0017447, 21.4220024, 1.56);
        Restaurant r10 = new Restaurant("Fitness House", 42.0008492, 21.4147021, 1.56);

        lst = new ArrayList<>();
        lst.add(r1);
        lst.add(r2);
        lst.add(r3);
        lst.add(r4);
        lst.add(r5);
        lst.add(r6);
        lst.add(r7);
        lst.add(r8);
        lst.add(r9);
        lst.add(r10);

        loadMap(v);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentMapInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentMapInteractionListener) {
            mListener = (OnFragmentMapInteractionListener) context;
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
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        if (map != null) {
            setUpMap(lst);
        }
    }

    private void loadMap(View v) {
        if (map == null) {
            ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
        } else {
            setUpMap(lst);
        }
    }

    /**
     * Function that sets the map over the city where the user currently is located
     */
    private void setUpMap(final List<Restaurant> restaurantsList) {

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                for (Restaurant pm : restaurantsList) {
                    map.addMarker(new MarkerOptions()
                            .title(pm.getName())
                            .position(
                                    new LatLng(pm.getLatitude(), pm.getLongitude()))
                            .snippet(pm.getDistance() + "m"));
                }
            }
        });
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
    public interface OnFragmentMapInteractionListener {
        // TODO: Update argument type and name
        void onFragmentMapInteraction(Uri uri);
    }
}
