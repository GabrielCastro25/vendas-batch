package br.com.gsc.vendasbatch.model;

import lombok.ToString;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString
public class Arquivo {

    private final Map<String, Vendedor> mapVendedores;
    private final List<Cliente> clientes;

    public Arquivo() {
        this.mapVendedores = new HashMap<>();
        this.clientes = new ArrayList<>();
    }

    public void addVendedor(final Vendedor vendedor) {
        this.mapVendedores.put(vendedor.getNome(), vendedor);
    }

    public void addCliente(final Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void addPedido(final String nomeVendedor, final Pedido pedido) {
        this.mapVendedores.get(nomeVendedor).getVendas().add(pedido);
    }

    public Integer getTotalClientes() {
        return this.clientes.size();
    }

    public Integer getTotalVendedores() {
        return this.mapVendedores.keySet().size();
    }

    public List<Vendedor> getVendedores() {
        return new ArrayList<>(this.mapVendedores.values());
    }
}
