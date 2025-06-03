package service;

import model.dao.ProdutoDao;
import model.dao.ProdutoDaoValidationDecorator;
import model.dao.impl.ProdutoDaoJDBC;
import model.entities.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProdutoService {

    private ProdutoDao produtoDao;

    public ProdutoService(Connection conn) {
        this.produtoDao = new ProdutoDaoJDBC(conn);
        this.produtoDao = new ProdutoDaoValidationDecorator(produtoDao);
    }

    public void cadastrar(Produto p) {
        System.out.println("Serviço: Tentando adicionar produto...");
        try {
            produtoDao.insert(p);
            System.out.println("Serviço: Produto adicionado com sucesso: " + p);
        } catch (IllegalArgumentException e) {
            System.err.println("Serviço: Erro de validação ao adicionar produto: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Serviço: Erro de banco de dados ao adicionar produto: " + e.getMessage());
        }
    }

    public void atualizar(Produto p) {
        System.out.println("Serviço: Tentando atualizar produto com ID " + p.getId() + "...");
        try {
            produtoDao.update(p);
            System.out.println("Serviço: Produto atualizado com sucesso: " + p);
        } catch (IllegalArgumentException e) {
            System.err.println("Serviço: Erro de validação ao atualizar produto: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Serviço: Erro de banco de dados ao atualizar produto: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        System.out.println("Serviço: Tentando remover produto com ID " + id + "...");
        try {
            produtoDao.delete(id);
            System.out.println("Serviço: Produto removido com sucesso: " + id);
        } catch (IllegalArgumentException e) {
            System.err.println("Serviço: Erro de validação ao remover produto: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Serviço: Erro de banco de dados ao remover produto: " + e.getMessage());
        }
    }

    public List<Produto> listar() {
        System.out.println("Serviço: Tentando listar todos os produtos...");
        try {
            List<Produto> produtos = produtoDao.findAll();
            System.out.println("Serviço: " + produtos.size() + " produtos encontrados.");
            return produtos;
        } catch (SQLException e) {
            System.err.println("Serviço: Erro de banco de dados ao listar todos os produtos: " + e.getMessage());
            return List.of();
        }
    }

    public Produto buscar(int id) {
        System.out.println("Serviço: Tentando buscar produto com ID " + id + "...");
        try {
            Produto produto = produtoDao.findById(id); // Agora retorna Produto ou null
            if (produto != null) {
                System.out.println("Serviço: Produto encontrado: " + produto);
            } else {
                System.out.println("Serviço: Produto com ID " + id + " não encontrado.");
            }
            return produto;
        } catch (IllegalArgumentException e) {
            System.err.println("Serviço: Erro de validação ao buscar produto por ID: " + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.err.println("Serviço: Erro de banco de dados ao buscar produto por ID: " + e.getMessage());
            return null;
        }
    }

    public void entradaEstoque(int id, int quantidade) {
        System.out.println("Serviço: Tentando aumentar a quantidade do produto com ID " + id + "...");
        try {
            Produto produto = produtoDao.findById(id); // retorna Produto ou null
            if (produto != null) {
                produto.setQuantidade(produto.getQuantidade() + quantidade);
                produtoDao.update(produto);
                System.out.println("Serviço: Estoque do produto aumentado com sucesso: " + produto);
            } else {
                System.out.println("Serviço: Produto com ID " + id + " não encontrado.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Serviço: Erro de validação ao buscar produto por ID: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Serviço: Erro de banco de dados ao buscar produto por ID: " + e.getMessage());
        }
    }

    public void saidaEstoque(int id, int quantidade) {
        System.out.println("Serviço: Tentando diminuir a quantidade do produto com ID " + id + "...");
        try {
            Produto produto = produtoDao.findById(id); // retorna Produto ou null
            if (produto != null) {
                produto.setQuantidade(produto.getQuantidade() - quantidade);
                produtoDao.update(produto);
                System.out.println("Serviço: Estoque do produto diminuido com sucesso: " + produto);
            } else {
                System.out.println("Serviço: Produto com ID " + id + " não encontrado.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Serviço: Erro de validação ao buscar produto por ID: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Serviço: Erro de banco de dados ao buscar produto por ID: " + e.getMessage());
        }
    }
}
