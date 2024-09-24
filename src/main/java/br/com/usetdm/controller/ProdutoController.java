package br.com.usetdm.controller;

import br.com.usetdm.model.Funcionario;
import br.com.usetdm.model.Produto;
import br.com.usetdm.repository.FuncionarioRepository;
import br.com.usetdm.repository.ProdutoRepository;
import br.com.usetdm.util.FileUploadUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

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

    @PostMapping("/salvar")
    public String salvar(
            @Valid Produto produto,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            @RequestParam("foto") MultipartFile multipartFile
    )throws IOException {

        produto.setImagem("semimagem");

        // Verifica se há erros de validação
        if(result.hasErrors()){
            return "produto/form-inserir";
        }

        String extensao = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());



        produtoRepository.save(produto);

        // fileName = user.getId() + "." + extensao;
        String fileName = produto.getId() + "." + extensao;

        produto.setImagem(fileName);

        produtoRepository.save(produto);

        String uploadPasta = "src/main/resources/static/assets/img/fotos-produtos";

        FileUploadUtil.saveFile(uploadPasta, fileName, multipartFile);

        redirectAttributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!");
        return "redirect:/produto";
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

        model.addAttribute("produto",  produto);
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
