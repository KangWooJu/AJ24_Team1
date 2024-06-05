package ac.kr.dankook.ace.dom_t1.Model.Service;

import java.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ac.kr.dankook.ace.dom_t1.Model.Entity.AuctionRegisterEntity;
import ac.kr.dankook.ace.dom_t1.Model.Entity.AuctionRequestEntity;
import ac.kr.dankook.ace.dom_t1.Model.Entity.SiteuserEntity;
import ac.kr.dankook.ace.dom_t1.Model.repository.AuctionRequestRepository;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AuctionRequestService {
    private final AuctionRequestRepository auctionRequestRepository;

    // 6.5 수정 -> username 맴버 변수 추가 : 파라미터 추가 필요 

    public AuctionRequestEntity createRequest(AuctionRegisterEntity auctionRegisterEntity,String username ,String content,SiteuserEntity author) // 댓글을 생성하는 모듈
    {
        AuctionRequestEntity auctionRequestEntity = new AuctionRequestEntity();
        // AuctionRequestEntity , AuctionRegisterEntity 참조필수
        auctionRequestEntity.setUsername(username); // ( 6.5 수정사항 -> username 맴버 변수 추가 )
        auctionRequestEntity.setContent(content); // 댓글내용 
        auctionRequestEntity.setCreateDate(LocalDateTime.now()); // 댓글 단 시간
        auctionRequestEntity.setAuctionregisterentity(auctionRegisterEntity); // 댓글을 단 등록글 
        auctionRequestEntity.setAuthor(author); // 글 등록자의 데이터 
        this.auctionRequestRepository.save(auctionRequestEntity); // 앞에서 받아온 정보들 저장하기
        return auctionRequestEntity;
    }

    public AuctionRequestEntity getAuctionRequestEntity(Integer id){ // 댓글 조회하는 모듈 
        Optional<AuctionRequestEntity> auctionRequestEntity = this.auctionRequestRepository.findById(id); // ( 6. 5 수정 ) -> id으로 찾아오기
        if(auctionRequestEntity.isPresent()){
            return auctionRequestEntity.get(); // username에 해당하는 댓글이 있을경우 가져오기 
        }
        else{
            throw new DataNotFoundException("답변을 찾을 수 없습니다."); // 없다면 "답변을 찾을 수 없습니다." 메시지 전송
        }
    }

    public void modify(AuctionRequestEntity auctionRequestEntity, String content){ // 댓글을 수정하는 모듈 
        auctionRequestEntity.setContent(content); // 댓글내용 데이터 setter
        auctionRequestEntity.setModifyDate(LocalDateTime.now()); // 댓글 작성시간 데이터 setter
        this.auctionRequestRepository.save(auctionRequestEntity); // 댓글 관련데이터를 리포지토리에 세이브 
    }

    public void delete(AuctionRequestEntity auctionRequestEntity){ // 댓글을 삭제하는 모듈 
        this.auctionRequestRepository.delete(auctionRequestEntity);
    }

    public void good(AuctionRequestEntity auctionRequestEntity, SiteuserEntity siteuserEntity){ // 좋아요 기능 
        auctionRequestEntity.getGood().add(siteuserEntity); // 엔티티의 good에 가져온 후 siteuserEntity에 추가 
        this.auctionRequestRepository.save(auctionRequestEntity); // 데이터 저장 
    }

    public AuctionRequestEntity createRequest(AuctionRegisterEntity auctionRegisterEntity, String content,
            SiteuserEntity siteuserEntity) {
        throw new UnsupportedOperationException("Unimplemented method 'createRequest'");
    }
}
