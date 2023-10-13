package com.wildcodeschool.wildandwizard.controller;

import com.wildcodeschool.wildandwizard.entity.School;
import com.wildcodeschool.wildandwizard.entity.Wizard;
import com.wildcodeschool.wildandwizard.repository.SchoolRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class SchoolController {

    // TODO : get school repository by dependency injection
    private final SchoolRepository schoolRepository;
    public SchoolController(SchoolRepository schoolRepositoryInjected){
        this.schoolRepository = schoolRepositoryInjected;
    }
    @GetMapping("/schools")
    public String getAll(Model model) {

        // TODO : find all schools
        model.addAttribute("schools", schoolRepository.findAll());


        return "schools";
    }

    @GetMapping("/school")
    public String getSchool(Model model,
                            @RequestParam(required = false) Long id) {

        // TODO : find one school by id
        School school = new School();
        if (id != null) {
            Optional<School> optionalSchool = schoolRepository.findById(id);
            if (optionalSchool.isPresent()) {
                school = optionalSchool.get();
            }
        }
        model.addAttribute("school", school);

        return "school";
    }

    @PostMapping("/school")
    public String postSchool(@ModelAttribute School school) {

        // TODO : create or update a school
        schoolRepository.save(school);

        return "redirect:/schools";
    }

    @GetMapping("/school/delete")
    public String deleteSchool(@RequestParam Long id) {

        // TODO : delete a school
        schoolRepository.deleteById(id);

        return "redirect:/schools";
    }
}
