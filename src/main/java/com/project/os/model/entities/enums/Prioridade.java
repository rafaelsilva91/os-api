package com.project.os.model.entities.enums;

public enum Prioridade {

    BAIXA(0, "BAIXA"),
    MEDIA(1,"MEDIA"),
    ALTA(2,"ALTA");

    private Integer cod;
    private String descricao;

    Prioridade(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Prioridade toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Prioridade prior : Prioridade.values()) {
            if (cod.equals(prior.getCod())) {
                return prior;
            }
        }

        throw new IllegalArgumentException("Prioridade Inválida! "+cod);

    }

}
