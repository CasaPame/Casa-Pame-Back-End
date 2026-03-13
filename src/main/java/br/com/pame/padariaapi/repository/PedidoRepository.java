package br.com.pame.padariaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pame.padariaapi.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByClienteIdOrderByIdDesc(Long clienteId);
} 