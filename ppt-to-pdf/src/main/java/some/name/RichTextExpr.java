package some.name;

import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.sl.usermodel.AutoNumberingScheme;

import java.util.ArrayList;
import java.util.List;

public class RichTextExpr {
	
	private List<WikiLine> wikiLines = new ArrayList<WikiLine>();

	public RichTextExpr(List<XSLFTextParagraph> pars) {
		for (XSLFTextParagraph par: pars) {
			boolean isBullet = par.isBullet();
			AutoNumberingScheme anScheme = par.getAutoNumberingScheme();
			int enumType = 0; 
			if (anScheme != null) {
				if (anScheme.toString().equals("arabicPeriod")) {
					enumType = 2;
				} else if (anScheme.toString().equals("alphaLcPeriod")) {
					enumType = 3;
				}
			} else if (isBullet) {
				enumType = 1;
			} else if (par.getText().startsWith("* ")) {
				enumType = 1;
			} else if (par.getText().matches("^[1-9]?[0-9]\\. .*")) {
				enumType = 2;
			} else if (par.getText().matches("^[a-z]\\. .*")) {
				enumType = 3;
			}
			wikiLines.add(new WikiLine(par, enumType));
		}
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		int prevEnumType = 0;
		for (WikiLine wikiLine: wikiLines) {
			if (wikiLine.getEnumType() != prevEnumType) {
				sb.append("\r\n");
			}
			sb.append(wikiLine.toString() + "\r\n");
			prevEnumType = wikiLine.getEnumType();
		}
		return sb.toString();
	}
		
	public String toTexEscaped() {
	  String result = toString();
	  // Do some last fixes to the Markdown text being returned
	  // result = result.replaceAllLiterally("ì", "\\v{g}")
	  return result;
	}
}
