package com.example.expensetracker.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetracker.R;
import com.example.expensetracker.data.entity.Category;
import com.example.expensetracker.data.entity.CategorySum;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> categories = new ArrayList<>();
    private List<CategorySum> sums = new ArrayList<>();

    public void setData(List<Category> categories, List<CategorySum> sums) {
        this.categories = categories;
        this.sums = sums;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category cat = categories.get(position);
        holder.tvCategoryName.setText(cat.name != null ? cat.name : "Uncategorized");
        
        double totalSpent = 0.0;
        if (sums != null) {
            for (CategorySum s : sums) {
                if (s.categoryId != null && s.categoryId == cat.id) {
                    totalSpent = s.total;
                    break;
                }
            }
        }
        
        holder.tvCategoryTotal.setText("₹ " + String.format("%.2f", totalSpent));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategoryName, tvCategoryTotal;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
            tvCategoryTotal = itemView.findViewById(R.id.tvCategoryTotal);
        }
    }
}
