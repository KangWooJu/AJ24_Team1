package dom.doms.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dom.doms.DTO.EvaluateDTO;
import dom.doms.Entity.Evaluate;
import dom.doms.Service.EvaluateService;







@Controller
@RequestMapping("/eva")/*/eva/~~~ 형식 */
public class EvaluateController {
    @Autowired
    private EvaluateService eservice;
    @PostMapping("/add")/*/eva/add 로 사용 */
    public String postMethodName(@RequestParam("itemid")int itemid,@RequestParam("evaluate price") int evaluate,@RequestParam("reason") String rreason) {
        EvaluateDTO edt = new EvaluateDTO();
        edt.setItemid(itemid);
        edt.setEvaluate(evaluate);
        edt.setReason(rreason);
        Evaluate ev = new Evaluate(edt);
        
        return null;
    }
    @GetMapping("/find")
    public String findeva(int id,Model model){
        List<Evaluate> el = eservice.find(id);
        model.addAttribute("evalist", el);
        return null;
    }
}
