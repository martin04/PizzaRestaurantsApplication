package com.martin.pizzarestaurantsapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.martin.pizzarestaurantsapplication.R;
import com.martin.pizzarestaurantsapplication.models.Restaurant;

import java.util.List;

/**
 * Created by Martin on 2/8/2016.
 */
public class RestaurantRecViewAdapter extends RecyclerView.Adapter<RestaurantRecViewAdapter.RestaurantViewHolder> {

    private List<Restaurant> lstRestaurants;

    public RestaurantRecViewAdapter(List<Restaurant> lstRestaurants){
        this.lstRestaurants = lstRestaurants;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.restaurant_list_item_layout, parent, false);
        return new RestaurantViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        Restaurant rest = lstRestaurants.get(position);
        holder.txtRestaurantName.setText(rest.getName());
        holder.txtDistanceToValue.setText(String.format("%.2f m",rest.getDistance()));
    }

    @Override
    public int getItemCount() {
        return lstRestaurants.size();
    }

    class RestaurantViewHolder extends RecyclerView.ViewHolder{
        private TextView txtRestaurantName;
        private TextView txtDistanceToRest;
        private TextView txtDistanceToValue;

        public RestaurantViewHolder(View itemView){
            super(itemView);
            txtRestaurantName = (TextView) itemView.findViewById(R.id.txtRestaurantName);
            txtDistanceToRest = (TextView) itemView.findViewById(R.id.txtDistanceTo);
            txtDistanceToValue = (TextView) itemView.findViewById(R.id.txtDistanceToValue);
        }
    }
}
