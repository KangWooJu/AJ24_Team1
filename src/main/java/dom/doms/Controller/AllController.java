package dom.doms.Controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import dom.doms.DTO.CustomerDTO;
import dom.doms.Entity.Customer;
import dom.doms.Service.CustomerService;



@Controller
@RequestMapping("/Customer")/*/Customer/~~~ 로 불러옴 */
public class AllController {
    /*@service는 @Component를 가지고있으므로 자동적으로 빈에 등록됨 따라서 auto wired로 생성 가능 */
    @Autowired
    private CustomerService cservice ;
    @GetMapping("/cfind")
    public String cfind(Model model) {
        return "customerfind";
    }
    
    @PostMapping("/add")/* /Customer/add */
    public String addcus(@RequestParam("email")String email,@RequestParam("psw") String psw, @RequestParam("nickname")String nickname,
                            @RequestParam("hint") String hint,@RequestParam("name") String name) {
        CustomerDTO ed = new CustomerDTO();
        ed.setEmail(email);
        ed.setPsw(psw);
        ed.setNickname(nickname);
        ed.setName(name);
        ed.setHint(hint);
        Customer nc = new Customer(ed);
        cservice.save(nc);
        return null;
    }
    @PostMapping("/find")
    public String findCustomer(@RequestParam("email") String email,@RequestParam("Psw") String psw){
        Optional<Customer> cs  = cservice.find(email, psw);
        System.out.println(cs.toString());
        return "redirect:/Customer/cfind";
    }
    
}
