package fr.eni.projetenchere.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import fr.eni.projetenchere.bll.EnchereManager;
import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.dal.jdbc.EnchereDAOJdbcImplementation;

/**
 * Servlet implementation class AfficherResultatRecherche
 */
@WebServlet(name = "ResultatRecherche", value = "/recherche")
public class AfficherResultatRecherche extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String filter = request.getParameter("filter");
			
			EnchereManager enchereManager = EnchereManager.getInstance();
			List<Enchere> encheres = enchereManager.selectAllEncheresFiltredByName(filter);
			request.setAttribute("encheres", encheres);
			System.out.println("ENCHERES DANS REQUETE" + request.getAttribute("encheres"));
			request.getRequestDispatcher("/WEB-INF/views/recherche.jsp").forward(request, response);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		System.out.println("ENCHERES DANS REQUETE " + request.getAttribute("encheres") +" DANS LE DOPOST");
	}

}
