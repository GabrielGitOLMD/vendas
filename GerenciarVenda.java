package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Venda;
import model.VendaDAO;
import model.VendaProduto;


@WebServlet("/gerenciarVenda")
public class GerenciarVenda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public GerenciarVenda() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, 
		HttpServletResponse response) 
		throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String mensagem = "";
		try {
			Venda v = (Venda) session.getAttribute("venda");
			VendaDAO vdao = new VendaDAO();
			
			if(vdao.registrar(v)) {
				mensagem = "Venda realizada com sucesso!";
			}else {
				mensagem = "Falha ao registrar a venda!";
			}
			
		} catch (SQLException e) {
			out.print("Erro: " + e.getMessage());
			e.printStackTrace();
		}
		out.println(
				"<script type='text/javascript'>" +
				"alert('" + mensagem + "');" +
				"location.href='listarVenda.jsp';" +
				"</script>"
			);
		
	
	}

	
	protected void doPost(HttpServletRequest request, 
		HttpServletResponse response) 
		throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String acao = request.getParameter("acao");
		System.out.println("Acão: " + acao);
				
		if(acao.equals("alterarQtd")) {
			try {
				Venda v = (Venda) session.getAttribute("venda");
				ArrayList<VendaProduto> carrinho = 
						(ArrayList<VendaProduto>)session.getAttribute("carrinho");
				String []qtd = request.getParameterValues("qtd");
				for(int i = 0; i < carrinho.size(); i++) {
					carrinho.get(i).setQtd(Integer.parseInt(qtd[i]));
				}
				session.setAttribute("carrinho", carrinho);
				
				session.setAttribute("venda", v);
				
				response.sendRedirect("formFinalizarVenda.jsp");
				
			}catch(Exception e) {
				out.print("Erro: " + e.getMessage());
				e.printStackTrace();
			}
			
				
			}
			
			
			
		}
	
	}


