package br.com.gsc.vendasbatch.layout.impl;

import br.com.gsc.vendasbatch.model.Cliente;
import br.com.gsc.vendasbatch.model.Arquivo;
import br.com.gsc.vendasbatch.layout.TipoLayout;

public class ClienteTipoLayoutImpl implements TipoLayout {

    @Override
    public void processar(final String[] partes, final Arquivo consolidado) {
        final var cliente = new Cliente();
        cliente.setCnpj(partes[1]);
        cliente.setNome(partes[2]);
        cliente.setArea(partes[3]);
        consolidado.addCliente(cliente);
    }
}
