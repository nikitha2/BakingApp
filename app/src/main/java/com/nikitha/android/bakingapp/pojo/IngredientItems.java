package com.nikitha.android.bakingapp.pojo;

import java.io.Serializable;

import androidx.room.PrimaryKey;

public class IngredientItems implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int id;
    int quantity;
    String measure;
    String ingredient;

    public IngredientItems(int quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
