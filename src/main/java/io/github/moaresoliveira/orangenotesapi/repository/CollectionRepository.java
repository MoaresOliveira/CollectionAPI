package io.github.moaresoliveira.orangenotesapi.repository;

import io.github.moaresoliveira.orangenotesapi.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {

    List<Collection> findAllByParentIsNull();

    List<Collection> findAllByParentId(Long parentId);

}
