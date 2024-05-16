package br.com.springboot.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.dao.CRUD;
import br.com.springboot.dao.FornecedorDAO;
import br.com.springboot.model.Fornecedor;

@Service
public class FornecedorBO implements CRUD<Fornecedor, Long> {
    
    // Injeção de dependência do FornecedorDAO para acessar operações de banco de dados
    @Autowired
    private FornecedorDAO dao;

    // Método para pesquisar um fornecedor pelo ID
    @Override
    public Fornecedor pesquisaPeloId(Long id) {
        return dao.pesquisaPeloId(id);
    }

    // Método para listar todos os fornecedores
    @Override
    public List<Fornecedor> listaTodos() {
        return dao.listaTodos();
    }

    // Método para inserir um novo fornecedor
    @Override
    public void insere(Fornecedor fornecedor) {
        dao.insere(fornecedor);
    }

    // Método para atualizar um fornecedor existente
    @Override
    public void atualiza(Fornecedor fornecedor) {
        dao.atualiza(fornecedor);
    }

    // Método para remover um fornecedor
    @Override
    public void remove(Fornecedor fornecedor) {
        dao.remove(fornecedor);
    }
    
    // Método para inativar um fornecedor
    public void inativa(Fornecedor fornecedor) {
        fornecedor.setAtivo(false);
        dao.atualiza(fornecedor);
    }
    
    // Método para ativar um fornecedor
    public void ativa(Fornecedor fornecedor) {
        fornecedor.setAtivo(true);
        dao.atualiza(fornecedor);
    }

}
