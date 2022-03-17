package toto.xdev.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import toto.xdev.modet.Message;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
	//Message findByMessage(String id);
}
