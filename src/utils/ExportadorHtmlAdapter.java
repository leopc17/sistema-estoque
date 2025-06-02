package utils;

import model.entities.Produto;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportadorHtmlAdapter implements ExportadorProdutos {

    @Override
    public void exportar(List<Produto> produtos, String nomeArquivo) {
        try (FileWriter writer = new FileWriter(nomeArquivo + ".html")) {
            writer.append("<!DOCTYPE html>\n<html>\n<head><title>Produtos</title><style>table{border-collapse:collapse;}th,td{border:1px solid black;padding:8px;}</style></head>\n<body>\n");
            writer.append("  <h1>Lista de Produtos</h1>\n");
            writer.append("  <table border='1'>\n");
            writer.append("    <tr><th>ID</th><th>Nome</th><th>Descrição</th><th>Preço</th><th>Quantidade</th></tr>\n");
            for (Produto p : produtos) {
                writer.append("    <tr><td>" +
                                    p.getId() + "</td><td>" +
                                    p.getNome() + "</td><td>" +
                                    p.getDescricao() + "</td><td>R$" +
                                    String.format("%.2f", p.getPreco()) + "</td><td>" +
                                    p.getQuantidade() +
                                    "</td></tr>\n");
            }
            writer.append("  </table>\n</body>\n</html>");
        } catch (IOException e) {
            System.err.println("Erro ao exportar HTML: " + e.getMessage());
        }
    }
}
