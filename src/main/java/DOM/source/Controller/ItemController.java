package DOM.source.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import DOM.source.DTO.ItemDTO;
import DOM.source.Entity.Item;
import DOM.source.Service.ItemService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/item")/*/item/~~~로 사용 */
public class ItemController {
    @Autowired
    private ItemService iservice;
    @GetMapping("/Item")/*/item/Item */
    public String addsome(Model model) {
        return "item";
    }
    @GetMapping("/find")
    public String findshow(Model model) {
        return "find";
    }    
    @PostMapping("/item/add")/*RequestParam은 DTO를 만들기 위해 사용 */
    public String additem(@RequestParam ("itemname") String itemname,
                            @RequestParam("price") int price,
                            @RequestParam("way") int way,
                            @RequestParam("seller") String seller,
                            @RequestParam("detail") String detail){
        ItemDTO aitem = new ItemDTO();
        aitem.setItemname(itemname);
        aitem.setPrice(price);
        aitem.setWay(way);
        aitem.setSeller(seller);
        aitem.setDetail(detail);
        /*빈 DTO 객체에 setter를 이용하여 정보를 넣고 DTO객체로 Entity생성 */
        Item item = new Item(aitem);
        iservice.save(item);
        return "redirect:/item/Item";
    }
    
    @PostMapping("/item/find")
    public String finditem(@RequestParam("Itemname") String itemname,Model model){
        List<Item> as =iservice.find(itemname);
        System.out.println(as.get(0).getItemname());
        return "finditem";
    }

    
    
}
