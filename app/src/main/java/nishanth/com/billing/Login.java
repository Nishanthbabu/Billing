package nishanth.com.billing;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by Nishanth on 2/13/2016.
 */
public class Login extends AppCompatActivity implements View.OnClickListener {

EditText enter_token;
Button save_button,cancle_button;
    String MASTER = "MASTER",OPERATOR = "OPERATOR";        ////// TOKENS

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        enter_token = (EditText) findViewById(R.id.login_edittext_token);
        save_button = (Button) findViewById(R.id.login_save_button);
        cancle_button = (Button) findViewById(R.id.login_cancle_button);

        save_button.setOnClickListener(this);
        cancle_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.login_save_button :
                Intent logedIn = new Intent(Login.this,MainActivity.class);
                if(enter_token.getText().toString().equalsIgnoreCase(MASTER))
                {
                    logedIn.putExtra("TYPE",0);
                    startActivity(logedIn);
                }
                else if(enter_token.getText().toString().equalsIgnoreCase(OPERATOR))
                {
                    logedIn.putExtra("TYPE",1);
                    startActivity(logedIn);
                }
                else
                {
                    Toast.makeText(Login.this, "Incorrect Token", Toast.LENGTH_SHORT).show();
                }
                break;

        }

    }
}
