package util;

import java.io.File;
import java.util.ArrayList;

public class FolderViewer {
	public static ArrayList<String> viewFolder(String filePath) {
		File folder = new File(filePath);
		File[] files = folder.listFiles();

		ArrayList<String> folders = new ArrayList<>();
		ArrayList<String> notes = new ArrayList<>();

		// sorting files between folders and others
		for (File file : files) {
			ArrayList<String> result = file.isDirectory() ? folders : notes;
			result.add(file.toString().replace(folder.getAbsolutePath() + "\\", ""));
		}
		return folders;
	}

	public static ArrayList<String> viewFiles(String filepath) {
		File folder = new File(filepath);
		File[] files = folder.listFiles();

		ArrayList<String> folders = new ArrayList<>();
		ArrayList<String> notes = new ArrayList<>();

		// sorting files between folders and others
		for (File file : files) {
			ArrayList<String> result = file.isDirectory() ? folders : notes;
			result.add(file.toString().replace(folder.getAbsolutePath() + "\\", ""));
		}

		return notes;
	}
	
	
}
