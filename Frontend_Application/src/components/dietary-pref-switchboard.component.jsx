import * as React from "react";
import FormGroup from "@mui/material/FormGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import Switch from "@mui/material/Switch";

export default function DietaryPrefSwitchboard() {
  const dietary_preferences = [
    "Vegetarian",
    "Vegan",
    "Paleo",
    "Gluten-free diet",
    "Ketogenic",
    "Lactose-intolerant",
    "Halal",
    "Kosher",
    "Allergic to Nuts",
  ];
  return (
    <FormGroup>
      <FormControlLabel
        control={<Switch defaultChecked />}
        label='No allergies'
      />
      {dietary_preferences.map((diet) => (
        <FormControlLabel control={<Switch />} label={diet} />
      ))}
    </FormGroup>
  );
}
