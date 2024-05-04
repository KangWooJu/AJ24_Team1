package DOM.source.Repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import DOM.source.Entity.Evaluate;

/*아이템id로 관련된 평가를 찾음 */
public interface EvaRepository extends JpaRepository<Evaluate,Integer>{
    List<Evaluate> findByItemid(int itemid);
}
