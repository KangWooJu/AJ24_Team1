package ac.kr.dankook.ace.dom_t1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Dom_Controller {

    @GetMapping("main")
    public String Dom_main(Model model)
    {
        return "dom_main";
    }

    @PostMapping("register") 
    public String Dom_register(Model model)
    {
        return "dom_register";
    }


    
}
