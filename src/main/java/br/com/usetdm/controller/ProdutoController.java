package br.com.usetdm.controller;

import br.com.usetdm.model.Funcionario;
import br.com.usetdm.model.Produto;
import br.com.usetdm.repository.FuncionarioRepository;
import br.com.usetdm.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        return "produto/index";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        // Busca o responsável no banco de dados pelo id
        Produto produto = produtoRepository.findById(id).get();

        // Remove o responsável do banco de dados
        produtoRepository.delete(produto);

        // Adiciona uma mensagem que será exibida no template
        redirectAttributes.addFlashAttribute("mensagem", "Produto excluído com sucesso!");

        // Redireciona para a página de listagem de responsáveis
        return "redirect:/produto";
    }

    @GetMapping("/form-inserir")
    public String cadastrar(Model model) {
        model.addAttribute("produto", new Produto());
        return "produto/form-inserir";
    }

    // Método para salvar o jogador
    @PostMapping("/inserir")
    public String inserir(
            @Valid Produto produto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        // Verifica se há erros de validação
        if (result.hasErrors()) {
            return "produto/form-inserir";
        }

        produtoRepository.save(produto);
        redirectAttributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!");
        return "redirect:/produto";
    }

    // Formulário de Alteração dos Jogadores
    @GetMapping("/form-alterar/{id}")
    public String formAlterar(@PathVariable("id") Long id, Model model) {

        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Funcionario inválido: " + id));

        model.addAttribute("Produto",  produto);
        return "produto/form-alterar";
    }

    // Método para alterar o jogador
    @PostMapping("/alterar")
    public String alterar(
            @Valid Produto produto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        // Verifica se há erros de validação
        if (result.hasErrors()) {
            return "produto/form-alterar";
        }


        produtoRepository.save(produto);
        redirectAttributes.addFlashAttribute("mensagem", "Produto alterado com sucesso!");
        return "redirect:/produto";
    }
}
