package controller;

import entity.Attendance;
import entity.User;
import persistence.GenericDao;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet(
        name = "ClockingActionServlet",
        urlPatterns = { "/ClockingActionServlet" }
)
public class ClockingActionServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("userId"));
        String action = request.getParameter("action");
        GenericDao<User> userDao = new GenericDao<>(User.class);
        User user = userDao.getById(userId);
        GenericDao<Attendance> attendanceDao = new GenericDao<>(Attendance.class);
        Attendance hours = new Attendance();

        if ("Clock In".equals(action)) {
            hours.setClockedStatus(true);
            hours.setUser(user);
            //userDao.clockIn(hours);
            user.getAttendance().add(hours);
            //////////////////////////////////
        } else if ("Clock out".equals(action)) {
            //user.setClockedIn(false);
            //userDao.clockOut(hours);
            //user.getLoggedHours().add(hours);
            userDao.saveOrUpdate(user);
        } else if ("Change job".equals(action)) {
            //hours.setJobId(jobId);
            //user.setClockedIn(false);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

}