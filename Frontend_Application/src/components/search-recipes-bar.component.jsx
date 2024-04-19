import * as React from "react";
import TextField from "@mui/material/TextField";
import Autocomplete from "@mui/material/Autocomplete";
import axios from "axios";

export default function SearchRecipesBar(props) {
  const setSearchedRecipes = props.setSearchedRecipes;

  const handleSearch = (event) => {
    console.log(event.target.value);
    axios
      .get("http://localhost:8080/api/v0/recipes/search/" + event.target.value)
      .then((response) => {
        response.data;
        console.log(response.data);
      })
      .then((data) => setSearchedRecipes(data))
      .catch((error) => console.log(error));
  };

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
            onKeyPress: (event) => {
              if (event.key === "Enter") {
                handleSearch(event);
              }
            },
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
