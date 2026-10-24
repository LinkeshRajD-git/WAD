import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GradeServlet extends HttpServlet {

    private String escapeHtml(String value) {
        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#x27;");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String grade = request.getParameter("grade");

        if (name == null || name.trim().isEmpty()
                || grade == null || grade.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Please enter both name and grade.");
            return;
        }

        name = escapeHtml(name.trim());
        grade = escapeHtml(grade.trim());

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'><head><meta charset='UTF-8'>");
        out.println("<title>Grade Result</title></head><body>");
        out.println("<h2>Hello, " + name + "!</h2>");
        out.println("<p>Your grade is: <strong>" + grade + "</strong></p>");
        out.println("<p><a href='index.html'>Go back</a></p>");
        out.println("</body></html>");
    }
}
