<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cálculo de Folha de Pagamento</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        table { border-collapse: collapse; }
        td { padding: 8px; }
        input[type="text"] { padding: 5px; width: 150px; }
        input[type="submit"] { padding: 8px 15px; background: #4CAF50; color: white; border: none; cursor: pointer; }
        input[type="submit"]:hover { background: #45a049; }
    </style>
</head>
<body>
    <h2>Sistema de Cálculo de Folha de Pagamento</h2>
    <form action="folha" method="post">
        <table>
            <tr>
                <td>Informe o valor do salário:</td>
                <td><input type="text" name="salario" size="10" placeholder="Ex: 2500.00" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Calcular" /></td>
            </tr>
        </table>
    </form>

    <br/>
    <h3>Exemplo de Cálculo:</h3>
    <ul>
        <li>Salário Bruto: R$ 3.000,00</li>
        <li>INSS (10%): R$ 300,00</li>
        <li>Salário Líquido: R$ 2.700,00</li>
    </ul>
</body>
</html>