package br.com.serratec.TrabalhoFinal.dto;

import br.com.serratec.TrabalhoFinal.entity.Cliente;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteDTO {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    public ClienteDTO(Cliente entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.telefone = entity.getTelefone();
        this.email = entity.getEmail();
        this.cpf = entity.getCpf();
        this.cep = entity.getCep();
        this.logradouro = entity.getLogradouro();
        this.bairro = entity.getBairro();
        this.localidade = entity.getLocalidade();
        this.uf = entity.getUf();
    }
}