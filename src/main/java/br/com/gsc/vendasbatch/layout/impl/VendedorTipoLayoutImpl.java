package br.com.gsc.vendasbatch.layout.impl;

import br.com.gsc.vendasbatch.model.Arquivo;
import br.com.gsc.vendasbatch.model.Vendedor;
import br.com.gsc.vendasbatch.layout.TipoLayout;

import java.math.BigDecimal;

public class VendedorTipoLayoutImpl implements TipoLayout {

    @Override
    public void processar(final String[] partes, final Arquivo consolidado) {
        final var vendedor = new Vendedor();
        vendedor.setCpf(partes[1]);
        vendedor.setNome(partes[2]);
        vendedor.setSalario(new BigDecimal(partes[3]));
        consolidado.addVendedor(vendedor);
    }
}
