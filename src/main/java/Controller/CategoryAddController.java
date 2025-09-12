package Controller;

import Model.Category;
import Service.CategoryService;
import Service.CategoryServiceImplement;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.nio.file.Path;

@MultipartConfig
@WebServlet(urlPatterns = {"/add"})
public class CategoryAddController extends HttpServlet {
    private final CategoryService cateService = new CategoryServiceImplement();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/WEB-INF/category/add_category.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String name = req.getParameter("name");
        Part filePart = req.getPart("icon");
        String fileName = Path.of(filePart.getSubmittedFileName()).getFileName().toString();
        Category category = new Category();
        category.setCate_name(name);
        category.setIcons(fileName);
        cateService.insert(category);
    }
}
