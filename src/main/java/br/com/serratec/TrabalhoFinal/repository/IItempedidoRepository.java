package br.com.serratec.TrabalhoFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serratec.TrabalhoFinal.entity.ItemPedido;

@Repository
public interface IItempedidoRepository extends JpaRepository<ItemPedido, Long> {
    
}