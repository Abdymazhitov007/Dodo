package kg.demo.dodo.repository;

import kg.demo.dodo.base.BaseRep;
import kg.demo.dodo.model.entity.Size;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRep extends BaseRep<Size> {

    @Query(value = "select s.name from tb_size s where status = 'ACTIVE'", nativeQuery = true)
    List<String> findALLName();
}
