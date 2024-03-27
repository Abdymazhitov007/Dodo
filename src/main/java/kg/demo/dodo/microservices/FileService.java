package kg.demo.dodo.microservices;



import kg.demo.dodo.model.response.FileServiceUploadResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "${micro.file.name}", url = "${micro.file.url}")
public interface FileService {

    @PostMapping( value = "/api/v1/file/upload", consumes = "multipart/form-data")
    FileServiceUploadResponse upload(@RequestPart MultipartFile file);


}
