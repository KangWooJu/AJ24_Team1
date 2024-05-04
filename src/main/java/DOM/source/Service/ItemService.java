package DOM.source.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DOM.source.Entity.Item;
import DOM.source.Repository.ItemRepository;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
@Service
public class ItemService {
    /*jpa repository를 extend했기 때문에 자동적으로 빈에 저장됨 그리고 리포지토리의쿼리를 사용하는 메서드를 만듬 */
    @Autowired
    private ItemRepository irepository;
    public void save(Item item){
        irepository.save(item);
    }
    public List<Item> find(String name){
        return irepository.findByItemnameContains(name);
    }

}
