package com.example.slab_warriors.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.slab_warriors.MenuActivity;
import com.example.slab_warriors.R;
import com.example.slab_warriors.adapters.FighterAdapter;
import com.example.slab_warriors.api.RequestTask;
import com.example.slab_warriors.data.Fighter;
import com.example.slab_warriors.databinding.FragmentSelectBinding;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;

public class SelectFragment extends Fragment{
    public FragmentSelectBinding binding;
    private FighterAdapter fighterAdapter;
    private List<Fighter> fighterList;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedEditor;
    private final String fighterUrl = "http://192.168.1.94:8000/api/fighters";
    @Override public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentSelectBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fighterList = new ArrayList<>();
        binding.buttonSelect.setEnabled(false);
        binding.fighteRView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.fighteRView.setLayoutManager(layoutManager);
        RequestTask fighterTask = new RequestTask(fighterUrl,"get");
        fighterTask.execute();
        fighterTask.setFinalTask(() -> {
            fighterList = Fighter.getFighters(fighterTask.response.getContent());
            fighterAdapter = new FighterAdapter(getContext(),fighterList);
            binding.fighteRView.setAdapter(fighterAdapter);
            binding.buttonSelect.setEnabled(true);
        });
        binding.buttonSelect.setOnClickListener(view1 -> {
            if(fighterAdapter.fighterPosition == -1 || fighterList.size() == 0){
                Toast.makeText(this.getContext(), R.string.no_selected_fighter, Toast.LENGTH_SHORT).show();
            }else {
                Fighter currentFighter = fighterList.get(fighterAdapter.fighterPosition);
                sharedPreferences = getActivity().getSharedPreferences("fighter", Context.MODE_PRIVATE);
                sharedEditor = sharedPreferences.edit();
                sharedEditor.putString("fighter", currentFighter.toString());
                sharedEditor.commit();
                NavHostFragment.findNavController(SelectFragment.this).navigate(R.id.toTheFieldFragment);
            }
        });
        binding.buttonCancel.setOnClickListener(view1 -> startActivity(new Intent(getActivity(), MenuActivity.class)));
        binding.fab.setOnClickListener(v -> Snackbar.make(v, R.string.choose, Snackbar.LENGTH_LONG).show());
    }
    @Override public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}