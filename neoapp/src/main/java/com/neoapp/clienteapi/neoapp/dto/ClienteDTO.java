package com.neoapp.clienteapi.neoapp.dto;

import java.time.LocalDate;

public class ClienteDTO {

    private Long id;
    private String name;
    private String telefone;
    private String email;
    private int idade;
    private LocalDate dataNasc;

    public ClienteDTO(){}

    

    public ClienteDTO(Long id, String name, String telefone, String email, int idade, LocalDate dataNasc) {
        this.id = id;
        this.name = name;
        this.telefone = telefone;
        this.email = email;
        this.idade = idade;
        this.dataNasc = dataNasc;
    }


    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNome() {return name;}
    public void setNome(String name) {this.name = name;}

    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public int getIdade() {return idade;}
    public void setIdade(int idade) {this.idade = idade;}

    public LocalDate getDataNasc() {return dataNasc;}
    public void setDataNasc(LocalDate dataNasc) {this.dataNasc = dataNasc;}

    

}
