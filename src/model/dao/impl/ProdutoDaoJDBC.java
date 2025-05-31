package model.dao.impl;

import model.dao.ProdutoDao;
import model.entities.Produto;

import java.util.List;

public class ProdutoDaoJDBC implements ProdutoDao {

    @Override
    public void insert(Produto p) {

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
        return List.of();
    }
}
