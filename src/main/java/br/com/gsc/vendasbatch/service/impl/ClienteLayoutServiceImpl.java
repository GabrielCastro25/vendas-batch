package br.com.gsc.vendasbatch.service.impl;

import br.com.gsc.vendasbatch.model.Cliente;
import br.com.gsc.vendasbatch.model.Arquivo;
import br.com.gsc.vendasbatch.service.LayoutService;

public class ClienteLayoutServiceImpl implements LayoutService {

    @Override
    public void processar(String[] partes, Arquivo consolidado) {
        final var cliente = new Cliente();
        cliente.setCnpj(partes[1]);
        cliente.setNome(partes[2]);
        cliente.setArea(partes[3]);
        consolidado.addCliente(cliente);
    }
}
