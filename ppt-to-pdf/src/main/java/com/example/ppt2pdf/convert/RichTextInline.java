package com.example.ppt2pdf.convert;

public class RichTextInline {
	private String normText;
	private boolean isBold;
	private boolean isItalic;
	
	public RichTextInline(String text, boolean isBold, boolean isItalic) {
		this.normText = text;
		this.isBold = isBold;
		this.isItalic = isItalic;
		if (normText.matches("\\s+")) {
			this.isBold = false;
			this.isItalic = false;
		}
	}
		
	@Override
	public String toString() {
		String prefix = "";
		if (normText.startsWith(" ")) {
			prefix = " ";
		}
		String suffix = "";
		if (normText.startsWith(" ")) {
			suffix = " ";
		}
		if (isBold) {
			return prefix + "**" + normText.trim() + "**" + suffix;
	    } else if (isItalic) {
	    	return prefix + "*" + normText.trim() + "*" + suffix;
	    } else {
	    	return normText;
	    }
	}    
}
