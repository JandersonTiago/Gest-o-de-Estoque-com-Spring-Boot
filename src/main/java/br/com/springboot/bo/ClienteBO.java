package br.com.springboot.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.dao.CRUD;
import br.com.springboot.dao.ClienteDAO;
import br.com.springboot.model.Cliente;

@Service
public class ClienteBO implements CRUD<Cliente, Long> {

    // Injeção de dependência do ClienteDAO para acessar operações de banco de dados
    @Autowired
    private ClienteDAO dao;

    // Método para pesquisar um cliente pelo ID
    @Override
    public Cliente pesquisaPeloId(Long id) {
        return dao.pesquisaPeloId(id);
    }

    // Método para listar todos os clientes
    @Override
    public List<Cliente> listaTodos() {
        return dao.listaTodos();
    }

    // Método para inserir um novo cliente
    @Override
    public void insere(Cliente cliente) {
        dao.insere(cliente);
    }

    // Método para atualizar um cliente existente
    @Override
    public void atualiza(Cliente cliente) {
        dao.atualiza(cliente);
    }

    // Método para remover um cliente
    @Override
    public void remove(Cliente cliente) {
        dao.remove(cliente);
    }
    
    // Método para inativar um cliente
    public void inativa(Cliente cliente) {
        cliente.setAtivo(false);
        dao.atualiza(cliente);
    }
    
    // Método para ativar um cliente
    public void ativa(Cliente cliente) {
        cliente.setAtivo(true);
        dao.atualiza(cliente);
    }
}
