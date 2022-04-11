package com.example.slab_warriors.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.slab_warriors.MenuActivity;
import com.example.slab_warriors.R;
import com.example.slab_warriors.databinding.FragmentEndgameBinding;

public class EndgameFragment extends Fragment {
    private FragmentEndgameBinding binding;
    private SharedPreferences sharedPref;
    private int index = 0;
    private Handler handler = new Handler();
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
        sharedPref = getContext().getSharedPreferences("winlose", Context.MODE_PRIVATE);
        binding.wlTView.setText(sharedPref.getString("winlose", "lose").equals("lose")?R.string.lose:R.string.win);
        new Thread(() -> {
            while (index < 100) {
                index += 1;
                handler.post(new Runnable() {
                    public void run() {
                        binding.endTView.setText(getString(R.string.earned)+index+getString(R.string.xp));
                    }
                });
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        binding.okButton.setOnClickListener(v-> {
            Intent backToMenu = new Intent(this.getActivity(), MenuActivity.class);
            startActivity(backToMenu);
        });
    }
    @Override public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}