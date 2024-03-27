package controller;

import entity.Attendance;
import entity.User;
import persistence.GenericDao;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet(
        name = "ClockInStatusServlet",
        urlPatterns = { "/ClockInStatusServlet" }
)
public class ClockInStatusServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("userId"));

        GenericDao<User> userDao = new GenericDao<>(User.class);
        GenericDao<Attendance> attendanceDao = new GenericDao<>(Attendance.class);

        User user = userDao.getById(userId);
        if (user == null) {
            request.setAttribute("userNotFound", true);
            RequestDispatcher errorDispatcher = request.getRequestDispatcher("index.jsp");
            errorDispatcher.forward(request, response);
            return;
        }

        List<Attendance> userAttendances = attendanceDao.getById(userId);

        boolean isClockedIn = false;
        for (int i = userAttendances.length() - 1; i >= 0; i--) {
            if (attendance.isClockedStatus()) {
                isClockedIn = true;
                break;
            }
        }

        request.setAttribute("isClockedIn", isClockedIn);
        request.setAttribute("userId", userId);

        RequestDispatcher dispatcher = request.getRequestDispatcher("clockingOptions.jsp");
        dispatcher.forward(request, response);

    }
}