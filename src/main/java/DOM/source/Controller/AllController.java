package DOM.source.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestParam;

import DOM.source.Entity.Customer;
import DOM.source.Service.CustomerService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping("/Customer")/*/Customer/~~~ 로 불러옴 */
public class AllController {
    /*@service는 @Component를 가지고있으므로 자동적으로 빈에 등록됨 따라서 auto wired로 생성 가능 */
    @Autowired
    private CustomerService cservice ;
    @PostMapping("/add")/* /Customer/add */
    public String addcus(@RequestParam("")String name) {
        //TODO: process POST request
        
        return null;
    }
    @GetMapping("/find")
    public String findCustomer(@RequestParam("email") String email,@RequestParam("psw") String psw){
        Customer cs  = cservice.find(email, psw);
        
        return null;
    }
    
}
