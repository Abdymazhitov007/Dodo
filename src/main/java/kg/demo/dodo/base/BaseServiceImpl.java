package kg.demo.dodo.base;

import jakarta.persistence.EntityNotFoundException;
import kg.demo.dodo.model.entity.enums.Status;
import kg.demo.dodo.util.Language;
import kg.demo.dodo.util.ResourceBundleLanguage;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.MappedSuperclass;
import java.util.List;
@MappedSuperclass
public abstract class BaseServiceImpl<E extends BaseEntity,
        R extends  BaseRep<E>,
        D extends BaseDTO,
        M extends BaseMapper<E,D>>
        implements BaseService<D> {

    protected final R rep;
    protected final M mapper;

    @Autowired
    protected CycleAvoidingMappingContext context;


    public BaseServiceImpl(R rep, M mapper) {
        this.rep = rep;
        this.mapper = mapper;
    }


    @Override
    public D save(D e) {
        return mapper.toDto(rep.save(mapper.toEntity(e,context)),context);
    }

    @Override
    public D findById(Long id, int lang) {
        return mapper.toDto(rep.findById(id).orElseThrow(()-> new EntityNotFoundException(ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "entityNotFound"))),context);
    }

    @Override
    public List<D> findAll() {
        return mapper.toDtos(rep.findAll(),context);
    }

    @Override
    public D update(D e) {

        return mapper.toDto(rep.saveAndFlush(mapper.toEntity(e,context)),context);
    }

    @Override
    public boolean delete(D e) {
        try {
            e.setStatus(Status.DELETED);
            save(e);
            return true;
        }catch (Exception ex){
            throw new RuntimeException("Не удалось удалить объект");
        }


    }
}
