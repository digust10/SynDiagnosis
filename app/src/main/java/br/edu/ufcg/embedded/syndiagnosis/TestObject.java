package br.edu.ufcg.embedded.syndiagnosis;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by digust10 on 18/08/2015.
 */
@ParseClassName("TestObject")
public class TestObject extends ParseObject implements Comparable<TestObject>{
    public String getNome(){
        return getString("Nome");
    }

    public void setNome(String name){
        put("Nome", name);
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
        return getNome() + "\n" + getEmail();
    }

   @Override
    public int compareTo(TestObject testObject) {
        if(testObject.getEmail().equals(this.getEmail())){
            if(testObject.getNome().equals(this.getNome())){
                return 1;
            }
        }
        return -1;
    }
}
