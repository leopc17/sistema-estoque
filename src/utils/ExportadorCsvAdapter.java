package utils;

import model.entities.Produto;

import java.util.List;

// Adapter para a classe antiga, se adequando a nova interface
public class ExportadorCsvAdapter implements ExportadorProdutos {

    private RelatorioCsv relatorioCsv;

    public ExportadorCsvAdapter(RelatorioCsv relatorioCsv) {
        this.relatorioCsv = relatorioCsv;
    }

    @Override
    public void exportar(List<Produto> produtos, String nomeArquivo) {
        relatorioCsv.gerar(produtos, nomeArquivo);
    }
}
