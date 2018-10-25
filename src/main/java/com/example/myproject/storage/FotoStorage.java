package com.example.myproject.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {
	
	public String SalvarTemporariamente(MultipartFile foto);

	public  byte[] recuperarFotoTemporaria(String nome);
	
}
