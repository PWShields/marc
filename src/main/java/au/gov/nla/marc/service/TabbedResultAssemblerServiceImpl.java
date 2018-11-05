package au.gov.nla.marc.service;

import au.gov.nla.marc.domain.input.InputRecord;
import au.gov.nla.marc.domain.input.InputRecords;
import au.gov.nla.marc.domain.output.HeaderRow;
import au.gov.nla.marc.domain.output.TabbedResultRow;
import au.gov.nla.marc.domain.output.TabbedResultTable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

@Service
public class TabbedResultAssemblerServiceImpl implements TabbedResultAssemblerService {
    @Override
    public TabbedResultTable mapToTabbedResult(InputRecords inputRecords) {
        TabbedResultTable tabbedResultTable = new TabbedResultTable();
        tabbedResultTable.setHeaderRow(mapHeaderRow(inputRecords));
        tabbedResultTable.setResultRows(mapResultRows(inputRecords));
        return tabbedResultTable;
    }

    private HeaderRow mapHeaderRow(InputRecords inputRecords) {
        HeaderRow headerRow = new HeaderRow();
        headerRow.setType(inputRecords.getRecords().get(0).getType());
        headerRow.setColumnHeadings(inputRecords.getTagHeadings());
        return headerRow;
    }

    private ArrayList<TabbedResultRow> mapResultRows(InputRecords inputRecords) {
        ArrayList<TabbedResultRow> resultRows = new ArrayList<>();
        for (InputRecord inputRecord : inputRecords.getRecords()) {
            TabbedResultRow resultRow = new TabbedResultRow();
            resultRow.setRecordId(inputRecord.getRecordId());
            Map<String, Collection<String>> tags = inputRecord.getTags().asMap();
            for (String eachTag : tags.keySet()) {
                ArrayList<String> values = new ArrayList(Arrays.asList(tags.get(eachTag).toArray()));
                resultRow.getTags().put(eachTag, new ArrayList(Arrays.asList(tags.get(eachTag).toArray())));
                for(int i = 0; i < values.size(); i++) {
                    resultRow.getExpandedTags().put(eachTag +"." + (i + 1), values.get(i));
                }
            }

            resultRows.add(resultRow);
        }
        return resultRows;
    }



}
