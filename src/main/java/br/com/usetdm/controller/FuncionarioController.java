package br.com.usetdm.controller;

import br.com.usetdm.model.Funcionario;
import br.com.usetdm.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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
    // Método para salvar o jogador
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

    // Formulário de Alteração dos Jogadores

    @GetMapping("/form-alterar/{id}")
    public String formAlterar(@PathVariable("id") Long id, Model model){

        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Funcionario inválido: " + id));

        model.addAttribute("funcionario", funcionario);
        return "funcionario/form-alterar";
    }

    // Método para alterar o jogador
    @PostMapping("/alterar")
    public String alterar(
            @Valid Funcionario funcionario,
            BindingResult result,
            RedirectAttributes redirectAttributes){

        // Verifica se há erros de validação
        if(result.hasErrors()){
            return "funcionario/form-alterar";
        }
        // Busca o aluno no banco de dados
        Funcionario funcionarioAtualizado = funcionarioRepository.findById(funcionario.getId()).orElseThrow(() -> new IllegalArgumentException("ID inválido"));


        // Seta os dados do aluno

        funcionarioAtualizado.setNome(funcionario.getNome());
        funcionarioAtualizado.setEmail(funcionario.getEmail());
        funcionarioAtualizado.setCpf(funcionario.getCpf());
        funcionarioAtualizado.setTelefone(funcionario.getTelefone());

        // Salva o aluno no banco de dados
        funcionarioRepository.save(funcionarioAtualizado);

        funcionarioRepository.save(funcionario);
        redirectAttributes.addFlashAttribute("mensagem", "Funcionario alterado com sucesso!");
        return "redirect:/funcionario";
    }

}
