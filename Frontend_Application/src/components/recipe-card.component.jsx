import React from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Typography from "@mui/material/Typography";
import { Button, CardActionArea, CardActions } from "@mui/material";
import { createSvgIcon } from "@mui/material/utils";
import DeleteIcon from "@mui/icons-material/Delete";
import Stack from "@mui/material/Stack";
import axios from "axios";

const PlusIcon = createSvgIcon(
  <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="h-6 w-6">
    <path strokeLinecap="round" strokeLinejoin="round" d="M12 4.5v15m7.5-7.5h-15" />
  </svg>,
  "Plus"
);

function RecipeCard(props) {
  const { name, receipeId, description, imageUrl, protein, fat, carbs, calories } = props.recipe;
  const type = props.type;
  const userInfo = props.userInfo;

  const handleAdd = () => {
    axios
      .post("http://localhost:8080/api/v0/users/addrecipetofavorites/", {
        email: userInfo.email,
        id: receipeId,
      })
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  const handleRemove = () => {
    console.log("removing");
    console.log(userInfo);
    axios
      .delete("http://localhost:8080/api/v0/users/addrecipetofavorites/", {
        email: userInfo.email,
        id: receipeId,
      })
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  };
  return (
    <Card sx={{ maxWidth: 345 }}>
      <CardActionArea>
        <CardMedia component="img" height="200" image={imageUrl} alt={name} />
        <CardContent sx={{ display: "flex", flexDirection: "row", justifyContent: "space-between" }}>
          <div style={{ width: "80%" }}>
            <Typography gutterBottom variant="h5" component="div">
              {name}
            </Typography>
            <Typography variant="body2" color="text.secondary">
              Carb: {carbs} | Protein: {protein} | Fat: {fat}
            </Typography>
          </div>
          <Stack direction="row" alignItems="center">
            {type === "add" && <PlusIcon fontSize="large" onClick={handleAdd} />}
            {type === "remove" && <DeleteIcon fontSize="large" onClick={handleRemove} />}
          </Stack>
        </CardContent>
      </CardActionArea>
    </Card>
  );
}

export default RecipeCard;
