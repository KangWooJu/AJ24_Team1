package ac.kr.dankook.ace.dom_t1.Model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ac.kr.dankook.ace.dom_t1.Model.Entity.AuctionRequestEntity;

@Repository
public interface AuctionRequestRepository extends JpaRepository<AuctionRequestEntity, Integer> {

    
}
