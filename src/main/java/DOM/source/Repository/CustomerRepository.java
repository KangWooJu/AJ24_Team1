package DOM.source.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import DOM.source.Entity.Customer;

import java.util.List;



/*email과 비밀번호로 찾음 */
public interface CustomerRepository extends JpaRepository<Customer,Integer>{
    Customer findByEmailAndPsw(String email,String psw);
    
} 
