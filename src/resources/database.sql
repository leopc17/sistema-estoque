CREATE DATABASE IF NOT EXISTS mapdb;

USE mapdb;

CREATE TABLE IF NOT EXISTS produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    preco DOUBLE,
    descricao TEXT,
    quantidade INT
);