package com.iiht.forum.usermicro.util;



	import java.nio.file.Files;
	import java.nio.file.Path;
	import java.nio.file.Paths;
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.stereotype.Service;
	import org.springframework.web.multipart.MultipartFile;

	import lombok.extern.slf4j.Slf4j;

	@Slf4j
	@Service
	public class StorageUtil{

		private final Path rootLocation = Paths.get("src/main/resources/static");
		public String store(MultipartFile file) {
			Path path = this.rootLocation.resolve(file.getOriginalFilename());
			try {
				Files.copy(file.getInputStream(), path);
				log.info("Uploaded Successfully");
			} catch (Exception e) {
				System.out.println(e);
				throw new RuntimeException("FAIL!");
			}
			return path.toString();
			
		}



	}

