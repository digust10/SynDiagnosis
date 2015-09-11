package br.edu.ufcg.embedded.syndiagnosis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

import br.edu.ufcg.embedded.syndiagnosis.Util.Validacao;

public class RecuperacaoDeSenha extends AppCompatActivity {

    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperacao_de_senha);

        email = (EditText) findViewById(R.id.edt_email);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recuperacao_de_senha, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void enviar(View view) {
        String emailUser = email.getText().toString();

        if ((emailUser) != null && Validacao.validaEmail(emailUser)) {
            ParseUser.requestPasswordResetInBackground(emailUser, new RequestPasswordResetCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {

                        Toast.makeText(getApplicationContext(), "Senha Enviada com sucesso!", Toast.LENGTH_LONG)
                                .show();
                        // An email was successfully sent with reset instructions.
                    } else {
                        // Something went wrong. Look at the ParseException to see what's up.
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG)
                                .show();
                    }
                }

            });
        }else {
            Toast.makeText(getApplicationContext(), "Email Invalido ou nao cadastrado", Toast.LENGTH_LONG)
                    .show();
        }

    }
}
