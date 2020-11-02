package com.nikitha.android.bakingapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import com.nikitha.android.bakingapp.R;
import com.nikitha.android.bakingapp.RecipieActivity;
import com.nikitha.android.bakingapp.RecipieDetailsActivity;
import com.nikitha.android.bakingapp.pojo.IngredientItems;
import com.nikitha.android.bakingapp.pojo.ListItems;
import com.nikitha.android.bakingapp.pojo.StepItems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.nikitha.android.bakingapp.CONSTANTS.EMPTY_STRING;
import static com.nikitha.android.bakingapp.CONSTANTS.POSITION_CLICKED;
import static com.nikitha.android.bakingapp.CONSTANTS.RECIPIE_BY_NAME;
import static com.nikitha.android.bakingapp.CONSTANTS.SINGLE_PANE;

public class RecipieFragment extends Fragment implements RecipieAdaptor.ListItemClickListener {
    RecipieAdaptor adapter;
    Context context;
    LinearLayoutManager layoutManager;
    RecyclerView recyclerView;
    Activity a;
    View rootview;
    Bundle bundle;
    ListItems listItems;
    OnRecipieStepClickListener mcallback;
    int positionClicked=0;
    ConstraintLayout singlePane_recipie;
    String isSinglePane;

    public interface OnRecipieStepClickListener{
        void OnRecipieStepSelected(int position);
    }
    public RecipieFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;

        mcallback=(OnRecipieStepClickListener) context;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview= inflater.inflate(R.layout.recipie_fragment, container, false);
        //context=container.getContext();
        bundle = getArguments();
        return rootview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new RecipieAdaptor(getContext(),new ArrayList<StepItems>(), RecipieFragment.this);
        layoutManager = new LinearLayoutManager(getContext());
        RecyclerView recyclerView=rootview.findViewById(R.id.recipie_frgament);
        singlePane_recipie=rootview.findViewById(R.id.singlePane_recipie);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        listItems = (ListItems) bundle.getSerializable(RECIPIE_BY_NAME);

        StringBuilder sb=new StringBuilder();
        for(IngredientItems i:listItems.getIngredient()){
            String s = String.valueOf(i.getQuantity())+" "+i.getMeasure()+" "+i.getIngredient();
            sb.append(s);
            sb.append("\n");
        }
        ArrayList<StepItems> listOfStepsForRecipie = listItems.getSteps();
        StepItems stepItems=new StepItems(0, sb.toString(), sb.toString(), null, null);
        listOfStepsForRecipie.add(0,stepItems);
        adapter.setData(listOfStepsForRecipie);

        isSinglePane= bundle.getString(SINGLE_PANE);

    }

    @Override
    public void onListItemClick(int position) {
        if(position!=0){
            if(isSinglePane!=null){
                Intent intent=new Intent(context, RecipieDetailsActivity.class);
                intent.putExtra(RECIPIE_BY_NAME,listItems);
                intent.putExtra(POSITION_CLICKED,position);
                startActivity(intent);
            }else{
                mcallback.OnRecipieStepSelected(position);
            }
            positionClicked=position;

        }

    }


}
