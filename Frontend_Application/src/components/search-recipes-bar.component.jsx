import * as React from "react";
import TextField from "@mui/material/TextField";
import Autocomplete from "@mui/material/Autocomplete";
import axios from "axios";
import { useEffect, useState } from "react";

export default function SearchRecipesBar(props) {
  // on load, get all recipes names from the backend
  const [recipes, setRecipes] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/v0/recipes/names"
        );
        const recipeArray = response.data;
        recipeArray.push(...testRecipes)

        setRecipes(response.data); // Assuming the response data is an array
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();

    console.log(recipes);
  }, []);

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
      options={recipes}
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
const testRecipes = [
  "Chicken Rice",
  "Spaghetti and Meatballs",
  "Vanilla Ice Cream",
  "Quesadilla",
  "Korean Fried Chicken",
  "Omelette",
  "Miso soup",
  "Sushi",
  "Ramen",
  "Risotto",
];
