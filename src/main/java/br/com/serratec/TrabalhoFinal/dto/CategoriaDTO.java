package br.com.serratec.TrabalhoFinal.dto;


import br.com.serratec.TrabalhoFinal.entity.Categoria;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class CategoriaDTO {
    private Long id;
    private String nome;
    
    
	public CategoriaDTO(Categoria entity) {
		super();
		this.id = entity.getId();
		this.nome = entity.getNome();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

    
    
    
}