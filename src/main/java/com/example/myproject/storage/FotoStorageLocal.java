package com.example.myproject.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FotoStorageLocal implements FotoStorage {

	private Path local;
	private Path localTemporario;

	{
		local = FileSystems.getDefault().getPath(System.getProperty("user.home"), ".pifotos");
	}

	public FotoStorageLocal() {
		criarPastas();
	}

	@Override
	public byte[] recuperarFotoTemporaria(String nome) {
		try {
			return Files.readAllBytes(localTemporario.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao recuperar Foto Temporaria", e);
		}
	}

	@Override
	public String SalvarTemporariamente(MultipartFile foto) {
		String nomeFoto = renomearFoto(foto.getOriginalFilename());
		try {
			foto.transferTo(new File(
					localTemporario.toAbsolutePath().toString() + FileSystems.getDefault().getSeparator() + nomeFoto));
		} catch (IOException e) {
			throw new RuntimeException("Erro salvando foto temporariamente", e);
		}

		return nomeFoto;

	}

	public Path getLocal() {
		return local;
	}

	public Path getLocalTemporario() {
		return localTemporario;
	}

	private void criarPastas() {
		this.localTemporario = FileSystems.getDefault().getPath(this.local.toString(), "temp");

		try {
			Files.createDirectories(local);
			Files.createDirectories(localTemporario);
		} catch (IOException e) {
			throw new RuntimeException("Erro criando pastas para Foto storage local");
		}
	}

	private String renomearFoto(String originalFilename) {
		return UUID.randomUUID().toString() + "_" + originalFilename;
	}

}
