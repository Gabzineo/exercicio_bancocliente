package br.com.goliveira;

import br.com.goliveira.dao.ClienteMapDAO;
import br.com.goliveira.dao.IClienteDAO;

import javax.swing.*;

public class App {

    private static IClienteDAO iClienteDAO;

    public static void main(String args[]){
        iClienteDAO = new ClienteMapDAO();

        String opcao = JOptionPane.showInputDialog(null,
                "Digite 1 para cadastro, 2 para consultar, 3 para exlusão, 4 para alteração ou 5 para sair",
                "Cadastro", JOptionPane.INFORMATION_MESSAGE);

        while (!isOpcaoValida(opcao)){
            if("".equals(opcao)){
                sair();
            }
            opcao = JOptionPane.showInputDialog(null,
                    "Opção invalida digite 1 para cadastro, 2 para consultar, 3 para exlusão, 4 para alteração ou 5 para sair",
                    "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        }

        while (isOpcaoValida(opcao)){
            if(isOpcaoValida(opcao)){
                sair();
            }
        }



        if(isOpcaoCadastro(opcao)){
            JOptionPane.showMessageDialog(null, "Opção: " + opcao, "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void sair() {
        JOptionPane.showMessageDialog(null, "Até logo", "Sair", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private static boolean isOpcaoValida(String opcao) {
        if ("1".equals(opcao) || "2".equals(opcao) || "3".equals(opcao) || "4".equals(opcao) ){
            return true;
        }
        return false;
    }

    private static boolean isOpcaoCadastro(String opcao) {
        return "1".equals(opcao);
    }
}
