package com.example.demo.servlet;

import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.*;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        if (resp == null) {
            throw new IOException("Response is null");
        }

        resp.setContentType("text/plain");
        PrintWriter writer = resp.getWriter();
        writer.write("Hello from servlet");
        writer.flush();
    }
}
