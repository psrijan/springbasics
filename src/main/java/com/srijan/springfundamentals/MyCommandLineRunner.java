package com.srijan.springfundamentals;

import com.srijan.springfundamentals.controller.WordController;
import com.srijan.springfundamentals.entity.Word;
import com.srijan.springfundamentals.util.ExcelReader;
import com.srijan.springfundamentals.repository.WordRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Slf4j
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    private WordRepository wordRepository;

    @Value("${initial.data}")
    private String enableInitialData;

    final Logger logger = LoggerFactory.getLogger(WordController.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void run(String... args) throws Exception {

//        testEntityManager();
        testLogger();

        if (enableInitialData.equalsIgnoreCase("Y")) {
            List<Word> wordList = ExcelReader.parseExcel();
            wordList.stream().forEach(word -> {
                log.info("Saving {} to repository" , word.getName());
                wordRepository.save(word);
            });
        }
    }

    public void testEntityManager() {
        Query query = entityManager.createQuery("select t from Word t where t.name like '%a'");
        List<Word> wordList = query.getResultList();
        log.debug(" SIze {}  {} {} " ,  wordList.size() ,wordList.get(0).getName() , wordList.get(0).getDefinition());
    }

    public void testLogger() {
        logger.info("Testing Logger Info");
        logger.debug("Testing Logger Debug");
        logger.error("Testing Logger error");
        log.info("Testing log Info");
        log.debug("Testing Log Debug");
        log.error("Testing Log error");
    }
}
