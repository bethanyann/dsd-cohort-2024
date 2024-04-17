import IngredientCard from "./ingredient-card.component.jsx";
import { Stack } from "@mui/material";

const dbMock = [
  {
    ingredientId: "1",
    name: "Pork",
    description: "This is a description",
    imageUrl:
      "https://www.recipetineats.com/wp-content/uploads/2022/10/12-hour-BBQ-pulled-pork_7-3.jpg?resize=650,813",
    protein: 150,
    fat: 100,
    carbs: 50,
    calories: 1000,
  },

  {
    ingredientId: "2",
    name: "BBQ Sauce",
    description: "This is a description",
    imageUrl:
      "https://www.recipetineats.com/wp-content/uploads/2022/10/12-hour-BBQ-pulled-pork_7-3.jpg?resize=650,813",
    protein: 150,
    fat: 100,
    carbs: 50,
    calories: 1000,
  },

  {
    ingredientId: "3",
    name: "Corn",
    description: "This is a description",
    imageUrl:
      "https://www.recipetineats.com/wp-content/uploads/2022/10/12-hour-BBQ-pulled-pork_7-3.jpg?resize=650,813",
    protein: 150,
    fat: 100,
    carbs: 50,
    calories: 1000,
  },
];

function IngredientCardContainer(props) {
  return (
    <Stack direction='row' spacing={2}>
      {dbMock.map((ingredient) => (
        <IngredientCard key={ingredient.ingredientId} ingredient={ingredient} />
      ))}
    </Stack>
  );
}

export default IngredientCardContainer;
