import { useEffect, useState } from "react";
import RecipeCard from "./recipe-card.component.jsx";
import { Stack } from "@mui/material";

function MyRecipesCardContainer(props) {
  const userInfo = props.userInfo;
  const recipes = props.recipes;

  return (
    <Stack direction="row" spacing={2} useFlexGap flexWrap="wrap">
      {recipes.map((recipe) => (
        <RecipeCard key={recipe.recipeId} recipe={recipe} type={props.type} userInfo={userInfo} />
      ))}
    </Stack>
  );
}

export default MyRecipesCardContainer;
