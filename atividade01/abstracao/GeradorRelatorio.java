package atividade01.abstracao;

public class GeradorRelatorio {
  public static void main(String[] args) {
    Relatorio relatorioPDF = new RelatorioPDF();
    Relatorio relatorioHTML = new RelatorioHTML();
    relatorioPDF.gerar();
    relatorioHTML.gerar();
  }
}
