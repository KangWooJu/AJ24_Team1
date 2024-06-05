package ac.kr.dankook.ace.dom_t1.controller;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;




@Data
public class AuctionRegisterForm {
    @NotEmpty(message="제목은 필수항목입니다.")
    @Size(max=200)
    private String title;

    @NotEmpty(message="내용은 필수항목입니다.")
    private String content; // 내용 

    private String username; // ( 6. 5 수정 사항 -> username 추가  )

    private String locationCode; // ( 5.24 수정)

    private String user_month; // ( 5.24 수정)

    private String user_day; // ( 5.24 수정 )

    private String category; // ( 5.24 수정 )

    private Integer category_num; // (6.3 수정 )

    private Integer price;



    

    // 사진등록 관련해서 DTO 추가 사항을 작성해주세요
    // 드래그로 카테고리 넣을 수 있도록 프론트 수정 및 DTO 부분 협의 부탁드립니다.
    // 민혁님이 추가해주시면 

    
}
