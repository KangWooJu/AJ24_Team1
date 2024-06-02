package ac.kr.dankook.ace.dom_t1.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AuctionBidForm {

    @NotEmpty(message="경매 등록자의 이름은 필수 항목입니다.")
    private String username;

    @NotEmpty(message="금액은 필수적으로 들어가야 합니다.")
    @Min(value=1,message="금액은 0원 보다 높아야 합니다.")
    private Double bidAmount;

}
