package br.edu.ufcg.embedded.syndiagnosis;

import android.app.ProgressDialog;
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

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufcg.embedded.syndiagnosis.DAO.medicosDAO;
import br.edu.ufcg.embedded.syndiagnosis.Model.Medico;


public class login extends ActionBarActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private List<TestObject> usuariosAprovadosNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        usuariosAprovadosNome = new ArrayList<TestObject>();
        aprovadosLista((ArrayList<TestObject>) usuariosAprovadosNome);

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

    //deveria ser um id unico. Tipo Email,
    private void aprovadosLista(final ArrayList<TestObject> adicionar){
        //Log.d("Entrou","entrou");
        ParseQuery<TestObject> query = new ParseQuery<TestObject>("TestObject");
        query.findInBackground(new FindCallback<TestObject>() {

            @Override
            public void done(List<TestObject> list, ParseException e) {
                for (TestObject objUser : list) {
                    //Log.d("Entrou", objUser.getNome());
                    if (objUser.getStatus())
                        adicionar.add(objUser);
                }
            }
        });
    }

    //deveria ser um id unico. Tipo Email,
    private boolean estaAprovado(final String userName){

        for (TestObject objUser : usuariosAprovadosNome) {

            if (objUser.getNome().equals(userName)){
                return true;
            }
        }
        return false;
    }

    public void Login(View view){
        // Consulta os objetos cadastrados no banco de dados através do DAO
        medicosDAO dao = new medicosDAO(getApplicationContext());
        // Guarda os objetos consultados em uma List
        final List<Medico> medicosCadastrados = dao.listaTodos();

        Log.i("Username", edtUsername.getText().toString());
        Log.i("Username", edtPassword.getText().toString());


        if(isCadastrado(edtUsername.getText().toString(), edtPassword.getText().toString(), medicosCadastrados)){
            //Log.d("Entrou", String.valueOf(estaAprovado(edtUsername.getText().toString())));
            if (estaAprovado(edtUsername.getText().toString())) {
                Log.i("Username",edtUsername.getText().toString());
                Intent intent = new Intent(this,MedicosCadastradosActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(), "Seu Cadastro ainda nao foi aprovado", Toast.LENGTH_LONG)
                        .show();
            }
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
