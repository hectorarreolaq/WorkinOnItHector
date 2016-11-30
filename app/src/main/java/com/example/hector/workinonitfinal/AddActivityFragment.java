package com.example.hector.workinonitfinal;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.api.model.StringList;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.app.Activity.RESULT_OK;


public class AddActivityFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText name,
            description,
            dateStart,
            dateFinish;

    private TextView location;

    private String userId;
    private int PLACE_PICKER_REQUEST;
    private Button addButton,regresarButton;

    private Context globalContext = null;

    private FirebaseDatabase db;
    private DatabaseReference ref;
    private FirebaseUser fbUser;


    public AddActivityFragment() {
        // Required empty public constructor
    }

    public static AddActivityFragment newInstance(String param1, String param2) {
        AddActivityFragment fragment = new AddActivityFragment();
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
        View v = inflater.inflate(R.layout.fragment_add_activity, container, false);

        name = (EditText) v.findViewById(R.id.name);
        description = (EditText)v.findViewById(R.id.description);
        dateStart = (EditText)v.findViewById(R.id.dateStart);
        dateFinish = (EditText)v.findViewById(R.id.dateFinish);
        location = (TextView) v.findViewById(R.id.location);

        //userId = intent.getStringExtra("usuario");

        db = FirebaseDatabase.getInstance();
        globalContext = this.getActivity().getApplicationContext();

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                Intent intent;
                try {
                    intent = builder.build(getActivity().getApplicationContext());
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });



        addButton = (Button) v.findViewById(R.id.addFragmentButton);
        regresarButton = (Button) v.findViewById(R.id.button6);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Habit habit = new Habit(name.getText().toString(),
                        description.getText().toString(),
                        dateStart.getText().toString(),
                        dateFinish.getText().toString(),
                        location.getText().toString());
                ref.child(name.getText().toString()).setValue(habit);
            }
        });
        regresarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListFragment fragment = ListFragment.newInstance("","");
                FragmentManager fm = getFragmentManager();

                fragment.setUser(userId);
                fragment.setRef(ref);

                FragmentTransaction t = fm.beginTransaction();
                t.replace(R.id.content_menu_principal, fragment, "ListFragment");
                t.commit();

            }
        });

        return v;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==PLACE_PICKER_REQUEST){
            if(resultCode == RESULT_OK){
                Place place = PlacePicker.getPlace(getActivity(), data);
                String address = String.format("Place: %s", place.getAddress());
                location.setText(address);
            }else {
                Log.e("Place error, ", resultCode + "" );
            }
        }
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

    public void setRef(DatabaseReference ref){
        this.ref = ref;
    }


}
