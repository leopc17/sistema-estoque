package utils;

import model.entities.Produto;

import java.util.List;

public interface ExportadorProdutos {

    void exportar(List<Produto> produtos, String nomeArquivo);
}