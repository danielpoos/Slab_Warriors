package com.example.slab_warriors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import com.example.slab_warriors.adapters.CardAdapter;
import com.example.slab_warriors.adapters.EnemyAdapter;
import com.example.slab_warriors.adapters.FighterAdapter;
import com.example.slab_warriors.api.RequestTask;
import com.example.slab_warriors.data.Card;
import com.example.slab_warriors.data.Enemy;
import com.example.slab_warriors.data.Fighter;
import com.example.slab_warriors.databinding.ActivityCharactersBinding;
import java.util.List;

public class CharactersActivity extends AppCompatActivity {
    private ActivityCharactersBinding binding;
    private CardAdapter cardAdapter;
    private EnemyAdapter enemyAdapter;
    private FighterAdapter fighterAdapter;
    private List<Card> cardList;
    private List<Enemy> enemyList;
    private List<Fighter> fighterList;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCharactersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.cardRView.setVisibility(View.VISIBLE);
        binding.enemyRView.setVisibility(View.GONE);
        binding.fighterRView.setVisibility(View.GONE);
        GridLayoutManager cardLayoutManager = new GridLayoutManager(this,3);
        cardLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.cardRView.setLayoutManager(cardLayoutManager);
        LinearLayoutManager enemyLayoutManager = new LinearLayoutManager(this);
        enemyLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.enemyRView.setLayoutManager(enemyLayoutManager);
        GridLayoutManager fighterLayoutManager = new GridLayoutManager(this,2);
        fighterLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.fighterRView.setLayoutManager(fighterLayoutManager);
        binding.cardsButton.setOnClickListener(v -> {
            binding.cardRView.setVisibility(View.VISIBLE);
            binding.enemyRView.setVisibility(View.GONE);
            binding.fighterRView.setVisibility(View.GONE);
        });
        binding.enemiesButton.setOnClickListener(v -> {
            binding.cardRView.setVisibility(View.GONE);
            binding.enemyRView.setVisibility(View.VISIBLE);
            binding.fighterRView.setVisibility(View.GONE);
        });
        binding.fightersButton.setOnClickListener(v -> {
            binding.cardRView.setVisibility(View.GONE);
            binding.enemyRView.setVisibility(View.GONE);
            binding.fighterRView.setVisibility(View.VISIBLE);
        });
        RequestTask cardTask = new RequestTask(getString(R.string.baseurl)+"/api/cards","get");
        cardTask.execute();
        cardTask.setFinalTask(() -> {
            cardList = Card.getCards(cardTask.response.getContent());
            cardAdapter = new CardAdapter(this,cardList);
            binding.cardRView.setAdapter(cardAdapter);
        });
        RequestTask enemyTask = new RequestTask(getString(R.string.baseurl)+"/api/enemy","get");
        enemyTask.execute();
        enemyTask.setFinalTask(() -> {
            enemyList = Enemy.getEnemies(enemyTask.response.getContent());
            enemyAdapter = new EnemyAdapter(this,enemyList);
            binding.enemyRView.setAdapter(enemyAdapter);
        });
        RequestTask fighterTask = new RequestTask(getString(R.string.baseurl)+"/api/fighters","get");
        fighterTask.execute();
        fighterTask.setFinalTask(() -> {
            fighterList = Fighter.getFighters(fighterTask.response.getContent());
            fighterAdapter = new FighterAdapter(this,fighterList);
            binding.fighterRView.setAdapter(fighterAdapter);
        });
    }
}