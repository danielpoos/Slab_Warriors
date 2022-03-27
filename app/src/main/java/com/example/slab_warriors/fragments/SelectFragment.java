package com.example.slab_warriors.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.slab_warriors.MenuActivity;
import com.example.slab_warriors.R;
import com.example.slab_warriors.api.RequestTask;
import com.example.slab_warriors.data.Fighter;
import com.example.slab_warriors.databinding.FragmentSelectBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class SelectFragment extends Fragment{
    public FragmentSelectBinding binding;
    private Handler handler;
    private FighterAdapter fighterAdapter;
    private List<Fighter> fighterList;
    int position = -1;
    private final String url = "http://192.168.1.94:8000/api/fighters";
    @Override public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentSelectBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fighterList = new ArrayList<>();
        binding.fighteRView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.fighteRView.setLayoutManager(layoutManager);
        RequestTask task = new RequestTask(url,"get");
        task.execute();
        task.setFinalTask(() -> {
            fighterList.add(new Fighter("a","a","a", 1,1,1));
            //fighterList = Fighter.getFighters(task.response.getContent());
            fighterAdapter = new FighterAdapter(getContext(),fighterList);
            binding.fighteRView.setAdapter(fighterAdapter);
        });
        /**/
        binding.buttonFirst.setOnClickListener(view1 -> {
            if(position == -1){
                Toast.makeText(this.getContext(), R.string.no_selected, Toast.LENGTH_SHORT).show();
            }
            NavHostFragment.findNavController(SelectFragment.this).navigate(R.id.toTheFieldFragment);
            Fighter currentFighter = fighterList.get(position-1);
        });
        binding.fab.setOnClickListener(v -> Snackbar.make(v, R.string.choose, Snackbar.LENGTH_LONG).setAction("Action", null).show());
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_BACK){
                    Toast.makeText(getContext(), "Valami", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), MenuActivity.class));
                    return true;
                }
                return false;
            }
        });
    }
    @Override public void onResume() {
        super.onResume();
        handler = new Handler();
        handler.postDelayed(new Runnable(){
            public void run(){
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }
    @Override public void onPause() {
        handler.removeCallbacksAndMessages(null);
        super.onPause();
    }
    @Override public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private class FighterAdapter extends RecyclerView.Adapter<FighterAdapter.FighterHolder>{
        public Context context;
        public List<Fighter> fighters;
        public FighterAdapter(Context context,List<Fighter> fighters) {
            this.context = context;
            this.fighters = fighters;
        }
        @NonNull @Override public FighterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.fragment_fighter,viewGroup,false);
            return new FighterHolder(view);
        }
        @Override public void onBindViewHolder(@NonNull FighterHolder fighterHolder, int i) {
            i = fighterHolder.getAdapterPosition();
            Fighter sir = fighters.get(i);
            fighterHolder.name.setText(sir.getName());
            fighterHolder.attack.setText(String.valueOf(sir.getAttack()));
            fighterHolder.health.setText(String.valueOf(sir.getHp()));
            fighterHolder.itemView.setOnClickListener(v -> position = fighterHolder.getAdapterPosition());
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
}