package mb;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import interfaces.CalculadoraLocal;

/**
 * MANAGED BEAN JSF - Conecta a interface web com o EJB
 */
@ManagedBean(name = "calcMB")
@RequestScoped
public class CalculadoraManagedBean {
    @EJB
    private CalculadoraLocal calc;

    private double x, y;
    private String resultado;

    // Getters e Setters
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }

    // Métodos de ação
    public void somar() {
        double result = calc.somar(x, y);
        resultado = "Soma = " + result;
    }

    public void subtrair() {
        double result = calc.subtrair(x, y);
        resultado = "Subtração = " + result;
    }

    public void multiplicar() {
        double result = calc.multiplicar(x, y);
        resultado = "Multiplicação = " + result;
    }

    public void dividir() {
        double result = calc.dividir(x, y);
        resultado = "Divisão = " + result;
    }
}
