package com.pdv.projectX.services;

import com.pdv.projectX.dtos.CadastrarClienteDTO;
import com.pdv.projectX.entities.Cliente;
import com.pdv.projectX.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {


    private final ClienteRepository clienteRepository;


    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    //Cadastrar cliente!
    @Transactional
    public Cliente cadastrarCliente(CadastrarClienteDTO dto) {
       Cliente c = new Cliente();
       c.setNome(dto.nome());
       c.setNumeroTelefone(dto.numeroTelefone());

       Cliente clienteSalvo = clienteRepository.save(c);
       return clienteSalvo;
    }


}
