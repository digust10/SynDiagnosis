package br.edu.ufcg.embedded.syndiagnosis;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

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
                        usuarios.add(newUser);
                        //}

                    }
                }
                ArrayAdapter<TestObject> adapter = new ArrayAdapter<TestObject>(ParseListActivity.this, android.R.layout.simple_list_item_1, usuarios);
                setListAdapter(adapter);
            }
        });
    }
}
