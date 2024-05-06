package dom.doms.Service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dom.doms.Entity.Evaluate;
import dom.doms.Repository.EvaRepository;
@Service
public class EvaluateService {
    /*jpa repository를 extend했기 때문에 자동적으로 빈에 저장됨 그리고 리포지토리의쿼리를 사용하는 메서드를 만듬 */
    @Autowired
    private EvaRepository erepository;
    public List<Evaluate> find(int id){
        return erepository.findByItemid(id);
    }
    public void save(Evaluate ev){
        erepository.save(ev);
    }
}
