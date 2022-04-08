package com.example.slab_warriors.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.slab_warriors.R;
import com.example.slab_warriors.data.Fighter;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;

public class FighterAdapter extends RecyclerView.Adapter<FighterAdapter.FighterHolder>{
    public Context context;
    public List<Fighter> fighters;
    public int fighterPosition = -1;
    public boolean selected = false;
    public FighterAdapter(Context context,List<Fighter> fighters) {
        this.context = context;
        this.fighters = fighters;
    }
    @NonNull @Override public FighterAdapter.FighterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_fighter,viewGroup,false);
        return new FighterAdapter.FighterHolder(view);
    }
    @Override public void onBindViewHolder(@NonNull FighterAdapter.FighterHolder fighterHolder, int i) {
        i = fighterHolder.getAdapterPosition();
        Fighter sir = fighters.get(i);
        fighterHolder.name.setText(sir.getName());
        fighterHolder.attack.setText(String.valueOf(sir.getAttack()));
        fighterHolder.health.setText(String.valueOf(sir.getHp()));
        fighterHolder.itemView.setOnClickListener(v -> {
            selected = !selected;
            if (selected) {
                fighterPosition = fighterHolder.getAdapterPosition();
                Snackbar.make(v,fighters.get(fighterPosition).getDetails(), Snackbar.LENGTH_LONG).show();
            }else fighterPosition = -1;
        });
    }
    @Override public int getItemCount() {
        return fighters.size();
    }
    public class FighterHolder extends RecyclerView.ViewHolder {
        TextView name, attack, health;
        public FighterHolder(@NonNull View item) {
            super(item);
            name = item.findViewById(R.id.fighterName);
            attack = item.findViewById(R.id.fighterAttack);
            health = item.findViewById(R.id.fighterHealth);
        }
    }
}
