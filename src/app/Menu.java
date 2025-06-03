package app;

import model.entities.Produto;
import service.ProdutoService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private ProdutoService produtoService;
    private Scanner sc = new Scanner(System.in);

    public Menu(Connection conn) {
        this.produtoService = new ProdutoService(conn);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Atualizar Produto");
            System.out.println("3. Excluir Produto");
            System.out.println("4. Listar Todos os Produtos");
            System.out.println("5. Buscar Produto por ID");
            System.out.println("6. Registrar Entrada de Estoque");
            System.out.println("7. Registrar Saída de Estoque");
            System.out.println("0. Sair");
            System.out.println("----------------------");
            System.out.print("Digite a opção desejada: ");
            try {
                opcao = sc.nextInt();
                sc.nextLine(); // Descartar a quebra de linha
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                sc.nextLine(); // Descartar a entrada inválida
                opcao = -1; // Para continuar o loop
            }

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    atualizarProduto();
                    break;
                case 3:
                    excluirProduto();
                    break;
                case 4:
                    listarProdutos();
                    break;
                case 5:
                    buscarProduto();
                    break;
                case 6:
                    entradaEstoque();
                    break;
                case 7:
                    saidaEstoque();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    if (opcao != -1) {
                        System.out.println("Opção inválida. Tente novamente.");
                    }
            }
        } while (opcao != 0);
        sc.close();
    }

    private void cadastrarProduto() {
        System.out.println("\n--- CADASTRAR PRODUTO ---");
        System.out.print("ID: ");
        int id = obterInteiro();
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();
        System.out.print("Preço: ");
        BigDecimal preco = obterBigDecimal();
        System.out.print("Quantidade Inicial: ");
        int quantidade = obterInteiro();

        Produto p = new Produto(id, nome, descricao, preco, quantidade);
        produtoService.cadastrar(p);
    }

    // Métodos auxiliares

    private int obterInteiro() {
        while (true) {
            try {
                int valor = sc.nextInt();
                sc.nextLine(); // Consumir a quebra de linha
                return valor;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                sc.nextLine(); // Consumir a entrada inválida
                System.out.print("Tente novamente: ");
            }
        }
    }

    private BigDecimal obterBigDecimal() {
        while (true) {
            try {
                BigDecimal valor = sc.nextBigDecimal();
                sc.nextLine(); // Consumir a quebra de linha
                return valor;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número decimal (ex: 12.34).");
                sc.nextLine(); // Consumir a entrada inválida
                System.out.print("Tente novamente: ");
            }
        }
    }
}
