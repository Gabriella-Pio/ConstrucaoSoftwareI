package atividade01.abstracao;

public class RelatorioHTML implements Relatorio {
    @Override
    public void gerar() {
        System.out.println("Gerando relatório em HTML");
    }
}