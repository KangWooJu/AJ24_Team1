package DOM.source.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DOM.source.Entity.Evaluate;
import DOM.source.Service.EvaluateService;





@Controller
@RequestMapping("/eva")/*/eva/~~~ 형식 */
public class EvaluateController {
    @Autowired
    private EvaluateService eservice;
    @PostMapping("/add")/*/eva/add 로 사용 */
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    @GetMapping("/find")
    public String findeva(int id,Model model){
        List<Evaluate> el = eservice.find(id);
        model.addAttribute("evalist", el);
        return null;
    }
}
