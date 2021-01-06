package br.com.gsc.vendasbatch;

import br.com.gsc.vendasbatch.model.Arquivo;
import br.com.gsc.vendasbatch.model.Cliente;
import br.com.gsc.vendasbatch.model.ItemPedido;
import br.com.gsc.vendasbatch.model.Pedido;
import br.com.gsc.vendasbatch.model.Vendedor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CenarioFactory {

    public static Vendedor criarVendedorJose() {
        return Vendedor.builder().nome("Jose").cpf("4130493204").salario(BigDecimal.ONE)
                .vendas(new ArrayList<>()).build();
    }

    public static Vendedor criarVendedorPaulo() {
        return Vendedor.builder().nome("Paulo").cpf("3432545453").salario(BigDecimal.ONE)
                .vendas(new ArrayList<>()).build();
    }

    public static Cliente criarCliente(String nomeCliente) {
        return Cliente.builder().area("Teste").cnpj("4234234").nome(nomeCliente).build();
    }

    public static Pedido criarPedido() {
        return Pedido.builder().id(new Random().nextInt())
                .itens(List.of(criarItem())).build();
    }

    public static ItemPedido criarItem() {
        final var random = new Random();
        return ItemPedido.builder().idItem(random.nextInt()).quantidade(random.nextInt()).preco(BigDecimal.ONE).build();
    }
}
