package com.kris.lm.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kris.lm.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExceFragment extends Fragment {

    public ExceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_excersise, container, false);
    }

}
