package DOM.source.DTO;

import DOM.source.Entity.Evaluate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EvaluateDTO {
    
    private int id;
    
    private int itemid;
    
    private int evaluate;
    
    private String reason;
    public EvaluateDTO(Evaluate ev){
        id = ev.getId();
        itemid = ev.getItemid();
        evaluate = ev.getEvaluate();
        reason = ev.getReason();
    }
}
