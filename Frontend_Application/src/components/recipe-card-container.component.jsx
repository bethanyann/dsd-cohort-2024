import RecipeCard from "./recipe-card.component.jsx";
import { Stack } from "@mui/material";

function RecipeCardContainer({userInfo, recipe}) {
  return (
    <Stack direction="row" spacing={2}>
      {dbMock.map((recipe) => (
        <RecipeCard key={recipe.recipeId} recipe={recipe} type={recipe.type} />
      ))}
    </Stack>
  );
}

export default RecipeCardContainer;
