package br.com.serratec.TrabalhoFinal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;


@Entity
@Table(name = "tb_item_pedido")
@Data
@NoArgsConstructor
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private BigDecimal valorVenda;

    @Column(nullable = false)
    private BigDecimal desconto;

    @Column(nullable = false)
    private Integer quantidade;

    public Double getSubTotal() {
        double valorTotalItens = this.valorVenda.doubleValue() * this.quantidade;
        return valorTotalItens - this.desconto.doubleValue();
    }
}
