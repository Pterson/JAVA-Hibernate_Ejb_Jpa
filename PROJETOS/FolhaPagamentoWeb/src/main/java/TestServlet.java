import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class TestServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Teste OK!");
    }
}