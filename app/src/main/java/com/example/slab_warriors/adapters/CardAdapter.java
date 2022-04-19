package com.example.slab_warriors.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;
import com.example.slab_warriors.R;
import com.example.slab_warriors.data.Card;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder>{
    public Context context;
    public List<Card> cards;
    public int cardPosition = -1;
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
        switch (sir.getName()){
            case "Alejandro":cardHolder.image.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.alejandro));
                break;
            case "Catapult":cardHolder.image.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.catapult));
                break;
            case "Cecilia":cardHolder.image.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.cecilia));
                break;
            case "Chloe":cardHolder.image.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.chloe));
                break;
            case "Footman":cardHolder.image.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.footman));
                break;
            case "Francis":cardHolder.image.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.francis));
                break;
            case "John S.":cardHolder.image.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.johns));
                break;
            case "Mad Dog":cardHolder.image.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.maddog));
                break;
        }
        cardHolder.itemView.setOnClickListener(v -> cardPosition = cardHolder.getAdapterPosition());
        cardHolder.itemView.setOnLongClickListener(v->{
            cardPosition = cardHolder.getAdapterPosition();
            Snackbar.make(v,cards.get(cardPosition).getDetails(), Snackbar.LENGTH_SHORT).show();
            cardPosition = -1;
            return false;
        });
    }
    public void removeCard(int pos){
        cards.remove(pos);
        notifyItemRemoved(pos);
        notifyItemRangeRemoved(pos,cards.size());
        notifyItemRangeChanged(pos,cards.size());
    }
    public void addCard(Card card){
        cards.add(card);
        notifyItemInserted(cards.size()-1);
        notifyItemRangeInserted(cards.size()-1,cards.size());
        notifyItemRangeChanged(cards.size()-1,cards.size());
    }
    @Override public int getItemCount() {
        return cards.size();
    }
    public class CardHolder extends RecyclerView.ViewHolder {
        TextView name, attack, health;
        ImageView image;
        public CardHolder(@NonNull View item) {
            super(item);
            name = item.findViewById(R.id.cardName);
            attack = item.findViewById(R.id.cardAttack);
            health = item.findViewById(R.id.cardHealth);
            image = item.findViewById(R.id.cardImage);
        }
    }
}
