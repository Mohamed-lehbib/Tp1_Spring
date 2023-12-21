package tech.chilo.sa.service;

import org.springframework.stereotype.Service;
import tech.chilo.sa.entites.Client;
import tech.chilo.sa.entites.Sentiment;
import tech.chilo.sa.enums.TypeSentiment;
import tech.chilo.sa.repository.SentimentRepository;

import java.util.List;

@Service
public class SentimentService {
    private ClientService clientService;
    private SentimentRepository sentimentRepository;

    public SentimentService(ClientService clientService, SentimentRepository sentimentRepository) {
        this.clientService = clientService;
        this.sentimentRepository = sentimentRepository;
    }

    public void creer(Sentiment sentiment) {
        Client client = this.clientService.lireOuCreer(sentiment.getClient());
        sentiment.setClient(client);
        sentiment.setType(TypeSentiment.POSITIF); //default value
        //Analyze de sentiment
        if (sentiment.getTexte().contains("pas")) {
            sentiment.setType(TypeSentiment.NEGATIVE);
        }
        this.sentimentRepository.save(sentiment);
    }

    public List<Sentiment> rechercher(TypeSentiment typeSentiment) {
        if (typeSentiment == null) {
            return this.sentimentRepository.findAll();
        } else {
            return this.sentimentRepository.findByType(typeSentiment);
        }
    }

    public void supprimer(int id) {
        this.sentimentRepository.deleteById(id);
    }

    public void modifier(Sentiment sentiment) {
        Sentiment existingSentiment = this.sentimentRepository.findById(sentiment.getId())
                .orElseThrow(() -> new RuntimeException("Sentiment not found"));
        existingSentiment.setTexte(sentiment.getTexte());
        existingSentiment.setType(sentiment.getType());
        existingSentiment.setClient(sentiment.getClient());
        this.sentimentRepository.save(existingSentiment);
    }
}
