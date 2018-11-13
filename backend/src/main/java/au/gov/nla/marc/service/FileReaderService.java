package au.gov.nla.marc.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileReaderService {

    List<String> readFile(String fileName);

    List<String> readFile(MultipartFile file);
}
