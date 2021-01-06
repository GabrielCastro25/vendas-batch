package br.com.gsc.vendasbatch.service;

import br.com.gsc.vendasbatch.CenarioFactory;
import br.com.gsc.vendasbatch.model.Arquivo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ArquivoServiceTest {

    @InjectMocks
    ArquivoService service;

    @Test
    public void popularArquivo_comDadosVendedor_esperadoSucesso() {
        final var consolidado = new Arquivo();
        final var linha = "001ç1234567891234çPedroç50000";
        this.service.popularArquivo(consolidado, linha);
        Assert.assertNotNull(consolidado.getVendedores());
        Assert.assertEquals(consolidado.getTotalVendedores().longValue(), 1l);
    }

    @Test
    public void popularArquivo_comDadosCliente_esperadoSucesso() {
        final var consolidado = new Arquivo();
        final var linha = "002ç2345675434544345çJose da SilvaçRural";
        this.service.popularArquivo(consolidado, linha);
        Assert.assertNotNull(consolidado.getTotalClientes());
        Assert.assertEquals(consolidado.getTotalClientes().longValue(), 1l);
    }

    @Test
    public void popularArquivo_comDadosVenda_esperadoSucesso() {
        final var consolidado = new Arquivo();
        consolidado.addVendedor(CenarioFactory.criarVendedorPaulo());
        final var linha = "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo";
        this.service.popularArquivo(consolidado, linha);
        Assert.assertNotNull(consolidado.getVendedores());
        Assert.assertNotNull(consolidado.getVendedores().get(0).getVendas());
        Assert.assertEquals(consolidado.getVendedores().get(0).getVendas().size(), 1l);
    }

    @Test
    public void gerarResumo_comArquivoValido_esperadoSucesso() {
        final var consolidado = new Arquivo();
        final var vendedor1 = CenarioFactory.criarVendedorPaulo();
        final var vendedor2 = CenarioFactory.criarVendedorJose();
        consolidado.addVendedor(vendedor1);
        consolidado.addVendedor(vendedor2);
        consolidado.addCliente(CenarioFactory.criarCliente("Cliente 1"));
        consolidado.addCliente(CenarioFactory.criarCliente("Cliente 2"));
        consolidado.addPedido(vendedor1.getNome(), CenarioFactory.criarPedido());

        final var resumo = this.service.gerarResumo(consolidado);
        Assert.assertNotNull(resumo);
        Assert.assertEquals(resumo.getQtdClientes().longValue(), 2l);
        Assert.assertEquals(resumo.getQtdVendedores().longValue(), 2l);
        Assert.assertEquals(resumo.getPiorVendedor(), vendedor2.getNome());
        Assert.assertNotNull(resumo.getIdMaiorPedido());
    }
}
