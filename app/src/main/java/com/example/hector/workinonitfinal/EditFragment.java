package com.example.hector.workinonitfinal;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class EditFragment extends Fragment {
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
            dateFinish,
            location;

    private Habit habit;

    private FirebaseDatabase database;
    private DatabaseReference ref;

    private Button botonEdit, botonReturn;


    public EditFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditRestaurantFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditFragment newInstance(String param1, String param2) {
        EditFragment fragment = new EditFragment();
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
        View v=inflater.inflate(R.layout.fragment_edit, container, false);

        name = (EditText) v.findViewById(R.id.name);
        description = (EditText)v.findViewById(R.id.description);
        dateStart = (EditText)v.findViewById(R.id.dateStart);
        dateFinish = (EditText)v.findViewById(R.id.dateFinish);
        location = (EditText)v.findViewById(R.id.location);


        name.setText(habit.getName());
        description.setText(habit.getDescription());
        dateStart.setText(habit.getDateStart());
        dateFinish.setText(habit.getDateFinish());
        location.setText(habit.getLocation());

        database = FirebaseDatabase.getInstance();
        //ref = database.getReference("Users");

        botonEdit = (Button) v.findViewById(R.id.buttonEdit);
        botonReturn = (Button) v.findViewById(R.id.buttonReturn);

        botonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Habit res = new Habit(name.getText().toString(),
                                        description.getText().toString(),
                                        dateStart.getText().toString(),
                                        dateFinish.getText().toString(),
                                        location.getText().toString());

                ref.child(habit.getName()).setValue(res);
                Toast.makeText(getActivity(),"Activity Updated",Toast.LENGTH_LONG).show();

            }
        });

        botonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListFragment fragment = ListFragment.newInstance("","");
                FragmentManager fm = getFragmentManager();
                FragmentTransaction t = fm.beginTransaction();
                t.replace(R.id.content_menu_principal, fragment, "ListFragment");
                t.commit();

            }
        });

        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }
}
