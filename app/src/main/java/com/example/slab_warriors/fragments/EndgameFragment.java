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

import com.airbnb.lottie.LottieDrawable;
import com.example.slab_warriors.MenuActivity;
import com.example.slab_warriors.R;
import com.example.slab_warriors.databinding.FragmentEndgameBinding;

public class EndgameFragment extends Fragment {
    private FragmentEndgameBinding binding;
    private int index = 0;
    private int xp;
    private Handler handler = new Handler();
    public EndgameFragment() {}
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEndgameBinding.inflate(inflater, container, false);
        SharedPreferences sharedPref = getContext().getSharedPreferences("winlose", Context.MODE_PRIVATE);
        if (sharedPref.getString("winlose", "lose").equals("lose")) {
            binding.animation.setAnimation(R.raw.cardloading02);
            binding.animation.setRepeatMode(LottieDrawable.REVERSE);
        }
        else {
            binding.animation.setAnimation(R.raw.firework);
            binding.animation.setRepeatMode(LottieDrawable.RESTART);
        }
        return binding.getRoot().getRootView();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.okButton.setEnabled(false);
        this.getView().setFocusableInTouchMode(true);
        this.getView().requestFocus();
        this.getView().setOnKeyListener((v, keyCode, event) -> {
            if(keyCode == KeyEvent.KEYCODE_BACK){
                getActivity().finish();
                return true;
            }
            else return false;
        });
        SharedPreferences sharedPref = getContext().getSharedPreferences("winlose", Context.MODE_PRIVATE);
        if (sharedPref.getString("winlose", "lose").equals("lose")){
            binding.wlTView.setText(getString(R.string.lose));
            xp = 50;
        }
        else{
            binding.wlTView.setText(getString(R.string.win));
            xp = 100;
        }
        new Thread(() -> {
            while (index < xp) {
                index += 1;
                handler.post(new Runnable() {
                    public void run() {
                        binding.endTView.setText(getString(R.string.earned)+index+getString(R.string.xp));
                        if (index == xp) binding.okButton.setEnabled(true);
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
            //if leveled up
            getActivity().finish();
            getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
    }
    @Override public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}