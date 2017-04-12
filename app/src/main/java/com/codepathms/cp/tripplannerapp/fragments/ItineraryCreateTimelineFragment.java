package com.codepathms.cp.tripplannerapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepathms.cp.tripplannerapp.R;
import com.codepathms.cp.tripplannerapp.models.Stop;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by melissa on 4/5/17.
 */

public class ItineraryCreateTimelineFragment extends Fragment{

    public static ItineraryCreateTimelineFragment newInstance() {
        ItineraryCreateTimelineFragment itineraryCreateTimelineFragment = new ItineraryCreateTimelineFragment();
//        Bundle args = new Bundle();
//        args.putParcelable("itinerary", Parcels.wrap(itinerary));
//        itineraryCreateTimelineFragment.setArguments(args);
        return itineraryCreateTimelineFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_timeline, container, false);


        return v;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportPlaceAutocompleteFragment autocompleteFragment = (SupportPlaceAutocompleteFragment)
                getChildFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName());
                String address = place.getAddress().toString();
                String name = place.getName().toString();
                String placeId = place.getId().toString();
                int priceLevel = place.getPriceLevel();

                Stop newStop = createNewStop(placeId, name, address);
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });


    }

    public Stop createNewStop(String placeId, String name, String location) {
        Stop newStop = new Stop();
        newStop.setTitle(name);
        newStop.setLocation(location);
        return newStop;
    }
}
