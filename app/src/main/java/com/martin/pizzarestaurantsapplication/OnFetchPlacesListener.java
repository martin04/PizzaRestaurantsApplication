package com.martin.pizzarestaurantsapplication;

import com.martin.pizzarestaurantsapplication.models.Restaurant;

import java.util.List;

/**
 * Created by Martin on 24.2.16.
 */
public interface OnFetchPlacesListener{
    void onPlacesFetched(List<Restaurant> lstPlaces);
}