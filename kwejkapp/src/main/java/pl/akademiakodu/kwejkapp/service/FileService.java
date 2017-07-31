package pl.akademiakodu.kwejkapp.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by itml on 24.06.2017.
 */
public interface FileService {
    String store(MultipartFile file) throws IOException, FileServiceException;
    Resource getFileAsResource(String filePath);
}
