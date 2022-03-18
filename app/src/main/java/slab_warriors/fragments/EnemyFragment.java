package slab_warriors.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import slab_warriors.databinding.FragmentEnemyBinding;

public class EnemyFragment extends Fragment {
    private FragmentEnemyBinding binding;
    public EnemyFragment() {}
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentEnemyBinding.inflate(inflater, container, false);
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