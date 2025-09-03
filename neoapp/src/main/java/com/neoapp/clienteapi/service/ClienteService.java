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
    public ClienteService(ClienteRepository clienteRepositorio) {
        this.clienteRepo = clienteRepositorio;
    }

    // Calcula a idade a partir da data de nascimento
    public int calcularIdade(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            return 0;
        }
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    // Cria um novo cliente
    public ClienteDTO criarClientes(Cliente cliente) {
        Cliente salvo = clienteRepo.save(cliente);
        return toDTO(salvo);
    }

    // Atualiza cliente existente
    public ClienteDTO atualizarCliente(Long id, ClienteUpdateDTO clienteAtt) {
        Cliente cliente = clienteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        if (clienteAtt.getNome() != null) cliente.setName(clienteAtt.getNome());
        if (clienteAtt.getEmail() != null) cliente.setEmail(clienteAtt.getEmail());
        if (clienteAtt.getDataNasc() != null) cliente.setDataNasc(clienteAtt.getDataNasc());
        if (clienteAtt.getTelefone() != null) cliente.setTelefone(clienteAtt.getTelefone());

        Cliente atualizado = clienteRepo.save(cliente);
        return toDTO(atualizado);
    }

    // Busca clientes paginados e filtrados por nome/email
    public Page<ClienteDTO> buscarCliente(String busca, Pageable pageable) {
        Page<Cliente> page;
        if (busca == null || busca.isEmpty()) {
            page = clienteRepo.findAll(pageable);
        } else {
            page = clienteRepo.findByNameAndEmail(busca, busca, pageable);
        }
        return page.map(this::toDTO);
    }

    // Busca cliente por ID
    public ClienteDTO buscarClienteById(Long id) {
        Cliente cliente = clienteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado por esse ID: " + id));
        return toDTO(cliente);
    }

    // Exclui cliente por ID
    public void excluirCliente(Long id) {
        if (!clienteRepo.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado por esse ID: " + id);
        }
        clienteRepo.deleteById(id);
    }

    // Converte Cliente para ClienteDTO
    private ClienteDTO toDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getName(),
                cliente.getTelefone(),
                cliente.getEmail(),
                calcularIdade(cliente.getDataNasc()), 
                cliente.getDataNasc()
        );
    }
}
