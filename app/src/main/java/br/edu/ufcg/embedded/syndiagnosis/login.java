package br.edu.ufcg.embedded.syndiagnosis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.edu.ufcg.embedded.syndiagnosis.DAO.medicosDAO;
import br.edu.ufcg.embedded.syndiagnosis.Model.Medico;


public class login extends ActionBarActivity {

    private EditText edtUsername;
    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        edtUsername = (EditText) findViewById(R.id.user);
        edtPassword = (EditText) findViewById(R.id.pwd);

        // Consulta os objetos cadastrados no banco de dados através do DAO
        medicosDAO dao = new medicosDAO(getApplicationContext());
        // Guarda os objetos consultados em uma List
        final List<Medico> medicosCadastrados = dao.listaTodos();


    }

    private Boolean isCadastrado(String username,String password,List<Medico> medicosCadastrados){
        Boolean isCadastrado = false;
        for(Medico med: medicosCadastrados){

            if(med.getName().equals(username) && med.getPassword().equals(password)){
                isCadastrado = true;
            }
        }
        return isCadastrado;
    }

    public void Login(View view){
        // Consulta os objetos cadastrados no banco de dados através do DAO
        medicosDAO dao = new medicosDAO(getApplicationContext());
        // Guarda os objetos consultados em uma List
        final List<Medico> medicosCadastrados = dao.listaTodos();

        Log.i("Username", edtUsername.getText().toString());
        Log.i("Username",edtPassword.getText().toString());


        if (isCadastrado(edtUsername.getText().toString(),edtPassword.getText().toString(),medicosCadastrados)){
            Log.i("Username",edtUsername.getText().toString());
            Intent intent = new Intent(this,MedicosCadastradosActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), "Usuario ou senha Invalidos", Toast.LENGTH_LONG)
                    .show();
        }

    }


    public void Register(View view){
        Intent intent = new Intent(this,CadastroMedicoActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
}
