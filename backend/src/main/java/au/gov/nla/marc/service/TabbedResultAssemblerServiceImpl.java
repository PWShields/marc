package au.gov.nla.marc.service;

import au.gov.nla.marc.domain.input.InputRecord;
import au.gov.nla.marc.domain.input.InputRecords;
import au.gov.nla.marc.domain.input.Tag;
import au.gov.nla.marc.domain.output.HeaderRow;
import au.gov.nla.marc.domain.output.TabbedResultRow;
import au.gov.nla.marc.domain.output.TabbedResultTable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TabbedResultAssemblerServiceImpl implements TabbedResultAssemblerService {
    @Override
    public TabbedResultTable mapToTabbedResult(InputRecords inputRecords) {
        TabbedResultTable tabbedResultTable = new TabbedResultTable();
        tabbedResultTable.setHeaderRow(mapHeaderRow(inputRecords));
        tabbedResultTable.setResultRows(mapResultRows(tabbedResultTable.getHeaderRow().getColumnHeadings(), inputRecords));
        //add type to front of header row, has to be done after resultRows have been expanded
        tabbedResultTable.setHeaderRow(addTypeToFrontOfHeaderRow(tabbedResultTable.getHeaderRow()));
        //build print rows
        tabbedResultTable.setResultRows(addPrintRows(tabbedResultTable.getResultRows()));
        return tabbedResultTable;
    }

    private ArrayList<TabbedResultRow> addPrintRows(ArrayList<TabbedResultRow> resultRows) {
        ArrayList<TabbedResultRow> modifiedRows = new ArrayList<>();
        for (TabbedResultRow row : resultRows) {
            TabbedResultRow modifiedResultRow = new TabbedResultRow(row);
            ArrayList<String> printRows = new ArrayList<>();
            TreeMap<String, String> expandedTags = row.getExpandedTags();
            printRows.add(row.getRecordId());
            for (String key : expandedTags.keySet()) {
                printRows.add(expandedTags.get(key));
            }
            modifiedResultRow.setPrintRow(printRows);
            modifiedRows.add(modifiedResultRow);
        }
        return modifiedRows;
    }

    private HeaderRow mapHeaderRow(InputRecords inputRecords) {
        HeaderRow headerRow = new HeaderRow();
        String type = inputRecords.getRecords().get(0).getType();
        headerRow.setType(type);
        ArrayList<String> headings = new ArrayList<String>();
        for (Tag tag : inputRecords.getTagHeadings()) {
            headings.add(tag.getPrintableName());
        }
        headerRow.setColumnHeadings(headings);
        return headerRow;
    }

    private ArrayList<TabbedResultRow> mapResultRows(ArrayList<String> columnHeadings, InputRecords inputRecords) {
        ArrayList<TabbedResultRow> resultRows = new ArrayList<>();
        for (InputRecord inputRecord : inputRecords.getRecords()) {
            TabbedResultRow resultRow = new TabbedResultRow();
            resultRow.setRecordId(inputRecord.getRecordId());
            Map<String, Collection<String>> tags = inputRecord.getTags().asMap();
            for (String eachTag : tags.keySet()) {
                ArrayList<String> values = new ArrayList(Arrays.asList(tags.get(eachTag).toArray()));
                resultRow.getTags().put(eachTag, new ArrayList(Arrays.asList(tags.get(eachTag).toArray())));
                for (int i = 0; i < values.size(); i++) {
                    resultRow.getExpandedTags().put(eachTag + "." + (i + 1), values.get(i));
                }
            }
            TabbedResultRow modifiedResultRow = fleshOutExpandedTags(columnHeadings, resultRow);
            resultRows.add(modifiedResultRow);
        }
        return resultRows;
    }

    private TabbedResultRow fleshOutExpandedTags(ArrayList<String> columnHeadings, TabbedResultRow resultRow) {
        TabbedResultRow modifiedResultRow = new TabbedResultRow(resultRow);
        for (String heading : columnHeadings) {
            if (resultRow.getExpandedTags().containsKey(heading) == false) {
                resultRow.getExpandedTags().put(heading, "");
            }
        }
        return modifiedResultRow;
    }

    private HeaderRow addTypeToFrontOfHeaderRow(HeaderRow headerRow) {
        headerRow.getColumnHeadings().add(0, headerRow.getType());
        return headerRow;
    }
}
