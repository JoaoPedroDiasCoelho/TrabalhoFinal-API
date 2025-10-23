package br.com.serratec.TrabalhoFinal.dto;

import java.time.Instant;

import lombok.Data;

@Data
public class ErroDTO {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
