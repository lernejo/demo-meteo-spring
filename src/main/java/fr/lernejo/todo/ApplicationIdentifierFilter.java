package fr.lernejo.todo;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class ApplicationIdentifierFilter implements Filter {
    private String uuidString;

    public ApplicationIdentifierFilter() {
        UUID uuid = UUID.randomUUID();
        uuidString = uuid.toString();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //((HttpServletResponse) response).setHeader("Instance-id", uuidString);
        if (response instanceof HttpServletResponse httpResponse) {
            httpResponse.setHeader("Instance-id", uuidString);
        }
        chain.doFilter(request, response);
    }
}
