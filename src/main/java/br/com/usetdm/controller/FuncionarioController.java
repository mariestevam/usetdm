package br.com.usetdm.controller;

import br.com.usetdm.model.Funcionario;
import br.com.usetdm.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
        Funcionario funcionario = funcionarioRepository.findById(id).get();

        // Remove o responsável do banco de dados
        funcionarioRepository.delete(funcionario);

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


    // Método para salvar o funcionário
    @PostMapping("/salvar")
    public String salvar(
            @Valid Funcionario funcionario,
            BindingResult result,
            RedirectAttributes redirectAttributes){

        // Verifica se há erros de validação
        if(result.hasErrors()){
            return "funcionario/form-inserir";
        }

        funcionarioRepository.save(funcionario);
        redirectAttributes.addFlashAttribute("mensagem", "Funcionario salvo com sucesso!");
        return "redirect:/funcionario";
    }




    // Formulário de Alteração dos Funcionário
    @GetMapping("/form-alterar/{id}")
    public String formAlterar(@PathVariable("id") Long id, Model model){

        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Funcionario inválido: " + id));

        model.addAttribute("funcionario", funcionario);
        return "funcionario/form-alterar";
    }

    // Método para alterar o funcionário
    @PostMapping("/alterar")
    public String alterar(
            @Valid Funcionario funcionario,
            BindingResult result,
            RedirectAttributes redirectAttributes){

        // Verifica se há erros de validação
        if(result.hasErrors()){
            return "funcionario/form-alterar";
        }

        funcionarioRepository.save(funcionario);
        redirectAttributes.addFlashAttribute("mensagem", "Funcionario alterado com sucesso!");
        return "redirect:/funcionario";
    }
}


