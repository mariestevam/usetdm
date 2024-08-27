package br.com.usetdm.controller;

import br.com.usetdm.model.Funcionario;
import br.com.usetdm.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public String index(Model model){
        model.addAttribute("funcionarios", funcionarioRepository.findAll());
        return "funcionario/index";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        // Busca o responsável no banco de dados pelo id
        Funcionario responsavel = funcionarioRepository.findById(id).get();

        // Remove o responsável do banco de dados
        funcionarioRepository.delete(responsavel);

        // Adiciona uma mensagem que será exibida no template
        redirectAttributes.addFlashAttribute("mensagem", "Funcionario excluído com sucesso!");

        // Redireciona para a página de listagem de responsáveis
        return "redirect:/funcionario";
    }

    @GetMapping("/form-inserir")
    public String cadastrar(Model model){
        model.addAttribute("funcionario", new Funcionario());
        return "funcionario/form-inserir";
    }
}
