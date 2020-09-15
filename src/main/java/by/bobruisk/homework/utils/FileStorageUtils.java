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
	public static final String DIR_TO_UPLOAD = ROOT_PATH + File.separator + "src\\main\\resources\\static\\files\\";
	public static final String DIR_PRINTERS_IMAGE=ROOT_PATH+File.separator+"src\\main\\resources\\static\\images\\printers\\";
	public static final String DIR_CARTRIDGES_IMAGE=ROOT_PATH+File.separator+"src\\main\\resources\\static\\images\\cartridges\\";
	public static final String DIR_PARTS_IMAGE=ROOT_PATH+File.separator+"src\\main\\resources\\static\\images\\parts\\";
	
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
	
	public static void saveImage(MultipartFile file, String sourcePath) throws IOException {
		byte[] bytes=file.getBytes();
		Path path=Paths.get(sourcePath);
		Files.write(path, bytes);
	}
	
	public static void removeImage(String sourcePath) throws IOException {
		Path path =Paths.get(sourcePath);
		Files.delete(path);
	}
}
