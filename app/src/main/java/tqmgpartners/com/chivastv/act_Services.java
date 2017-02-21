package tqmgpartners.com.chivastv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import tqmgpartners.com.chivastv.Adapters.clsServiceAdapter;
import tqmgpartners.com.chivastv.Entities.clsEditorial;

public class act_Services extends AppCompatActivity {
    clsEditorial contenido;
    ListView lv_products;
    ImageButton ib_back;
    TextView tv_title_toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_services);
        Intent intReceiver = getIntent();
        contenido = (clsEditorial) intReceiver.getSerializableExtra("EDITORIAL");
        lv_products = (ListView) findViewById(R.id.lv_products);
        ib_back = (ImageButton)findViewById(R.id.ib_back);
        tv_title_toolbar = (TextView) findViewById(R.id.tv_title_toolbar);
        tv_title_toolbar.setText(getResources().getString(R.string.strWatch));
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Log.i("size",""+contenido.get_strTitle());
        clsServiceAdapter adaptador = new clsServiceAdapter(this,contenido.get_colProducts());
        lv_products.setAdapter(adaptador);
        lv_products.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);

    }
}
