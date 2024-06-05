package ac.kr.dankook.ace.dom_t1.Model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class SiteuserEntity {
    @Id // 기본키로 지정 
    @Column(unique = true)
    private Integer id; // ( 6. 5 수정 -> username에서 id로 변경 )

    @Column(unique = true) // 값의 중복을 피하기 위해서 지정
    private String username; 

    @Column
    private String psw;

    @Column
    private String hint; // 비밀번호 찾기 기능에 필요한 힌트 부분 

    @Column(unique = true) // 값의 중복을 피하기 위해서 지정 -> 계정 한개당 한개의 이메일만 가능
    private String email;
}
