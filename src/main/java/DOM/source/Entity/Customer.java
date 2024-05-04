package DOM.source.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
 /*데이터베이스와 소통하기위한 객체 수정이 되면 위험할수도 있어 DTO를 이용하여 생성 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String email;
    @Column
    private String psw;
    @Column()
    private String nickname;
    @Column
    private String name;
    @Column
    private String hint;

}
