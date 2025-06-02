package model.dao;

import model.entities.Produto;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class ProdutoDaoValidationDecorator implements ProdutoDao {

    private final ProdutoDao decorated;

    public ProdutoDaoValidationDecorator(ProdutoDao decorated) {
        this.decorated = decorated;
    }

    private void productValidation(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto não pode ser vazio.");
        }

        if (produto.getPreco() == null || produto.getPreco().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Preço do produto não pode ser nulo ou negativo.");
        }

        if (produto.getQuantidade() < 0) {
            throw new IllegalArgumentException("Quantidade do produto não pode ser nula ou negativa.");
        }
    }

    @Override
    public void insert(Produto p) throws SQLException {
        productValidation(p);
        decorated.insert(p);
    }

    @Override
    public void update(Produto p) throws SQLException {
        if (p.getId() <= 0) {
            throw new IllegalArgumentException("ID do produto não pode ser menor ou igual a zero para atualização.");
        }
        productValidation(p);
        decorated.update(p);
    }

    @Override
    public void delete(int ID) throws SQLException {
        if (ID <= 0) {
            throw new IllegalArgumentException("ID do produto inválido para exclusão.");
        }
        decorated.delete(ID);
    }

    @Override
    public Produto findById(int ID) throws SQLException {
        if (ID <= 0) {
            throw new IllegalArgumentException("ID do produto inválido para exclusão.");
        }

        return decorated.findById(ID);
    }

    @Override
    public List<Produto> findAll() throws SQLException {
        return decorated.findAll();
    }
}
