package com.example.BT12.Repository;

import com.example.BT12.Entity.MessageEntity;
import com.example.BT12.Model.ChatMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, String> {
    @Query("select m from MessageEntity m where (:room is null or m.room = :room) order by m.createdAt desc")
    List<MessageEntity> findByRoomOrderByCreatedAtDesc(String room, Pageable pageable);

    // Lịch sử hội thoại riêng tư giữa 2 người
    @Query("""
         select m from MessageEntity m
         where ((m.sender = :a and m.receiver = :b) or (m.sender = :b and m.receiver = :a))
         order by m.createdAt desc
         """)
    List<MessageEntity> findPrivateHistory(String a, String b, Pageable pageable);
}
