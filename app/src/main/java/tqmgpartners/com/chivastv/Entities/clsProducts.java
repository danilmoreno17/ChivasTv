package tqmgpartners.com.chivastv.Entities;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by tqmg_fbazurto on 28/11/2016.
 */
public class clsProducts implements Serializable {
    private String _strId;
    private String _strTitle;
    private String _strDefinition;
    private String _strURL;
    private String _decPrice;
    private String _objNode;

    public String get_Id() {
        return _strId;
    }

    public void set_strId(String _strId) {
        this._strId = _strId;
    }

    public String getTitle() {
        return _strTitle;
    }

    public void set_strTitle(String _strTitle) {
        this._strTitle = _strTitle;
    }

    public String getDefinition() {
        return _strDefinition;
    }

    public void set_strDefinition(String _strDefinition) {
        this._strDefinition = _strDefinition;
    }

    public String getUrl() {
        return _strURL;
    }

    public void set_strURL(String _strURL) {
        this._strURL = _strURL;
    }

    public String getPrice() {
        try {

            Double dblPrice = Double.parseDouble(_decPrice); // Make use of autoboxing.  It's also easier to read.
            if(dblPrice>0)
            {
                return _decPrice;
            }
            else
                return "";
        } catch (NumberFormatException e) {
            // p did not contain a valid double
            return "";
        }


    }

    public void set_decPrice(String _decPrice) {
        this._decPrice = _decPrice;
    }


    public String get_objNode() {
        return _objNode;
    }

    public void set_objNode(String _objNode) {
        this._objNode = _objNode;
    }


    public clsProducts(String _strId, String _strTitle, String _strDefinition, String _strURL, String _decPrice) {
        this._strId = _strId;
        this._strTitle = _strTitle;
        this._strDefinition = _strDefinition;
        this._strURL = _strURL;
        this._decPrice = _decPrice;
    }

    public clsProducts(JSONObject _pJSONProduct, JSONObject _pJSONTechnical) {
        try{



            this._strId = (_pJSONProduct.has("_id"))? _pJSONProduct.getString("_id"):"";
            //Technicals
            this._strDefinition = (_pJSONTechnical.has("Definition"))? _pJSONTechnical.getString("Definition"):"";
            this._strURL = ( _pJSONTechnical.getJSONObject("media").getJSONObject("AV_ClearTS").has("fileName"))? _pJSONTechnical.getJSONObject("media").getJSONObject("AV_ClearTS").getString("fileName"):"";
            //TODO cambiar por el objeto NODO
            //this._objNode = (_pJSONTechnical.getJSONObject("media").getJSONObject("AV_ClearTS").has("comment"))? _pJSONTechnical.getJSONObject("media").getJSONObject("AV_ClearTS").getString("comment"):"";

            JSONObject objPrecio = _pJSONProduct.getJSONObject("price");

            this._decPrice = (objPrecio.has("value"))? objPrecio.getString("value"):"";
            System.out.println(_pJSONProduct.toString()+"-"+_pJSONTechnical.toString());

        }
        catch(JSONException jex)
        {
            Log.e("JsonException products",jex.getMessage());
        }
    }
}
