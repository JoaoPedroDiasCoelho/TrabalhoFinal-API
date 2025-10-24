package br.com.serratec.TrabalhoFinal.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.serratec.TrabalhoFinal.dto.ProdutoDTO;
import br.com.serratec.TrabalhoFinal.entity.Categoria;
import br.com.serratec.TrabalhoFinal.entity.Produto;
import br.com.serratec.TrabalhoFinal.exception.DatabaseException;
import br.com.serratec.TrabalhoFinal.exception.ResourceNotFoundException;
import br.com.serratec.TrabalhoFinal.repository.ICategoriaRepository;
import br.com.serratec.TrabalhoFinal.repository.IProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private IProdutoRepository repository;

    @Autowired
    private ICategoriaRepository categoriaRepository;

    // --- Helper de Mapeamento (Simples) ---
    private void copyDtoToEntity(ProdutoDTO dto, Produto entity) {
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());

        // Garante que o ID da Categoria foi fornecido
        if (dto.getCategoriaId() == null) {
            throw new DatabaseException("O produto deve estar atrelado a uma categoria (categoriaId é obrigatório).");
        }

        // Busca a Categoria (Validação de Existência)
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria ID " + dto.getCategoriaId() + " não encontrada."));

        // Associa a Categoria ao Produto
        entity.setCategoria(categoria);
    }
    // -------------------------------------------

    // READ ALL (Com Paginação)
    public Page<ProdutoDTO> findAllPaged(Pageable pageable) {
        Page<Produto> page = repository.findAll(pageable);
        return page.map(ProdutoDTO::new);
    }

    // READ BY ID
    public ProdutoDTO findById(Long id) {
        Optional<Produto> obj = repository.findById(id);
        Produto entity = obj.orElseThrow(() -> new ResourceNotFoundException("Produto ID " + id + " não encontrado."));
        return new ProdutoDTO(entity);
    }

    // INSERT (POST)
    public ProdutoDTO insert(ProdutoDTO dto) {
        Produto entity = new Produto();
        copyDtoToEntity(dto, entity); // Validação de Categoria ocorre aqui
        entity = repository.save(entity);
        return new ProdutoDTO(entity);
    }

    // UPDATE (PUT)
    public ProdutoDTO update(Long id, ProdutoDTO dto) {
        try {
            Produto entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity); // Validação de Categoria ocorre aqui
            entity = repository.save(entity);
            return new ProdutoDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("ID " + id + " não encontrado para atualização.");
        }
    }

    // DELETE (DELETE)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("ID " + id + " não encontrado para exclusão.");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            // Lidar com o erro de integridade de dados (ex: produto associado a pedidos)
            throw new DatabaseException("Violação de Integridade: Produto ID " + id + " possui pedidos associados.");
        }
    }
}