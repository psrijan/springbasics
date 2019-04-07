package com.srijan.springfundamentals;

import com.srijan.springfundamentals.entity.Word;
import com.srijan.springfundamentals.excel.ExcelReader;
import com.srijan.springfundamentals.repository.WordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    private WordRepository wordRepository;

    @Value("${initial.data}")
    private String enableInitialData;


    @Override
    public void run(String... args) throws Exception {

        if (enableInitialData.equalsIgnoreCase("Y")) {
            List<Word> wordList = ExcelReader.parseExcel();
            wordList.stream().forEach(word -> {
                log.info("Saving {} to repository" , word.getName());
                wordRepository.save(word);
            });
        }
    }
}
