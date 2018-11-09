package au.gov.nla.marc.service;


import au.gov.nla.marc.domain.input.InputRecord;
import au.gov.nla.marc.domain.input.InputRecords;
import au.gov.nla.marc.domain.input.Tag;
import au.gov.nla.marc.domain.output.TabbedResultTable;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Service
public class MarcTextServiceImpl implements MarcTextService {

    private static final Logger logger = LoggerFactory.getLogger(MarcTextServiceImpl.class);

    FileReaderService fileReaderService;
    TabbedResultAssemblerService tabbedResultAssemblerService;

//    @Todo: add all possible values here
    String recordIdField = "001";


    public MarcTextServiceImpl(FileReaderService fileReaderService, TabbedResultAssemblerService tabbedResultAssemblerService) {
        this.fileReaderService = fileReaderService;
        this.tabbedResultAssemblerService = tabbedResultAssemblerService;
    }

    @Override
    public TabbedResultTable transFormToTabbedOutPut(String fileName) {
        InputRecords inputRecords = buildInputRecords(fileName);
        addColumnHeadingsToInputRecords(inputRecords);
        TabbedResultTable tabbedResultTable = tabbedResultAssemblerService.mapToTabbedResult(inputRecords);
        return tabbedResultTable;
    }


    private InputRecords buildInputRecords(String fileName) {
        List<String> fileRows = fileReaderService.readFile(fileName);
        InputRecords inputRecords = new InputRecords();
        InputRecord inputRecord = new InputRecord();
        int lineNumber = 1;
        for (String row : fileRows) {
            String tag = StringUtils.substringBefore(row, " ");
            if (tag.equals(recordIdField)) {
                if (lineNumber != 1) {
                    inputRecords.getRecords().add(inputRecord);
                    updateTagCount(inputRecords, inputRecord);
                }
                inputRecord = new InputRecord();
                inputRecord.setType(tag);
                inputRecord.setRecordId(StringUtils.substringAfter(row, " "));
            } else {
                inputRecord.getTags().put((tag), "$" + StringUtils.substringAfter(row, "$"));
            }
            lineNumber++;
        }
        inputRecords.getRecords().add(inputRecord);//add the last record
        return inputRecords;
    }

    private void addColumnHeadingsToInputRecords(InputRecords inputRecords) {
        ArrayList<Tag> tagHeadings = inputRecords.getTagHeadings();
        HashMap<String, Integer> countOfTags = inputRecords.getCountOfTags();
        for (String tag : countOfTags.keySet()) {
            Integer count = (Integer) countOfTags.get(tag);
            for (int i = 0; i < count; i++) {
                Tag newTag = new Tag(tag + "." + String.valueOf(i + 1), tag, (i + 1));
                tagHeadings.add(newTag);
            }
        }
        tagHeadings.sort(Comparator.comparing(Tag::getTagNumber)
                .thenComparing(Tag::getTagPosition));
        inputRecords.setTagHeadings(tagHeadings);
    }


    private void updateTagCount(InputRecords inputRecords, InputRecord inputRecord) {
        HashMap<String, Integer> existingTagMaxs = inputRecords.getCountOfTags();
        for (String tag : inputRecord.getTags().keys()) {
            Integer tagCount = inputRecord.getTags().asMap().get(tag).size();

            if (existingTagMaxs.containsKey(tag)) {
                if (tagCount > existingTagMaxs.get(tag))
                    inputRecords.getCountOfTags().replace(tag, tagCount);
            } else {
                inputRecords.getCountOfTags().put(tag, tagCount);
            }
        }
    }
}
