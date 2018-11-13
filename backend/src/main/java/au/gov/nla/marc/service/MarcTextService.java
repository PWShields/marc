package au.gov.nla.marc.service;

import au.gov.nla.marc.domain.output.TabbedResultTable;
import org.springframework.web.multipart.MultipartFile;

public interface MarcTextService {

    TabbedResultTable transFormToTabbedOutPut(String fileName);

    TabbedResultTable transFormToTabbedOutPut(MultipartFile file);
}
