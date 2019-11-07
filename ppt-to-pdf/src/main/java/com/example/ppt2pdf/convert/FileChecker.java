package com.example.ppt2pdf.convert;

import java.io.File;
import java.io.FilenameFilter;

/**
 * This class verifies timestamps and if the slides are properly exported to PNG.
 */
public class FileChecker {

	/**
	 * Return true, if "pngDir" exists and is a directory
	 */	
	public boolean isPngDirPresent(File pngDir) {
	
	/**
	 * Return any missing PNG image file number (or -1 if all exist)
	 */
	public int isPngDirFull(File pngDir, int numSlides) {
		for (int j = 1; j <= numSlides; j++) {
			
		}
	}

	/**
	 * Are all PNG files from "pngDir" newer than the PowerPoint file
	 */
	public int isPngDirNewer(File pngDir, File pptFile) {
		File[] list = pngDir.listFiles((dir, name) -> name.endsWith(".png"));
		for (int i = 0; i < list.length; i++) {
			if (list[i].lastModified() < pptFile.lastModified()) {
				return false;
			}
		}
		return true;
	}

}
