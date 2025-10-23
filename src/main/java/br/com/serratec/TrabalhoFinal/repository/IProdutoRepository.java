package br.com.serratec.TrabalhoFinal.repository;

import br.com.serratec.TrabalhoFinal.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProdutoRepository extends JpaRepository<Produto, Long> {

}
