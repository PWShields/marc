package au.gov.nla.marc.service;

import au.gov.nla.marc.domain.output.TabbedResultTable;
import org.springframework.web.multipart.MultipartFile;

/**
 * The interface Marc text service.
 */
public interface MarcTextService {

    /**
     * Trans form to tabbed out put tabbed result table.
     *
     * Input is a file path as a string
     *
     * Use case is for test cases
     *
     * @param fileName the file name
     * @return the tabbed result table
     */
    TabbedResultTable transFormToTabbedOutPut(String fileName);

    /**
     * Trans form to tabbed out put tabbed result table.
     *
     * Input is a remote file posted to the application
     * This is the main use case
     *
     * @param file the file
     * @return the tabbed result table
     */
    TabbedResultTable transFormToTabbedOutPut(MultipartFile file);
}
