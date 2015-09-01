package br.edu.ufcg.embedded.syndiagnosis;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import br.edu.ufcg.embedded.syndiagnosis.Adapters.ParseListAdapter;

/**
 * Created by digust10 on 18/08/2015.
 */
public class ParseListActivity extends ListActivity {
    List<TestObject> usuarios = new ArrayList<TestObject>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parse_list);

        ((Button) findViewById(R.id.buttonAceitar)).setOnClickListener(onClick);
        ((Button) findViewById(R.id.buttonRejeitar)).setOnClickListener(onClick);

        ParseQuery<TestObject> query = new ParseQuery<TestObject>("TestObject");
        query.findInBackground(new FindCallback<TestObject>() {
            @Override
            public void done(List<TestObject> list, ParseException e) {
                if(e != null){
                    //Erro
                }else{
                    for(TestObject objUser : list){
                        //if(!objUser.getStatus()){ //o admin so pega os usuarios que ainda precisam de autorizacao
                        TestObject newUser = new TestObject();
                        newUser.setEmail(objUser.getEmail());
                        newUser.setNome(objUser.getNome());
                        newUser.setStatus(objUser.getStatus());
                        //usuarios.add(newUser);
                        //So quem esta requisitando acesso
                        if(objUser.getStatus() == false){
                            usuarios.add(newUser);
                        }
                        //}

                    }
                }
                ArrayAdapter<TestObject> adapter = new ParseListAdapter(ParseListActivity.this, R.layout.item_parse_list, usuarios);//simple_list_item_multiple_choice
                setListAdapter(adapter);
            }
        });
    }

    private void aceitarTodos(){

        /*for(TestObject aceitaveis : usuarios){

            //aceitaveis.setStatus(true);
            aceitaveis.saveInBackground();
        }*/
        Log.d("Entrou", "modificacoa");
        ParseQuery<TestObject> query = new ParseQuery<TestObject>("TestObject");
        query.findInBackground(new FindCallback<TestObject>() {
            @Override
            public void done(List<TestObject> usr, ParseException e) {
                if(e != null){
                    //Erro
                }else{
                    for(TestObject objUser : usr){
                        for (TestObject usuario : usuarios){
                            if(objUser.compareTo(usuario) == 1){
                                objUser.setStatus(usuario.getStatus());
                                objUser.saveInBackground();
                                Log.d("Entrou", String.valueOf(objUser.getStatus()));
                            }
                        }
                    }
                }
            }
        });

        /*Log.d("Entrou", "modificacoa");
        ParseQuery<TestObject> query = new ParseQuery<TestObject>("TestObject");
        query.findInBackground(new FindCallback<TestObject>() {
            @Override
            public void done(List<TestObject> usr, ParseException e) {
                if(e != null){
                    //Erro
                }else{
                    for(TestObject objUser : usr){
                        //if(usuarios.contains(objUser)){
                            objUser.setStatus(true);
                            objUser.saveInBackground();
                            //Log.d("Entrou", String.valueOf(objUser.getStatus()));
                        //}

                    }
                }
            }
        });*/
    }

    private void rejeitarTodos(){

        Log.d("Entrou", "modificacoa");
        ParseQuery<TestObject> query = new ParseQuery<TestObject>("TestObject");
        query.findInBackground(new FindCallback<TestObject>() {
            @Override
            public void done(List<TestObject> usr, ParseException e) {
                if(e != null){
                    //Erro
                }else{
                    for(TestObject objUser : usr){
                        for (TestObject usuario : usuarios){
                            if(objUser.compareTo(usuario) == 1){
                                //objUser.setStatus(usuario.getStatus());
                                if(usuario.getStatus())
                                    objUser.deleteInBackground();
                                //objUser.saveInBackground();
                                Log.d("Entrou", String.valueOf(objUser.getStatus()));
                            }
                        }
                    }
                }
            }
        });
    }


    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent it = null;
            switch (v.getId()) {
                case R.id.buttonAceitar:
                    aceitarTodos();
                    //it = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                    Toast.makeText(getApplicationContext(), "Todos os usuarios foram aceitos", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.buttonRejeitar:
                    rejeitarTodos();
                    //it = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                    Toast.makeText(getApplicationContext(), "Todos os usuarios foram rejeitados", Toast.LENGTH_SHORT).show();
                    break;
            }
            if (it != null)
                startActivity(it);
        }
    };
}
