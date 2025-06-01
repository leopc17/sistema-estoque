package model.dao;

import model.entities.Produto;

import java.sql.SQLException;
import java.util.List;

public interface ProdutoDao {

    void insert(Produto p) throws SQLException;
    void update(Produto p) throws SQLException;
    void delete(int ID) throws SQLException;
    Produto findById(int ID) throws SQLException;
    List<Produto> findAll() throws SQLException;
}
