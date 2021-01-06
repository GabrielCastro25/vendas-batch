package br.com.gsc.vendasbatch.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumoVenda {
    private Integer qtdClientes;
    private Integer qtdVendedores;
    private Integer idMaiorPedido;
    private String piorVendedor;
}
