package com.neoapp.clienteapi.neoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neoapp.clienteapi.neoapp.dto.ClienteDTO;
import com.neoapp.clienteapi.neoapp.dto.ClienteUpdateDTO;
import com.neoapp.clienteapi.neoapp.model.Cliente;
import com.neoapp.clienteapi.neoapp.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "API para Gerenciamento de Clientes pessoa física")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteServ) {
        this.clienteService = clienteServ;
    }

    @Operation(summary = "Cadastrar novo Cliente")
    @PostMapping
    public ResponseEntity<ClienteDTO> cadastrarNovoCliente(@RequestBody Cliente cliente){
        ClienteDTO dto = clienteService.criarClientes(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        
    }

    @Operation(summary = "Atualizar cliente existente")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> attCliente(@PathVariable Long id, @RequestBody ClienteUpdateDTO dto) {
        ClienteDTO att = clienteService.atualizarCliente(id, dto);
        return ResponseEntity.ok(att);
    }
    
    @Operation(summary = "Listar clientes (Paginado e com Busca)")
    @GetMapping
    public ResponseEntity<Page<ClienteDTO>> listarClientes(@RequestParam(required = false) String busca, Pageable pageable) {
        Page<ClienteDTO> clientes = clienteService.buscarCliente(busca, pageable);
        return ResponseEntity.ok(clientes);

    }

    @Operation(summary = "Buscar cliente por ID")
    @GetMapping("/{id}")
    public ResponseEntity <ClienteDTO> buscarById (@PathVariable Long id) {
        ClienteDTO cliente = clienteService.buscarClienteById(id);
        return ResponseEntity.ok(cliente);
    }
    
    @Operation(summary = "Excluir cliente")
    @DeleteMapping("/{id}")
    public ResponseEntity <Void> excluirCliente (@PathVariable Long id) {
        clienteService.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }

   

}
