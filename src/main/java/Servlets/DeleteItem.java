package Servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
@WebServlet(name = "Servlets.DeleteItem", value = "/Servlets.DeleteItem")
public class DeleteItem extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtain the item to be deleted from the request
        String itemToDelete = request.getParameter("to_Do_item");

        if (itemToDelete != null && !itemToDelete.isEmpty()) {
            // Instantiate your DAO class for database operations
            DatabaseConnection dao = new DatabaseConnection("jdbc:mysql://localhost:3306/todolist", "root", "pass123");

            try {
                // Delete the item from the database
                dao.deleteItem(itemToDelete);
            } catch (Exception e) {
                // Handle any exceptions (e.g., database errors)
                e.printStackTrace(); // Log the exception for debugging
            }
        }

        // Redirect back to the ToDo list page
        response.sendRedirect("ToDo.jsp");
    }
}