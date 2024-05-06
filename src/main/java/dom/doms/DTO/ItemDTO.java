package dom.doms.DTO;


import dom.doms.Entity.Item;
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
public class ItemDTO {
    int id;
    String itemname;
    int price;
    int way;
    String seller;
    String detail;
    String buyer;
    int Cprice;
    int status;
    String date;
    int category;
    String link;

    public ItemDTO(Item as){
        id = as.getId();
        itemname = as.getItemname();
        price = as.getPrice();
        way = as.getWay();
        seller = as.getSeller();
        status = as.getStatus();
        date = as.getDate();
        category = as.getCategory();
        link = as.getLink();
    }
}
