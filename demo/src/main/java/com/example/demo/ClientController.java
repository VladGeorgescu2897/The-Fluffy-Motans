package com.example.demo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        String var = "Echipa fantastica";
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping(value = "/admissionForm.html", method = RequestMethod.GET)
    public ModelAndView getAdmissionForm() {
        ModelAndView model = new ModelAndView("AdmissionForm");
        return model;
    }

    @RequestMapping(value = "/submitAdmissionForm.html", method = RequestMethod.POST)
    public ModelAndView submitAdmissionForm(@RequestParam("studentName") String name, @RequestParam("studentHobby") String hobby) {

        ModelAndView model = new ModelAndView("AdmissionSuccess");
        model.addObject("msg","Det");
        System.out.println(name + hobby);
        return model;
    }

    @RequestMapping(value = "/submitAdmissionForm2.html", method = RequestMethod.POST)
    public ModelAndView submitAdmissionForm2(@RequestParam("studentName2") String name, @RequestParam("studentHobby2") String hobby) {

        ModelAndView model = new ModelAndView("AdmissionSuccess2");
        model.addObject("msg","Det");
        System.out.println(name + hobby);
        return model;
    }
}