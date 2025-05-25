package com.pizzaria.model;

import java.util.Date;

public class Pedido {
    private int id;
    private int clienteId;
    private int produtoId;
    private int quantidade;
    private double total;
    private String formaPagamento;
    private String status;
    private Date dataPedido;

    public Pedido() {}

    public Pedido(int clienteId, int produtoId, int quantidade, double total, String formaPagamento, String status, Date dataPedido) {
        this.clienteId = clienteId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.total = total;
        this.formaPagamento = formaPagamento;
        this.status = status;
        this.dataPedido = dataPedido;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }

    public int getProdutoId() { return produtoId; }
    public void setProdutoId(int produtoId) { this.produtoId = produtoId; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public String getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getDataPedido() { return dataPedido; }
    public void setDataPedido(Date dataPedido) { this.dataPedido = dataPedido; }
}
