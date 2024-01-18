package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    // 싱글톤, MultiThread 환경에서는 반드시 ConcurrentHashMap or AtomicLong
    private static final Map<Long, Item> store = new HashMap<>();  // static
    private static long sequence = 0L;  // static


    public Item save(Item item) {
        item.setId(++sequence);

        store.put(item.getId(), item);

        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }
    // Collection 으로 감싸면, store 에 영향이 없어서.
    // 타입 맞춰야 하는 등 어차피 감싸야돼.

    public void update(Long itemId, Item updateParam) {  // ItemDto.class 해서 하는 게 맞음.
        Item findItem = findById(itemId);

        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }

}
