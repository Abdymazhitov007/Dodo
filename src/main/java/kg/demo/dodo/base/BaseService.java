package kg.demo.dodo.base;

import java.util.List;

public interface BaseService<D extends BaseDTO> {

    D save(D e);
    D findById(Long id);
    List<D> findAll();

    D update(D e);

    boolean delete(D e);

}
