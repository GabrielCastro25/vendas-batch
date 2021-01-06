package br.com.gsc.vendasbatch.service;

import br.com.gsc.vendasbatch.layout.LayoutEnum;
import br.com.gsc.vendasbatch.model.Arquivo;
import br.com.gsc.vendasbatch.model.Pedido;
import br.com.gsc.vendasbatch.model.ResumoVenda;
import br.com.gsc.vendasbatch.model.Vendedor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/*
 * Classe responsavel regras de negocios do arquivo
 */
@Service
@Slf4j
public class ArquivoService {

    public void popularArquivo(final Arquivo consolidado, String linha) {
        final var partes = linha.split("ç");
        final var layout = partes[0];
        LayoutEnum.interpretarLinhas(layout, partes, consolidado);
    }

    public ResumoVenda gerarResumo(Arquivo arquivo) {
        final var resumo = new ResumoVenda();
        resumo.setQtdVendedores(arquivo.getTotalVendedores());
        resumo.setQtdClientes(arquivo.getTotalClientes());

        Pedido maiorPedido = null;
        Vendedor piorVendedor = null;
        for (Vendedor vendedor: arquivo.getVendedores()) {
            // valida se a ocorrencia e maior que o maior pedido corrente
            if (maiorPedido == null ||
                 (vendedor.getMaiorVenda() != null && vendedor.getMaiorVenda().getTotalPedido().compareTo(maiorPedido.getTotalPedido()) > 0)) {
                maiorPedido = vendedor.getMaiorVenda();
            }

            // valida se a ocorrencia e menor que o atual menor corrente
            if (piorVendedor == null || vendedor.getTotalVendas().compareTo(piorVendedor.getTotalVendas()) < 0) {
                piorVendedor = vendedor;
            }
        }

        resumo.setPiorVendedor(piorVendedor.getNome());
        resumo.setIdMaiorPedido(maiorPedido.getId());

        return resumo;
    }

}
