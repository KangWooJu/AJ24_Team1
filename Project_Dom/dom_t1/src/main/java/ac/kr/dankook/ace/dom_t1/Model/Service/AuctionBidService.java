package ac.kr.dankook.ace.dom_t1.Model.Service;

import java.time.LocalDateTime;


import org.springframework.stereotype.Service;

import ac.kr.dankook.ace.dom_t1.Model.Entity.AuctionBidEntity;
import ac.kr.dankook.ace.dom_t1.Model.Entity.AuctionRegisterEntity;
import ac.kr.dankook.ace.dom_t1.Model.Entity.SiteuserEntity;
import ac.kr.dankook.ace.dom_t1.Model.repository.AuctionBidRepository;
import ac.kr.dankook.ace.dom_t1.Model.repository.AuctionRegisterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


// 서비스 5.28 생성 
@Service
@RequiredArgsConstructor
public class AuctionBidService {

    
    private final AuctionBidRepository auctionBidRepository;
    private final AuctionRegisterRepository auctionRegisterRepository;

    // ( 6 .5 수정 -> username에서 id로 파라미터 변경 )
    @Transactional
    public void createAuctionBid(Integer id ,SiteuserEntity bidder,Double bidAmount) {
        
        
        AuctionRegisterEntity auctionRegisterEntity = auctionRegisterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("경매 등록자를 찾을 수 없습니다."));

        AuctionBidEntity auctionBidEntity = new AuctionBidEntity();
        LocalDateTime bidDate = LocalDateTime.now(); // 입찰 시간 받기 
        auctionBidEntity.setId(auctionRegisterEntity.getId()); // 경매 등록자의 username 설정  ( 6 .5 수정 -> username에서 id로 파라미터 변경 )
        auctionBidEntity.setAuctionRegisterEntity(auctionRegisterEntity);
        auctionBidEntity.setBidder(bidder);
        auctionBidEntity.setBidAmount(bidAmount);
        auctionBidEntity.setBidDate(bidDate);
        auctionBidRepository.save(auctionBidEntity);
    } // BidEntity에 관한 정보를 저장 


    public Double getHighestBidByUsername(Integer id){

        return auctionBidRepository.findTopByAuctionRegisterEntityOrderByBidAmountDesc(id).getBidAmount();
        // repository의 메소드를 통해서 얻은 최고입찰금액을 get을 통해 얻어오는 메소드 
        // ( 6 .5 수정 -> username에서 id로 파라미터 변경 )
    }

    public AuctionBidEntity findHighestBidder(AuctionBidEntity auctionBidEntity) {
        return auctionBidRepository.findTopByAuctionRegisterEntityOrderByBidAmountDesc(auctionBidEntity.getId());
    } // 최고 입찰자의 이름을 entity에 저장하기 위한 메소드 
    // ( 6 .5 수정 -> username에서 id로 파라미터 변경 )
}
