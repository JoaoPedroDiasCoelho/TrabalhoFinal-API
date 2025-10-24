package br.com.serratec.TrabalhoFinal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import br.com.serratec.TrabalhoFinal.entity.Pedido;
import br.com.serratec.TrabalhoFinal.utils.StatusPedido;

@Data
@NoArgsConstructor
public class PedidoDTO {

    private Long id;
    private Instant data;

    private Long clienteId;
    private String clienteNome;

    private StatusPedido status;

    private Double total;

    private List<ItemPedidoDTO> itens;

    public PedidoDTO(Pedido entity) {
        this.id = entity.getId();
        this.data = entity.getData();
        this.status = entity.getStatus();
        this.total = entity.getTotal();

        this.clienteId = entity.getCliente().getId();
        this.clienteNome = entity.getCliente().getNome();

        this.itens = entity.getItens().stream()
                .map(ItemPedidoDTO::new).collect(Collectors.toList());
    }
}