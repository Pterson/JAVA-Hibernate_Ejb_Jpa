package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.FolhaPagamentoBean;
import interfaces.FolhaPagamento;

@WebServlet("/folha")
public class ServletFolha extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private FolhaPagamento fp = new FolhaPagamentoBean();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html; charset=UTF-8");

        try {
            double salario = Double.parseDouble(request.getParameter("salario"));
            fp.setSalario(salario);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resultado do Cálculo</title>");
            out.println("<meta charset='UTF-8'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Resultado do Cálculo de Folha</h2>");
            out.println("<p>Salário Bruto: R$ " + String.format("%.2f", salario) + "</p>");
            out.println("<p>Desconto INSS (10%): R$ " + String.format("%.2f", fp.calcularINSS(10)) + "</p>");
            out.println("<p><strong>Salário Líquido: R$ " + String.format("%.2f", fp.calcularSalarioLiquido()) + "</strong></p>");
            out.println("<p>" + fp.getCartao() + "</p>");
            out.println("<br/><a href='index.jsp'>Voltar</a>");
            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            out.println("Erro: " + e.getMessage());
            out.println("<br/><a href='index.jsp'>Voltar</a>");
        }
    }
}