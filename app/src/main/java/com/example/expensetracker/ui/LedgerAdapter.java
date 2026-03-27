package com.example.expensetracker.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetracker.R;
import com.example.expensetracker.data.entity.ContactLedger;

import java.util.ArrayList;
import java.util.List;

public class LedgerAdapter extends RecyclerView.Adapter<LedgerAdapter.LedgerViewHolder> {
    private List<ContactLedger> ledgers = new ArrayList<>();

    public void setLedgers(List<ContactLedger> ledgers) {
        this.ledgers = ledgers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LedgerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ledger, parent, false);
        return new LedgerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LedgerViewHolder holder, int position) {
        ContactLedger ledger = ledgers.get(position);
        holder.tvContactName.setText(ledger.name != null ? ledger.name : "Unknown Contact");
        
        double balance = ledger.totalReceived - ledger.totalGiven;
        if (balance >= 0) {
            holder.tvBalance.setText("+ ₹" + String.format("%.2f", balance));
            holder.tvBalance.setTextColor(0xFF388E3C); // Green
        } else {
            holder.tvBalance.setText("- ₹" + String.format("%.2f", Math.abs(balance)));
            holder.tvBalance.setTextColor(0xFFD32F2F); // Red
        }
    }

    @Override
    public int getItemCount() {
        return ledgers.size();
    }

    static class LedgerViewHolder extends RecyclerView.ViewHolder {
        TextView tvContactName, tvBalance;

        public LedgerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContactName = itemView.findViewById(R.id.tvContactName);
            tvBalance = itemView.findViewById(R.id.tvBalance);
        }
    }
}
