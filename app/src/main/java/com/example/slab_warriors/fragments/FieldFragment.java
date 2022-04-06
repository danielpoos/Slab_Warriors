package com.example.slab_warriors.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.slab_warriors.R;
import com.example.slab_warriors.adapters.CardAdapter;
import com.example.slab_warriors.api.RequestTask;
import com.example.slab_warriors.data.Fighter;
import com.example.slab_warriors.data.Card;
import com.example.slab_warriors.data.Enemy;
import com.example.slab_warriors.databinding.FragmentFieldBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class FieldFragment extends Fragment {
    private FragmentFieldBinding binding;
    private CardAdapter cardAdapter;
    private List<Card> cardList;
    private SharedPreferences sharedPreferences;
    private Enemy boss;
    private final String cardUrl = "http://192.168.1.94:8000/api/cards";
    private final String enemyUrl = "http://192.168.1.94:8000/api/enemy";
    //private final String cardUrl = "http://10.4.18.17:8000/api/cards";
    //private final String enemyUrl = "http://10.4.18.17:8000/api/enemy";
    public FieldFragment(){}
    @Override public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentFieldBinding.inflate(inflater, container, false);
        return binding.getRoot();
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
        LinearLayoutManager cardManager = new LinearLayoutManager(getContext());
        cardManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager deckManager = new LinearLayoutManager(getContext());
        deckManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.fieldedCards.setLayoutManager(cardManager);
        binding.deckRView.setLayoutManager(deckManager);
        RequestTask cardTask = new RequestTask(cardUrl,"get");
        cardTask.execute();
        cardTask.setFinalTask(() -> {
            cardList = Card.getCards(cardTask.response.getContent());
            cardAdapter = new CardAdapter(getContext(),cardList);
            binding.deckRView.setAdapter(cardAdapter);
        });
        RequestTask enemyTask = new RequestTask(enemyUrl,"get");
        enemyTask.execute();
        enemyTask.setFinalTask(new Runnable() {
            @Override
            public void run() {
                Enemy.getEnemies(enemyTask.response.getContent());
                boss = Enemy.getRandomEnemy(1);
                binding.enemyName.setText(boss.getName()+getString(R.string.level)+boss.getLevel()+".");
                binding.enemyHealth.setMax(boss.getHp());
                binding.enemyHealth.setProgress(boss.getHp());
            }
        });
        sharedPreferences = getActivity().getSharedPreferences("fighter", Context.MODE_PRIVATE);
        String fighterString = sharedPreferences.getString("fighter", "No fighter");
        Fighter fighter = Fighter.toFighter(fighterString);
        binding.fighterName.setText(fighter.getName()+getString(R.string.level)+fighter.getLevel()+".");
        binding.fighterHealth.setText(String.valueOf(fighter.getHp()));
        binding.fighterAttack.setText(String.valueOf(fighter.getAttack()));
        binding.fighterLayout.setOnClickListener(v-> {
            binding.fabFighterInfo.setVisibility(View.VISIBLE);
            binding.fighterLayout.setVisibility(View.GONE);
        });
        binding.fabFighterInfo.setOnClickListener(v-> {
            binding.fabFighterInfo.setVisibility(View.GONE);
            binding.fighterLayout.setVisibility(View.VISIBLE);
            Snackbar.make(v, fighter.getDetails(), Snackbar.LENGTH_LONG).show();
        });
        binding.enemyLayout.setOnClickListener(v->{});
        //binding.fieldedCards
        //NavHostFragment.findNavController(FieldFragment.this).navigate(R.id.toTheEndgameFragment);
    }
    @Override public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
