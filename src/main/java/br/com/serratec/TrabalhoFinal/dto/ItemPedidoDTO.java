package br.com.serratec.TrabalhoFinal.dto;

import br.com.serratec.TrabalhoFinal.entity.ItemPedido;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemPedidoDTO {

    private Long id;
    private Long produtoId;
    private String produtoNome;

    private BigDecimal valorVenda;
    private BigDecimal desconto;
    private Integer quantidade;
    private Double subTotal;

    public ItemPedidoDTO(ItemPedido entity) {
        this.id = entity.getId();
        this.produtoId = entity.getProduto().getId();
        this.produtoNome = entity.getProduto().getNome();
        this.valorVenda = entity.getValorVenda();
        this.desconto = entity.getDesconto();
        this.quantidade = entity.getQuantidade();
        this.subTotal = entity.getSubTotal();
    }
}