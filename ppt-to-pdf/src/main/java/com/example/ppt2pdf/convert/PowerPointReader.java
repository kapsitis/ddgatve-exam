package com.example.ppt2pdf.convert;

import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFComment;
import org.apache.poi.xslf.usermodel.XSLFNotes;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;


/*
 http://stackoverflow.com/questions/24873725/how-to-get-pptx-slide-notes-text-using-apache-poi
 http://stackoverflow.com/questions/21523267/how-to-convert-pptx-files-to-jpg-or-png-for-each-slide-on-linux
 http://stackoverflow.com/questions/35054419/does-apache-poi-api-support-pptx2png-conversion-in-java-programs-for-pptx-gene
 */
public class PowerPointReader {   
	  
	static String destDir = "src/main/resources/";
	static String srcDir = "src/main/resources/";
	
	static List<String> fileNames = Arrays.asList(new String[] { "DataAdmin1-v85", 
			"DataAdmin2-v85", "DataAdmin3-v85", "DataAdmin4-v85" });

	/**
	 * Entry point: Receives flags and a list of PPTX files to convert. 
	 */
	public static void main(String[] args) throws IOException {
		
	  
		StringBuffer labSb = new StringBuffer();
		for (String fileName: fileNames) {
			
			XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(srcDir + fileName + ".pptx"));
            StringBuffer sb = new StringBuffer();

			Dimension pgsize = ppt.getPageSize();

			List<XSLFSlide> slides = ppt.getSlides();
			for (int i = 0; i < slides.size(); i++) {
				System.out.println("Slide " + (i + 1));
				if (i > 0) {
					sb.append("\r\n\r\n\\newpage\r\n\r\n");
				}
								
				//sb.append("\\setcounter{subsection}{0}\r\n\r\n")
								
				XSLFSlide currentSlide = slides.get(i);
				if (currentSlide.getComments() != null && currentSlide.getComments().size() > 0) {
					List<XSLFComment> comments = currentSlide.getComments();
					for (int j = 0; j < comments.size(); j++) {
						String com = comments.get(j).getText();
						System.out.println("'" + fileName + "'#" + (i+1) + " ***COM*** " + com);
					}
				}

				XSLFNotes tempNotes = currentSlide.getNotes();
				List<XSLFTextParagraph> pars = new ArrayList<XSLFTextParagraph>();
				if (tempNotes != null) {
					for (List<XSLFTextParagraph> pp: tempNotes.getTextParagraphs()) {
						pars.addAll(pp);
					}
				}

				sb.append(String.format("![](%s/Slide%02d.png", fileName, i + 1) + 
						"){ width=100% height=40.5% }\r\n\r\n\\vspace{5mm}\r\n");
				boolean prevIsBullet = false;
				if (pars.size() > 1) {
					RichTextExpr expr = new RichTextExpr(pars);
					sb.append(expr.toTexEscaped());
				}
			}								  
			if (includeBibliography) {
				String theBibliography = new String(
						Files.readAllBytes(Paths.get(destDir + fileName + ".bib.txt")), 
						StandardCharsets.UTF_8); 
				sb.append(theBibliography);
			}
			Files.write(Paths.get(destDir + fileName + ".md"),
					sb.toString().getBytes(StandardCharsets.UTF_8));
		}
	}
}
