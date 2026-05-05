package com.example.accessingdatamongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

@Controller
public class ViewController {
    private static Logger logger = Logger.getLogger("ViewController");
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        logger.info("hey----> " + customerRepository.findAll());
        return "Home";
    }

    @GetMapping("/projections")
    public String projections(Model model) {
        model.addAttribute("customers", customerRepository.findNameAndExcludeId());
        return "projections";
    }

    @GetMapping("/aggregations")
    public String aggregations(Model model) {
        model.addAttribute("groups", customerRepository.groupByLastnameAndFirstnames());
        return "aggregations";
    }
}
