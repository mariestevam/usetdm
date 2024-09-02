package br.com.usetdm.enums;

public enum Tipoproduto {
    TENIS("Tenis"),
    SALTO("Salto"),;


    private String descricao;

    Tipoproduto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
