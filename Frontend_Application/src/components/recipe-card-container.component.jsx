import RecipeCard from "./recipe-card.component.jsx";
import { Stack } from "@mui/material";

function RecipeCardContainer(props) {
  return (
    <Stack direction="row" spacing={2}>
      {dbMock.map((recipe) => (
        <RecipeCard key={recipe.recipeId} recipe={recipe} type={props.type} />
      ))}
    </Stack>
  );
}

export default RecipeCardContainer;
