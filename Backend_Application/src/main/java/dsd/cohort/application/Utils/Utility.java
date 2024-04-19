package dsd.cohort.application.Utils;

import java.security.Key;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dsd.cohort.application.ingredient.IngredientEntity;
import dsd.cohort.application.ingredient.IngredientRepository;
import dsd.cohort.application.ingredient.IngredientServiceImpl;
import dsd.cohort.application.recipe.RecipeEntity;

@Component
public class Utility {

    private DecimalFormat df = new DecimalFormat("#.00");

    private String secret = "icantbelieveitsnotsecret";

    private IngredientRepository ingredientRepository;

    private IngredientServiceImpl ingredientServiceImpl;

    private long lastCallTime;

    public Utility(IngredientRepository ingredientRepository, IngredientServiceImpl ingredientServiceImpl) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientServiceImpl = ingredientServiceImpl;
        this.lastCallTime = System.currentTimeMillis() / 1000;
    }

    public String consolidateString(List<String> list) {

        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }
        return sb.toString();
    }

    public List<String> tokenizeString(String str) {

        // slice string into a list of string tokens that are 255 characters or less
        List<String> tokens = new ArrayList<>();
        int index = 0;
        while (index < str.length()) {
            int endIndex = Math.min(index + 255, str.length());
            tokens.add(str.substring(index, endIndex));
            index = endIndex;
        }

        return tokens;
    }

    public String encryptString(String str) {

        Key aesKey = new SecretKeySpec(secret.getBytes(), "AES");

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);

            byte[] encrypted = cipher.doFinal(str.getBytes());
            str = new String(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }

    public String decryptString(String str) {

        Key key = new SecretKeySpec(secret.getBytes(), "AES");

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);

            String decrypted = new String(cipher.doFinal(str.getBytes()));

            str = decrypted;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }

    public RecipeEntity recipeHandler(JsonNode jsonNode, String recipeId) {

        RecipeEntity newRecipe = new RecipeEntity();

        JsonNode recipeNode = jsonNode.findValue("recipe");

        newRecipe.setName(recipeNode.findValue("label").textValue());
        newRecipe.setDescription(recipeNode.findValue("label").textValue());
        newRecipe.setRecipeId(recipeId);
        newRecipe.setImageUrl(recipeNode.findValue("image").textValue());
        newRecipe.setUrl(recipeNode.findValue("url").textValue());
        newRecipe.setYield(recipeNode.findValue("yield").intValue());
        newRecipe.setTotalTime(recipeNode.findValue("totalTime").intValue());

        // get nutrients from json
        JsonNode nutrients = recipeNode.findValue("totalNutrients");

        Double caloriesD = recipeNode.findValue("calories").doubleValue();
        newRecipe.setCalories(Double.parseDouble(df.format(caloriesD)));

        double fats = nutrients
                .findValue("FAT")
                .findValue("quantity")
                .doubleValue();
        newRecipe.setFat(Double.parseDouble(df.format(fats)));

        double protein = nutrients
                .findValue("PROCNT")
                .findValue("quantity")
                .doubleValue();
        newRecipe.setProtein(Double.parseDouble(df.format(protein)));

        double carbs = nutrients
                .findValue("CHOCDF")
                .findValue("quantity")
                .doubleValue();
        newRecipe.setCarbs(Double.parseDouble(df.format(carbs)));

        // get ingredients from json
        JsonNode ingredientsJson = recipeNode.findValue("ingredients");

        Set<IngredientEntity> ingredients = new HashSet<>();

        for (JsonNode ingredient : ingredientsJson) {
            ingredients.add(parseIngredient(ingredient));
        }

        newRecipe.setIngredients(ingredients);

        System.out.println("\n\nSuccessful recipe parse\n\n");

        return newRecipe;
    }

    public IngredientEntity parseIngredient(JsonNode ingredient) {

        String foodId = ingredient.findValue("foodId").textValue();
        IngredientEntity existingIngredient = ingredientRepository.findByFoodId(foodId);

        if (existingIngredient != null) {
            return existingIngredient;
        }

        IngredientEntity newIngredient = new IngredientEntity();

        newIngredient.setFoodId(ingredient.findValue("foodId").textValue());
        newIngredient.setText(ingredient.findValue("text").textValue());
        newIngredient.setQuantity(ingredient.findValue("quantity").intValue());
        newIngredient.setMeasure(ingredient.findValue("measure").textValue());
        newIngredient.setName(ingredient.findValue("food").textValue());
        newIngredient.setFoodCategory(ingredient.findValue("foodCategory").textValue());
        newIngredient.setImageUrl(ingredient.findValue("image").textValue());

        Double weight = ingredient.findValue("weight").doubleValue();
        newIngredient.setWeight(Double.parseDouble(df.format(weight)));

        ingredientServiceImpl.createIngredient(newIngredient);

        System.out.println("\n\nSuccessful ingredient parse\n\n");

        return newIngredient;

    }

    public JsonNode stringToJson(String response) throws JsonProcessingException, JsonMappingException {
        // declare variables for parsing
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode;
        // parse response to json
        jsonNode = mapper.readTree(response);

        return jsonNode;
    }

    public boolean compareTime(long time) {

        if (time - 60 > lastCallTime) {
            lastCallTime = time;
            return true;
        }

        return false;
    }
}
