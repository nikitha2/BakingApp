package com.nikitha.android.bakingapp.pojo;

import java.io.Serializable;
import java.util.ArrayList;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "RecipieCards")
public class ListItems  implements Serializable{

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private ArrayList<IngredientItems> ingredients;
    private ArrayList<StepItems> steps;


    public ListItems(String name, ArrayList<IngredientItems> ingredients, ArrayList<StepItems> steps) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
    }
    @Ignore
    public ListItems() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<IngredientItems> getIngredient() {
        return ingredients;
    }

    public void setIngredient(ArrayList<IngredientItems> ingredient) {
        this.ingredients = ingredient;
    }

    public ArrayList<StepItems> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<StepItems> steps) {
        this.steps = steps;
    }

    public ArrayList<IngredientItems> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<IngredientItems> ingredients) {
        this.ingredients = ingredients;
    }
}
