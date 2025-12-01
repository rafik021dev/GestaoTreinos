/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaotreinos.enums;

public enum TipoRefeicao {

    CAFEDAMANHA("Café da Manhã"),
    ALMOCO("Almoço"),
    JANTA("Janta"),
    LANCHE("Lanche"),
    CEIA("Ceia");
 

    private final String label;

    TipoRefeicao(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}

