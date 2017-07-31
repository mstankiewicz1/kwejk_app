package pl.akademiakodu.kwejkapp.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.UUID;

/**
 * Created by itml on 24.06.2017.
 */
@Service
public class FileServiceImpl implements FileService {

    private static final String UPLOAD_PATH = "upload";

    public String store(MultipartFile file) throws IOException, FileServiceException {
        this.initFilesystem();

        if (file.isEmpty()) {
            throw new MultipartException("File is empty");
        }

        InputStream inputStream = file.getInputStream();
        Path uploadLocation = Paths.get(UPLOAD_PATH);

        long epochSecond =  Instant.now().getEpochSecond();
        String newFileName = UUID.randomUUID() + "-" + epochSecond + "-" + file.getOriginalFilename();
        Path targetPath = uploadLocation.resolve(newFileName);
        long bytesWritten = Files.copy(inputStream, targetPath);

        if (bytesWritten <= 0) {
            throw new FileServiceException("Couldn't copy file");
        }
        return newFileName;
    }

    @Override
    public Resource getFileAsResource(String filePath) {
        try {
            Path uploadPath = Paths.get(UPLOAD_PATH);
            Path targetFilePath = uploadPath.resolve(filePath);
            Resource resource = new UrlResource(targetFilePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                return null;
            }
        } catch (MalformedURLException exc) {
            return null;
        }
    }

    private void initFilesystem() throws IOException {
        Path uploadPath = Paths.get(UPLOAD_PATH);
        if (!Files.exists(uploadPath)) {
            Files.createDirectory(uploadPath);
        }
    }
}
