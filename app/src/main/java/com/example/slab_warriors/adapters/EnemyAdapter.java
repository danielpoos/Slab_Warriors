package com.example.slab_warriors.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.slab_warriors.R;
import com.example.slab_warriors.data.Enemy;
import java.util.List;

public class EnemyAdapter extends RecyclerView.Adapter<EnemyAdapter.EnemyHolder>{
    public Context context;
    public List<Enemy> enemies;
    public int enemyPosition = -1;
    public EnemyAdapter(Context context,List<Enemy> enemies) {
        this.context = context;
        this.enemies = enemies;
    }
    @NonNull @Override public EnemyAdapter.EnemyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_enemy,viewGroup,false);
        return new EnemyAdapter.EnemyHolder(view);
    }
    @Override public void onBindViewHolder(@NonNull EnemyAdapter.EnemyHolder enemyHolder, int i) {
        i = enemyHolder.getAdapterPosition();
        Enemy sir = enemies.get(i);
        enemyHolder.name.setText(sir.getName());
        enemyHolder.health.setProgress(sir.getHp());
        enemyHolder.itemView.setOnClickListener(v -> enemyPosition = enemyHolder.getAdapterPosition());
    }
    @Override public int getItemCount() {
        return enemies.size();
    }
    public class EnemyHolder extends RecyclerView.ViewHolder {
        TextView name;
        ProgressBar health;
        ImageView image;
        public EnemyHolder(@NonNull View item) {
            super(item);
            name = item.findViewById(R.id.enemyName);
            health = item.findViewById(R.id.enemyHealth);
            image = item.findViewById(R.id.enemyImage);
        }
    }
}