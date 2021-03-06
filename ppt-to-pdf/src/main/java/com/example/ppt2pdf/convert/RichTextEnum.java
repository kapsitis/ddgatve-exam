package com.example.ppt2pdf.convert;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;

public class RichTextEnum {

	private List<RichTextInline> runs;
	private int enumType;

	public RichTextEnum(XSLFTextParagraph para, int enumType) {
		this.enumType = enumType;
		runs = new ArrayList<RichTextInline>();
		for (XSLFTextRun run : para.getTextRuns()) {
			runs.add(new RichTextInline(run.getRawText(), run.isBold(), run.isItalic()));
		}
	}
	
	public int getEnumType() {
		return enumType;
	}

	@Override
	public String toString() {
		StringBuffer sbResult = new StringBuffer();
		for (RichTextInline wr : runs) {
			sbResult.append(wr.toString());
		}
		String result = sbResult.toString();
		if (enumType == 0) {
			return result;
		} else if (enumType == 1) {
			if (result.matches("^(\\* )?\\s*$")) {
				return "";
			} else if (result.matches("^\\* .*")) {
				return result;
			} else {
				return "* " + result;
			}
		} else if (enumType == 2) {
			if (result.matches("^([1-9]?[0-9]\\. )?\\s*$")) {
				return "";
			} else if (result.matches("^[1-9]?[0-9]\\. .*")) {
				return result;
			} else {
				return "1. " + result;
			}
		} else if (enumType == 3) {
			if (result.matches("^([a-z]\\. )?\\s*$")) {
				return "";
			} else if (result.matches("^[a-z]\\. .*")) {
				return result;
			} else {
				return "a. " + result;
			}
		} else {
			return result;
		}
	}
}
