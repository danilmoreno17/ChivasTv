package tqmgpartners.com.chivastv.Entities;



import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by tqmg_fbazurto on 26/11/2016.
 */
public class clsEditorial implements Serializable {

    private String _strId;
    private String _strTitle;
    private String _strDescription;
    private String _strActors;
    private String _strCategories;
    private String _strPromoImages;
    private ArrayList<clsProducts> _colProducts = new ArrayList<>();
    private String _strUrl;
    public clsEditorial() {

    }


    public clsEditorial(String _strId, String _strTitle, String _strDescription, String _strActors, String _strPromoImages, String _strCategories) {
        this._strId = _strId;
        this._strTitle = _strTitle;
        this._strDescription = _strDescription;
        this._strActors = _strActors;
        this._strPromoImages = _strPromoImages;
        this._strCategories=_strCategories;
    }

    public clsEditorial(JSONObject jsonObject) {
        try {

            this._strId = (jsonObject.has("id") ? jsonObject.getString("id") : "");
            this._strTitle = (jsonObject.has("Title") ? jsonObject.getString("Title") : "");
            this._strDescription = (jsonObject.has("Description") ? jsonObject.getString("Description") : "");
            //this._strActors = (jsonObject.has("Actors")?jsonObject.getString("Actors"):"").replace("[","").replace("]","").replace('"',' ');;
            this._strActors = "$9.99";
            this._strCategories = (jsonObject.has("Categories")?jsonObject.getString("Categories"):"").replace("[","").replace("]","").replace('"',' ');;
            this._strPromoImages = (jsonObject.has("PromoImages")?jsonObject.getJSONArray("PromoImages").getString(0):"");
            if(jsonObject.has("technicals")) {
                /** Asignar productos **/
                for (int y = 0; y < jsonObject.getJSONArray("technicals").length(); ++y) {
                    JSONArray objJSONProducts = jsonObject.getJSONArray("technicals").getJSONObject(y).getJSONArray("products");
                    JSONObject objJSONTechnical = jsonObject.getJSONArray("technicals").getJSONObject(y);

                    for (int z = 0; z < objJSONProducts.length(); z++) {
                        clsProducts objProduct = new clsProducts(objJSONProducts.getJSONObject(z), objJSONTechnical);
                        this._colProducts.add(objProduct);
                    }
                }
            }
        }catch (Exception e){
            Log.e("JsonException",e.getMessage());
        }
    }


    public String get_strId() {
        return _strId;
    }

    public void set_strId(String _strId) {
        this._strId = _strId;
    }

    public String get_strTitle() {
        return _strTitle;
    }

    public void set_strTitle(String _strTitle) {
        this._strTitle = _strTitle;
    }

    public String get_strDescription() {
        return _strDescription;
    }

    public void set_strDescription(String _strDescription) {
        this._strDescription = _strDescription;
    }

    public String get_strActors() {
        return _strActors;
    }

    public void set_strActors(String _strActors) {
        this._strActors = _strActors;
    }

    public String get_strPromoImages() {
        return _strPromoImages;
    }

    public void set_strPromoImages(String _strPromoImages) {
        this._strPromoImages = _strPromoImages;
    }
    public String get_strCategories() {
        return _strCategories;
    }

    public void set_strCategories(String _strCategories) {
        this._strCategories = _strCategories;
    }

    public ArrayList<clsProducts> get_colProducts() {
        return _colProducts;
    }

    public void set_colProducts(ArrayList<clsProducts> _colProducts) {
        this._colProducts = _colProducts;
    }

    public String get_strUrl() {
        return _strUrl;
    }

    public void set_strUrl(String _strUrl) {
        this._strUrl = _strUrl;
    }

}
