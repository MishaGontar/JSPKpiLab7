package com.example.demo1.Controllers;

import com.example.demo1.Models.Trip;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "TripSearchController", value = "/TripSearchController")
public class TripSearchController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int a = Integer.parseInt(request.getParameter("from"));
        int b = Integer.parseInt(request.getParameter("to"));
        int c = Integer.parseInt(request.getParameter("train"));
        Trip.addTrip(a,b,c);
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }
}
