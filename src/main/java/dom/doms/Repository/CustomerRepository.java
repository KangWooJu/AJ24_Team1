package dom.doms.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dom.doms.Entity.Customer;






/*email과 비밀번호로 찾음 */
public interface CustomerRepository extends JpaRepository<Customer,Integer>{
    Optional<Customer> findByEmailAndPsw(String email,String psw);
    
} 
