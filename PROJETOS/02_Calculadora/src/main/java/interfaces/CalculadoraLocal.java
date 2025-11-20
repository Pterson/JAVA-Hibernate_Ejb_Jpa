package interfaces;

import javax.ejb.Local;

/**
 * INTERFACE LOCAL DO EJB - CALCULADORA
 * Define o contrato que o EJB deve implementar
 * @Local - Indica que esta é uma interface local para EJB
 */
@Local
public interface CalculadoraLocal {
    double somar(double x, double y);
    double subtrair(double x, double y);
    double multiplicar(double x, double y);
    double dividir(double x, double y);
}