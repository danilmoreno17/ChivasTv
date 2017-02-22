package tqmgpartners.com.chivastv;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import tqmgpartners.com.chivastv.Adapters.adaOnDemandAdapter;
import tqmgpartners.com.chivastv.Entities.clsEditorial;

public class act_Sipnosis extends AppCompatActivity {
    ImageView ib_posterSipnosis;
    TextView tv_titleSipnosis;
    TextView tv_categoriesSipnosis;
    TextView tv_ActorsSipnosis;
    TextView tv_descriptionSipnosis;
    RecyclerView rec_RelatedVideos;
    Button btn_Watch;
    ImageButton ib_back;
    TextView tv_title_toolbar;
    clsEditorial contenido;
    Activity context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sipnosis);
        context=this;
        ib_posterSipnosis = (ImageView)findViewById(R.id.ib_posterSipnosis);
        tv_titleSipnosis = (TextView)findViewById(R.id.tv_titleSipnosis);
        tv_categoriesSipnosis = (TextView)findViewById(R.id.tv_categoriesSipnosis);
        tv_ActorsSipnosis = (TextView)findViewById(R.id.tv_actorsSipnosis);
        tv_descriptionSipnosis = (TextView)findViewById(R.id.tv_descriptionSipnosis);
        btn_Watch = (Button)findViewById(R.id.btn_Watch);
        ib_back = (ImageButton)findViewById(R.id.ib_back);
        tv_title_toolbar = (TextView) findViewById(R.id.tv_title_toolbar);
        rec_RelatedVideos=(RecyclerView)findViewById(R.id.rec_RelatedVideos);
        tv_title_toolbar.setText(getResources().getString(R.string.app_name));
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intReceiver = getIntent();
        contenido = (clsEditorial) intReceiver.getSerializableExtra("EDITORIAL");
        Picasso.with(this).load(contenido.get_strPromoImages()).into(ib_posterSipnosis);
        ib_posterSipnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStream();
            }
        });
        tv_titleSipnosis.setText(contenido.get_strTitle());
        tv_ActorsSipnosis.setText(contenido.get_strActors());
        tv_categoriesSipnosis.setText(contenido.get_strCategories());
        tv_descriptionSipnosis.setText(contenido.get_strDescription());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rec_RelatedVideos.setLayoutManager(mLayoutManager);
        rec_RelatedVideos.setItemAnimator(new DefaultItemAnimator());


        final adaOnDemandAdapter objAdaProgrammes = new adaOnDemandAdapter(((ChivasTv)getApplication()).colEditorials);
        rec_RelatedVideos.setAdapter(objAdaProgrammes);
        btn_Watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = context.getLayoutInflater();
                View rootview = inflater.inflate(R.layout.dialog_purchase, null);
                TextView tv_openBrowser = (TextView)rootview.findViewById(R.id.tv_openBrowser);
                tv_openBrowser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OpenBrowser();
                    }
                });
                new AlertDialog.Builder(context)
                        .setView(rootview)
                        .setCancelable(true)
                        .show();
            }
        });
    }
    private void openStream(){
        Intent intent = new Intent(this, StreamActivity.class);
        startActivity(intent);
    }
    private void OpenBrowser(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.chivastv.mx/"));
        startActivity(browserIntent);
    }
}
