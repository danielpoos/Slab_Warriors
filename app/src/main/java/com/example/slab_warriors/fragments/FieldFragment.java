package com.example.slab_warriors.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FieldFragment extends Fragment {
    private FragmentFieldBinding binding;
    private CardAdapter cardAdapter;
    private List<Card> cardList;
    private CardAdapter fieldedCardAdapter;
    private List<Card> fieldedCardList;
    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreference;
    private SharedPreferences.Editor editor;
    private int placeableCards = 0;
    private int maximumCardsOnField = 0;
    private int enemyGetAttacked = 0;
    private Enemy boss;
    private Fighter fighter;
    private boolean fighterSelected = false;
    private boolean fighterAttacked = false;
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
        fieldedCardList = new LinkedList<>();
        fieldedCardAdapter = new CardAdapter(getContext(),fieldedCardList);
        binding.fieldedCards.setAdapter(fieldedCardAdapter);
        initiateCards();
        initiateEnemy();
        initiateFighter();
        binding.fighterLayout.setOnClickListener(v-> {
            fighterSelected = true;
        });
        binding.fabFighterInfo.setOnClickListener(v-> {
            if (binding.howToPlay.getVisibility() == View.VISIBLE){
                binding.howToPlay.setVisibility(View.GONE);
            }
            else binding.howToPlay.setVisibility(View.VISIBLE);
        });
        binding.howToPlay.setOnClickListener(v-> {
            binding.howToPlay.setVisibility(View.GONE);
        });
        binding.enemyLayout.setOnClickListener(v-> interactWithEnemy());
        binding.fieldLayout.setOnClickListener(v-> interactWithField());
        binding.placeCard.setOnClickListener(v-> interactWithField());
        binding.fabNext.setOnClickListener(v->{
            attackRandomCard();
            fighterAttacked = false;
            placeableCards = 0;
            enemyGetAttacked = 0;
        });
    }
    private void initiateCards() {
        RequestTask cardTask = new RequestTask(getContext().getString(R.string.baseurl)+"/api/cards","get");
        cardTask.execute();
        cardTask.setFinalTask(() -> {
            cardList = Card.getCards(cardTask.response.getContent());
            cardAdapter = new CardAdapter(getContext(),cardList);
            binding.deckRView.setAdapter(cardAdapter);
        });
    }
    private void initiateEnemy() {
        RequestTask enemyTask = new RequestTask(getContext().getString(R.string.baseurl)+"/api/enemy","get");
        enemyTask.execute();
        enemyTask.setFinalTask(() -> {
            Enemy.getEnemies(enemyTask.response.getContent());
            boss = Enemy.getRandomEnemy(1);
            binding.enemyName.setText(boss.getName()+getString(R.string.level)+boss.getLevel()+".");
            binding.enemyHealth.setMax(boss.getHp());
            binding.enemyHealth.setProgress(boss.getHp());
            switch (boss.getName()){
                case "Yargol":binding.enemyImage.setImageDrawable(AppCompatResources.getDrawable(getContext(), R.drawable.yargol));break;
                case "": break;
            }
        });
    }
    private void initiateFighter() {
        sharedPreferences = getActivity().getSharedPreferences("fighter", Context.MODE_PRIVATE);
        String fighterString = sharedPreferences.getString("fighter", "No fighter");
        fighter = Fighter.toFighter(fighterString);
        binding.fighterName.setText(fighter.getName()+getString(R.string.level)+fighter.getLevel()+".");
        binding.fighterHealth.setText(String.valueOf(fighter.getHp()));
        binding.fighterAttack.setText(String.valueOf(fighter.getAttack()));
        switch (fighter.getName()){
            case "Don":
                binding.fighterImage.setImageDrawable(AppCompatResources.getDrawable(getContext(), R.drawable.don));break;
            case "Karl":
                binding.fighterImage.setImageDrawable(AppCompatResources.getDrawable(getContext(), R.drawable.karl));break;
            case "Shean":
                binding.fighterImage.setImageDrawable(AppCompatResources.getDrawable(getContext(), R.drawable.shean));break;
            case "Victor":
                binding.fighterImage.setImageDrawable(AppCompatResources.getDrawable(getContext(), R.drawable.victor));break;
        }
    }
    private void interactWithField() {
        if (fighterSelected) fighterSelected = false;
        if (cardAdapter.cardPosition != -1){
            placeableCards++;
            binding.noCardTView.setVisibility(View.GONE);
            binding.fieldedCards.setVisibility(View.VISIBLE);
            binding.placeCard.setVisibility(View.VISIBLE);
            if (placeableCards <4 && maximumCardsOnField <7){
                Card selectedCard = cardAdapter.cards.get(cardAdapter.cardPosition);
                Card sc = new Card(selectedCard.getName(), selectedCard.getType(), selectedCard.getDetails(), selectedCard.getLevel(), selectedCard.getAttack(), selectedCard.getHp());
                fieldedCardAdapter.addCard(sc);
                maximumCardsOnField = fieldedCardList.size();
            }
            else Toast.makeText(getContext(), getString(R.string.cards_left) + " "+maximumCardsOnField, Toast.LENGTH_SHORT).show();
            cardAdapter.cardPosition = -1;
        }
        else if (binding.noCardTView.getVisibility() == View.VISIBLE){
            Toast.makeText(getContext(), getString(R.string.no_card), Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getContext(), getString(R.string.no_card_selected), Toast.LENGTH_SHORT).show();
    }
    private void interactWithEnemy() {
        if(fighterSelected && !fighterAttacked){
            boss.setHp(boss.getHp()-fighter.getAttack());
            binding.enemyImage.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake));
            theBossHasNoHp();
            fighterSelected = false;
            fighterAttacked = true;
        }else if(fighterSelected && fighterAttacked){
            Toast.makeText(getContext(), getString(R.string.already_attacked), Toast.LENGTH_SHORT).show();
            fighterSelected = false;
        }else if(enemyGetAttacked>2){
            Toast.makeText(getContext(), getString(R.string.cant_attack), Toast.LENGTH_SHORT).show();
        }else if(fieldedCardAdapter.cardPosition != -1){
            enemyGetAttacked++;
            boss.setHp(boss.getHp()-fieldedCardList.get(fieldedCardAdapter.cardPosition).getAttack());
            binding.enemyImage.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake));
            theBossHasNoHp();
            fieldedCardAdapter.cardPosition = -1;
            //animation
        }else if (cardAdapter.cardPosition != -1){
            cardAdapter.cardPosition = -1;
            Toast.makeText(getContext(), getString(R.string.no_card_selected), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(), getString(R.string.cant_attack), Toast.LENGTH_SHORT).show();
        }
    }
    private void theBossHasNoHp() {
        if (boss.getHp() <= 0){
            boss.setHp(0);
            sharedPreference = getContext().getSharedPreferences("winlose", Context.MODE_PRIVATE);
            editor = sharedPreference.edit();
            editor.putString("winlose", "win");
            editor.commit();
            binding.enemyHealth.setProgress(0);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            NavHostFragment.findNavController(FieldFragment.this).navigate(R.id.toTheEndgameFragment);
        }
        binding.enemyHealth.setProgress(boss.getHp());
    }
    private void attackTheFighter(){
        binding.fighterImage.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake));
        fighter.setHp(fighter.getHp()-boss.getAttack());
        binding.fighterHealth.setText(fighter.getHp()+"");
        if (fighter.getHp() <= 0){
            fighter.setHp(0);
            sharedPreference = getContext().getSharedPreferences("winlose", Context.MODE_PRIVATE);
            editor = sharedPreference.edit();
            editor.putString("winlose", "lose");
            editor.commit();
            binding.fighterHealth.setText("0");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            NavHostFragment.findNavController(FieldFragment.this).navigate(R.id.toTheEndgameFragment);
        }
    }
    private void attackRandomCard(){
        if (fieldedCardList.size() == 0) {
            attackTheFighter();
            return;
        }
        Random r = new Random();
        int randomInt = r.nextInt(fieldedCardList.size());
        Card attackedCard = fieldedCardList.get(randomInt);
        attackedCard.setHp(attackedCard.getHp()-boss.getAttack());
        fieldedCardAdapter.notifyItemChanged(randomInt);
        if (attackedCard.getHp()<0){
            try {
                Thread.sleep(650);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fieldedCardAdapter.removeCard(randomInt);
            maximumCardsOnField = fieldedCardList.size();
        }
        if (fieldedCardAdapter.cards.size() == 0){
            binding.noCardTView.setVisibility(View.VISIBLE);
            binding.fieldedCards.setVisibility(View.GONE);
            binding.placeCard.setVisibility(View.GONE);
        }
    }
    @Override public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
