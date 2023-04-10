package com.gasaver.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gasaver.databinding.FragmentMyResponsesBinding;

//import com.e.gasserviceapp.fragment.databinding.FragmentMyResponsesBinding;


public class MyResponsesFragment extends Fragment {

    RecyclerView rv_my_responses;
    LinearLayout ll_no_data;
    int SELECTED_POS;

    public static Fragment getInstance(int position) {
        MyResponsesFragment myResponsesFragment = new MyResponsesFragment();
        Bundle args = new Bundle();
        args.putInt("SELECTED_POS", position);
        myResponsesFragment.setArguments(args);
        return myResponsesFragment;
    }



    private FragmentMyResponsesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentMyResponsesBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }










}