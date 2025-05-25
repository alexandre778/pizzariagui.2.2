
package com.pizzaria.service;

import com.pizzaria.dao.PedidoDAO;
import com.pizzaria.dao.ProdutoDAO;
import com.pizzaria.model.Pedido;
import com.pizzaria.model.Produto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoService {

    private PedidoDAO pedidoDAO;
    private ProdutoDAO produtoDAO;

    public PedidoService(PedidoDAO pedidoDAO, ProdutoDAO produtoDAO) {
        this.pedidoDAO = pedidoDAO;
        this.produtoDAO = produtoDAO;
    }

    public List<Pedido> listarPedidosPorCliente(int clienteId) throws SQLException {
        List<Pedido> pedidos = pedidoDAO.listarPedidos();
        List<Pedido> pedidosCliente = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getClienteId() == clienteId) {
                pedidosCliente.add(pedido);
            }
        }
        return pedidosCliente;
    }

    public void realizarPedido(int clienteId, int produtoId, int quantidade, double total, String formaPagamento) throws SQLException {
        Pedido pedido = new Pedido(clienteId, produtoId, quantidade, total, formaPagamento, "Pendente", new Date());
        pedidoDAO.inserirPedido(pedido);
    }

    public double calcularTotalPedido(int clienteId) throws SQLException {
        List<Pedido> pedidos = listarPedidosPorCliente(clienteId);
        double total = 0;
        for (Pedido pedido : pedidos) {
            Produto produto = produtoDAO.buscarPorId(pedido.getProdutoId());
            total += produto.getPreco() * pedido.getQuantidade();
        }
        return total;
    }

    public void realizarPagamento(int clienteId, String formaPagamento) throws SQLException {
        double total = calcularTotalPedido(clienteId);
        System.out.println("Total a pagar: R$ " + String.format("%.2f", total));

        List<Pedido> pedidos = listarPedidosPorCliente(clienteId);
        for (Pedido pedido : pedidos) {
            pedido.setStatus("Pago");
            pedidoDAO.atualizarPedido(pedido);
        }

        System.out.println("Pagamento realizado com sucesso via " + formaPagamento);
    }

    public void excluirPedido(int pedidoId) throws SQLException {
        pedidoDAO.excluir(pedidoId);
    }
}
