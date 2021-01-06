package br.com.gsc.vendasbatch.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Cliente {
    private String cnpj;
    private String nome;
    private String area;
}
