package tech.chilo.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.chilo.sa.entites.Sentiment;
import tech.chilo.sa.enums.TypeSentiment;

import java.util.List;

@Repository
public interface SentimentRepository extends JpaRepository<Sentiment, Integer> {
    List<Sentiment> findByType(TypeSentiment typeSentiment);
}
