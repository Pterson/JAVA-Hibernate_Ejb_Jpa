package interfaces;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

public interface FolhaPagamento {
    double calcularINSS(double taxa);
    double calcularSalarioLiquido();
    void setSalario(double salario);
    double getSalario();
    String getCartao();
}