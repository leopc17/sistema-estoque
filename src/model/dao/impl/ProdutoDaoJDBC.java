package model.dao.impl;

import model.dao.ProdutoDao;
import model.entities.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDaoJDBC implements ProdutoDao {

    private Connection conn;

    public ProdutoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Produto p) throws SQLException {
        String sql = "INSERT INTO produtos (nome, descricao, preco, quantidade) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setBigDecimal(3, p.getPreco());
            stmt.setInt(4, p.getQuantidade());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    p.setId(rs.getInt(1));
                }
            }
            System.out.println("ProdutoDaoJdbc: Produto inserido com sucesso. ID gerado: " + p.getId());
        }
    }

    @Override
    public void update(Produto p) throws SQLException {
        String sql = "UPDATE produtos SET nome = ?, descricao = ?, preco = ?, quantidade = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setBigDecimal(3, p.getPreco());
            stmt.setInt(4, p.getQuantidade());
            stmt.setLong(5, p.getId());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("ProdutoDaoJdbc: Nenhuma linha afetada na atualização para o Produto ID " + p.getId() + ".");
            } else {
                System.out.println("ProdutoDaoJdbc: Produto atualizado com sucesso. ID: " + p.getId());
            }
        }
    }

    @Override
    public void delete(int ID) throws SQLException {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ID);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("ProdutoDaoJdbc: Nenhuma linha afetada na exclusão para o Produto ID " + ID + ".");
            } else {
                System.out.println("ProdutoDaoJdbc: Produto excluído com sucesso. ID: " + ID);
            }
        }
    }

    @Override
    public Produto findById(int ID) throws SQLException {
        String sql = "SELECT id, nome, descricao, preco, quantidade FROM produtos WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, ID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("ProdutoDaoJdbc: Produto encontrado por ID: " + ID);
                    return mapRowToProduto(rs);
                }
            }
            System.out.println("ProdutoDaoJdbc: Produto com ID " + ID + " não encontrado no banco de dados.");
            return null; // retorna null caso não encontre o produto
        }
    }

    @Override
    public List<Produto> findAll() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT id, nome, descricao, preco, quantidade FROM produtos";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                produtos.add(mapRowToProduto(rs));
            }
            System.out.println("ProdutoDaoJdbc: Listados " + produtos.size() + " produtos do banco de dados.");
        }
        return produtos;
    }

    private Produto mapRowToProduto(ResultSet rs) throws SQLException {
        Produto produto = new Produto();
        produto.setId(rs.getInt("id"));
        produto.setNome(rs.getString("nome"));
        produto.setDescricao(rs.getString("descricao"));
        produto.setPreco(rs.getBigDecimal("preco"));
        produto.setQuantidade(rs.getInt("quantidade"));
        return produto;
    }
}
