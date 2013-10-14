package az.neuron.ask.web;

import az.neuron.ask.Constants;
import az.neuron.ask.domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class FilterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = Constants.ACTION_LOGIN;
        String address = Constants.PAGE_INDEX;
        boolean forward = true;

        if (request.getParameter(Constants.ACTION) != null) {
            action = request.getParameter("action");
        }

        try {
            HttpSession session =  request.getSession(true);
            User g_user = (User) session.getAttribute(Constants.USER);
            if(g_user!=null){
                if (action.equalsIgnoreCase(Constants.ACTION_LOGOUT)){
                    address="ls?action=logout";
                }
            } else {
                address = Constants.PAGE_LOGIN;
                forward = false;
            }

        } catch (Exception ex) {
            forward = true;
            address = Constants.PAGE_ERROR;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        if (forward) {
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(address);
        }
    }
}
