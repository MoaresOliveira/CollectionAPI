package io.github.moaresoliveira.orangenotesapi.mapper;

import io.github.moaresoliveira.orangenotesapi.model.Collection;
import io.github.moaresoliveira.orangenotesapi.model.dto.CollectionDTO;
import io.github.moaresoliveira.orangenotesapi.model.form.CollectionForm;
import io.github.moaresoliveira.orangenotesapi.model.form.CollectionUpdateForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CollectionMapper {

    CollectionMapper INSTANCE = Mappers.getMapper(CollectionMapper.class);

    @Mapping(target = "parent", source = "parent.id")
    CollectionDTO toCollectionDTO(Collection collection);
    @Mapping(target = "parentId", source = "parent")
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "url", source = "url")
    Collection toCollection(CollectionDTO collectionDTO);

    @Mapping(target = "url", source = "url")
    Collection toCollection(CollectionForm form);

    @Mapping(target = "url", source = "url")
    Collection toCollection(CollectionUpdateForm collectionDTO);

    List<Collection> toCollectionList(List<CollectionDTO> collectionDTOList);

    List<CollectionDTO> toCollectionDTOList(List<Collection> collectionList);

}
