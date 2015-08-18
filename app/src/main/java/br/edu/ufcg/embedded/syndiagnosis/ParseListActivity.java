package br.edu.ufcg.embedded.syndiagnosis;

import android.app.ListActivity;
import android.os.Bundle;

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
    //List<TestObject> usuario = new ArrayList<TestObject>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        ParseQuery<TestObject> query = new ParseQuery<TestObject>("TestObject");
        query.findInBackground(new FindCallback<TestObject>() {
            @Override
            public void done(List<TestObject> list, ParseException e) {

            }
        });
    }
}
