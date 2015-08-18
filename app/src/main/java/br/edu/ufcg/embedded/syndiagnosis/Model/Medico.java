package br.edu.ufcg.embedded.syndiagnosis.Model;

import java.io.Serializable;

/**
 * Created by Nicolas on 18/08/2015.
 */
public class Medico implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;
    private String name;
    private String email;
    private String password;
    private String crm;
    private String cpf;

    public Medico(){

    }

    public Medico(String name,String email,String password,String cpf,String crm){
        this.name = name;
        this.cpf = cpf;
        this.crm = crm;
        this.email = email;
        this.password = password;

    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCrm() {
        return crm;
    }
    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
