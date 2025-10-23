package br.com.serratec.TrabalhoFinal.dto;


import br.com.serratec.TrabalhoFinal.entity.Categoria;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoriaDTO {
    private Long id;
    private String nome;

    public CategoriaDTO(Categoria entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
    }
}