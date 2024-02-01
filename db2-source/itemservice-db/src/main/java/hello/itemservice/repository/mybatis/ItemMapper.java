package hello.itemservice.repository.mybatis;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
// MyBatis Spring 모듈에서 자동 인식.
// 구현체 자동으로 생성 (이름 읽어서 xml 호출) 후 빈으로 등록
// => 의존관계 주입 가능.
// Proxy 기술 사용.
public interface ItemMapper {

    void save(Item item);

    void update(@Param("id") Long id, @Param("updateParam") ItemUpdateDto updateParam);

    Optional<Item> findById(Long id); // MyBatis Optional 지원.

    List<Item> findAll(ItemSearchCond itemSearch);

}
