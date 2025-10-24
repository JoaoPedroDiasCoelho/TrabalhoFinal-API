package br.com.serratec.TrabalhoFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serratec.TrabalhoFinal.entity.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {
}