package com.example.slab_warriors.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.slab_warriors.R;
import com.example.slab_warriors.data.Card;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder>{
    public Context context;
    public List<Card> cards;
    public int cardPosition = -1;
    public CardAdapter(Context context,List<Card> cards) {
        this.context = context;
        this.cards = cards;
    }
    @NonNull
    @Override public CardAdapter.CardHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_fighter,viewGroup,false);
        return new CardAdapter.CardHolder(view);
    }
    @Override public void onBindViewHolder(@NonNull CardAdapter.CardHolder cardHolder, int i) {
        i = cardHolder.getAdapterPosition();
        Card sir = cards.get(i);
        cardHolder.name.setText(sir.getName());
        cardHolder.attack.setText(String.valueOf(sir.getAttack()));
        cardHolder.health.setText(String.valueOf(sir.getHp()));
        cardHolder.itemView.setOnClickListener(v -> cardPosition = cardHolder.getAdapterPosition());
    }
    @Override public int getItemCount() {
        return cards.size();
    }
    public class CardHolder extends RecyclerView.ViewHolder {
        TextView name, attack, health;
        public CardHolder(@NonNull View item) {
            super(item);
            name = item.findViewById(R.id.fighterName);
            attack = item.findViewById(R.id.fighterAttack);
            health = item.findViewById(R.id.fighterHealth);
        }
    }
}
