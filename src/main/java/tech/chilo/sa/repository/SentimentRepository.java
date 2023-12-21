package tech.chilo.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.chilo.sa.entites.Sentiment;

@Repository
public interface SentimentRepository extends JpaRepository<Sentiment, Integer> {
}
