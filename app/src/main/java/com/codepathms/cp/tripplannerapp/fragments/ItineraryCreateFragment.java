package com.codepathms.cp.tripplannerapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.codepathms.cp.tripplannerapp.R;
import com.codepathms.cp.tripplannerapp.models.Itinerary;

/**
 * Created by melissa on 4/12/17.
 */

public class ItineraryCreateFragment extends Fragment {
    OnItineraryCreatedListener mCallback;

    // Container Activity must implement this interface
    public interface OnItineraryCreatedListener {
        public void onItinerarySave(Itinerary newItinerary);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnItineraryCreatedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnItineraryCreatedListener");
        }

    }

    public static ItineraryCreateFragment newInstance() {
        ItineraryCreateFragment itineraryCreateFragment = new ItineraryCreateFragment();

        return itineraryCreateFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_itinerary, container, false);

        final EditText etCreateItineraryTitle = (EditText) v.findViewById(R.id.etCreateItineraryTitle);
        final EditText etCreateItineraryDescription = (EditText) v.findViewById(R.id.etCreateItineraryDescription);

        Button btnCreateSave = (Button) v.findViewById(R.id.btnCreateItinerarySave);
        btnCreateSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Itinerary itinerary = saveItinerary(etCreateItineraryTitle.getText().toString(), etCreateItineraryDescription.getText().toString());
                mCallback.onItinerarySave(itinerary);
            }
        });

        return v;

    }

    public Itinerary saveItinerary(String title, String description) {
        //TODO: Create new Itinerary object, Save Itinerary to Parse DB here

        Itinerary it = new Itinerary();
        it.setTitle(title);
        it.setDescription(description);
        it.save();
        return it;

    }

}
