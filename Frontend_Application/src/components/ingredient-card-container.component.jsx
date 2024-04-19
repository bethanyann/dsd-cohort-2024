// import IngredientCard from "./ingredient-card.component.jsx";
// import { Stack } from "@mui/material";

// const dbMock = [
//   {
//     ingredientId: "1",
//     name: "Pork",
//     description: "This is a description",
//     imageUrl:
//       "https://www.recipetineats.com/wp-content/uploads/2022/10/12-hour-BBQ-pulled-pork_7-3.jpg?resize=650,813",
//     protein: 150,
//     fat: 100,
//     carbs: 50,
//     calories: 1000,
//   },

//   {
//     ingredientId: "2",
//     name: "BBQ Sauce",
//     description: "This is a description",
//     imageUrl:
//       "https://www.recipetineats.com/wp-content/uploads/2022/10/12-hour-BBQ-pulled-pork_7-3.jpg?resize=650,813",
//     protein: 150,
//     fat: 100,
//     carbs: 50,
//     calories: 1000,
//   },

//   {
//     ingredientId: "3",
//     name: "Corn",
//     description: "This is a description",
//     imageUrl:
//       "https://www.recipetineats.com/wp-content/uploads/2022/10/12-hour-BBQ-pulled-pork_7-3.jpg?resize=650,813",
//     protein: 150,
//     fat: 100,
//     carbs: 50,
//     calories: 1000,
//   },
// ];

import { useEffect, useState } from "react";
import IngredientCard from "./ingredient-card.component.jsx";
import { Stack } from "@mui/material";

function IngredientCardContainer(props) {
  const [myIngredient, setMyIngredient] = useState([]);

  useEffect(() => {
    console.log("Fetching ingredients...");
    const fetchIngredients = async () => {
      try {
        const response = await fetch(
          "http://localhost:8080/api/v0/ingredients/"
        );
        const data = await response.json();
        setMyIngredient(data);
        console.log(data);
      } catch (error) {
        console.error("Error fetching ingredients:", error);
      }
    };

    fetchIngredients();
  }, []);
  return (
    <Stack direction='row' spacing={2} useFlexGap flexWrap='wrap'>
      {myIngredient.map((ingredient) => (
        <IngredientCard key={ingredient.foodId} ingredient={ingredient} />
      ))}
    </Stack>
  );
}

export default IngredientCardContainer;
