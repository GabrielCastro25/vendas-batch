package br.com.gsc.vendasbatch.service;

import br.com.gsc.vendasbatch.model.Arquivo;

public interface LayoutService {
    void processar(String[] partes, Arquivo consolidado);
}
