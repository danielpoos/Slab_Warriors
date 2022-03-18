package com.example.slab_warriors.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.slab_warriors.R;
import com.example.slab_warriors.data.Fighter;
import com.example.slab_warriors.databinding.FragmentFighterBinding;
import com.example.slab_warriors.databinding.FragmentSelectBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class SelectFragment extends Fragment {
    public FragmentSelectBinding binding;
    FragmentFighterBinding bindingFighter;
    public List<Fighter> fighters;
    @Override public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentSelectBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonFirst.setOnClickListener(view1 -> NavHostFragment.findNavController(SelectFragment.this)
                .navigate(R.id.toTheFieldFragment));
        binding.fab.setOnClickListener(v -> Snackbar.make(v, R.string.choose, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        //Todo listview -> recyclerview
        binding.fighterListView.getFocusedChild().setOnClickListener(view2 -> binding.fighterListView.getFocusedChild().setBackgroundColor(Color.CYAN));
        binding.fighterListView.setAdapter((ListAdapter) new FighterFragment());
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private class FighterAdapter extends ArrayAdapter<Fighter>{
        public FighterAdapter() {
            super(SelectFragment.this.getContext(),R.layout.fragment_fighter,fighters);
        }
        @Override @NonNull public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            bindingFighter= FragmentFighterBinding.inflate(getLayoutInflater());
            Fighter colleague = fighters.get(position);
            bindingFighter.fighterName.setText(colleague.getName());
            bindingFighter.fighterAttack.setText(colleague.getAttack());
            bindingFighter.fighterHealth.setText(colleague.getHp());
            //bindingFighter.fighterImage.setImageBitmap();
            return bindingFighter.getRoot().getRootView();
        }
    }

}