package br.com.gsc.vendasbatch.layout;

import br.com.gsc.vendasbatch.layout.impl.ClienteTipoLayoutImpl;
import br.com.gsc.vendasbatch.layout.impl.VendaTipoLayoutImpl;
import br.com.gsc.vendasbatch.layout.impl.VendedorTipoLayoutImpl;
import br.com.gsc.vendasbatch.model.Arquivo;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public enum LayoutEnum {
    VENDEDOR("001", VendedorTipoLayoutImpl.class),
    CLIENTE("002", ClienteTipoLayoutImpl.class),
    VENDA("003", VendaTipoLayoutImpl.class);

    private Class<? extends TipoLayout> implementacao;
    private String codigo;

    LayoutEnum(String codigo, Class classeImplementacao) {
        this.codigo = codigo;
        this.implementacao = classeImplementacao;
    }

    public static void interpretarLinhas(final String codigoLayout, final String[] partes,final Arquivo arquivo) {
       final var layout = obterLayoutPorCodigo(codigoLayout);

       // caso exista layout para o valor selecionado, realiza processamento
       if (layout != null) {
           try {
               final var instanciaLayout = layout.implementacao.getDeclaredConstructor().newInstance();
               instanciaLayout.processar(partes, arquivo);
           } catch (InstantiationException | IllegalAccessException |
                   InvocationTargetException | NoSuchMethodException e) {
               e.printStackTrace();
           }
       }
    }

    public static LayoutEnum obterLayoutPorCodigo(final String codigo) {
        return Arrays.asList(LayoutEnum.values()).stream().filter(f -> f.codigo.equalsIgnoreCase(codigo))
                .findFirst().orElse(null);
    }

}
