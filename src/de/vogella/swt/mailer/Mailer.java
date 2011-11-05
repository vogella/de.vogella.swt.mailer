package de.vogella.swt.mailer;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.program.Program;

public class Mailer {


	public void open(String to, String subject,String body, List<File> attachments) {
		if (SWT.getPlatform().contains("win")){
			openMailWindows(to, subject, body, attachments);
		} else if (SWT.getPlatform().contains("gtk")){
			openMailLinux(to, subject, body, attachments);
		}
	}
	// This works for Linux with Thunderbird
	public void openMailLinux(String to, String subject,String body, List<File> attachments) {
		String mailto = "mailto:" + enc(to) + "?subject=" + enc(subject) + "&body=" + enc(body);
		for (File f : attachments) {
			mailto += "&attach=" + f.getAbsolutePath();
		}
		Program.launch(mailto);
		try {
			// For using the parameters check the following description
			// http://kb.mozillazine.org/Command_line_arguments_%28Thunderbird%29
			Runtime.getRuntime().exec("thunderbird -compose");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// This works for Windows
	public void openMailWindows(String to, String subject,String body, List<File> attachments) {
		String mailto = "mailto:" + enc(to) + "?subject=" + enc(subject) + "&body=" + enc(body);
		for (File f : attachments) {
			mailto += "&attach=" + f.getAbsolutePath();
		}
		Program.launch(mailto);
	}

	private String enc(String p) {
		if (p == null)
			p = "";
		try {
			return URLEncoder.encode(p, "UTF-8").replace("+", "%20");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException();
		}
	}

	public void wrap() {
	}

}