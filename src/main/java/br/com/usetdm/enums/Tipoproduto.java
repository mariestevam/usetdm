package br.com.usetdm.enums;

public enum Tipoproduto {
    TENIS("Tenis"),
    SALTO("Salto"),
    BOTA("Bota"),
    MOCASSIM("Mocassim"),
    SLIPON("Slip-on"),

    SAPATILHAS("Sapatilhas"),

    OXFORD("Oxford"),

    CHINELO("Chinelo/Rasteirinha"),

    SANDALIA("Sandália"),

    INFANTIL("Infantil"),;




    private String descricao;

    Tipoproduto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
