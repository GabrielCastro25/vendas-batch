package br.com.gsc.vendasbatch.layout.impl;

import br.com.gsc.vendasbatch.model.ItemPedido;
import br.com.gsc.vendasbatch.model.Pedido;
import br.com.gsc.vendasbatch.model.Arquivo;
import br.com.gsc.vendasbatch.layout.TipoLayout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendaTipoLayoutImpl implements TipoLayout {

    @Override
    public void processar(final String[] partes, final Arquivo consolidado) {
        final var pedido = new Pedido();
        pedido.setId(Integer.parseInt(partes[1]));
        final var vendedor = partes[3];
        pedido.setItens(this.criarItens(partes[2]));
        consolidado.addPedido(vendedor, pedido);
    }

    private List<ItemPedido> criarItens(final String parteListaItens) {
        List<ItemPedido> listaRetorno = new ArrayList<>();
        final var itensString = parteListaItens.replace("[", "").replace("]", "").split(",");
        ItemPedido itemPedido = null;
        for (String parteItem: itensString) {
            itemPedido = new ItemPedido();
            final var itemString = parteItem.split("-");
            itemPedido.setIdItem(Integer.parseInt(itemString[0]));
            itemPedido.setQuantidade(Integer.parseInt(itemString[1]));
            itemPedido.setPreco(new BigDecimal(itemString[2]));
            listaRetorno.add(itemPedido);
        }

        return listaRetorno;
    }
}
