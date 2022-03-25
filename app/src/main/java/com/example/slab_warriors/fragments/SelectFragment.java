package com.example.slab_warriors.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.slab_warriors.MenuActivity;
import com.example.slab_warriors.R;
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
    @Override public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentSelectBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.fighteRView.setHasFixedSize(true);
        getTestItemsIntoList();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.fighteRView.setLayoutManager(layoutManager);
        fighterAdapter = new FighterAdapter(getContext(),fighterList);
        binding.fighteRView.setAdapter(fighterAdapter);
        binding.buttonFirst.setOnClickListener(view1 -> {
                if(position > 0){
                    NavHostFragment.findNavController(SelectFragment.this).navigate(R.id.toTheFieldFragment);
                }else {
                    Toast.makeText(this.getContext(), R.string.no_selected, Toast.LENGTH_SHORT).show();
                }
        });
        binding.fab.setOnClickListener(v -> Snackbar.make(v, R.string.choose, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_BACK){
                    Toast.makeText(getContext(), "Valami", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), MenuActivity.class));
                    return true;
                }
                return false;
            }
        });
    }
    private void getTestItemsIntoList() {
        fighterList = new ArrayList<>();
        fighterList.add(new Fighter("a","a","a",1,2,3));
        fighterList.add(new Fighter("a","a","a",1,2,3));
        fighterList.add(new Fighter("a","a","a",1,2,3));
    }
    @Override
    public void onResume() {
        super.onResume();
        handler = new Handler();
        handler.postDelayed(new Runnable(){
            public void run(){
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }
    @Override
    public void onPause() {
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
            position = fighterHolder.getAdapterPosition();
            fighterHolder.itemView.setOnClickListener(v->Toast.makeText(context, "Position"+i, Toast.LENGTH_SHORT).show());
        }
        @Override public int getItemCount() {
            return fighters.size();
        }
        public class FighterHolder extends RecyclerView.ViewHolder {
            public FighterHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }

}