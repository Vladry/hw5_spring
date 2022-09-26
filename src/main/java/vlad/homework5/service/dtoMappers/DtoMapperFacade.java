package vlad.homework5.service.dtoMappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

public class DtoMapperFacade<E, D> {
    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    private final ModelMapper modelMapper = new ModelMapper();

    public DtoMapperFacade(Class<E> eClass, Class<D> dClass) {
        this.entityClass = eClass;
        this.dtoClass = dClass;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
    }

    public E convertToEntity(D dto) {
        final E entity = modelMapper.map(dto, entityClass);
        decorateEntity(entity, dto);
        return entity;
    }

    public D convertToDto(E entity) {
        final D dto = modelMapper.map(entity, dtoClass);
        decorateDto(dto, entity);
        return dto;
    }

    protected void decorateEntity(final E entity, final D dto){}
    protected void decorateDto(final D dto, final E entity){}

}
