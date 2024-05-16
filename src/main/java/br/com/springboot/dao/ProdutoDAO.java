package br.com.springboot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.springboot.model.Produto;

// Classe que implementa operações CRUD para a entidade Produto
@Repository
@Transactional
public class ProdutoDAO implements CRUD<Produto, Long> {
	
	@PersistenceContext
	private EntityManager em;

	// Pesquisa um produto pelo ID
	@Override
	public Produto pesquisaPeloId(Long id) {
		return em.find(Produto.class, id);
	}

	// Lista todos os produtos
	@Override
	public List<Produto> listaTodos() {
		// Cria uma consulta JPQL para selecionar todos os produtos
		Query query = em.createQuery("select p from Produto p");
		// Executa a consulta e retorna a lista de produtos resultante
		return query.getResultList();
	}

	// Insere um novo produto
	@Override
	public void insere(Produto produto) {
		// Persiste o produto no banco de dados
		em.persist(produto);
	}

	// Atualiza um produto existente
	@Override
	public void atualiza(Produto produto) {
		// Mescla o produto no contexto de persistência
		em.merge(produto);
	}

	// Remove um produto do banco de dados
	@Override
	public void remove(Produto produto) {
		// Remove o produto do contexto de persistência
		em.remove(produto);
	}
}
