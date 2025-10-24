package br.com.serratec.TrabalhoFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serratec.TrabalhoFinal.entity.Pedido;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Long> {
}
