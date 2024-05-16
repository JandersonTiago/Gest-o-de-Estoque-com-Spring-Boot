package br.com.springboot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.springboot.bo.FornecedorBO;
import br.com.springboot.bo.NotaEntradaBO;
import br.com.springboot.bo.ProdutoBO;
import br.com.springboot.model.NotaEntrada;
import br.com.springboot.model.NotaEntradaItem;

@Controller
@RequestMapping("/nota-entrada")
public class NotaEntradaController {

	@Autowired
	private NotaEntradaBO notaEntradaBO; // Injeção do serviço de manipulação de NotaEntrada

	@Autowired
	private FornecedorBO fornecedorBO; // Injeção do serviço de manipulação de Fornecedor

	@Autowired
	private ProdutoBO produtoBO; // Injeção do serviço de manipulação de Produto

	// Endpoint para exibir formulário de criação de nova nota de entrada
	@RequestMapping(value="/novo", method=RequestMethod.GET)
	public ModelAndView novo(ModelMap model) {
		model.addAttribute("notaEntrada", new NotaEntrada()); // Adiciona um objeto NotaEntrada vazio ao modelo
		model.addAttribute("fornecedores", fornecedorBO.listaTodos()); // Adiciona a lista de fornecedores ao modelo
		return new ModelAndView("/nota-entrada/formulario", model); // Retorna a página do formulário
	}

	// Endpoint para salvar uma nota de entrada
	@RequestMapping(value="", method=RequestMethod.POST)
	public String salva(@Valid @ModelAttribute NotaEntrada notaEntrada,
						BindingResult result,
						RedirectAttributes attr,
						ModelMap model) {
		
		if (notaEntrada.getFornecedor().getId() == null) // Verifica se o fornecedor foi selecionado
			result.rejectValue("fornecedor", "field.required"); // Adiciona um erro ao resultado de validação
		
		if (result.hasErrors()) { // Verifica se houve erros de validação
			model.addAttribute("fornecedores", fornecedorBO.listaTodos()); // Adiciona a lista de fornecedores ao modelo
			return "/nota-entrada/formulario"; // Retorna para o formulário de nota de entrada
		}
		
		if (notaEntrada.getId() == null) { // Verifica se é uma nova nota de entrada
			notaEntradaBO.insere(notaEntrada); // Insere a nova nota de entrada
			attr.addFlashAttribute("feedback", "A nota de entrada foi cadastrada com sucesso"); // Adiciona mensagem de feedback
		} else {
			notaEntradaBO.atualiza(notaEntrada); // Atualiza a nota de entrada existente
			attr.addFlashAttribute("feedback", "Os dados da nota de entrada foram atualizados com sucesso"); // Adiciona mensagem de feedback
		}
		
		return "redirect:/nota-entrada"; // Redireciona para a lista de notas de entrada
	}

	// Endpoint para listar todas as notas de entrada
	@RequestMapping(value="", method=RequestMethod.GET)
	public ModelAndView lista(ModelMap model) {
		model.addAttribute("notas", notaEntradaBO.listaTodos()); // Adiciona a lista de notas de entrada ao modelo
		return new ModelAndView("/nota-entrada/lista", model); // Retorna a página de lista de notas de entrada
	}

	// Endpoint para adicionar um item à nota de entrada
	@RequestMapping(value="/{id}/item", method=RequestMethod.GET)
	public ModelAndView produto(@PathVariable("id") Long id, ModelMap model) {
		NotaEntradaItem nei = new NotaEntradaItem(); // Cria um novo item de nota de entrada
		NotaEntrada ne = notaEntradaBO.pesquisaPeloId(id); // Pesquisa a nota de entrada pelo ID
		nei.setNotaEntrada(ne); // Associa a nota de entrada ao item
		model.addAttribute("notaEntradaItem", nei); // Adiciona o item de nota de entrada ao modelo
		model.addAttribute("produtos", produtoBO.listaTodos()); // Adiciona a lista de produtos ao modelo
		return new ModelAndView("/nota-entrada-item/formulario", model); // Retorna o formulário de item de nota de entrada
	}

	// Endpoint para editar uma nota de entrada
	@RequestMapping(value="/edita/{id}", method=RequestMethod.GET)
	public ModelAndView edita(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("notaEntradaItem", new NotaEntradaItem()); // Adiciona um item de nota de entrada vazio ao modelo
		model.addAttribute("fornecedores", fornecedorBO.listaTodos()); // Adiciona a lista de fornecedores ao modelo
		model.addAttribute("notaEntrada", notaEntradaBO.pesquisaPeloId(id)); // Adiciona a nota de entrada ao modelo
		return new ModelAndView("/nota-entrada/formulario", model); // Retorna o formulário de nota de entrada
	}

	// Endpoint para remover uma nota de entrada
	@RequestMapping(value="/remove/{id}", method=RequestMethod.GET)
	public String remove(@PathVariable("id") Long id, RedirectAttributes attr) {
		NotaEntrada ne = notaEntradaBO.pesquisaPeloId(id); // Pesquisa a nota de entrada pelo ID
		notaEntradaBO.remove(ne); // Remove a nota de entrada
		attr.addFlashAttribute("feedback", "Nota entrada removida com sucesso"); // Adiciona mensagem de feedback
		return "redirect:/nota-entrada"; // Redireciona para a lista de notas de entrada
	}
}
