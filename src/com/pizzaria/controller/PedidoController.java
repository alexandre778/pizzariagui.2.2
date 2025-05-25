package com.pizzaria.controller;

import com.pizzaria.dao.PedidoDAO;
import com.pizzaria.dao.ProdutoDAO;
import com.pizzaria.model.Pedido;
import com.pizzaria.service.PedidoService;

import java.sql.SQLException;
import java.util.List;

public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController() throws SQLException {
        this.pedidoService = new PedidoService(new PedidoDAO(), new ProdutoDAO());
    }

    public void realizarPedido(int clienteId, int produtoId, int quantidade, double total, String formaPagamento) throws SQLException {
        pedidoService.realizarPedido(clienteId, produtoId, quantidade, total, formaPagamento);
    }

    public List<Pedido> obterPedidos() throws SQLException {
        return pedidoService.listarPedidosPorCliente(1); // <- Altere para o cliente desejado
    }

    public void pagarPedido(int clienteId, String formaPagamento) throws SQLException {
        pedidoService.realizarPagamento(clienteId, formaPagamento);
    }

    public void excluirPedido(int pedidoId) throws SQLException {
        pedidoService.excluirPedido(pedidoId);
    }
}
