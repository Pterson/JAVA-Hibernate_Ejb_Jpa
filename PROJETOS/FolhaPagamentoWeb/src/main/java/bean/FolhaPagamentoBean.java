package main.java.bean;

// IMPORT CORRETO - mesmo nível de pacote
import interfaces.FolhaPagamento;
import java.io.Serializable;

public class FolhaPagamentoBean implements FolhaPagamento, Serializable {
    private static final long serialVersionUID = 1L;

    private double salario;

    @Override
    public double calcularINSS(double taxa) {
        return salario * taxa / 100;
    }

    @Override
    public double calcularSalarioLiquido() {
        return salario - calcularINSS(10);
    }

    @Override
    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public double getSalario() {
        return this.salario;
    }

    @Override
    public String getCartao() {
        return "Cartão de Pagamento: R$ " + String.format("%.2f", calcularSalarioLiquido());
    }
}