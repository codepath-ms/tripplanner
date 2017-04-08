package com.codepathms.cp.tripplannerapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codepathms.cp.tripplannerapp.R;
import com.codepathms.cp.tripplannerapp.models.Itinerary;

import java.util.List;

/**
 * Created by melissa on 4/4/17.
 */

public class ItineraryArrayAdapter extends ArrayAdapter<Itinerary> {
    private Context context;

    public ItineraryArrayAdapter(Context context, List<Itinerary> itineraries) {
        super(context, android.R.layout.simple_list_item_1, itineraries);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Itinerary itinerary =  getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_itinerary, parent, false);
        }

        TextView tvItineraryTitle = (TextView) convertView.findViewById(R.id.tvItineraryItemTitle);
        tvItineraryTitle.setText(itinerary.getTitle());


        return convertView;
    }
}
