package com.tms.repository;

import java.util.List;

import com.tms.model.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {

    /**
     * @param id
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT * FROM lt_comments_car where car_id=:id")
    List<Comments> getCommentsByCar_id(int id);
}
