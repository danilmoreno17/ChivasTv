package tqmgpartners.com.chivastv;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class act_Login extends AppCompatActivity {
    EditText et_email;
    EditText et_password;
    Button btn_login;
    TextView tv_logoChivas, tv_signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        tv_logoChivas = (TextView)findViewById(R.id.tv_logoChivas);
        tv_signUp = (TextView)findViewById(R.id.tv_singUp);
        et_email = (EditText)findViewById(R.id.et_email);
        et_password = (EditText)findViewById(R.id.et_password);
        btn_login = (Button)findViewById(R.id.btn_Login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ChivasTv)getApplication()).strToken="1234";
                Intent intent = new Intent(v.getContext(), act_Browse.class);
                startActivity(intent);
            }
        });
        //SetFonts();
    }
    private void SetFonts(){
        Typeface miPropiaTypeFace = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeueLTStd Lt.ttf");
        tv_logoChivas.setTypeface(miPropiaTypeFace);
        tv_signUp.setTypeface(miPropiaTypeFace);
        et_email.setTypeface(miPropiaTypeFace);
        et_password.setTypeface(miPropiaTypeFace);
        btn_login.setTypeface(miPropiaTypeFace);
    }
}
