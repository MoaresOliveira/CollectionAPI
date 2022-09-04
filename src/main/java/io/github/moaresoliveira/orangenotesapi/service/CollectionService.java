package io.github.moaresoliveira.orangenotesapi.service;

import io.github.moaresoliveira.orangenotesapi.exception.CollectionNotFoundException;
import io.github.moaresoliveira.orangenotesapi.mapper.CollectionMapper;
import io.github.moaresoliveira.orangenotesapi.model.Collection;
import io.github.moaresoliveira.orangenotesapi.model.dto.CollectionDTO;
import io.github.moaresoliveira.orangenotesapi.model.form.CollectionForm;
import io.github.moaresoliveira.orangenotesapi.model.form.CollectionUpdateForm;
import io.github.moaresoliveira.orangenotesapi.repository.CollectionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CollectionService {

    private static final CollectionMapper mapper = CollectionMapper.INSTANCE;
    private final CollectionRepository collectionRepository;

    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public CollectionDTO findById(Long id) {
        Optional<Collection> collectionOptional = collectionRepository.findById(id);
        if (collectionOptional.isPresent()) {
            CollectionDTO collectionDTO = mapper.toCollectionDTO(collectionOptional.get());
            return collectionDTO;
        }
        throw new CollectionNotFoundException(id);
    }

    public List<CollectionDTO> findCollectionContent(Long id){
        Collection collection = mapper.toCollection(findById(id));
        List<Collection> collections = this.collectionRepository.findAllByParentId(id);
        collections.forEach(c -> {
            c.setParent(collection);
            this.collectionRepository.save(c);
        });

        if(collections.isEmpty()){
            return new ArrayList<>();
        }
        return mapper.toCollectionDTOList(collections);
    }

    public List<CollectionDTO> findAll() {
        List<Collection> collections = collectionRepository.findAllByParentIsNull();
        System.out.println(collections);
        if(collections.isEmpty()) {
            return new ArrayList<>();
        }
        return mapper.toCollectionDTOList(collections);
    }

    public CollectionDTO save(CollectionForm form) {
        Collection collection = mapper.toCollection(form);
        collection.setCreatedAt(dateNow());
        collection.setPercentage(0);
        return mapper.toCollectionDTO(collectionRepository.save(collection));
    }

    public CollectionDTO update(Long id, CollectionUpdateForm collection) {
        Collection collectionToUpdate = mapper.toCollection(findById(id));
        collectionToUpdate.setName(collection.getName());
        collectionToUpdate.setDescription(collection.getDescription());
        collectionToUpdate.setUrl(collection.getUrl());

        return mapper.toCollectionDTO(collectionRepository.save(collectionToUpdate));
    }

    public CollectionDTO finishCollection(Long id) {
        Collection collection = mapper.toCollection(findById(id));
        collection.setFinishedAt(dateNow());
        collection.setFinished(true);

        collectionRepository.save(collection);
        setPercentage(collection.getParentId());
        return mapper.toCollectionDTO(collection);
    }

    public CollectionDTO addContentToCollection(Long id, CollectionForm form) {
        Collection collectionToUpdate = mapper.toCollection(findById(id));
        Collection content = mapper.toCollection(form);
        content.setParent(collectionToUpdate);
        content.setParentId(collectionToUpdate.getId());
        content.setUrl(form.getUrl());
        content.setCreatedAt(dateNow());

        Collection contentToAdd = collectionRepository.save(content);
        if(collectionToUpdate.getContent() == null) {
            collectionToUpdate.setContent(new ArrayList<>());
        }
        collectionToUpdate.getContent().add(contentToAdd);


        return mapper.toCollectionDTO(setPercentage(collectionToUpdate.getId()));
    }

    public boolean deleteById(Long id) {
        Collection collection = mapper.toCollection(findById(id));
        collectionRepository.delete(collection);
        return true;
    }

    private Collection setPercentage(Long id) {
        Collection collection = mapper.toCollection(findById(id));
        List<Collection> collections = collectionRepository.findAllByParentId(id);
        if(collections.isEmpty()) {
            collection.setPercentage(100);
            return collectionRepository.save(collection);
        }
        int total = collections.size();
        int finished = 0;
        for (Collection c : collections) {
            if(c.isFinished()) {
                finished++;
            }
        }
        collection.setPercentage((finished * 100) / total);
        return collectionRepository.save(collection);
    }

    private String dateNow(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

}
