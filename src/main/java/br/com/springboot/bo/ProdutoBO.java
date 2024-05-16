package br.com.springboot.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.dao.CRUD;
import br.com.springboot.dao.ProdutoDAO;
import br.com.springboot.model.Produto;

@Service
public class ProdutoBO implements CRUD<Produto, Long> {
    
    // Injeção de dependência do ProdutoDAO
    @Autowired
    private ProdutoDAO dao;

    // Método para pesquisar um produto pelo ID
    @Override
    public Produto pesquisaPeloId(Long id) {
        return dao.pesquisaPeloId(id);
    }

    // Método para listar todos os produtos
    @Override
    public List<Produto> listaTodos() {
        return dao.listaTodos();
    }

    // Método para inserir um novo produto
    @Override
    public void insere(Produto produto) {
        dao.insere(produto);
    }

    // Método para atualizar um produto existente
    @Override
    public void atualiza(Produto produto) {
        dao.atualiza(produto);
    }

    // Método para remover um produto
    @Override
    public void remove(Produto produto) {
        dao.remove(produto);
    }
    
    // Método para ativar um produto
    public void ativa(Produto produto) {
        produto.setAtivo(true); // Define o produto como ativo
        dao.atualiza(produto); // Atualiza o produto no banco de dados
    }
    
    // Método para inativar um produto
    public void inativa(Produto produto) {
        produto.setAtivo(false); // Define o produto como inativo
        dao.atualiza(produto); // Atualiza o produto no banco de dados
    }
}
