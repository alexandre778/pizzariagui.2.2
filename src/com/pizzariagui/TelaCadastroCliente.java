package com.pizzariagui;

import com.pizzaria.controller.ClienteController;
import com.pizzaria.model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class TelaCadastroCliente extends JFrame {

    private JTextField txtNome, txtTelefone, txtEndereco;
    private JButton btnSalvar, btnListar, btnAbrirPedido;
    private JTable tabelaClientes;

    private ClienteController clienteController;

    public TelaCadastroCliente() throws SQLException {
        clienteController = new ClienteController();

        setTitle("Cadastro de Cliente");
        setSize(800, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 20, 100, 25);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(120, 20, 200, 25);
        add(txtNome);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(20, 60, 100, 25);
        add(lblTelefone);

        txtTelefone = new JTextField();
        txtTelefone.setBounds(120, 60, 200, 25);
        add(txtTelefone);

        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setBounds(20, 100, 100, 25);
        add(lblEndereco);

        txtEndereco = new JTextField();
        txtEndereco.setBounds(120, 100, 200, 25);
        add(txtEndereco);

        btnSalvar = new JButton("Salvar Cliente");
        btnSalvar.setBounds(20, 150, 300, 30);
        add(btnSalvar);

        btnListar = new JButton("Listar Clientes");
        btnListar.setBounds(20, 190, 300, 30);
        add(btnListar);

        btnAbrirPedido = new JButton("Abrir Pedido do Cliente Selecionado");
        btnAbrirPedido.setBounds(20, 230, 300, 30);
        add(btnAbrirPedido);

        tabelaClientes = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabelaClientes);
        scrollPane.setBounds(350, 20, 420, 500);
        add(scrollPane);

        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarCliente();
            }
        });

        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarClientes();
            }
        });

        btnAbrirPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirTelaPedidoSelecionado();
            }
        });

        setVisible(true);
    }

    // 🔥 Salvar cliente e abrir TelaPedido automaticamente
    private void salvarCliente() {
        try {
            String nome = txtNome.getText();
            String telefone = txtTelefone.getText();
            String endereco = txtEndereco.getText();

            if (nome.isEmpty() || telefone.isEmpty() || endereco.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
                return;
            }

            int clienteId = clienteController.cadastrarCliente(nome, telefone, endereco);

            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso! ID: " + clienteId);

            limparCampos();
            listarClientes();

            // 🔥 Abre a TelaPedido passando o clienteId
            new TelaPedido(clienteId).setVisible(true);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente: " + ex.getMessage());
        }
    }

    // 🔥 Listar clientes na tabela
    private void listarClientes() {
        List<Cliente> clientes = clienteController.obterClientes();

        String[] colunas = {"ID", "Nome", "Telefone", "Endereço"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        for (Cliente c : clientes) {
            Object[] row = {
                    c.getId(),
                    c.getNome(),
                    c.getTelefone(),
                    c.getEndereco()
            };
            model.addRow(row);
        }

        tabelaClientes.setModel(model);
    }

    // 🔥 Abrir tela de pedido do cliente selecionado na tabela
    private void abrirTelaPedidoSelecionado() {
        int linha = tabelaClientes.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente na tabela.");
            return;
        }

        int clienteId = (int) tabelaClientes.getValueAt(linha, 0);

        try {
            new TelaPedido(clienteId).setVisible(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao abrir pedido: " + e.getMessage());
        }
    }

    // 🔥 Limpar campos
    private void limparCampos() {
        txtNome.setText("");
        txtTelefone.setText("");
        txtEndereco.setText("");
    }

    public static void main(String[] args) throws SQLException {
        new TelaCadastroCliente();
    }
}
