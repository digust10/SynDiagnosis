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
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import br.edu.ufcg.embedded.syndiagnosis.Adapters.ParseListAdapter;

/**
 * Created by digust10 on 18/08/2015.
 */
public class ParseListActivity extends ListActivity {
    List<ParseUser> usuarios = new ArrayList<ParseUser>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parse_list);

        ((Button) findViewById(R.id.buttonAceitar)).setOnClickListener(onClick);

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> list, ParseException e) {
                if(e != null){
                    //Erro
                }else{
                    for(ParseUser objUser : list){
                        //if(!objUser.getStatus()){ //o admin so pega os usuarios que ainda precisam de autorizacao
                        ParseUser newUser = new ParseUser();
                        newUser.setEmail(objUser.getEmail());
                        newUser.setUsername(objUser.getUsername());
                        newUser.put("status",objUser.get("status"));
                        //usuarios.add(newUser);
                        //So quem esta requisitando acesso
                        if(objUser.get("status") == false){
                            usuarios.add(newUser);
                        }
                        //}

                    }
                }
                ArrayAdapter<ParseUser> adapter = new ParseListAdapter(ParseListActivity.this, R.layout.item_parse_list, usuarios);//simple_list_item_multiple_choice
                setListAdapter(adapter);
            }
        });
    }

    private int compareToUser(ParseUser testObject , ParseUser otherUser) {
        if(testObject.getEmail().equals(otherUser.getEmail())){
            if(testObject.getUsername().equals(otherUser.getUsername())){
                return 1;
            }
        }
        return -1;
    }

    private void aceitarTodos(){

        /*for(TestObject aceitaveis : usuarios){

            //aceitaveis.setStatus(true);
            aceitaveis.saveInBackground();
        }*/
        Log.d("Entrou", "modificacoa");
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> usr, ParseException e) {
                if(e != null){
                    //Erro
                }else{
                    for(ParseUser objUser : usr){
                        for (ParseUser usuario : usuarios){
                            if(compareToUser(usuario, objUser) == 1){
                                objUser.put("status",usuario.getBoolean("status"));
                                objUser.saveInBackground();
                                Log.d("Entrou", String.valueOf(objUser.getBoolean("status")));
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
            }
            if (it != null)
                startActivity(it);
        }
    };
}
