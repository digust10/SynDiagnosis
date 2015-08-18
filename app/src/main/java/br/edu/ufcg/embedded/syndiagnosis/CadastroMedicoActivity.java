package br.edu.ufcg.embedded.syndiagnosis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroMedicoActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText password;
    private EditText crm;
    private EditText cpf;
    private Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_medico);

        name = (EditText) findViewById(R.id.editText);
        email = (EditText) findViewById(R.id.editText2);
        password = (EditText) findViewById(R.id.editText3);
        crm = (EditText) findViewById(R.id.editText4);
        cpf = (EditText) findViewById(R.id.editText5);
        cadastrar = (Button) findViewById(R.id.button9);
        //cadastrar.setOnClickListener((View.OnClickListener) this);

        cadastrar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent it = null;
                //TODO: salvar dados no BD
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastro_medico, menu);
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



    //private void getDados(){
        //private EditText name = (EditText) findViewById(R.id.editText);
        //private EditText email = (EditText) findViewById(R.id.editText2);
        //private EditText password = (EditText) findViewById(R.id.editText3);
        //private EditText crm = (EditText) findViewById(R.id.editText4);
        //private EditText cpf = (EditText) findViewById(R.id.editText5);
        //private Button cadastrar = (Button) findViewById(R.id.button9);
    //}

}
