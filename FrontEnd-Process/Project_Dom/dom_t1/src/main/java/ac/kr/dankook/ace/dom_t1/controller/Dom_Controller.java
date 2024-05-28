package ac.kr.dankook.ace.dom_t1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Dom_Controller {

    @GetMapping("main")
    public String Dom_main(Model model)
    {
        return "dom_main";
    }

    @GetMapping("sign_in")
    public String Dom_login(Model model)
    {
        model.addAttribute("loginInputForm", new LoginInputForm("", ""));
        return "sign-in";
    }

    @GetMapping("sign_up")
    public String Dom_registration(Model model)
    {
        return "sign-up";
    }

    @GetMapping("post_product")
    public String Dom_post_product(Model model, ProductPostForm productPostForm)
    {
        model.addAttribute("productPostForm", productPostForm);
        return "post-product";
    }

    @PostMapping("post_product")
    public String Dom_postProduct(@ModelAttribute("productPostForm") ProductPostForm productPostForm, Model model)
    {
        System.out.println(productPostForm);
        // Form -> Entity
        return "post-product";
    }

    @GetMapping("password_check")
    public String Dom_password_check(Model model)
    {
        return "password-check";
    }

    @PostMapping("register") 
    public String Dom_register(Model model)
    {
        return "dom_register";
    }

    @GetMapping("request_form")
    public String Dom_request_form(Model model)
    {
        return "Request_form";
    }

    @GetMapping("product_list")
    public String Dom_product_list(Model model)
    {
        return "product-list";
    }

    @GetMapping("product_detail")
    public String Dom_product_detail(Model model)
    {
        return "product-detail";
    }

    @PostMapping("main/search")
    public String Dom_main_search(@ModelAttribute("searchForm") SearchForm searchForm)
    {
        System.out.println(searchForm.getInput());
        return "dom_main";
    }

    @PostMapping("login/submit")
    public String DOM_Login_Submit(@ModelAttribute LoginInputForm inputForm, Model model){
        model.addAttribute("loginInputForm", new LoginInputForm("", ""));
        System.out.println(inputForm.toString());
        return "login_form";
    }
    
}
