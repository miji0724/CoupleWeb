package kr.couple.web.service;

import java.util.Random;

public interface MakePassword {

	public static String makePassword(int length) {
		String newPassword = "";
		
		Random random = new Random() ;
		String spStr = "~!@#$%^&&*" ;
		for(int i=0; i<length; i++) {
			switch (random.nextInt(4)) {
			case 0:
				newPassword += random.nextInt(10);
				break ;
			case 1:
				newPassword += (char)('A' + random.nextInt(26)) ;
				break ;
			case 2:
				newPassword += (char)('A' + random.nextInt(26)) ;
				break ;
			case 3:
				newPassword += spStr.charAt(random.nextInt(spStr.length()));
				break ;
			}
		}
		return newPassword;
	}
}
