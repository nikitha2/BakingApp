package com.nikitha.android.bakingapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nikitha.android.bakingapp.pojo.IngredientItems;
import com.nikitha.android.bakingapp.pojo.ListItems;
import com.nikitha.android.bakingapp.pojo.StepItems;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdaptor extends RecyclerView.Adapter {
    ListItemClickListener mClickListener;
    Context context;
     ArrayList<ListItems> items;
        int i=0;
        public MainAdaptor(Context context,ArrayList<ListItems> items, ListItemClickListener mClickListener) {
            this.context=context;
            this.items=items;
            this.mClickListener=mClickListener;
        }

    public void setData(List<ListItems> data) {
        items.clear();
        items.addAll(data);
        notifyDataSetChanged();
    }

    public interface ListItemClickListener{
            void onListItemClick(int position);
        }
        public  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            // each data item is just a string in this case
            public View v;

            public MyViewHolder(View v) {
                super(v);
                this.v = v;
                v.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int position=getAdapterPosition();
                mClickListener.onListItemClick(position);
            }
        }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipie_card_layout, parent, false);
        v.setBackgroundResource(R.color.colorAccent);
        MyViewHolder myViewHolder=new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListItems current = items.get(position);
        TextView textView=(TextView) holder.itemView.findViewById(R.id.title);
        textView.setText(current.getName());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
