package br.com.serratec.TrabalhoFinal.dto;

import java.math.BigDecimal;

import br.com.serratec.TrabalhoFinal.entity.Produto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdutoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;

    private Long categoriaId;
    private String categoriaNome;

    public ProdutoDTO(Produto entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.descricao = entity.getDescricao();
        this.preco = entity.getPreco();

        if (entity.getCategoria() != null) {
            this.categoriaId = entity.getCategoria().getId();
            this.categoriaNome = entity.getCategoria().getNome();
        }
    }
}