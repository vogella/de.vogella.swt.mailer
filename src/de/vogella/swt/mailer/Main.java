package de.vogella.swt.mailer;

public class Main {
	public static void main (String[] args){
		Mailer mailer = new Mailer();
		mailer.openMailLinux("test@gmail.com", "Hello", "Testing", null);
	}
}
