package com.gasaver.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gasaver.databinding.FragmentSavedSearchesBinding;

//import com.e.gasserviceapp.fragment.databinding.FragmentSavedSearchesBinding;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SavedSearchesFragment extends Fragment {



    private FragmentSavedSearchesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentSavedSearchesBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


}