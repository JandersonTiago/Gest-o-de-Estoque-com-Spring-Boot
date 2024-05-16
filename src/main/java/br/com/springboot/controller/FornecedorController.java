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
import br.com.springboot.model.Fornecedor;

@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {
	
	@Autowired
	private FornecedorBO fornecedorBO;
	
	// Método para exibir o formulário de cadastro de fornecedor
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(ModelMap model) {
		model.addAttribute("fornecedor", new Fornecedor());
		return new ModelAndView("/fornecedor/formulario", model);
	}
	
	// Método para salvar um novo fornecedor ou atualizar um existente
	@RequestMapping(value = "", method=RequestMethod.POST)
	public String salva(@Valid @ModelAttribute Fornecedor fornecedor, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors())
			return "fornecedor/formulario";
		
		if (fornecedor.getId() == null) {
			fornecedorBO.insere(fornecedor);
			attr.addFlashAttribute("feedback", "O fornecedor foi cadastrado com sucesso");
		}
		else { 
			fornecedorBO.atualiza(fornecedor);
			attr.addFlashAttribute("feedback", "O fornecedor foi atualizado com sucesso");
		}
		return "redirect:/fornecedores";
	}
	
	// Método para listar todos os fornecedores
	@RequestMapping(value = "", method=RequestMethod.GET)
	public ModelAndView lista(ModelMap model) {
		model.addAttribute("fornecedores", fornecedorBO.listaTodos());
		return new ModelAndView("/fornecedor/lista", model);		
	}

	// Método para exibir o formulário de edição de um fornecedor
	@RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
	public ModelAndView edita(@PathVariable("id") Long id, ModelMap model) {
		try {
			model.addAttribute("fornecedor", fornecedorBO.pesquisaPeloId(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/fornecedor/formulario", model);
	}
	
	// Método para inativar um fornecedor
	@RequestMapping(value = "/inativa/{id}", method = RequestMethod.GET)
	public String inativa(@PathVariable("id") Long id, RedirectAttributes attr) {
		try {
			Fornecedor fornecedor = fornecedorBO.pesquisaPeloId(id); 
			fornecedorBO.inativa(fornecedor);
			attr.addFlashAttribute("feedback", "O fornecedor foi inativado com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/fornecedores";
	}
	
	// Método para ativar um fornecedor
	@RequestMapping(value = "/ativa/{id}", method = RequestMethod.GET)
	public String ativa(@PathVariable("id") Long id, RedirectAttributes attr) {
		System.out.println(id);
		try {
			Fornecedor fornecedor = fornecedorBO.pesquisaPeloId(id); 
			fornecedorBO.ativa(fornecedor);
			attr.addFlashAttribute("feedback", "O fornecedor foi ativado com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/fornecedores";
	}
}
