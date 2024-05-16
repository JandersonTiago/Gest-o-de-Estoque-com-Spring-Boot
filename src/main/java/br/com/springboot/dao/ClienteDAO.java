package br.com.springboot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.springboot.model.Cliente;

@Repository
@Transactional
public class ClienteDAO implements CRUD<Cliente, Long> {
    
    // Injeção de dependência do EntityManager para interagir com o banco de dados
    @PersistenceContext
    private EntityManager entityManager;
    
    // Método para pesquisar um cliente pelo ID
    @Override
    public Cliente pesquisaPeloId(Long id) {
        return entityManager.find(Cliente.class, id);
    }

    // Método para listar todos os clientes
    @Override
    public List<Cliente> listaTodos() {
        // Consulta JPQL para selecionar todos os clientes
        Query query = entityManager.createQuery("SELECT c FROM Cliente c");
        return (List<Cliente>) query.getResultList();
    }

    // Método para inserir um novo cliente
    @Override
    public void insere(Cliente cliente) {
        entityManager.persist(cliente);
    }

    // Método para atualizar um cliente existente
    @Override
    public void atualiza(Cliente cliente) {
        entityManager.merge(cliente);
    }

    // Método para remover um cliente
    @Override
    public void remove(Cliente cliente) {
        entityManager.remove(cliente);
    }
}
