package service;

import model.dao.ProdutoDao;
import model.dao.impl.ProdutoDaoJDBC;
import model.entities.Produto;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public class ProdutoService {

    private final ProdutoDao produtoDao;

    public ProdutoService(Connection conn) {
        this.produtoDao = new ProdutoDaoJDBC(conn);
    }

    public void cadastrar(Produto p) {
        if (p.getPreco().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo.");
        }
        if (p.getQuantidade() < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa.");
        }

        // pode adicionar mais regras de negócio, caso necessário

        produtoDao.insert(p);
    }

    public void atualizar(Produto p) {
        // regras de negócio
    }

    public void excluir(int id) {
        // regras de negócio
    }

    public List<Produto> listar() {
        return produtoDao.findAll();
    }

    public Produto buscar(int id) {
        return produtoDao.findById(id);
    }

    public void entradaEstoque(int quantidade) {
        // regras de negócio
    }

    public void saidaEstoque(int quantidade) {
        // regras de negócio
    }
}
