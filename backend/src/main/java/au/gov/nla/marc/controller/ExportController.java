package au.gov.nla.marc.controller;

import au.gov.nla.marc.domain.output.TabbedResultRow;
import au.gov.nla.marc.domain.output.TabbedResultTable;
import au.gov.nla.marc.service.MarcTextServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping(value = "/")
public class ExportController {

    private static final Logger logger = LoggerFactory.getLogger(ExportController.class);

    MarcTextServiceImpl marcTextService;

    public ExportController(MarcTextServiceImpl marcTextService) {
        this.marcTextService = marcTextService;
    }

    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void fileUpload(@RequestParam("formData") MultipartFile file) {
        logger.info("File received");
    }

    @PostMapping(value = "download/csv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.ALL_VALUE)
    public void downLoadCSV(HttpServletResponse response,  @RequestParam MultipartFile file) throws IOException {
        logger.info("File received");
        TabbedResultTable outputData = marcTextService.transFormToTabbedOutPut(file);

        String csvFileName = "marcTabbed.csv";
        response.setContentType("text/csv");

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);

        ArrayList<TabbedResultRow> resultRows = outputData.getResultRows();

        ArrayList<String> headerRow = outputData.getHeaderRow().getColumnHeadings();

        ICsvListWriter csvListWriter = null;
        try {
            csvListWriter = new CsvListWriter(response.getWriter(), CsvPreference.EXCEL_PREFERENCE);

            csvListWriter.write(headerRow);
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
