package controller;
import entity.Attendance;
import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import persistence.GenericDao;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.time.LocalDateTime;
@WebServlet(
        name = "ClockingActionServlet",
        urlPatterns = { "/ClockingActionServlet" }
)
public class ClockingActionServlet extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String action = request.getParameter("action");
        GenericDao<Attendance> attendanceDao = new GenericDao<>(Attendance.class);
        GenericDao<User> userDao = new GenericDao<>(User.class);
        Attendance attendance = new Attendance();
        User user = (User) request.getSession().getAttribute("currentUser");

        if ("Clock In".equals(action)) {
            attendance.setUser(user);
            attendance.setClockInTime(LocalDateTime.now());
            attendance.setClockedStatus(true);
            attendanceDao.saveOrUpdate(attendance);
            request.setAttribute("successMessage", "Successfully clocked in");
        } else if ("Clock Out".equals(action)) {
            attendance.setUser(user);
            attendance.setClockOutTime(LocalDateTime.now());
            attendance.setClockedStatus(false);
            attendanceDao.saveOrUpdate(attendance);
            request.setAttribute("successMessage", "Successfully clocked out");
        }

        response.sendRedirect("index.jsp");
    }
}
