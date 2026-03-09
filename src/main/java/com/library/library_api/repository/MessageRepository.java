package com.library.library_api.repository;
import com.library.library_api.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageRepository extends  JpaRepository<Message, Long>{

}