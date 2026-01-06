package rti.system.dev.ollamajavaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rti.system.dev.ollamajavaapi.domain.entity.ChatLogs;

@Repository
public interface OllamaJavaRepository extends JpaRepository<ChatLogs, Long> {
}
