package br.com.serratec.TrabalhoFinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.serratec.TrabalhoFinal.dto.ClienteDTO;
import br.com.serratec.TrabalhoFinal.entity.Cliente;
import br.com.serratec.TrabalhoFinal.exception.DatabaseException;
import br.com.serratec.TrabalhoFinal.exception.ResourceNotFoundException;
import br.com.serratec.TrabalhoFinal.repository.IClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private IClienteRepository repository;

    private void copyDtoToEntity(ClienteDTO dto, Cliente entity) {
        entity.setNome(dto.getNome());
        entity.setTelefone(dto.getTelefone());
        entity.setEmail(dto.getEmail());
        entity.setCpf(dto.getCpf());
        
        entity.setCep(dto.getCep());
        entity.setLogradouro(dto.getLogradouro());
        entity.setBairro(dto.getBairro());
        entity.setLocalidade(dto.getLocalidade());
        entity.setUf(dto.getUf());
    }

    public Page<ClienteDTO> findAllPaged(Pageable pageable) {
        Page<Cliente> page = repository.findAll(pageable);
        return page.map(ClienteDTO::new);
    }

    public ClienteDTO findById(Long id) {
        Optional<Cliente> obj = repository.findById(id);
        Cliente entity = obj.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        return new ClienteDTO(entity);
    }

    public ClienteDTO insert(ClienteDTO dto) {
        Cliente entity = new Cliente();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClienteDTO(entity);
    }

    public ClienteDTO update(Long id, ClienteDTO dto) {
        try {
            Cliente entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ClienteDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("ID não encontrado");
        }
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("ID não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Cliente possui pedidos");
        }
    }
}