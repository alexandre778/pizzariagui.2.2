🍕 Pizzaria Sabor da Itália - Sistema Desktop (PIZZARIAGUI.2.2)
Este projeto é um sistema de gerenciamento para a Pizzaria Sabor da Itália, desenvolvido em Java Swing, com interface gráfica amigável e conexão com banco de dados MySQL. Permite realizar cadastros de clientes, pedidos e possui autenticação de acesso.

🚀 Funcionalidades
🔐 Tela de Login
Acesso restrito com usuário e senha.

Usuário padrão: ****
Senha padrão: ****

👤 Cadastro de Clientes
Permite cadastrar, listar e selecionar clientes.

🧾 Pedidos
Realiza pedidos associados a clientes. É possível escolher a forma de pagamento (Dinheiro, Cartão ou Pix) e gerar o total do pedido.

📑 Listagem de Dados
Visualização dos clientes cadastrados e pedidos realizados.

🛠️ Tecnologias Utilizadas
Java (JDK 8 ou superior)

Java Swing (Interface Gráfica)

JDBC (Conexão com MySQL)

MySQL (Banco de Dados)

Padrão MVC (Model - View - Controller)

🎯 Estrutura do Projeto
cpp
Copiar
Editar
com.pizzariagui         // Telas (Interface gráfica)
com.pizzaria.model      // Classes de modelo (Cliente, Pedido, Produto)
com.pizzaria.controller // Regras de negócio e controle
com.pizzaria.dao        // Acesso ao banco de dados
com.pizzaria.db         // Conexão com o banco
🏗️ Telas Disponíveis
TelaLogin.java – Tela de autenticação.

TelaCadastroCliente.java – Cadastro e listagem de clientes.

TelaPedido.java – Cadastro e listagem de pedidos.

🗄️ Banco de Dados
Banco: pizzaria

Tabelas:

cliente

produto

pedido

Observação: As credenciais e o script de criação do banco estão configuráveis no projeto.

🔧 Como Executar o Projeto
✅ Clone o repositório no seu computador:




git clone https://github.com/seu-usuario/PIZZARIAGUI.2.2.git
✅ Abra no NetBeans ou em outra IDE Java compatível com Maven.

✅ Configure o banco de dados MySQL:

Banco: pizzaria

Usuário: ****

Senha: sua_senha

Execute o script de criação das tabelas (script.sql).

✅ Execute a classe TelaLogin.java para iniciar o sistema.

🔑 Login Padrão
Usuário	Senha
admin	****

💡 Melhorias Futuras
Adição de controle de produtos diretamente pela interface.

Geração de relatórios (PDF ou Excel).

Implementação de tela de pagamentos com integração.

Validação aprimorada de campos.

🧠 Desenvolvido Com
❤️ Por [Alexandre Martins]

📧 Contato: [alexgmax@gmail.com]
