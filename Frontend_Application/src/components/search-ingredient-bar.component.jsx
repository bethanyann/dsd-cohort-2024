import * as React from "react";
import TextField from "@mui/material/TextField";
import Autocomplete from "@mui/material/Autocomplete";

export default function SearchIngredientsBar() {
  return (
    <Autocomplete
      id='search-ingredients-bar'
      disableClearable
      options={ingredients.map((ingredient) => ingredient.title)}
      renderInput={(params) => (
        <TextField
          {...params}
          label='Search for Ingredients'
          InputProps={{
            ...params.InputProps,
            type: "search",
          }}
        />
      )}
    />
  );
}

// Test ingredients
const ingredients = [
  { title: "Pork" },
  {
    title: "Lemon",
  },
  {
    title: "BBQ Sauce",
  },
  { title: "Corn" },
  {
    title: "Rice",
  },
  {
    title: "Egg",
  },
];
