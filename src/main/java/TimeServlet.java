import org.apache.taglibs.standard.extra.spath.ParseException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

@WebServlet(urlPatterns = "/time")
    public class TimeServlet extends HttpServlet {
        @Override
        public void doGet(HttpServletRequest request, HttpServletResponse response)
                throws IOException {
            String currentUtcTime = "";
            String timezone = request.getParameter("timezone");
            if (timezone == null) {
                timezone = "UTC";
            }
                try {
                    currentUtcTime = getCurrentUtcTime(timezone);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Current Time</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + currentUtcTime + " " + timezone + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        public static String getCurrentUtcTime(String zone) throws ParseException {
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone(zone));
            return df.format(date);
        }

    }
