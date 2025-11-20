package bean;

import javax.ejb.Stateless;
import interfaces.CalculadoraLocal;

/**
 * IMPLEMENTAÇÃO DO EJB - CALCULADORA
 * @Stateless - EJB sem estado, gerenciado pelo container
 */
@Stateless
public class CalculadoraBean implements CalculadoraLocal {

    @Override
    public double somar(double x, double y) {
        return x + y;
    }

    @Override
    public double subtrair(double x, double y) {
        return x - y;
    }

    @Override
    public double multiplicar(double x, double y) {
        return x * y;
    }

    @Override
    public double dividir(double x, double y) {
        return x / y;
    }
}