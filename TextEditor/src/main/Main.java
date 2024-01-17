package main;

import util.FolderViewer;
import util.Window;

public class Main {

	public static void main(String[] args) {
		// setting up the frame
		Window frame = new Window("NoteTakingApp", 420, 420);
		frame.start();
		// testing out file viewer
		FolderViewer.viewFolder("C:\\Users\\colen\\Downloads");
	}
}
