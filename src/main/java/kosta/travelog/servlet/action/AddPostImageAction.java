package kosta.travelog.servlet.action;

import kosta.travelog.exception.BadRequestException;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.service.ImageService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;

@Slf4j
public class AddPostImageAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {

        int postId = Integer.parseInt(request.getParameter("postId"));

        Collection<Part> parts = request.getParts();
        ImageService service;
        try {
            service = new ImageService();
        } catch (DatabaseConnectException e) {
            throw new RuntimeException(e);
        }


        for (Part imagePart : parts) {
            if (imagePart == null || imagePart.getSubmittedFileName() == null || imagePart.getSize() == 0) {
                continue;
            }
            try {
                service.addImage(imagePart, postId);
            } catch (BadRequestException e) {
                log.error(e.getMessage());
                request.setAttribute("data", new ResponseModel(400, "bad request"));
                return new URLModel();
            } catch (DatabaseQueryException e) {
                throw new RuntimeException(e);
            } catch (DatabaseConnectException e) {
                throw new RuntimeException(e);
            }
        }

        request.setAttribute("data", new ResponseModel(200, "success"));
        return new URLModel();
    }
}

/*

        String saveDirectory = request.getServletContext().getRealPath("upload");

        File dir = new File(saveDirectory);
        if (!dir.exists())
            dir.mkdirs();

        log.info(saveDirectory);
        int maxPostSize = 15 * 1024 * 1024;
        String encoding = "UTF-8";

        MultipartRequest mr = new MultipartRequest(request, saveDirectory, maxPostSize, encoding);

        String fileName = mr.getParameter("image");
        log.info(fileName);

        String ext = fileName.substring(fileName.lastIndexOf("."));
        String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
        String newFileName = now + ext;

        ResponseModel responseModel = null;
        try {
            boolean result = new PostService().createImage(PostImageVO.builder().
                    postId(Integer.parseInt(request.getParameter("postId")))
                    .images(fileName).build());

            JsonObject json = new JsonObject();
            json.addProperty("data", result);
            responseModel = new ResponseModel(201, json, "created");

        } catch (DatabaseConnectException e) {
            log.error(e.getMessage());
            responseModel = new ResponseModel(500, "Server Error");
          
        } finally {
            request.setAttribute("data", responseModel);
        }
*/
