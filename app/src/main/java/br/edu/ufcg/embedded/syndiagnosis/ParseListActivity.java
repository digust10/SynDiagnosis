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
                ArrayAdapter<TestObject> adapter = new ArrayAdapter<TestObject>(ParseListActivity.this, android.R.layout.simple_list_item_1, usuarios);//simple_list_item_multiple_choice
                setListAdapter(adapter);
            }
        });
    }

    private void aceitarTodos(){
        /*for(TestObject aceitaveis : usuarios){
            aceitaveis.setStatus(true);
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
                        //if(usuarios.contains(objUser)){
                            objUser.setStatus(true);
                            objUser.saveInBackground();
                            //Log.d("Entrou", String.valueOf(objUser.getStatus()));
                        //}

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
                    it = new Intent(getApplicationContext(), login.class);
                    Toast.makeText(getApplicationContext(), "Todos os usuarios foram aceitos", Toast.LENGTH_SHORT).show();
                    break;
            }
            if (it != null)
                startActivity(it);
        }
    };
}
