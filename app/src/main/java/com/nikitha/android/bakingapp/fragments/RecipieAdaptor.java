package com.nikitha.android.bakingapp.fragments;

import android.app.Notification;
import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.nikitha.android.bakingapp.R;
import com.nikitha.android.bakingapp.pojo.IngredientItems;
import com.nikitha.android.bakingapp.pojo.ListItems;
import com.nikitha.android.bakingapp.pojo.StepItems;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipieAdaptor extends RecyclerView.Adapter {
    Context context;
     ListItemClickListener mClickListener;
    ArrayList<StepItems> items;
        int i=0;
        public RecipieAdaptor(Context context, ArrayList<StepItems> items, ListItemClickListener mClickListener) {
            this.context=context;
            this.items=items;
            this.mClickListener=mClickListener;
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipie_listitem_layout, parent, false);
        // v.setBackgroundColor(Color.parseColor("#80008"+i));i+=1;
        TextView sampleText = (TextView) v.findViewById(R.id.recipie_item);
       // sampleText.setText("ViewHolder: "+i);i++;
        v.setBackgroundResource(R.color.colorAccent);
        MyViewHolder myViewHolder=new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        StepItems current = items.get(position);
        TextView textView=(TextView) holder.itemView.findViewById(R.id.recipie_item);

        String desc = current.getShortDescription();
        int stepNum = current.getId();
        if(position==0) {
            String heading = context.getText(R.string.Ingredients).toString();
             textView.setText(heading + "\n" + desc);
        }
        else
            textView.setText(String.valueOf(position)+".\t" +desc);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(ArrayList<StepItems>  data) {
        items.clear();
        items.addAll(data);
        notifyDataSetChanged();
    }
}
