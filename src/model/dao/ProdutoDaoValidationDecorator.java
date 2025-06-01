package model.dao;

import model.entities.Produto;

import java.math.BigDecimal;
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
    public void insert(Produto p) {
        productValidation(p);
        decorated.insert(p);
    }

    @Override
    public void update(Produto p) {

    }

    @Override
    public void delete(int ID) {

    }

    @Override
    public Produto findById(int ID) {
        return null;
    }

    @Override
    public List<Produto> findAll() {
        return decorated.findAll();
    }
}
