package com.codepathms.cp.tripplannerapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.codepathms.cp.tripplannerapp.R;
import com.codepathms.cp.tripplannerapp.activities.CreateItineraryActivity;
import com.codepathms.cp.tripplannerapp.activities.ItineraryDetailActivity;
import com.codepathms.cp.tripplannerapp.adapters.ItineraryArrayAdapter;
import com.codepathms.cp.tripplannerapp.models.Itinerary;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melissa on 4/4/17.
 */

public class ItineraryListFragment extends Fragment {
    private final int REQUEST_CODE = 20;

    List<Itinerary> itineraryList;
    private ItineraryArrayAdapter itineraryAdapter;
    public ListView lvItineraries;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_itinerary_list, container, false);

        //Get Itineraries ListView and set Adapter
        lvItineraries = (ListView) v.findViewById(R.id.lvItineraries);
        lvItineraries.setAdapter(itineraryAdapter);

        lvItineraries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Itinerary selectedItinerary = itineraryList.get(position);
                Intent i = new Intent(getActivity().getApplicationContext(), ItineraryDetailActivity.class);
                i.putExtra("itinerary", Parcels.wrap(selectedItinerary));
                startActivityForResult(i, REQUEST_CODE);

            }
        });

        FloatingActionButton fabCreate = (FloatingActionButton) v.findViewById(R.id.fabCreate);
        fabCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), CreateItineraryActivity.class);
                startActivityForResult(i,10);

            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        itineraryList = new ArrayList<>();
        itineraryAdapter = new ItineraryArrayAdapter(getActivity(), itineraryList);

        itineraryList.addAll(getItineraries());

    }

    public void newItineraryCreated(Itinerary newItinerary) {
        itineraryList.add(newItinerary);
        itineraryAdapter.notifyDataSetChanged();
    }

    public ArrayList<Itinerary> getItineraries() {
        // TODO: Get Itineraries from Parse DB and return

        List<Itinerary> itineraryList = SQLite.select().from(Itinerary.class).queryList();

        /* creating some mock data if DB is empty*/
        if (itineraryList.size() == 0) {
            ArrayList<Itinerary> mockItineraries = new ArrayList<Itinerary>();
            Itinerary it1 = new Itinerary();
            it1.setTitle("Dinner and Dessert");
            it1.setDescription("A nice evening in Los Gatos");
            it1.setImageUrl("http://i.imgur.com/nLB5Nce.jpg");
            it1.save();

            Itinerary it2 = new Itinerary();
            it2.setTitle("Biking and Picnic at the beach");
            it2.setDescription("Great for active families");
            it2.save();

            mockItineraries.add(it1);
            mockItineraries.add(it2);
            return mockItineraries;
        }

        return (ArrayList<Itinerary>)itineraryList;
    }
}
