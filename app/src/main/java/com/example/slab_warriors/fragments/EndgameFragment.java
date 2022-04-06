package com.example.slab_warriors.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.slab_warriors.MenuActivity;
import com.example.slab_warriors.R;
import com.example.slab_warriors.databinding.FragmentEndgameBinding;

public class EndgameFragment extends Fragment {
    private FragmentEndgameBinding binding;
    private final int earnedXP = 100;
    public EndgameFragment() {}
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEndgameBinding.inflate(inflater, container, false);
        return binding.getRoot().getRootView();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.getView().setFocusableInTouchMode(true);
        this.getView().requestFocus();
        this.getView().setOnKeyListener((v, keyCode, event) -> {
            if(keyCode == KeyEvent.KEYCODE_BACK){
                getActivity().finish();
                return true;
            }
            else return false;
        });
        //binding.wlTView.setText(R.string.win + R.string.lose);
        binding.endTView.setText(getString(R.string.earned)+earnedXP+getString(R.string.xp));
        binding.okButton.setOnClickListener(v-> startActivity(new Intent(this.getActivity(), MenuActivity.class)));
    }
    @Override public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}