package Controller;

import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet(urlPatterns = {"/waiting"})
public class WaitingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        if (session!=null && session.getAttribute("user")!=null) {
            User user = (User) session.getAttribute("user");
            switch (user.getId()){
                case 1:
                    req.getRequestDispatcher("/WEB-INF/admin/home.jsp").forward(req, resp);
                    break;
                    case 2:
                        req.getRequestDispatcher("/WEB-INF/manager/home.jsp").forward(req, resp);
                        break;
                        case 3:
                            req.getRequestDispatcher("/WEB-INF/user/home.jsp").forward(req, resp);
                            break;
            }
        }else {
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
    }
}
