package br.com.goliveira.dao;

import br.com.goliveira.domain.Cliente;
import java.util.HashMap;
import java.util.Map;

public class ClienteMapDAO implements IClienteDAO {

    private Map<Long, Cliente> map;

    public ClienteMapDAO() {
        this.map = new HashMap<>();
    }

    @Override
    public Boolean cadastrar(Cliente cliente) {
        if (this.map.containsKey(cliente.getCpf())) {
            return false;
        }
        this.map.put(cliente.getCpf(), cliente);
        return true;
    }

    @Override
    public Cliente consultar(Long cpf) {
        return this.map.get(cpf);
    }

    @Override
    public void excluir(Long cpf) {
        this.map.remove(cpf);
    }

    @Override
    public void alterar(Cliente cliente) {
        this.map.put(cliente.getCpf(), cliente);
    }
}
