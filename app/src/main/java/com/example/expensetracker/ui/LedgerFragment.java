package com.example.expensetracker.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetracker.R;

public class LedgerFragment extends Fragment {

    private LedgerViewModel viewModel;
    private LedgerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ledger, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewLedger);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new LedgerAdapter();
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LedgerViewModel.class);
        viewModel.getAllLedgers().observe(getViewLifecycleOwner(), ledgers -> {
            if (adapter != null) {
                adapter.setLedgers(ledgers);
            }
        });
    }
}
