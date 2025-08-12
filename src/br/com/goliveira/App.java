
package br.com.goliveira;

import br.com.goliveira.dao.ClienteMapDAO;
import br.com.goliveira.dao.IClienteDAO;
import br.com.goliveira.domain.Cliente;

import javax.swing.*;


public class App {

    private static IClienteDAO iClienteDAO;

    public static void main(String args[]) {
        iClienteDAO = new ClienteMapDAO();

        while (true) {
            String opcao = JOptionPane.showInputDialog(null,
                    "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair.",
                    "Cadastro de Clientes", JOptionPane.INFORMATION_MESSAGE);

            if (opcao == null) {
                sair();
            }

            switch (opcao) {
                case "1":
                    cadastrar();
                    break;
                case "2":
                    consultar();
                    break;
                case "3":
                    excluir();
                    break;
                case "4":
                    alterar();
                    break;
                case "5":
                    sair();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida! Por favor, digite um número de 1 a 5.", "Erro", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }


    private static void cadastrar() {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do cliente:", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        String cpfStr = JOptionPane.showInputDialog(null, "Digite o CPF do cliente (apenas números):", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        String telStr = JOptionPane.showInputDialog(null, "Digite o telefone do cliente (apenas números):", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        String endereco = JOptionPane.showInputDialog(null, "Digite o endereço do cliente:", "Cadastro", JOptionPane.INFORMATION_MESSAGE);

        if (nome == null || cpfStr == null || telStr == null || endereco == null ||
                nome.trim().isEmpty() || cpfStr.trim().isEmpty() || telStr.trim().isEmpty() || endereco.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios.", "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Long cpf = Long.parseLong(cpfStr);
            Long tel = Long.parseLong(telStr);

            Cliente cliente = new Cliente(nome, cpf, tel, endereco);
            Boolean isCadastrado = iClienteDAO.cadastrar(cliente);

            if (isCadastrado) {
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente com este CPF já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "CPF e Telefone devem conter apenas números.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }


    private static void consultar() {
        String cpfStr = JOptionPane.showInputDialog(null, "Digite o CPF do cliente que deseja consultar:", "Consulta", JOptionPane.INFORMATION_MESSAGE);
        if (cpfStr == null || cpfStr.trim().isEmpty()) return;

        try {
            Long cpf = Long.parseLong(cpfStr);
            Cliente cliente = iClienteDAO.consultar(cpf);

            if (cliente != null) {
                JOptionPane.showMessageDialog(null, "Cliente encontrado:\n" + cliente.toString(), "Resultado da Consulta", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado.", "Resultado da Consulta", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "CPF deve conter apenas números.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }


    private static void excluir() {
        String cpfStr = JOptionPane.showInputDialog(null, "Digite o CPF do cliente que deseja excluir:", "Exclusão", JOptionPane.INFORMATION_MESSAGE);
        if (cpfStr == null || cpfStr.trim().isEmpty()) return;

        try {
            Long cpf = Long.parseLong(cpfStr);
            iClienteDAO.excluir(cpf);
            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso (se existente).", "Exclusão", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "CPF deve conter apenas números.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }


    private static void alterar() {
        String cpfStr = JOptionPane.showInputDialog(null, "Digite o CPF do cliente que deseja alterar:", "Alteração", JOptionPane.INFORMATION_MESSAGE);
        if (cpfStr == null || cpfStr.trim().isEmpty()) return;

        try {
            Long cpf = Long.parseLong(cpfStr);
            Cliente cliente = iClienteDAO.consultar(cpf);

            if (cliente != null) {
                String novoNome = JOptionPane.showInputDialog(null, "Digite o novo nome:", "Alteração", JOptionPane.INFORMATION_MESSAGE);
                String novoTelStr = JOptionPane.showInputDialog(null, "Digite o novo telefone:", "Alteração", JOptionPane.INFORMATION_MESSAGE);
                String novoEndereco = JOptionPane.showInputDialog(null, "Digite o novo endereço:", "Alteração", JOptionPane.INFORMATION_MESSAGE);

                if (novoNome == null || novoTelStr == null || novoEndereco == null ||
                        novoNome.trim().isEmpty() || novoTelStr.trim().isEmpty() || novoEndereco.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios para alteração.", "Erro de Alteração", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Long novoTel = Long.parseLong(novoTelStr);

                Cliente clienteAtualizado = new Cliente(novoNome, cpf, novoTel, novoEndereco);
                iClienteDAO.alterar(clienteAtualizado);

                JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado para alteração.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "CPF e Telefone devem conter apenas números.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }


    private static void sair() {
        JOptionPane.showMessageDialog(null, "Até logo!", "Saindo", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}