package br.edu.ufcg.embedded.syndiagnosis;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by digust10 on 18/08/2015.
 */
@ParseClassName("TestObject")
public class TestObject extends ParseObject {
    public String getName(){
        return getString("Name");
    }

    public void setName(String name){
        put("Name", name);
    }

    public String getEmail(){
        return getString("Email");
    }

    public void setEmail(String email){
        put("Email", email);
    }

    public Boolean getStatus(){
        return getBoolean("Status");
    }

    public void setStatus(Boolean status){
        put("Status", status);
    }

    @Override
    public String toString(){
        return getName() + "\n" + getEmail();
    }
}
