import * as React from "react";
import TextField from "@mui/material/TextField";
import Autocomplete from "@mui/material/Autocomplete";

export default function SearchMyRecipesBar() {
  return (
    <Autocomplete
      id="recipe-search-bar"
      disableClearable
      options={recipes.map((recipe) => recipe.title)}
      renderInput={(params) => (
        <TextField
          {...params}
          label="Search for Recipes"
          InputProps={{
            ...params.InputProps,
            type: "search",
          }}
        />
      )}
    />
  );
}

// Test recipes
const recipes = [
    { title: "Chicken Rice", ingredients: ["chicken", "rice"] },
    {
      title: "Spaghetti and Meatballs",
      ingredients: ["spaghetti", "meatballs", "sauce"],
    },
    {
      title: "Vanilla Ice Cream",
      ingredients: ["milk", "sugar", "vanilla extract"],
    },
    { title: "Quesadilla", ingredients: ["tortilla", "cheese"] },
    {
      title: "Korean Fried Chicken",
      ingredients: ["chicken", "flour", "gochujang"],
    },
    {
      title: "Omelette",
      ingredients: ["egg", "spinach", "tomato"],
    },
  ];