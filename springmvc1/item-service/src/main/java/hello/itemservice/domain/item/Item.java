package hello.itemservice.domain.item;

import lombok.Getter;
import lombok.Setter;

//@Data
// 예측가능하지 않은 동작이 가능하므로 
// 핵심 domain 에서는 사용 권장 x
// DTO (단순 데이터 왔다갔다) 에는 써도 됨
@Getter
@Setter
public class Item {

    private Long id;
    private String itemName;
    private Integer price; // Nullable
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

}
