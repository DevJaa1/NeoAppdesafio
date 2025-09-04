package com.neoapp.clienteapi.neoapp.dto;

import java.time.LocalDate;

public class ClienteUpdateDTO {

    private String nome;
    private String telefone;
    private String email;
    private LocalDate dataNasc;

    public ClienteUpdateDTO(){}

    public ClienteUpdateDTO(String nome, String telefone, String email, LocalDate dataNasc) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataNasc = dataNasc;
    }

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public LocalDate getDataNasc() {return dataNasc;}
    public void setDataNasc(LocalDate dataNasc) {this.dataNasc = dataNasc;}

    


}
