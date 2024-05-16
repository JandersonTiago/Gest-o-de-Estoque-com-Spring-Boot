package br.com.springboot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.springboot.model.Fornecedor;

@Repository
@Transactional
public class FornecedorDAO implements CRUD<Fornecedor, Long> {
    
    // Injeção de dependência do EntityManager para interagir com o banco de dados
    @PersistenceContext
    private EntityManager em;

    // Método para pesquisar um fornecedor pelo ID
    @Override
    public Fornecedor pesquisaPeloId(Long id) {
        return em.find(Fornecedor.class, id);
    }

    // Método para listar todos os fornecedores
    @Override
    public List<Fornecedor> listaTodos() {
        // Consulta JPQL para selecionar todos os fornecedores
        Query query = em.createQuery("select f from Fornecedor f");
        return query.getResultList();
    }

    // Método para inserir um novo fornecedor
    @Override
    public void insere(Fornecedor fornecedor) {
        em.persist(fornecedor);
    }

    // Método para atualizar um fornecedor existente
    @Override
    public void atualiza(Fornecedor fornecedor) {
        em.merge(fornecedor);
    }

    // Método para remover um fornecedor
    @Override
    public void remove(Fornecedor fornecedor) {
        em.remove(fornecedor);
    }

}
