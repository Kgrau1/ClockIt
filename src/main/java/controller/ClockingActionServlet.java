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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
            attendance.setDate(LocalDate.now());
            attendance.setClockedStatus(true);
            attendanceDao.saveOrUpdate(attendance);
            request.setAttribute("successMessage", "Successfully clocked in");
        } else if ("Clock Out".equals(action)) {
            List<Attendance> userAttendance = attendanceDao.getByPropertyEqual("user", user);
            // Find the last attendance record for the user (assuming it's the latest clock-in)
            Attendance latestAttendance = userAttendance.get(userAttendance.size() - 1);
            // Update the clock-out time for the latest attendance record
            latestAttendance.setClockOutTime(LocalDateTime.now());
            latestAttendance.setClockedStatus(false);
            attendanceDao.saveOrUpdate(latestAttendance);
        }

        response.sendRedirect("index.jsp");
    }
}
