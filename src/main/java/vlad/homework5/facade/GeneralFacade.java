package vlad.homework5.facade;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

public class GeneralFacade<E, RQ, RS> {
    private final Class<E> entityClass;
    private final Class<RS> dtoRsClass;

    private final ModelMapper modelMapper = new ModelMapper();

    public GeneralFacade(Class<E> eClass, Class<RS> rsClass) {
        this.entityClass = eClass;
        this.dtoRsClass = rsClass;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
    }

    public E convertToEntity(RQ requestDTO) {
        final E entity = modelMapper.map(requestDTO, entityClass);
        decorateEntity(entity, requestDTO);
        return entity;
    }

    public RS convertToDto(E entity) {
        final RS dto = modelMapper.map(entity, dtoRsClass);
        decorateDto(dto, entity);
        return dto;
    }

    protected void decorateEntity(final E entity, final RQ dto){}
    protected void decorateDto(final RS dto, final E entity){}

}

