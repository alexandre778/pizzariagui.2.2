package com.pizzaria.controller;

import com.pizzaria.dao.ClienteDAO;
import com.pizzaria.model.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteController {

    private ClienteDAO clienteDAO;

    public ClienteController() throws SQLException {
        clienteDAO = new ClienteDAO();
    }

    public int cadastrarCliente(String nome, String telefone, String endereco) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        cliente.setEndereco(endereco);

        return clienteDAO.inserir(cliente); // Retorna o ID gerado
    }

    public List<Cliente> obterClientes() {
        return clienteDAO.listarTodos();
    }
}
