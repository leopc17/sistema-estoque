package model.dao;

import model.entities.Produto;

import java.util.List;

public interface ProdutoDao {

    void insert(Produto p);
    void update(Produto p);
    void delete(int ID);
    Produto findById(int ID);
    List<Produto> findAll();
}
