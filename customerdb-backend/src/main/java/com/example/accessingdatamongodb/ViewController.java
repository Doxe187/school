package com.example.accessingdatamongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.util.List;
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
}
