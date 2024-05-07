package ac.kr.dankook.ace.dom_t1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping("main/search")
    public String Dom_main_search(@ModelAttribute("searchform") SearchForm searchForm)
    {
        System.out.println(searchForm.getInput());
        return "dom_main";
    }


    
}
