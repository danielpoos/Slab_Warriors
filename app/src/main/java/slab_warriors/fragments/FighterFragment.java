package slab_warriors.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import slab_warriors.databinding.FragmentFighterBinding;

public class FighterFragment extends Fragment {
    private FragmentFighterBinding binding;
    public FighterFragment() {}
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentFighterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}