package com.srijan.springfundamentals.excel;

import com.srijan.springfundamentals.entity.Word;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class ExcelReader {
//
    public static final String FILE_NAME = "C:\\Users\\srijan\\Documents\\personal\\projects\\springbasics\\src\\main\\resources\\vocabulary.xlsx";

    public static void main(String[] args ) {
        List<Word> wordList = parseExcel();
        log.info("{}" , wordList.size());
        log.info("{} , {} ", wordList.get(0).getName() ,  wordList.get(0).getDefinition());
    }

    public static List<Word> parseExcel() {
        try {

            List<Word> wordArrayList = new ArrayList<>();

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                Word word = new Word();
                int counter = 0;
                while (cellIterator.hasNext()) {
                    counter++;
                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        if(counter ==1 ) {
                            word.setName(currentCell.getStringCellValue()!= null ? currentCell.getStringCellValue().trim():"");
                        } else {
                            word.setDefinition(currentCell.getStringCellValue()!= null?currentCell.getStringCellValue().trim():"");
                        }
                        log.info(currentCell.getStringCellValue());
                    }

                }
                wordArrayList.add(word);

            }
            return wordArrayList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
