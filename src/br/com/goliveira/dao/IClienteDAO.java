
package br.com.goliveira.dao;

import br.com.goliveira.domain.Cliente;


public interface IClienteDAO {

    /**
     * Cadastra um novo cliente.
     * @param cliente Cliente a ser cadastrado.
     * @return true se cadastrado com sucesso, false se o cliente já existir.
     */
    public Boolean cadastrar(Cliente cliente);

    /**
     * @param cpf CPF do cliente.
     * @return O objeto Cliente se encontrado, ou null se não encontrado.
     */
    public Cliente consultar(Long cpf);

    /**
     * Exclui um cliente pelo CPF.
     * @param cpf CPF do cliente a ser excluído.
     */
    public void excluir(Long cpf);

    /**
     * Altera os dados de um cliente existente.
     * @param cliente Objeto Cliente com os dados atualizados.
     */
    public void alterar(Cliente cliente);
}

