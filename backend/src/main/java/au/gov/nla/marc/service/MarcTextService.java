package au.gov.nla.marc.service;

import au.gov.nla.marc.domain.output.TabbedResultTable;

public interface MarcTextService {

    TabbedResultTable transFormToTabbedOutPut(String fileName);
}
