package br.com.serratec.TrabalhoFinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.serratec.TrabalhoFinal.dto.ItemPedidoDTO;
import br.com.serratec.TrabalhoFinal.dto.PedidoDTO;
import br.com.serratec.TrabalhoFinal.entity.Cliente;
import br.com.serratec.TrabalhoFinal.entity.ItemPedido;
import br.com.serratec.TrabalhoFinal.entity.Pedido;
import br.com.serratec.TrabalhoFinal.entity.Produto;
import br.com.serratec.TrabalhoFinal.exception.ResourceNotFoundException;
import br.com.serratec.TrabalhoFinal.repository.IClienteRepository;
import br.com.serratec.TrabalhoFinal.repository.IItempedidoRepository;
import br.com.serratec.TrabalhoFinal.repository.IPedidoRepository;
import br.com.serratec.TrabalhoFinal.repository.IProdutoRepository;
import br.com.serratec.TrabalhoFinal.utils.StatusPedido;
import jakarta.persistence.EntityNotFoundException;
import java.time.Instant;

@Service
public class PedidoService {

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IProdutoRepository produtoRepository;

    @Autowired
    private IItempedidoRepository itemPedidoRepository;

    @Transactional(readOnly = true)
    public Page<PedidoDTO> findAllPaged(Pageable pageable) {
        Page<Pedido> page = pedidoRepository.findAll(pageable);
        return page.map(PedidoDTO::new);
    }

    @Transactional(readOnly = true)
    public PedidoDTO findById(Long id) {
        Pedido entity = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido ID " + id + " não encontrado."));
        return new PedidoDTO(entity);
    }

    @Transactional
    public PedidoDTO insert(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setData(Instant.now());
        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente ID " + dto.getClienteId() + " não encontrado."));
        pedido.setCliente(cliente);

        pedido = pedidoRepository.save(pedido);

        for (ItemPedidoDTO itemDto : dto.getItens()) {
            Produto produto = produtoRepository.findById(itemDto.getProdutoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto ID " + itemDto.getProdutoId() + " não encontrado."));

            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProduto(produto);

            item.setQuantidade(itemDto.getQuantidade());
            item.setValorVenda(itemDto.getValorVenda());
            item.setDesconto(itemDto.getDesconto());

            pedido.getItens().add(item);
        }

        pedido = pedidoRepository.save(pedido);

        return new PedidoDTO(pedido);
    }

    @Transactional
    public PedidoDTO updateStatus(Long id, String novoStatus) {
        try {
            Pedido pedido = pedidoRepository.getReferenceById(id);

            StatusPedido status = StatusPedido.valueOf(novoStatus.toUpperCase());

            pedido.setStatus(status);

            pedido = pedidoRepository.save(pedido);
            return new PedidoDTO(pedido);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Pedido ID " + id + " não encontrado para atualização.");
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Status '" + novoStatus + "' inválido. Status válidos: AGUARDANDO_PAGAMENTO, PAGO, ENVIADO, ENTREGUE, CANCELADO.");
        }
    }
}