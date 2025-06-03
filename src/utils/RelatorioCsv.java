package utils;

import model.entities.Produto;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

// Classe Legado
public class RelatorioCsv {

    public void gerar(List<Produto> produtos, String nomeArquivo) {
        try (FileWriter writer = new FileWriter(nomeArquivo + ".csv")) {
            writer.append("ID;Nome;Descricao;Preco;quantidade\n"); // cabeçalho
            for (Produto p : produtos) {
                writer.append (
                        p.getId() + ";" +
                                p.getNome() + ";" +
                                p.getDescricao() + ";" +
                                String.format("%.2f", p.getPreco()) + ";" +
                                p.getQuantidade() + "\n"
                );
            }
        } catch (IOException e) {
            System.err.println("Erro ao exportar CSV: " + e.getMessage());
        }
    }
}
