package DOM.source.DTO;



import DOM.source.Entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*데이터전달을 위한 객체 엔티티를 생성하는데 사용 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDTO {
    
    private int id;
   
    private String email;
    
    private String psw;
    
    private String nickname;

    public CustomerDTO(Customer cu){
        id = cu.getId();
        email = cu.getEmail();
        psw = cu.getPsw();
        nickname = cu.getNickname();
    }
}
