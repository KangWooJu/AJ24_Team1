package dom.doms.Entity;




import dom.doms.DTO.EvaluateDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 /*데이터베이스와 소통하기위한 객체 수정이 되면 위험할수도 있어 DTO를 이용하여 생성 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private int itemid;
    @Column
    private int evaluate;
    @Column
    private String reason;
    public Evaluate (EvaluateDTO edt){
        id = edt.getId();
        itemid =edt.getItemid();
        evaluate = edt.getEvaluate();
        reason = edt.getReason();
    }
}
