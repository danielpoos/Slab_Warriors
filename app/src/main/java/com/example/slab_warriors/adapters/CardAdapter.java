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
import com.google.android.material.snackbar.Snackbar;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder>{
    public Context context;
    public List<Card> cards;
    public int cardPosition = -1;
    public boolean selected = false;
    public CardAdapter(Context context,List<Card> cards) {
        this.context = context;
        this.cards = cards;
    }
    @NonNull @Override public CardAdapter.CardHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_card,viewGroup,false);
        return new CardAdapter.CardHolder(view);
    }
    @Override public void onBindViewHolder(@NonNull CardAdapter.CardHolder cardHolder, int i) {
        i = cardHolder.getAdapterPosition();
        Card sir = cards.get(i);
        cardHolder.name.setText(sir.getName());
        cardHolder.attack.setText(String.valueOf(sir.getAttack()));
        cardHolder.health.setText(String.valueOf(sir.getHp()));
        cardHolder.itemView.setOnClickListener(v -> {
            selected = !selected;
            if (selected) cardPosition = cardHolder.getAdapterPosition();
            else cardPosition = -1;
        });
        cardHolder.itemView.setOnLongClickListener(v->{
            cardPosition = cardHolder.getAdapterPosition();
            Snackbar.make(v,cards.get(cardPosition).getDetails(), Snackbar.LENGTH_SHORT).show();
            cardPosition = -1;
            return false;
        });
    }
    public void removeCard(){
        //cards.remove(cardPosition);
        notifyItemRemoved(cardPosition);
        notifyItemRangeChanged(cardPosition,cards.size());
        selected = false;
    }
    public void addCard(Card card){
        selected = false;
        cards.add(card);
        notifyItemInserted(cardPosition);
        notifyItemRangeChanged(cardPosition,cards.size());
    }
    @Override public int getItemCount() {
        return cards.size();
    }
    public class CardHolder extends RecyclerView.ViewHolder {
        TextView name, attack, health;
        public CardHolder(@NonNull View item) {
            super(item);
            name = item.findViewById(R.id.cardName);
            attack = item.findViewById(R.id.cardAttack);
            health = item.findViewById(R.id.cardHealth);
        }
    }
}
