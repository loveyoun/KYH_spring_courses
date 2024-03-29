package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;


    /**
     * 상품 목록
     */
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();

        model.addAttribute("items", items);

        return "basic/items";
    }

    /**
     * 상품 상세
     */
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);

        model.addAttribute("item", item);

        return "basic/item";
    }

    /**
     * 상품 등록 폼
     */
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    /**
     * 상품 등록
     */
    //@PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam int price,
                            @RequestParam Integer quantity,
                            Model model) {
        Item item = new Item();      // 여기부터
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);  // 여기까지 @ModelAttribute 가 자동으로 호출

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {
        itemRepository.save(item);

//        model.addAttribute("item", item);  // Item 객체 생성 후, 자동 추가. 생략 가능

        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        // Model model
        // 스프링이 밖에서 Model 다 만듦. 거기에 자동으로 들어감.
        // Item 객체를 담은 Model 객체는 View 에서 또 쓰인다.

        // @ModelAttribute default rule : Item.class -> name = "item"

        itemRepository.save(item);

        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);

        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);

        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) { // URL 인코딩도 다 해결.
        Item savedItem = itemRepository.save(item);

        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        // 치환하고 남은 건 query parameter 로 들어가게 된다. ?status=true

        return "redirect:/basic/items/{itemId}";  // redirectAttributes 에 넣은 값이 치환된다.
    }

    /**
     * 상품 수정 폼
     */
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);

        model.addAttribute("item", item);

        return "basic/editForm";
    }

    /**
     * 상품 수정
     */
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);

        return "redirect:/basic/items/{itemId}";  //@PathVariable 사용. 뒤에 /edit 안남게
        // 원래 View 로 가야하는데, Controller 로 가게.
        // Location header 로 들어간다.
    }


    /**
     * 테스트용 데이터 추가
     **/
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

}

