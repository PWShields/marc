package au.gov.nla.marc.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileReaderServiceImpl implements FileReaderService {


    @Override
    public List<String> readFile(String fileName) {
        List<String> fileByLine = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            fileByLine = stream
                    .filter(line -> StringUtils.isNoneEmpty(line))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileByLine;
    }

    @Override
    public List<String> readFile(MultipartFile file) {
        List<String> fileByLine = new ArrayList<>();
        BufferedReader bufferedReader;
        try {
            String line;
            InputStream is = file.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(is));
            while ((line = bufferedReader.readLine()) != null) {
                fileByLine.add(line);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return fileByLine;
    }

}
