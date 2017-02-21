package tqmgpartners.com.chivastv.Adapters;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import tqmgpartners.com.chivastv.R;

import java.util.ArrayList;
import java.util.List;

import tqmgpartners.com.chivastv.Entities.clsEditorial;
import tqmgpartners.com.chivastv.Entities.clsProducts;

/**
 * Created by tqmg_ecdev01 on 27/11/2016.
 */

public class clsServiceAdapter extends BaseAdapter {

    private ArrayList<clsProducts> _list;
    private Context _objContext;
    private LayoutInflater _objInflater;
    private ViewHolder _objViewHolder;



    /***
     *
     * @param _pContext
     * @param _pList
     */

    public clsServiceAdapter(Context _pContext, ArrayList<clsProducts> _pList){
        this._objContext = _pContext;
        this._list = _pList;

    }
    @Override
    public int getCount() {
        return _list.size();
    }

    @Override
    public Object getItem(int i) {
        return _list.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int iposition, View view, ViewGroup viewGroup) {

        try{
            if(view == null){

                _objInflater = (LayoutInflater)_objContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = _objInflater.inflate(R.layout.service_list_row,viewGroup,false);
                _objViewHolder = new ViewHolder();


                _objViewHolder._imgService = (ImageView) view.findViewById(R.id.imService);
                _objViewHolder._txtService = (TextView) view.findViewById(R.id.txtService);
                _objViewHolder._txtPrice = (TextView) view.findViewById(R.id.txtPrice);
                _objViewHolder._txtDefinition = (TextView) view.findViewById(R.id.txtDefinition);
                _objViewHolder._btnwatch = (Button)view.findViewById(R.id.btnSerWatch);
                if(Build.VERSION.SDK_INT>=21){
                    switch (_list.get(iposition).get_objNode()) {
                        case "YouTube":
                            //Picasso.with(_objContext).load(R.drawable.ic_youtube_logo).into(_objViewHolder._imgService);
                            _objViewHolder._imgService.setImageResource(R.drawable.ic_youtube_logo);

                            break;
                        case "Netflix":
                            //Picasso.with(_objContext).load(R.drawable.ic_netflix_logo).into(_objViewHolder._imgService);
                            _objViewHolder._imgService.setImageResource(R.drawable.ic_netflix_logo);

                            break;
                        case "Hulu":
                            //Picasso.with(_objContext).load(R.drawable.ic_hulu_logo).into(_objViewHolder._imgService);
                            _objViewHolder._imgService.setImageResource(R.drawable.ic_hulu_logo);

                            break;
                        case "Amazon.com":
                            //Picasso.with(_objContext).load(R.drawable.ic_amazon_logo).into(_objViewHolder._imgService);
                            _objViewHolder._imgService.setImageResource(R.drawable.ic_amazon_logo);

                            break;
                        case "HBO":
                            //Picasso.with(_objContext).load(R.drawable.ic_hbo_logo).into(_objViewHolder._imgService);
                            _objViewHolder._imgService.setImageResource(R.drawable.ic_hbo_logo);
                    }
                }
                else
                {
                    switch (_list.get(iposition).get_objNode()) {
                        case "YouTube":
                            Picasso.with(_objContext).load(R.drawable.youtube).into(_objViewHolder._imgService);
                           // _objViewHolder._imgService.setImageResource(R.drawable.yout);

                            break;
                        case "Netflix":
                            Picasso.with(_objContext).load(R.drawable.netflix).into(_objViewHolder._imgService);
                            //_objViewHolder._imgService.setImageResource(R.drawable.netflix);

                            break;
                        case "Hulu":
                            Picasso.with(_objContext).load(R.drawable.hulu).into(_objViewHolder._imgService);
                            //_objViewHolder._imgService.setImageResource(R.drawable.hulu);

                            break;
                        case "Amazon.com":
                            Picasso.with(_objContext).load(R.drawable.amazoncom).into(_objViewHolder._imgService);
                            //_objViewHolder._imgService.setImageResource(R.drawable.ic_amazon_logo);

                            break;
                        case "HBO":
                            Picasso.with(_objContext).load(R.drawable.hbo).into(_objViewHolder._imgService);
                            //_objViewHolder._imgService.setImageResource(R.drawable.ic_hbo_logo);
                    }
                }


                _objViewHolder._txtService.setText(_list.get(iposition).get_objNode());
                _objViewHolder._txtPrice.setText(_list.get(iposition).getPrice());
                _objViewHolder._txtDefinition.setText(_list.get(iposition).getDefinition());

                _objViewHolder._btnwatch.setId(iposition);
                _objViewHolder._btnwatch.setNextFocusDownId(iposition+1);
                _objViewHolder._btnwatch.setNextFocusDownId(iposition-1);

               _objViewHolder._btnwatch.setClickable(true);
                _objViewHolder._btnwatch.setFocusable(true);
                _objViewHolder._btnwatch.setOnFocusChangeListener(new View.OnFocusChangeListener(){
                    @Override
                    public void onFocusChange(View view, boolean hasFocus) {


                        if (hasFocus) {
                            ((LinearLayout)view.getParent()).setBackgroundColor(view.getResources().getColor( R.color.BDR_blue));

                        } else {
                            ((LinearLayout)view.getParent()).setBackgroundColor(view.getResources().getColor( R.color.BG_02));

                        }
                    }


                } );

                _objViewHolder._btnwatch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (_list.get(iposition).get_objNode())
                        {
                            case "YouTube":

                                abrirYoutube(_list.get(iposition).getUrl());
                                break;
                            case "Netflix":

                                abrirNetflix(_list.get(iposition).getUrl());
                                break;
                            case "Hulu":

                                abrirHulu(_list.get(iposition).getUrl());
                                break;
                            case "Amazon.com":
                                abrirAmazon(_list.get(iposition).getUrl());
                                break;
                            case "HBO":

                                abrirHBOGO(_list.get(iposition).getUrl());
                                break;
                            case "VUDU":

                                abrirVUDU(_list.get(iposition).getUrl());
                                break;

                        }
                        view.setTag(_objViewHolder);
                    }
                });

                    }else{
                        _objViewHolder = (ViewHolder)view.getTag();

                    }

        }catch(Exception e){
            e.printStackTrace();
        }
        return view;
    }
    public void abrirYoutube(String url){
        String[] ab = url.split("\\?");
        ab = ab[1].split("=");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + ab[1]));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _objContext.startActivity(intent);
    }
    public void abrirHBOGO(String url){

        try {
            String[] strPedazos=url.split("/");
            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
            //http://www.hbogo.com/#home/video&assetID=GOROSTGP38970?videoMode=embeddedVideo/
            //hbogo://deeplink/MO.MO/GOROSTGP38970
            //hbogo://deeplink/GOROSTGP38970
            intent.setClassName("com.HBO", "com.hbo.go.LaunchActivity");
            //intent.setData(Uri.parse(url));
            //intent.putExtra("MO.MO","GOROSTGP38970");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _objContext.startActivity(intent);
        }
        catch(Exception e)
        {
// netflix app isn't installed, send to website.
            e.printStackTrace();
            Log.e("Error",e.getMessage());
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            String getId[] = url.split("hbogo://deeplink/MO.MO/");
            String nurl = "http://www.hbogo.com/#home/video&assetID="+getId[1]+"?videoMode=embeddedVideo/";
            intent.setData(Uri.parse(nurl));
            _objContext.startActivity(intent);
        }
    }
    public void abrirVUDU(String url){
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setClassName("com.vudu.google.tv.vuduapp", "com.vudu.google.tv.vuduapp.VuduActivity");
            intent.setData(Uri.parse(url));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _objContext.startActivity(intent);
        }
        catch(Exception e)
        {
// netflix app isn't installed, send to website.
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse(url));
            _objContext.startActivity(intent);
        }

    }
    public void abrirNetflix(String url){

        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setClassName("com.netflix.mediaclient", "com.netflix.mediaclient.ui.launch.UIWebViewActivity");
            intent.setData(Uri.parse(url));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _objContext.startActivity(intent);
        }
        catch(Exception e)
        {
// netflix app isn't installed, send to website.
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse(url));
            _objContext.startActivity(intent);
        }

    }
    public void abrirAmazon(String url){

            String[] ab = url.split("\\?");
            if(ab.length>1)
            {;}
            else
            {
                ab = url.split("/");
                ab[1]=ab[2];
            }

            String asin = ab[1];
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.amazon.avod.thirdpartyclient", "com.amazon.avod.thirdpartyclient.ThirdPartyPlaybackActivity"));
                intent.putExtra("asin", asin.replace("asin=", ""));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                PackageManager manager = _objContext.getPackageManager();
                List<ResolveInfo> infos = manager.queryIntentActivities(intent, 0);
                if (infos.size() > 0) {
                    _objContext.startActivity(intent);
                }else{
                    Intent intend = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.amazon.com/gp/mas/dl/android?" + ab[1]));
                    intend.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    _objContext.startActivity(intend);
                }
            }catch(Exception edx){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.amazon.com/gp/mas/dl/android?" + ab[1]));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                _objContext.startActivity(intent);
            }
    }
    public void abrirHulu(String url){
        try{

        String[] ab = url.split("w//");
        ab = ab[1].split("=");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("hulu://w/" + ab[1]));
        _objContext.startActivity(intent);
        }catch(Exception edx){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _objContext.startActivity(intent);
        }
    }

    public static class ViewHolder{
        ImageView _imgService;
        TextView _txtService;
        TextView _txtPrice;
        TextView _txtDefinition;
        Button _btnwatch;
    }




}
