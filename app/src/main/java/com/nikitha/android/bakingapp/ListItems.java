package com.nikitha.android.bakingapp;

import java.util.ArrayList;

public class ListItems {
    int id;
    String name;
    ArrayList<IngredientItems> ingredient;
    ArrayList<StepItems> steps;

    public ListItems(int id,String name, ArrayList<IngredientItems> ingredient, ArrayList<StepItems> steps) {
        this.id = id;
        this.name = name;
        this.ingredient = ingredient;
        this.steps = steps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public ListItems() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<IngredientItems> getIngredient() {
        return ingredient;
    }

    public void setIngredient(ArrayList<IngredientItems> ingredient) {
        this.ingredient = ingredient;
    }

    public ArrayList<StepItems> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<StepItems> steps) {
        this.steps = steps;
    }
}
