package Controller;

import Model.Category;
import Model.User;
import Service.CategoryService;
import Service.CategoryServiceImplement;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(urlPatterns = {"/edit"})
@jakarta.servlet.annotation.MultipartConfig
public class CategoryEditController extends HttpServlet {
    private CategoryServiceImplement  categoryService=new CategoryServiceImplement();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("đã chuyển đến trang edit");
        String idStr = req.getParameter("id");
        System.out.println(idStr);
        Category category= categoryService.get_cateid(Integer.parseInt(idStr));
        req.setAttribute("category",category);
        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/WEB-INF/category/edit_category.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id   = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String oldIcon = req.getParameter("oldIcon"); // có thể null
        Category current = categoryService.get_cateid(id);
        Part filePart = req.getPart("icon");
        String iconToSave = (oldIcon != null && !oldIcon.isBlank()) ? oldIcon : current.getIcons();

        if (filePart != null && filePart.getSize() > 0) {
            String submitted = filePart.getSubmittedFileName();
            if (submitted != null && !submitted.isBlank()) {
                String baseName = Paths.get(submitted).getFileName().toString().replaceAll("[\\\\/]+", "");
                String unique = System.currentTimeMillis() + "_" + baseName;
                String uploadDir = getServletContext().getRealPath("/uploads");
                Files.createDirectories(Paths.get(uploadDir));
                Path savePath = Paths.get(uploadDir, unique);
                try (InputStream in = filePart.getInputStream()) {
                    Files.copy(in, savePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                }
                if (oldIcon != null && !oldIcon.isBlank() && !oldIcon.equals(unique)) {
                    Path oldPath = Paths.get(uploadDir, oldIcon);
                    try { Files.deleteIfExists(oldPath); } catch (Exception ignore) {}
                }
                iconToSave = unique;
            }
        }
        current.setCate_name(name);
        current.setIcons(iconToSave);
        categoryService.update(current);
        resp.sendRedirect(req.getContextPath() + "/list");
    }
}
