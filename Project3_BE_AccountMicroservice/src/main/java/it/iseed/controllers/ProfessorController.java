package it.iseed.controllers;


import it.iseed.entities.MaterialEntity;
import it.iseed.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class ProfessorController  {

    @Autowired
    private ProfessorService professorService;

    @RequestMapping(value="/uploadController", method = RequestMethod.POST)
    public ModelAndView upLoadMaterial(HttpServletRequest request) {

        String examName = request.getParameter("nameE");
        String idExam = request.getParameter("idE");
        String exam = request.getParameter("exam");

        ModelAndView model = new ModelAndView();

        model.addObject(request.getSession().getAttribute("professor"));
        model.addObject("nameE", examName);
        model.addObject("idE",idExam);
        model.setViewName("upLoadMaterial");

        return model;
    }

    @RequestMapping(value="/insertMaterialController", method = RequestMethod.POST)
    public ModelAndView insertMaterial(HttpServletRequest request, @RequestParam("nameE") String examName,
                                       @RequestParam("videos") String videoLink,
                                       @RequestParam("idE") String id_exam,
                                       @RequestParam("notes") File file) throws IOException, ServletException {
        ModelAndView model = new ModelAndView();


        //byte[] bytes = readBytesFromFile();

        byte[] bytes = new byte[1000];
        MaterialEntity m = new MaterialEntity(id_exam, bytes, videoLink, examName);

        boolean t = professorService.insertMeaterial(m);

        if(t){
        model.addObject(request.getSession().getAttribute("professor"));

        model.setViewName("professor/welcomeDocente");
        }
        else{
            model.setViewName("error");
        }
        return model;
    }

    private static byte[] readBytesFromFile(String filePath) throws IOException {
        File inputFile = new File(filePath);
        FileInputStream inputStream = new FileInputStream(inputFile);

        byte[] fileBytes = new byte[(int) inputFile.length()];
        inputStream.read(fileBytes);
        inputStream.close();

        return fileBytes;
    }

    @RequestMapping(value="/createTest", method = RequestMethod.POST)
    public ModelAndView createTest(HttpServletRequest request){
        ModelAndView model = new ModelAndView();

        model.setViewName("success");
        return model;
    }

}
