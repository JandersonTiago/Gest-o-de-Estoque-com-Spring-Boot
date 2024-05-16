package br.com.springboot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.springboot.model.NotaEntradaItem;

@Repository // Indica que esta classe é um componente de acesso a dados e permite que o Spring a reconheça automaticamente.
@Transactional // Define que cada método público nesta classe deve ser envolto por uma transação.
public class NotaEntradaItemDAO implements CRUD<NotaEntradaItem, Long> { // Implementa a interface CRUD, que contém métodos básicos de operações CRUD.

    @PersistenceContext // Injeta automaticamente um EntityManager, que gerencia as entidades do JPA.
    private EntityManager em;

    @Override
    public NotaEntradaItem pesquisaPeloId(Long id) { // Método para pesquisar um NotaEntradaItem pelo ID.
        return em.find(NotaEntradaItem.class, id); // Utiliza o método find do EntityManager para buscar a entidade pelo ID.
    }

    @Override
    public List<NotaEntradaItem> listaTodos() { // Método para listar todos os NotaEntradaItem.
        Query query = em.createNamedQuery("select nei from NotaEntradaItem nei"); // Cria uma consulta com a JPQL para selecionar todos os NotaEntradaItem.
        return query.getResultList(); // Retorna a lista de resultados da consulta.
    }

    @Override
    public void insere(NotaEntradaItem notaEntradaItem) { // Método para inserir um novo NotaEntradaItem.
        em.persist(notaEntradaItem); // Utiliza o método persist do EntityManager para inserir a entidade no banco de dados.
    }

    @Override
    public void atualiza(NotaEntradaItem notaEntradaItem) { // Método para atualizar um NotaEntradaItem existente.
        em.merge(notaEntradaItem); // Utiliza o método merge do EntityManager para atualizar a entidade no banco de dados.
    }

    @Override
    public void remove(NotaEntradaItem notaEntradaItem) { // Método para remover um NotaEntradaItem existente.
        em.remove(notaEntradaItem); // Utiliza o método remove do EntityManager para excluir a entidade do banco de dados.
    }
    
    public List<NotaEntradaItem> listaItensNota(Long notaEntradaId){ // Método para listar os itens de uma nota de entrada específica.
        Query query = em.createQuery("from NotaEntradaItem n where n.notaEntrada.id = :notaEntradaId") // Cria uma consulta com a JPQL para selecionar os itens da nota de entrada com o ID especificado.
                .setParameter("notaEntradaId", notaEntradaId); // Define o parâmetro "notaEntradaId" na consulta com o valor fornecido.
        return query.getResultList(); // Retorna a lista de resultados da consulta.
    }
}
