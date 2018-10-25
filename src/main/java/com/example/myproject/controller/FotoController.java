package com.example.myproject.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.myproject.dto.FotoDTO;
import com.example.myproject.storage.FotoStorage;

@RestController
@RequestMapping("/fotos")
public class FotoController {
	
	@Autowired
	FotoStorage fotoStorage;
		
	@PostMapping
	public ResponseEntity<FotoDTO> nova(@RequestParam("files[]")MultipartFile[] files) throws IOException {
		String nomeFoto = fotoStorage.SalvarTemporariamente(files[0]);
		String contentType = files[0].getContentType();
		return ResponseEntity.ok(new FotoDTO(nomeFoto, contentType));
	}
	
	@GetMapping("/temp/{nome}")
	public ResponseEntity<byte[]> recuperarTemporaria(@PathVariable String nome) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<>(fotoStorage.recuperarFotoTemporaria(nome), headers, HttpStatus.OK );
	}
	
	
}
