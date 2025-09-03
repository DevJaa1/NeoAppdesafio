package com.neoapp.clienteapi.model;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate dataNasc;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String telefone;


    public Cliente(){}

    
    public Cliente(Long id, String name, LocalDate dataNasc, String email, String telefone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.telefone = telefone;
        this.dataNasc = dataNasc;
    }



//GETTERS AND SETTERS
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}


    public String getName() {return name;}
    public void setName(String name) {this.name = name;}


    public LocalDate getDataNasc() {return dataNasc;}
    public void setDataNasc(LocalDate dataNasc) {this.dataNasc = dataNasc;}


    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}


    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}

    //retorna idade com base no nascimento
    public int CalcularIdade() {
        return Period.between(this.dataNasc, LocalDate.now()).getYears();
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    

    


}
