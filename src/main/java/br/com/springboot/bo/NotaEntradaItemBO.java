package br.com.springboot.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.dao.CRUD;
import br.com.springboot.dao.NotaEntradaItemDAO;
import br.com.springboot.model.NotaEntradaItem;

@Service
public class NotaEntradaItemBO implements CRUD<NotaEntradaItem, Long> {

	@Autowired
	private NotaEntradaItemDAO dao; // Injeção do DAO para acesso aos dados de NotaEntradaItem
	
	@Override
	public NotaEntradaItem pesquisaPeloId(Long id) {
		return dao.pesquisaPeloId(id); // Método para pesquisar um item de nota de entrada pelo ID
	}

	@Override
	public List<NotaEntradaItem> listaTodos() {
		return dao.listaTodos(); // Método para listar todos os itens de nota de entrada
	}

	@Override
	public void insere(NotaEntradaItem notaEntradaItem) {
		dao.insere(notaEntradaItem); // Método para inserir um item de nota de entrada
	}

	@Override
	public void atualiza(NotaEntradaItem notaEntradaItem) {
		dao.atualiza(notaEntradaItem); // Método para atualizar um item de nota de entrada
	}

	@Override
	public void remove(NotaEntradaItem notaEntradaItem) {
		dao.remove(notaEntradaItem); // Método para remover um item de nota de entrada
	}
	
	// Método para verificar se um item de nota de entrada já foi adicionado à nota de entrada
	public boolean itemJaAdicionado(NotaEntradaItem notaEntradaItem) {
		Long notaEntradaId = notaEntradaItem.getNotaEntrada().getId(); // Obtém o ID da nota de entrada associada ao item
		List<NotaEntradaItem> itens = dao.listaItensNota(notaEntradaId); // Obtém todos os itens da nota de entrada
		
		Long produtoId = notaEntradaItem.getProduto().getId(); // Obtém o ID do produto do item
		
		if (notaEntradaItem.getId() == null) { // Verifica se é uma inserção de novo item
			for (NotaEntradaItem item : itens) { // Itera sobre todos os itens da nota de entrada
				if (item.getProduto().getId() == produtoId) { // Verifica se o produto já foi adicionado
					return true; // Retorna true se o produto já foi adicionado
				}
			}
		} else { // Caso contrário, é uma atualização de um item existente
			Long notaEntradaItemId = notaEntradaItem.getId(); // Obtém o ID do item de nota de entrada
			for (NotaEntradaItem item : itens) { // Itera sobre todos os itens da nota de entrada
				if (item.getProduto().getId() == produtoId && notaEntradaItemId != item.getId()) { // Verifica se o produto já foi adicionado, excluindo o próprio item atual
					return true; // Retorna true se o produto já foi adicionado
				}
			}
		}
		return false; // Retorna false se o produto ainda não foi adicionado
	}	
}
