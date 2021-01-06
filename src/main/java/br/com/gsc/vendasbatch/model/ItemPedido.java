package br.com.gsc.vendasbatch.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class ItemPedido {
    private Integer idItem;
    private Integer quantidade;
    private BigDecimal preco;

    public BigDecimal getValorItem() {
        return this.preco.multiply(BigDecimal.valueOf(quantidade));
    }
}
