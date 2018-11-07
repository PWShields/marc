package au.gov.nla.marc.service;

import au.gov.nla.marc.domain.input.InputRecords;
import au.gov.nla.marc.domain.output.TabbedResultTable;

public interface TabbedResultAssemblerService {
    TabbedResultTable mapToTabbedResult(InputRecords inputRecords);
}
