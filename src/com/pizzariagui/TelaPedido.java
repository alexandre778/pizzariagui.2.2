package com.pizzariagui;

import com.pizzaria.controller.PedidoController;
import com.pizzaria.model.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaPedido extends JFrame {

    private JTextField txtClienteId, txtProdutoId, txtQuantidade, txtTotal;
    private JComboBox<String> cbFormaPagamento;
    private JButton btnSalvar, btnListar;
    private JTable tabelaPedidos;

    private PedidoController pedidoController;

    // 🔥 Construtor padrão
    public TelaPedido() throws SQLException {
        pedidoController = new PedidoController();

        setTitle("Tela de Pedido");
        setSize(800, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblClienteId = new JLabel("ID Cliente:");
        lblClienteId.setBounds(20, 20, 100, 25);
        add(lblClienteId);

        txtClienteId = new JTextField();
        txtClienteId.setBounds(120, 20, 150, 25);
        add(txtClienteId);

        JLabel lblProdutoId = new JLabel("ID Produto:");
        lblProdutoId.setBounds(20, 60, 100, 25);
        add(lblProdutoId);

        txtProdutoId = new JTextField();
        txtProdutoId.setBounds(120, 60, 150, 25);
        add(txtProdutoId);

        JLabel lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setBounds(20, 100, 100, 25);
        add(lblQuantidade);

        txtQuantidade = new JTextField();
        txtQuantidade.setBounds(120, 100, 150, 25);
        add(txtQuantidade);

        JLabel lblTotal = new JLabel("Total (R$):");
        lblTotal.setBounds(20, 140, 100, 25);
        add(lblTotal);

        txtTotal = new JTextField();
        txtTotal.setBounds(120, 140, 150, 25);
        add(txtTotal);

        JLabel lblPagamento = new JLabel("Pagamento:");
        lblPagamento.setBounds(20, 180, 100, 25);
        add(lblPagamento);

        cbFormaPagamento = new JComboBox<>(new String[]{"Dinheiro", "Cartão", "Pix"});
        cbFormaPagamento.setBounds(120, 180, 150, 25);
        add(cbFormaPagamento);

        btnSalvar = new JButton("Salvar Pedido");
        btnSalvar.setBounds(20, 230, 250, 30);
        add(btnSalvar);

        btnListar = new JButton("Listar Pedidos");
        btnListar.setBounds(20, 270, 250, 30);
        add(btnListar);

        tabelaPedidos = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabelaPedidos);
        scrollPane.setBounds(300, 20, 470, 500);
        add(scrollPane);

        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    salvarPedido();
                } catch (SQLException ex) {
                    Logger.getLogger(TelaPedido.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    listarPedidos();
                } catch (SQLException ex) {
                    Logger.getLogger(TelaPedido.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        setVisible(true);
    }

    // 🔥 Construtor adicional recebendo clienteId
    public TelaPedido(int clienteId) throws SQLException {
        this(); // Chama o construtor padrão
        setClienteId(clienteId);
    }

    // 🔥 Método para definir clienteId externamente
    public void setClienteId(int clienteId) {
        txtClienteId.setText(String.valueOf(clienteId));
        txtClienteId.setEditable(false); // Deixa não editável após ser setado
    }

    // 🔥 Salvar pedido
    private void salvarPedido() throws SQLException {
        try {
            int clienteId = Integer.parseInt(txtClienteId.getText());
            int produtoId = Integer.parseInt(txtProdutoId.getText());
            int quantidade = Integer.parseInt(txtQuantidade.getText());
            double total = Double.parseDouble(txtTotal.getText());
            String formaPagamento = cbFormaPagamento.getSelectedItem().toString();

            pedidoController.realizarPedido(clienteId, produtoId, quantidade, total, formaPagamento);

            JOptionPane.showMessageDialog(this, "Pedido salvo com sucesso!");
            limparCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Verifique os dados inseridos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 🔥 Listar pedidos na tabela
    private void listarPedidos() throws SQLException {
        List<Pedido> pedidos = pedidoController.obterPedidos();

        String[] colunas = {"ID", "Cliente", "Produto", "Qtd", "Total", "Pagamento", "Data"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        for (Pedido p : pedidos) {
            Object[] row = {
                    p.getId(),
                    p.getClienteId(),
                    p.getProdutoId(),
                    p.getQuantidade(),
                    p.getTotal(),
                    p.getFormaPagamento(),
                    p.getDataPedido()
            };
            model.addRow(row);
        }

        tabelaPedidos.setModel(model);
    }

    // 🔥 Limpar campos (mantém clienteId se estiver bloqueado)
    private void limparCampos() {
        if (txtClienteId.isEditable()) {
            txtClienteId.setText("");
        }
        txtProdutoId.setText("");
        txtQuantidade.setText("");
        txtTotal.setText("");
        cbFormaPagamento.setSelectedIndex(0);
    }

    // 🔥 Main para testar a tela
    public static void main(String[] args) throws SQLException {
        new TelaPedido();
    }
}
