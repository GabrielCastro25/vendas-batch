package br.com.gsc.vendasbatch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@ToString
public class Pedido {
    private Integer id;
    private List<ItemPedido> itens;

    public BigDecimal getTotalPedido() {
        return this.itens.stream().map(ItemPedido::getValorItem).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
