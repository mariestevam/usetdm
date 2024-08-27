package br.com.usetdm.enums;

public enum Funcao {
    ADMINISTRADOR("Administrador"),
    VENDEDOR("Vendedor"),;


    private String descricao;

    Funcao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}