package br.com.gsc.vendasbatch.layout;

import br.com.gsc.vendasbatch.model.Arquivo;

public interface TipoLayout {
    void processar(final String[] partes, final Arquivo consolidado);
}
