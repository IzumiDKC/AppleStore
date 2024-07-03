package Nhom08_AppleStore.controller;

import Nhom08_AppleStore.model.User;
import Nhom08_AppleStore.repository.UserRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class ExcelExportController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportExcel() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Users");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Username");
            header.createCell(1).setCellValue("Phone");
            header.createCell(2).setCellValue("Email");

            List<User> users = userRepository.findAll();
            int rowIdx = 1;
            for (User user : users) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(user.getUsername());
                row.createCell(1).setCellValue(user.getPhone());
                row.createCell(2).setCellValue(user.getEmail());
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=userslist.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

