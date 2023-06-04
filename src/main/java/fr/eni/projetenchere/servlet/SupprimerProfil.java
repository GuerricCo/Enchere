package fr.eni.projetenchere.servlet;

import java.io.IOException;

import fr.eni.projetenchere.bll.UserManager;
import fr.eni.projetenchere.bo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SupprimerCompte", value = "/DeleteAccount")
public class SupprimerProfil extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'utilisateur de la session
        User user = (User) request.getSession().getAttribute("utilisateurConnecte");

        if (user != null) {
            // Appeler la méthode de suppression d'utilisateur dans UserManager
            UserManager um = UserManager.getInstance();
            um.deleteUser(user);

            // Terminer la session
            request.getSession().invalidate();

            // Rediriger vers la page d'accueil
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            // Si l'utilisateur n'est pas connecté, redirigez vers la page de connexion
            response.sendRedirect(request.getContextPath() + "/Login");
        }
    }
}
