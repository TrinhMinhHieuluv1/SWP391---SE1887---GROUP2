package filter;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author SCN
 */
public class authentication implements Filter {

    private static final boolean debug = true;
    private FilterConfig filterConfig = null;

    public authentication() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("authentication:DoBeforeProcessing");
        }
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("authentication:DoAfterProcessing");
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Lấy đường dẫn yêu cầu
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        // Log đường dẫn để kiểm tra
        System.out.println("Path: " + path);

        // Kiểm tra quyền truy cập nếu đường dẫn thuộc /admin/*
        if (path.startsWith("/admin")) {
            HttpSession session = httpRequest.getSession(false);

            // Log session để kiểm tra
            System.out.println("Session: " + session);

            // Kiểm tra nếu session tồn tại và có thông tin account
            if (session != null && session.getAttribute("account") != null) {
                Object account = session.getAttribute("account");

                // Log account để kiểm tra
                System.out.println("Account: " + account);

                // Kiểm tra account có thuộc kiểu User không
                if (account instanceof User) {
                    User user = (User) account;

                    // Log roleID để kiểm tra
                    System.out.println("RoleID: " + user.getRoleID());

                    // Nếu là admin, tiếp tục xử lý
                    if (user.getRoleID() == 1) {
                        chain.doFilter(request, response);
                        return;
                    }
                } else {
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/home?RoleErr=true");
                    return;
                }
            }

            // Nếu không có session và ko phải admin logout ra thì -> roleErr
            if (!path.equals("/admin/logout")) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/home?RoleErr=true");
            } else { // khi admin logout ra thì về home
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/home");
            }
            return;
        }

        // Nếu không phải đường dẫn /admin/*, tiếp tục xử lý bình thường
        chain.doFilter(request, response);
    }

    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("authentication:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("authentication()");
        }
        StringBuffer sb = new StringBuffer("authentication(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
