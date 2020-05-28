package ru.job4j.dream.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Photo;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        int candidateId = Integer.valueOf(req.getParameter("id"));
        try {
            List<FileItem> items = upload.parseRequest(req);
            File folder = new File("images");
            if (!folder.exists()) {
                folder.mkdir();
            }
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    int id = PsqlStore.instOf().createPhoto(new Photo(item.getName()));
                    Candidate candidate = PsqlStore.instOf().findCandidateById(candidateId);
                    candidate.setPhotoId(id);
                    PsqlStore.instOf().updateCandidate(candidate);
                    File file = new File(folder + File.separator + id);
                    try (
                            BufferedReader reader = new BufferedReader(new InputStreamReader(item.getInputStream()));
                            PrintWriter out = new PrintWriter(new FileWriter(file));
                    ) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            out.print(line);
                        }
                    }

                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/candidate/candidates.do");
    }
}
