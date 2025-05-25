package com.pizzaria.dao;

import com.pizzaria.db.DBConnection;
import com.pizzaria.model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    private Connection conexao;

    public PedidoDAO() throws SQLException {
        this.conexao = DBConnection.getConnection();
    }

    public void inserirPedido(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedido (cliente_id, produto_id, quantidade, total, forma_pagamento, status, data_pedido) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, pedido.getClienteId());
        stmt.setInt(2, pedido.getProdutoId());
        stmt.setInt(3, pedido.getQuantidade());
        stmt.setDouble(4, pedido.getTotal());
        stmt.setString(5, pedido.getFormaPagamento());
        stmt.setString(6, pedido.getStatus());
        stmt.setDate(7, new java.sql.Date(pedido.getDataPedido().getTime()));
        stmt.executeUpdate();
        stmt.close();
    }

    public List<Pedido> listarPedidos() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Pedido pedido = new Pedido();
            pedido.setId(rs.getInt("id"));
            pedido.setClienteId(rs.getInt("cliente_id"));
            pedido.setProdutoId(rs.getInt("produto_id"));
            pedido.setQuantidade(rs.getInt("quantidade"));
            pedido.setTotal(rs.getDouble("total"));
            pedido.setFormaPagamento(rs.getString("forma_pagamento"));
            pedido.setStatus(rs.getString("status"));
            pedido.setDataPedido(rs.getDate("data_pedido"));
            pedidos.add(pedido);
        }

        rs.close();
        stmt.close();

        return pedidos;
    }

    public void atualizarPedido(Pedido pedido) throws SQLException {
        String sql = "UPDATE pedido SET status = ? WHERE id = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, pedido.getStatus());
        stmt.setInt(2, pedido.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    public void excluir(int pedidoId) throws SQLException {
        String sql = "DELETE FROM pedido WHERE id = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, pedidoId);
        stmt.executeUpdate();
        stmt.close();
    }
}
