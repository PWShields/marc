package au.gov.nla.marc.controller;

import au.gov.nla.marc.domain.output.TabbedResultRow;
import au.gov.nla.marc.domain.output.TabbedResultTable;
import au.gov.nla.marc.service.MarcTextServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/")
public class ExportController {

    MarcTextServiceImpl marcTextService;

    public ExportController(MarcTextServiceImpl marcTextService) {
        this.marcTextService = marcTextService;
    }

    @PostMapping(value = "download/csv", produces = "text/csv")
    public void downLoadCSV(HttpServletResponse response) throws IOException {

        TabbedResultTable outputData = marcTextService.transFormToTabbedOutPut("/Users/pshields/Documents/IntellijProjects/marc/backend/src/test/resources/data/manyRecordInput.txt");

// TabbedResultTable outputData = marcTextService.transFormToTabbedOutPut("/Users/pshields/Documents/IntellijProjects/marc/backend/src/test/resources/data/multipleRecordInput.txt");

        String csvFileName = "marcTabbed.csv";
        response.setContentType("text/csv");
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);

        ArrayList<TabbedResultRow> resultRows = outputData.getResultRows();

        ICsvListWriter csvListWriter = null;
        try {
            csvListWriter = new CsvListWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

            csvListWriter.write(outputData.getHeaderRow().getColumnHeadings());
            for (TabbedResultRow row : resultRows) {
                csvListWriter.write(row.getPrintRow());
            }
        } finally {
            if (csvListWriter != null) {
                csvListWriter.close();
            }
        }

    }


}
