package br.com.serratec.TrabalhoFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.serratec.TrabalhoFinal.entity.Categoria;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
}