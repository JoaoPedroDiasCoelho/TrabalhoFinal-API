package br.com.serratec.TrabalhoFinal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.TrabalhoFinal.dto.CategoriaDTO;
import br.com.serratec.TrabalhoFinal.entity.Categoria;
import br.com.serratec.TrabalhoFinal.exception.ResourceNotFoundException;
import br.com.serratec.TrabalhoFinal.repository.ICategoriaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private ICategoriaRepository repository;

    public List<CategoriaDTO> findAll() {
        List<Categoria> list = repository.findAll();
return list;
    }

    public CategoriaDTO findById(Long id) {
        Categoria entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada para o ID: " + id));
        return new CategoriaDTO(entity);
    }

    public CategoriaDTO insert(CategoriaDTO dto) {
        Categoria entity = new Categoria();
        entity.setNome(dto.getNome());
        entity = repository.save(entity);
        return new CategoriaDTO(entity);
    }

    public CategoriaDTO update(Long id, CategoriaDTO dto) {
        try {
            Categoria entity = repository.getReferenceById(id);
            entity.setNome(dto.getNome());
            entity = repository.save(entity);
            return new CategoriaDTO(entity);
        } catch (jakarta.persistence.EntityNotFoundException e) {
            throw new ResourceNotFoundException("ID " + id + " não encontrado para atualização.");
        }
    }
}