package com.vivian.World.Banking.App.service;

import com.vivian.World.Banking.App.payload.response.BankResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface GeneralUserService {

    ResponseEntity<BankResponse<String>> uploadProfilePIcs(MultipartFile multipartFile);
}
