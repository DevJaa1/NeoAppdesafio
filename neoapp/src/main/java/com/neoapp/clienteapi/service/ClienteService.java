package com.neoapp.clienteapi.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.neoapp.clienteapi.dto.ClienteDTO;
import com.neoapp.clienteapi.dto.ClienteUpdateDTO;
import com.neoapp.clienteapi.model.Cliente;
import com.neoapp.clienteapi.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepo;

    @Autowired
    public ClienteService (ClienteRepository clienteRepositorio) {
        this.clienteRepo = clienteRepositorio;
    }

     public int calcularIdade(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            return 0;
        }
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public ClienteDTO criarClientes (Cliente cliente) {
        Cliente salvo = clienteRepo.save(cliente);
        return toDTO(salvo);
        
    }

    public ClienteDTO atualizarCliente(Long id, ClienteUpdateDTO clienteatt){
        Cliente cliente = clienteRepo.findById(id).orElseThrow(() -> 
        new RuntimeException("Cliente não encontrado!"));

        if(clienteatt.getNome()!= null) cliente.setName(clienteatt.getNome());
        if(clienteatt.getEmail()!= null) cliente.setEmail(clienteatt.getEmail());
        if(clienteatt.getDataNasc()!=null) cliente.setDataNasc(clienteatt.getDataNasc());
        if(cliente.getTelefone()!=null) cliente.setTelefone(clienteatt.getTelefone());

        Cliente atualizado = clienteRepo.save(cliente);
        return toDTO(atualizado);
    }

    
    public Page<ClienteDTO> buscarCliente(String busca, Pageable pageable) {
        
        Page <Cliente> page;
        if(busca == null || busca.isEmpty()) {
            page = clienteRepo.findAll(pageable);
        }else {
            page = clienteRepo.findByNameAndEmail(busca, busca, pageable);
        }
        return page.map(this::toDTO);   
    }

    public ClienteDTO buscarClienteById(Long id) {
        Cliente cliente = clienteRepo.findById(id).orElseThrow(() -> 
        new RuntimeException("Cliente não encontrado por esse ID: " + id));

        return toDTO(cliente);
    }

    public void excluirCliente(Long id) {
        if(!clienteRepo.existsById(id)){
            throw new RuntimeException("Cliente não encontrado por esse ID:" + id);
        }
        clienteRepo.deleteById(id);
    }

    private ClienteDTO toDTO (Cliente cliente) {
        return new ClienteDTO(
            cliente.getId(),
            cliente.getName(),
            cliente.getTelefone(),
            cliente.getEmail(),
            cliente.CalcularIdade(),
            cliente.getDataNasc()
        );
    }




}
