package by.bobruisk.homework.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileStorageUtils {

	private static final String ROOT_PATH = System.getProperty("user.dir");
	private static final String DIR_TO_UPLOAD = ROOT_PATH + File.separator + "src\\main\\resources\\static\\files\\";

	public static List<Path> saveTempFilesToStorage(List<MultipartFile> files) throws IOException {
		List<Path> filePaths = new ArrayList<Path>();
		for (int i = 0; i < files.size(); i++) {
			byte[] bytes = files.get(i).getBytes();
			String filePath = DIR_TO_UPLOAD + files.get(i).getOriginalFilename();
			Path path = Paths.get(filePath);
			Files.write(path, bytes);
			filePaths.add(path);
		}
		return filePaths;
	}

	public static void removeTempFilesFromStorage(List<Path> filePaths) throws IOException {
		for (int i = 0; i < filePaths.size(); i++) {
			Files.delete(filePaths.get(i));
		}
	}
}
