package com.example.harv0kz.dd5ediceroller.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.math.MathUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harv0kz.dd5ediceroller.R;
import com.example.harv0kz.dd5ediceroller.activity.DiceRollingMain;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DiceRollingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DiceRollingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiceRollingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RNG diceRoller = new RNG();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DiceRollingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiceRollingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiceRollingFragment newInstance(String param1, String param2) {
        DiceRollingFragment fragment = new DiceRollingFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_dice_rolling, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View rootView, Bundle savedInstanceState){
        super.onViewCreated(rootView, savedInstanceState);
        final View v = rootView;
        //Rest of the view
        Spinner flatRollSpinner = (Spinner) rootView.findViewById(R.id.flatStatRoll);
        String[] statArray= {"Strength","Dexterity","Constitution","Intelligence","Wisdom","Charisma"};
        ArrayAdapter<String> flatRollItems = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, statArray);
        flatRollItems.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        flatRollSpinner.setAdapter(flatRollItems);
        flatRollSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                //Really for correct logging
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner skillRollSpinner = (Spinner) rootView.findViewById(R.id.skillRoll);
        //18 skills 0 index 17max
        String[] skillArray = {"Athletics","Acrobatics","Sleight of Hand","Stealth","Arcana","History","Investigation","Nature","Religion","Animal Handling","Insight","Medicine","Perception","Survival","Deception","Intimidation","Performance","Persuasion"};
        ArrayAdapter<String> skillRollItems = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, skillArray);
        skillRollItems.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        skillRollSpinner.setAdapter(skillRollItems);
        skillRollSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                //Get saved stats and apply proficiency/expertise bonus to it
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner savingThrowSpinner= (Spinner) rootView.findViewById(R.id.savingThrowRoll);
        ArrayAdapter<String> savingThrowItems = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, statArray); //replace statArray with charsheet designated saves
        savingThrowItems.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        savingThrowSpinner.setAdapter(savingThrowItems);
        savingThrowSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                //Get saving throw profs and populate list instead
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button flatRollButton = (Button) getView().findViewById(R.id.flatRollButton);
        flatRollButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                TextView viewRoll = (TextView) getView().findViewById(R.id.showRoll);
                int result = randomNumber(1,20);
                viewRoll.setText(Integer.toString(result));
            }
        });

        Button skillRollButton = (Button) getView().findViewById(R.id.skillRollButton);
        skillRollButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                TextView viewRoll = (TextView) getView().findViewById(R.id.showRoll);
                int result = randomNumber(1,20);
                viewRoll.setText(Integer.toString(result));
            }
        });

        Button savingThrowButton = (Button) getView().findViewById(R.id.savingThrowButton);
        savingThrowButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                TextView viewRoll = (TextView) getView().findViewById(R.id.showRoll);
                int result = randomNumber(1,20);
                viewRoll.setText(Integer.toString(result));
            }
        });

    }

    public int randomNumber(int numberDice, int diceSides){
        int result = 0;
        List<Integer> diceRolled = new ArrayList<Integer>();

        if(numberDice < 1){
            numberDice = 1;
        }

        switch(diceSides){
            case 4:
                for(int i = 0; i < numberDice; i++){
                    int oneRoll = diceRoller.rollD4();
                    diceRolled.add(oneRoll);
                }
                for (int j : diceRolled){
                    result += j;
                }
                break;
            case 6:
                for(int i = 0; i < numberDice; i++){
                    int oneRoll = diceRoller.rollD6();
                    diceRolled.add(oneRoll);
                }
                for (int j : diceRolled){
                    result += j;
                }
                break;
            case 8:
                for(int i = 0; i < numberDice; i++){
                    int oneRoll = diceRoller.rollD8();
                    diceRolled.add(oneRoll);
                }
                for (int j : diceRolled){
                    result += j;
                }
                break;
            case 10:
                for(int i = 0; i < numberDice; i++){
                    int oneRoll = diceRoller.rollD10();
                    diceRolled.add(oneRoll);
                }
                for (int j : diceRolled){
                    result += j;
                }
                break;
            case 12:
                for(int i = 0; i < numberDice; i++){
                    int oneRoll = diceRoller.rollD12();
                    diceRolled.add(oneRoll);
                }
                for (int j : diceRolled){
                    result += j;
                }
                break;
            case 20:
                for(int i = 0; i < numberDice; i++){
                    int oneRoll = diceRoller.rollD4();
                    diceRolled.add(oneRoll);
                }
                for (int j : diceRolled){
                    result += j;
                }
                break;
            default:
                result = 1;
                break;
        }

        return result;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
