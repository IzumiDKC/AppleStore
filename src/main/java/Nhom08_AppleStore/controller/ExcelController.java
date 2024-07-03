package Nhom08_AppleStore.controller;

import Nhom08_AppleStore.model.Role;
import Nhom08_AppleStore.model.User;
import Nhom08_AppleStore.repository.IRoleRepository;
import Nhom08_AppleStore.repository.UserRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RestController
public class ExcelController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // Skip header row
                    continue;
                }
                String username = row.getCell(0).getStringCellValue();
                String phone = row.getCell(1).getStringCellValue();
                String email = row.getCell(2).getStringCellValue();

                User user = User.builder()
                        .username(username)
                        .phone(phone)
                        .email(email)
                        .build();

                userRepository.save(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File processing error");
        }
        return ResponseEntity.ok("File uploaded successfully");
    }
}

