package com.example.harv0kz.dd5ediceroller.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.math.MathUtils;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harv0kz.dd5ediceroller.R;
import com.example.harv0kz.dd5ediceroller.activity.DiceRollingMain;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.min;
import static java.lang.Math.max;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DiceRollingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DiceRollingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public final class DiceRollingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RNG diceRoller = new RNG();
    private String textNumbers;
    private String textNumbersID = "textNumbers";

    private TextView viewRoll;
    private Bundle savedState = null;

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

        viewRoll = (TextView) rootView.findViewById(R.id.showRoll);

        if(savedInstanceState != null && savedState == null) {
            savedState = savedInstanceState.getBundle("ViewRoll");
        }
        if(savedState != null) {
            viewRoll.setText(savedState.getCharSequence("ViewRoll"));
        }
        savedState = null;

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        savedState = saveState(); /* vstup defined here for sure */
        viewRoll = null;
    }

    private Bundle saveState() { /* called either from onDestroyView() or onSaveInstanceState() */
        Bundle state = new Bundle();
        state.putCharSequence("ViewRoll", viewRoll.getText());
        return state;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        /* If onDestroyView() is called first, we can use the previously savedState but we can't call saveState() anymore */
        /* If onSaveInstanceState() is called first, we don't have savedState, so we need to call saveState() */
        /* => (?:) operator inevitable! */
        outState.putBundle("ViewRoll", (savedState != null) ? savedState : saveState());
    }

    @Override
    public void onViewCreated(View rootView, Bundle savedInstanceState){
        super.onViewCreated(rootView, savedInstanceState);
        final View v = rootView;
        //Rest of the view

        RadioGroup advDisRadioGroup = (RadioGroup) getView().findViewById(R.id.advDisadvApplier);
        RadioButton noneRadio = (RadioButton) getView().findViewById(R.id.applyNone);
        noneRadio.setChecked(true);
        String[] statArray= {"Strength","Dexterity","Constitution","Intelligence","Wisdom","Charisma"};

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
                viewRoll.setText("");
                for(Spannable span:d20Rolls()){
                    viewRoll.append(span);
                }
            }
        });

        Button skillRollButton = (Button) getView().findViewById(R.id.skillRollButton);
        skillRollButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                viewRoll.setText("");
                for(Spannable span:d20Rolls()){
                    viewRoll.append(span);
                }
            }
        });

        Button savingThrowButton = (Button) getView().findViewById(R.id.savingThrowButton);
        savingThrowButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                viewRoll.setText("");
                for(Spannable span:d20Rolls()){
                    viewRoll.append(span);
                }
            }
        });

    }

    public List<Spannable> d20Rolls (){
        List<Spannable> roller = new ArrayList<Spannable>();
        int rollOne = diceRoller.randomNumber(1,20);
        int rollTwo = diceRoller.randomNumber(1,20);
        int higher = max(rollOne,rollTwo);
        int lower = min(rollOne,rollTwo);
        Spannable firstRoll;
        Spannable secondRoll;
        Spannable comma = new SpannableString(", ");

        if(rollOne == rollTwo){
            higher = max(diceRoller.randomNumber(1,20),diceRoller.randomNumber(1,20));
            lower = min(diceRoller.randomNumber(1,20),diceRoller.randomNumber(1,20));
        }

        if(advDisGroupListener().equals("Advantage")){
            firstRoll = new SpannableString(critParser(higher));
            secondRoll = new SpannableString(critParser(lower));
            secondRoll.setSpan(new ForegroundColorSpan(Color.GRAY),0,secondRoll.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            roller.add(firstRoll);
            roller.add(comma);
            roller.add(secondRoll);
            return roller;
        }
        else if(advDisGroupListener().equals("Disadvantage")){
            firstRoll = new SpannableString(critParser(lower));
            secondRoll = new SpannableString(critParser(higher));
            secondRoll.setSpan(new ForegroundColorSpan(Color.GRAY),0,secondRoll.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            roller.add(firstRoll);
            roller.add(comma);
            roller.add(secondRoll);
            return roller;
        }
        else{
            firstRoll = new SpannableString(critParser(rollOne));
            roller.add(firstRoll);
            return roller;
        }
    }

    public String critParser(int result){
        String roll = "";
        if(result==1){
            roll = "Natural 1 (Auto fail)";
        }
        else if(result==20){
            roll = "Natural 20 (Crit)";
        }
        else {
            roll = String.valueOf(result);
        }
        return roll;
    }

    public String advDisGroupListener(){
        RadioGroup advDisRadioGroup = (RadioGroup) getView().findViewById(R.id.advDisadvApplier);
        int selectedButtonId = advDisRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedButton = (RadioButton) advDisRadioGroup.findViewById(selectedButtonId);
        return (String) selectedButton.getText();
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
