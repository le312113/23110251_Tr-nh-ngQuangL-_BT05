package Controller;

import Dao.UserDaoImplement;
import Model.User;
import Service.UserServiceImplement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private UserServiceImplement userServiceImplement = new UserServiceImplement();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session!=null && session.getAttribute("user")!=null) {
            resp.sendRedirect(req.getContextPath()+"/waiting");
            return;
        }
        req.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        if (username == null || password == null ) {
            req.setAttribute("error", "The username or password is empty");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }
        User user = userServiceImplement.getUser(username);
        if (user==null || !user.getPassWord().equals(password)) {
            req.setAttribute("error", "The username or password is wrong");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }
        HttpSession  session = req.getSession(true);
        session.setAttribute("user", user);
        resp.sendRedirect(req.getContextPath()+"/waiting");
    }
}
