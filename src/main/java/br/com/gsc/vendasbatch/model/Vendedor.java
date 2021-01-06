package br.com.gsc.vendasbatch.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@ToString
public class Vendedor {

    @Setter
    private String cpf;

    @Setter
    private String nome;

    @Setter
    private BigDecimal salario;

    private final List<Pedido> vendas;

    public Vendedor() {
        this.vendas = new ArrayList<>();
    }

    public Pedido getMaiorVenda() {
        return this.vendas.stream().max(Comparator.comparing(Pedido::getTotalPedido)).orElse(null);
    }

    public BigDecimal getTotalVendas() {
        return this.vendas.stream().map(Pedido::getTotalPedido).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
