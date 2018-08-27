package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String JSON_NAME_OBJECT_KEY = "name";
    private static final String JSON_NAME_MAIN_KEY = "mainName";
    private static final String JSON_NAME_ALSO_KNOWN_AS_KEY = "alsoKnownAs";
    private static final String JSON_ORIGIN_KEY = "placeOfOrigin";
    private static final String JSON_DESCRIPTION_KEY = "description";
    private static final String JSON_IMAGE_KEY = "image";
    private static final String JSON_INGREDIENTS_KEY = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        String mainName;
        List<String> alsoKnownAs = null;
        String placeOfOrigin;
        String description;
        String image;
        List<String> ingredients = null;

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject nameObject = jsonObject.getJSONObject(JSON_NAME_OBJECT_KEY);

            mainName = nameObject.getString(JSON_NAME_MAIN_KEY);
            alsoKnownAs = getStringArrayData(nameObject.getJSONArray(JSON_NAME_ALSO_KNOWN_AS_KEY));
            placeOfOrigin = jsonObject.getString(JSON_ORIGIN_KEY);
            description = jsonObject.getString(JSON_DESCRIPTION_KEY);
            image = jsonObject.getString(JSON_IMAGE_KEY);
            ingredients = getStringArrayData(jsonObject.getJSONArray(JSON_INGREDIENTS_KEY));

            Sandwich sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
            return sandwich;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static List<String> getStringArrayData(JSONArray jsonArray) throws JSONException{
        List<String> data = new ArrayList<String>();
        for(int i = 0; i < jsonArray.length(); i++){
            Log.d("Sandwich", jsonArray.getString(i));
            data.add(jsonArray.getString(i));
        }
        return data;
    }
}
