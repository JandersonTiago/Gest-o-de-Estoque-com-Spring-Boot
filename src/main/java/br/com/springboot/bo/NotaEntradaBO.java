package br.com.springboot.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.dao.CRUD;
import br.com.springboot.dao.NotaEntradaDAO;
import br.com.springboot.model.NotaEntrada;

@Service
public class NotaEntradaBO implements CRUD<NotaEntrada, Long> {

	@Autowired
	private NotaEntradaDAO notaEntradaDAO; // Injeção do DAO para acesso aos dados de NotaEntrada
	
	@Override
	public NotaEntrada pesquisaPeloId(Long id) {
		return notaEntradaDAO.pesquisaPeloId(id); // Método para pesquisar uma nota de entrada pelo ID
	}

	@Override
	public List<NotaEntrada> listaTodos() {
		return notaEntradaDAO.listaTodos(); // Método para listar todas as notas de entrada
	}

	@Override
	public void insere(NotaEntrada notaEntrada) {
		notaEntradaDAO.insere(notaEntrada); // Método para inserir uma nova nota de entrada
	}

	@Override
	public void atualiza(NotaEntrada notaEntrada) {
		notaEntradaDAO.atualiza(notaEntrada); // Método para atualizar uma nota de entrada existente
	}

	@Override
	public void remove(NotaEntrada notaEntrada) {
		notaEntradaDAO.remove(notaEntrada); // Método para remover uma nota de entrada
	}
}
