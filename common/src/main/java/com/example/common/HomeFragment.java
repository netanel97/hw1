package com.example.common;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.common.databinding.FragmentMainBinding;


public class HomeFragment extends Fragment {

    private FragmentMainBinding binding;

    private HomeRecyclerViewAdapter mAdapter;
    private RecyclerView resultRV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        resultRV = binding.homeFragmentLSTTextList; // Connect between xml to Recyclerview

        return view;
    }
}