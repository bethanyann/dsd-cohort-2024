import RecipeCard from "./recipe-card.component.jsx";
import { Stack } from "@mui/material";

const dbMock = [
  {
    recipeId: "1",
    name: "Pulled Pork",
    description: "This is a description",
    imageUrl: "https://www.recipetineats.com/wp-content/uploads/2022/10/12-hour-BBQ-pulled-pork_7-3.jpg?resize=650,813",
    protein: 150,
    fat: 100,
    carbs: 50,
    calories: 1000,
  },

  {
    recipeId: "2",
    name: "Pulled Pork",
    description: "This is a description",
    imageUrl: "https://www.recipetineats.com/wp-content/uploads/2022/10/12-hour-BBQ-pulled-pork_7-3.jpg?resize=650,813",
    protein: 150,
    fat: 100,
    carbs: 50,
    calories: 1000,
  },

  {
    recipeId: "3",
    name: "Pulled Pork",
    description: "This is a description",
    imageUrl: "https://www.recipetineats.com/wp-content/uploads/2022/10/12-hour-BBQ-pulled-pork_7-3.jpg?resize=650,813",
    protein: 150,
    fat: 100,
    carbs: 50,
    calories: 1000,
  },
];

function RecipeCardContainer(props) {
  return (
    <Stack direction="row" spacing={2}>
      {dbMock.map((recipe) => (
        <RecipeCard key={recipe.recipeId} recipe={recipe} />
      ))}
    </Stack>
  );
}

export default RecipeCardContainer;
