package com.example.hector.workinonitfinal;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FirebaseDatabase db;
    DatabaseReference ref;

    ListView listview;

    private String userId;

    public ListFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
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
        // Inflate the layout for this fragment


        View v=inflater.inflate(R.layout.fragment_list, container, false);
        db= FirebaseDatabase.getInstance();
        ref=db.getReference("Restaurants");

        listview = (ListView) v.findViewById(R.id.listview);

        ValueEventListener listener = new ValueEventListener() {

            ArrayList<Habit> habits;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                habits = new ArrayList<Habit>();
                Habit habit = new Habit();
                for(DataSnapshot userSnap: dataSnapshot.getChildren()){
                    habit = userSnap.getValue(Habit.class);
                    habits.add(habit);
                }

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        DetailsFragment frag = DetailsFragment.newInstance("","");
                        frag.setHabit(habits.get(position));
                        FragmentManager mf= getFragmentManager();
                        FragmentTransaction ft= mf.beginTransaction();
                        ft.replace(R.id.content_menu_principal,frag,"RestaurantVisualization");
                        ft.commit();
                    }
                });


                MyAdapter adapter =new MyAdapter(habits, getActivity());
                listview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        ref.addValueEventListener(listener);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setUser(String string){
        this.userId = string;
    }


}
