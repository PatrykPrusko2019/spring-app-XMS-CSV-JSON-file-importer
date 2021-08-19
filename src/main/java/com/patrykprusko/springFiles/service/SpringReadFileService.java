package com.patrykprusko.springFiles.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.patrykprusko.springFiles.model.Policy;

public interface SpringReadFileService {

	 List<Policy> findAll();

	boolean saveDataFromUploadFile(MultipartFile file);

}
