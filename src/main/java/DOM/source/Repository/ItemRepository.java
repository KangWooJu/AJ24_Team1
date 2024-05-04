package DOM.source.Repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import DOM.source.Entity.Item;


/*item을 찾는 방식 Contains는 특정문자열을 가지고있는 아이템을 전부 반환*/
public interface ItemRepository extends JpaRepository<Item,Integer>{
    List<Item> findBySeller(String seller);
    List<Item> findByItemnameContains(String partsofname);
}