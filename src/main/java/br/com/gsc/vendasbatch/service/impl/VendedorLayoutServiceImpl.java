package br.com.gsc.vendasbatch.service.impl;

import br.com.gsc.vendasbatch.model.Arquivo;
import br.com.gsc.vendasbatch.model.Vendedor;
import br.com.gsc.vendasbatch.service.LayoutService;

import java.math.BigDecimal;

public class VendedorLayoutServiceImpl implements LayoutService {

    @Override
    public void processar(String[] partes, Arquivo consolidado) {
        final var vendedor = new Vendedor();
        vendedor.setCpf(partes[1]);
        vendedor.setNome(partes[2]);
        vendedor.setSalario(new BigDecimal(partes[3]));
        consolidado.addVendedor(vendedor);
    }
}
