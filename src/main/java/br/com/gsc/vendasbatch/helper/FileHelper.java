package br.com.gsc.vendasbatch.helper;

import br.com.gsc.vendasbatch.model.Arquivo;
import br.com.gsc.vendasbatch.service.ArquivoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.stream.Stream;

/*
 * Classe responsavel por ler e criar arquivo
 */
@Service
@Slf4j
public class FileHelper {

    @Autowired
    private ArquivoService arquivoService;

    @Scheduled(cron = "*/5 * * * * ?")
    public void realizarProcessamento() throws IOException {
        log.info("Inicio processamento");
        final var consolidado = new Arquivo();
        try (Stream<Path> paths = Files.walk(Paths.get("data/in"))) {
            paths.map(Path::toFile).filter(f -> f.getName().endsWith(".dat"))
                    .forEach(f -> lerArquivo(f, consolidado));
        }

        this.gerarArquivoSaida(consolidado);
        log.info("Fim processamento");
    }

    public void lerArquivo(final File file, final Arquivo consolidado) {
        log.info("Lendo arquivo: "+file.getName());
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            while ((line = br.readLine()) != null) {
                this.arquivoService.popularArquivo(consolidado, line);
            }
        } catch (FileNotFoundException e) {
            log.error("Arquivo nao encontrado", e);
        } catch (IOException e) {
            log.error("Erro inesperado", e);
        }
    }

    public void gerarArquivoSaida(final Arquivo consolidado) {
        log.info("Gerando arquivo saida");
        final var nomeArquivoSaida = LocalDateTime.now().toString() +".done.dat";
        try {
            final var resumo = this.arquivoService.gerarResumo(consolidado);
            FileWriter myWriter = new FileWriter("data/out/"+nomeArquivoSaida);
            myWriter.write("Quantidade de clientes:  "+resumo.getQtdClientes());
            myWriter.write("\nQuantidade de vendedor: "+resumo.getQtdVendedores());
            myWriter.write("\nID da Venda mais cara: "+resumo.getIdMaiorPedido());
            myWriter.write("\nPior vendedor: "+resumo.getPiorVendedor());
            myWriter.close();
        } catch (IOException e) {
            log.error("Erro ao gerar arquivo", e);
        }
    }
}
