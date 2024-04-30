import { useEffect, useState } from "react";
import RecipeCard from "./recipe-card.component.jsx";
import { Stack } from "@mui/material";

function MyRecipesCardContainer({userInfo, recipes}) {

  return (
    <Stack direction="row" spacing={2} useFlexGap flexWrap="wrap">
      {recipes.map((recipe) => (
        <RecipeCard key={recipe.recipeId} recipe={recipe} userInfo={userInfo} />
      ))}
    </Stack>
  );
}

export default MyRecipesCardContainer;
