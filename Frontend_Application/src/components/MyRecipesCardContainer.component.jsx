import { useEffect, useState } from "react";
import RecipeCard from "./recipe-card.component.jsx";
import { Stack } from "@mui/material";

function MyRecipesCardContainer(props) {
  const [myRecipes, setMyRecipes] = useState([]);

  useEffect(() => {
    console.log("Fetching recipes...");
    const fetchRecipes = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/v0/recipes/");
        const data = await response.json();
        setMyRecipes(data);
        console.log(data);
      } catch (error) {
        console.error("Error fetching recipes:", error);
      }
    };

    fetchRecipes();
  }, []);
  return (
    <Stack direction="row" spacing={2} useFlexGap flexWrap="wrap">
      {myRecipes.map((recipe) => (
        <RecipeCard key={recipe.recipeId} recipe={recipe} />
      ))}
    </Stack>
  );
}

export default MyRecipesCardContainer;
