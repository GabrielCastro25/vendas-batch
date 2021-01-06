package br.com.gsc.vendasbatch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.batch.core.annotation.AfterRead;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemPedido {
    private Integer idItem;
    private Integer quantidade;
    private BigDecimal preco;

    public BigDecimal getValorItem() {
        return this.preco.multiply(BigDecimal.valueOf(quantidade));
    }
}
