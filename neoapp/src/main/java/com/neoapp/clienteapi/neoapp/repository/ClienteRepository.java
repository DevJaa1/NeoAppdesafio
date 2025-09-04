package com.neoapp.clienteapi.neoapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.neoapp.clienteapi.neoapp.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    Page<Cliente> findByNameAndEmail(String nome, String email, Pageable pageable);

}
