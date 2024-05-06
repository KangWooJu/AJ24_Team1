package dom.doms.Entity;


import dom.doms.DTO.ItemDTO;
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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String itemname;
    @Column
    private int price;
    @Column
    private int way;
    @Column
    private String seller;
    @Column
    private String detail;
    @Column
    private String buyer;
    @Column
    private int Cprice;
    @Column
    private int status;
    @Column
    private String Date;
    @Column
    private int category;
    @Column
    private String link;
    public Item(ItemDTO itd){
        id = itd.getId();
        itemname = itd.getItemname();
        price = itd.getPrice();
        way = itd.getWay();
        seller = itd.getSeller();
        detail = itd.getDetail();
        status = itd.getStatus();
        category = itd.getCategory();
        }
    }

