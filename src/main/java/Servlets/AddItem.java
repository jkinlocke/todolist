package Servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
@WebServlet(name = "Servlets.AddItem", value = "/Servlets.addItem")
public class AddItem extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtain the item to be added from the request
        String newItem = request.getParameter("item");

        if (newItem != null && !newItem.isEmpty()) {
            // Instantiate your DAO class for database operations
            DatabaseConnection dao = new DatabaseConnection("jdbc:mysql://localhost:3306/todolist", "root", "pass123");

            try {
                // Add the item to the database
                dao.insertItem(newItem);
            } catch (Exception e) {
                // Handle any exceptions (e.g., database errors)
                e.printStackTrace(); // Log the exception for debugging
            }
        }

        // Redirect back to the ToDo list page
        response.sendRedirect("ToDo.jsp");
    }
}