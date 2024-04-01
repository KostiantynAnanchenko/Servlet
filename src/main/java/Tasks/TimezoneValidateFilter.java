package Tasks;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.TimeZone;

@WebFilter("/time")
public class TimezoneValidateFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String timezone = httpRequest.getParameter("timezone");
        if (timezone != null && !timezone.isEmpty()) {
            if (!isValidTimezone(timezone)) {
                httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                httpResponse.setContentType("text/html");
                httpResponse.getWriter().println("<html><body><h1>Invalid timezone</h1></body></html>");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isValidTimezone(String timezone) {
        String[] availableTimezones = TimeZone.getAvailableIDs();
        for (String tz : availableTimezones) {
            if (tz.equals(timezone)) {
                return true;
            }
        }
        return false;
    }

    public void init(FilterConfig fConfig) throws ServletException {}

    public void destroy() {}
}