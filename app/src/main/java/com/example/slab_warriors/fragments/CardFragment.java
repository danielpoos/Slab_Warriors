package com.example.slab_warriors.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.slab_warriors.databinding.FragmentCardBinding;

public class CardFragment extends Fragment {
    private FragmentCardBinding binding;
    public CardFragment() {}
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentCardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}