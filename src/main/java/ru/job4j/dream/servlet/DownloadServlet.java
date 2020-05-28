package ru.job4j.dream.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        resp.setContentType("id=" + id);
        resp.setContentType("image/png");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + id + "\"");
        File file = new File("images" + File.separator + id);
        try (
                BufferedReader reader = new BufferedReader(new FileReader(file));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(resp.getOutputStream()));
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                out.print(line);
            }
        }
    }
}
