package main.java.interfaces;

public interface FolhaPagamento {
    double calcularINSS(double taxa);
    double calcularSalarioLiquido();
    void setSalario(double salario);
    double getSalario();
    String getCartao();
}