package com.study.sociallogin.repository;

import com.study.sociallogin.model.Boards;
import com.study.sociallogin.model.Locations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Boards, Long> {
    Boards findByBoardId(Long id);

    @Query("SELECT b FROM Boards b WHERE b.boardId IN (SELECT bl.boardId FROM BoardLikes bl WHERE bl.userEmail = :userEmail)")
    List<Boards> findBoardsLikedByUser(String userEmail);

    @Query("SELECT b FROM Boards b WHERE b.boardTitle LIKE %:keyword% AND b.boardId IN (SELECT bl.boardId FROM BoardLikes bl WHERE bl.userEmail = :userEmail)")
    List<Boards> findBoardsLikedByUserWithTitle(String userEmail, String keyword);

    @Query("SELECT b FROM Boards b WHERE b.boardTitle LIKE %:keyword%")
    List<Boards> findBoardsWithTitle(String keyword);


}
