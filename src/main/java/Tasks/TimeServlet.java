package Tasks;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String timezone = request.getParameter("timezone");
        ZoneId zoneId = timezone != null && !timezone.isEmpty() ? ZoneId.of(timezone) : ZoneOffset.UTC;

        LocalDateTime currentTime;
        if (timezone != null && !timezone.isEmpty()) {
            currentTime = LocalDateTime.now(zoneId);
        } else {
            currentTime = LocalDateTime.now(ZoneOffset.UTC);
        }

        String formattedTime = currentTime.toString();

        out.println("<html><body>");
        out.println("<h1>Current Time (" + zoneId.toString() + "): " + formattedTime + "</h1>");
        out.println("</body></html>");
    }
}