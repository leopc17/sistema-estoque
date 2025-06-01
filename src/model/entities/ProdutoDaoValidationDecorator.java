package model.entities;

import model.dao.ProdutoDao;
import model.entities.Produto;

import java.util.List;

public class ProdutoDaoValidationDecorator implements ProdutoDao {

    private final ProdutoDao decorated;

    public ProdutoDaoValidationDecorator(ProdutoDao decorated) {
        this.decorated = decorated;
    }

    @Override
    public void inserir(Produto p) {
        
    }

    @Override
    public void atualizar(Produto p) {

    }

    @Override
    public void deletar(Integer ID) {

    }

    @Override
    public Produto procurarPorID(Integer ID) {
        return null;
    }

    @Override
    public List<Produto> ListarProdutos() {
        return List.of();
    }
}
