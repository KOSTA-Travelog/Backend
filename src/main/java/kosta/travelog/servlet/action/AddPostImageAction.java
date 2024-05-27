package kosta.travelog.servlet.action;

import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;
import kosta.travelog.dto.PostImageDTO;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.service.PostService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class AddPostImageAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {

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
            boolean result = new PostService().createImage(PostImageDTO.builder().
                    postId(Integer.parseInt(request.getParameter("postId")))
                    .images(fileName).build());

            JsonObject json = new JsonObject();
            json.addProperty("data", result);
            responseModel = new ResponseModel(201, json, "created");

        } catch (DatabaseConnectException e) {
            log.error(e.getMessage());
            responseModel = new ResponseModel(500, "Server Error");
        } catch (DatabaseQueryException e) {
            log.error(e.getMessage());
            responseModel = new ResponseModel(500, "데이터를 불러오지 못했습니다.");
        } finally {
            request.setAttribute("data", responseModel);
        }

        return new URLModel();
    }
}
