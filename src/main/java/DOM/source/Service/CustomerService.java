package DOM.source.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DOM.source.Entity.Customer;
import DOM.source.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
public class CustomerService {
    /*jpa repository를 extend했기 때문에 자동적으로 빈에 저장됨 그리고 리포지토리의쿼리를 사용하는 메서드를 만듬 */
    @Autowired
    private CustomerRepository crepository;
    public void save(Customer customer){
        crepository.save(customer);
    }
    public Customer find(String email,String psw){
        return crepository.findByEmailAndPsw(email,psw);
    }
}