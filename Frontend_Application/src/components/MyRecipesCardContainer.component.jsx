import RecipeCard from "./recipe-card.component.jsx";
import { Stack } from "@mui/material";

const dbMyRecipesMock = [
      {
        recipeId: "1",
        name: "Teriyaki Salmon Noodles",
        description: "Noodles and salmon tossed with a traditional four-ingredient Teriyaki Sauce",
        imageUrl: "https://www.recipetineats.com/wp-content/uploads/2022/07/Teriyaki-Salmon-Noodles_1-1.jpg",
        protein: 34,
        fat: 30,
        carbs: 66,
        calories: 683,
      },

      {
        recipeId: "2",
        name: "Teriyaki Salmon Noodles",
        description: "Noodles and salmon tossed with a traditional four-ingredient Teriyaki Sauce",
        imageUrl: "https://www.recipetineats.com/wp-content/uploads/2022/07/Teriyaki-Salmon-Noodles_1-1.jpg",
        protein: 34,
        fat: 30,
        carbs: 66,
        calories: 683,
      },

      {
        recipeId: "3",
        name: "Teriyaki Salmon Noodles",
        description: "Noodles and salmon tossed with a traditional four-ingredient Teriyaki Sauce",
        imageUrl: "https://www.recipetineats.com/wp-content/uploads/2022/07/Teriyaki-Salmon-Noodles_1-1.jpg",
        protein: 34,
        fat: 30,
        carbs: 66,
        calories: 683,
      },

      {
        recipeId: "4",
        name: "Teriyaki Salmon Noodles",
        description: "Noodles and salmon tossed with a traditional four-ingredient Teriyaki Sauce",
        imageUrl: "https://www.recipetineats.com/wp-content/uploads/2022/07/Teriyaki-Salmon-Noodles_1-1.jpg",
        protein: 34,
        fat: 30,
        carbs: 66,
        calories: 683,
      },

      {
        recipeId: "5",
        name: "Teriyaki Salmon Noodles",
        description: "Noodles and salmon tossed with a traditional four-ingredient Teriyaki Sauce",
        imageUrl: "https://www.recipetineats.com/wp-content/uploads/2022/07/Teriyaki-Salmon-Noodles_1-1.jpg",
        protein: 34,
        fat: 30,
        carbs: 66,
        calories: 683,
      },

      {
        recipeId: "6",
        name: "Teriyaki Salmon Noodles",
        description: "Noodles and salmon tossed with a traditional four-ingredient Teriyaki Sauce",
        imageUrl: "https://www.recipetineats.com/wp-content/uploads/2022/07/Teriyaki-Salmon-Noodles_1-1.jpg",
        protein: 34,
        fat: 30,
        carbs: 66,
        calories: 683,
      },
];

function MyRecipesCardContainer(props) {
    return (
      <Stack direction="row" spacing={2} useFlexGap flexWrap="wrap">
        {dbMyRecipesMock.map((recipe) => (
          <RecipeCard key={recipe.recipeId} recipe={recipe} />
        ))}
      </Stack>
    );
  }
  
  export default MyRecipesCardContainer;