package com.patrykprusko.springFiles.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import javax.transaction.Transactional;
import java.util.Iterator;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.patrykprusko.springFiles.model.Policy;
import com.patrykprusko.springFiles.repository.SpringReadFileRepository;


@Service
@Transactional
public class SpringReadFileServiceImpl implements SpringReadFileService {

	@Autowired 
	private SpringReadFileRepository springReadFileRepository;

	@Override
	public List<Policy> findAll() {
		
		return (List<Policy>) springReadFileRepository.findAll();
	}

	@Override
	public boolean saveDataFromUploadFile(MultipartFile file) {
		
		boolean isFlag = false;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		if(extension.equalsIgnoreCase("json")) {
			isFlag = readDataFromJson(file);
		} else if(extension.equalsIgnoreCase("csv")) {
			isFlag = readDataFromCsv(file);
		} else if(extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")) {
			isFlag = readDataFromExcel(file);
		}

		return isFlag;
	}

	private boolean readDataFromExcel(MultipartFile file) {

		Workbook workbook = getWorkbook(file);
		Sheet sheet = workbook.getSheetAt(0);

		Iterator<Row> rows = sheet.iterator();
		rows.next();
		while( rows.hasNext() ) {
			Row row = rows.next();
			Policy policy = new Policy();

			if( row.getCell(0).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				String policyNumber = NumberToTextConverter.toText(row.getCell(0).getNumericCellValue());
				policy.setPolicyNumber(policyNumber);
			}
			
			if ( row.getCell(1) == null ) {
				policy.setTypeOfPolicy("");
			} else {
			
				if( row.getCell(1).getCellType() == Cell.CELL_TYPE_STRING) {
				policy.setTypeOfPolicy(row.getCell(1).getStringCellValue());
				}
			}
			
			if( row.getCell(2).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				String sumPolicy = NumberToTextConverter.toText(row.getCell(2).getNumericCellValue());
				policy.setSumPolicy(sumPolicy);
			}
			
			
			if ( row.getCell(3) == null ) {
				policy.setName("");
			} else {
				if( row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
					policy.setName(row.getCell(3).getStringCellValue());
				}
			}
			
			if ( row.getCell(4) == null ) {
				policy.setSurname("");
			} else {
				
				if( row.getCell(4).getCellType() == Cell.CELL_TYPE_STRING) {
					policy.setSurname(row.getCell(4).getStringCellValue());
				}
			}
			
			if ( row.getCell(5) == null ) {
				policy.setInsuredItem("");
			} else {
				
				if( row.getCell(5).getCellType() == Cell.CELL_TYPE_STRING) {
					policy.setInsuredItem(row.getCell(5).getStringCellValue());
				}
			}
			
			
			policy.setFileType( FilenameUtils.getExtension(file.getOriginalFilename()) );
			springReadFileRepository.save(policy);
		}

		return true;
	}
	
	
	
	private Workbook getWorkbook(MultipartFile file) {
		Workbook workbook = null;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());

		try {
			if( extension.equalsIgnoreCase("xlsx")) {
				workbook = new XSSFWorkbook(file.getInputStream());
			} else if( extension.equalsIgnoreCase("xls")) {
				workbook = new HSSFWorkbook(file.getInputStream());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return workbook;
	}

	private boolean readDataFromCsv(MultipartFile file) {
		try {
			InputStreamReader reader = new InputStreamReader(file.getInputStream());
			CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
			List<String[]> rows = csvReader.readAll();
			for(String[] row : rows) { 
				springReadFileRepository.save(new Policy(row[0], row[1], row[2], row[3], row[4], row[5], FilenameUtils.getExtension(file.getOriginalFilename()) ));
			}
			return true;

		} catch(Exception e) {
			return false;
		}

	}
	
	
	
	private boolean readDataFromJson(MultipartFile file) {
		// TODO Auto-generated method stub
		try {
			InputStream inputStream = file.getInputStream();
			ObjectMapper mapper = new ObjectMapper();
			List<Policy> policy = Arrays.asList(mapper.readValue(inputStream, Policy[].class));
			if(policy != null && policy.size() > 0) {
				for(Policy policy2 : policy) {
					
					policy2.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
					springReadFileRepository.save(policy2);
					
				}
			}
			return true;
		
		} catch(Exception e) {
			return false;
		}
		
	}
	

}
