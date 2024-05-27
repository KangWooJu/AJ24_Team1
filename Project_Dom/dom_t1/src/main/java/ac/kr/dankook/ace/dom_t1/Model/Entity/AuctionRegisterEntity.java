package ac.kr.dankook.ace.dom_t1.Model.Entity;
import java.time.LocalDateTime;

import java.util.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class AuctionRegisterEntity {

    @Id // id 속성을 기본키로 지정 즉, 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username; // 고유번호 - 등록자의 아이디

    @Column(length = 200) 
    private String title; // 제목 : 글자수 제한 200 

    @Column(columnDefinition = "TEXT") // 텍스트를 열데이터로 넣을 수 있음을 의미하며 글자 수 제한이 없음.
    private String content; // 내용

    private Integer price; // 시작가격 

    private String category; // 카테고리 ( 각 )

    private String locationCode; // 거래 지역 코드 ( 5.24 수정 )

    private String user_month; // 경매 시작 월 ( 5.24 수정 )

    private String user_day; // 경매 시작 일 ( 5.24 수정 )

    private LocalDateTime createDate; // 작성일자
    private LocalDateTime modifyDate; // 수정날짜

    @OneToMany(mappedBy = "AuctionRegisterEntity", cascade = CascadeType.REMOVE) // CascadeType.REMOVE: 게시물이 삭제되면 Request글들 전부가 한번에 삭제 되도록 한다.
    private List<AuctionRequestEntity> auctionRequestEntityList; // 구매희망(AuctionRequest)를 하나의 리스트에 담고 이를 Entity의 속성에 추가. 이때 , 구매희망에 참조하려면 AuctionRegisterEntity.getAuctionRequestEntityList();

    @ManyToOne
    private SiteuserEntity author; // 사용자 한명이 여러개의 글을 작성

    @ManyToMany // 다대 다 관계로 설정
    Set<SiteuserEntity> good; // 좋아요 기능 

   
}
