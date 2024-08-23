package com.guess.the.word.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class GameService {

	@Autowired
	ConfigurableApplicationContext applicationContext;
	
	private String[] randomWords = {"narasimma","veerapan","guru","karupu"};
	char[] arrayNull;
	private String randomwords1;
	Random randomObj = new Random();
	
	
	
	
	public GameService() {
		

		randomwords1 = randomWords[randomObj.nextInt(randomWords.length)];
		System.out.println("The word : " + randomwords1);
		 arrayNull = new char[randomwords1.length()];
	}



	public String getRandomWord() {
//		return "Service [randomWords=" + Arrays.toString(randomWords) + ", randomObj=" + randomObj + "]";
		String count = " ";
		for (char c:arrayNull) {
			if(c == '\u0000') {
				count = count + " _";
				
		}
			else {
				count = count + c;
			}
//		count = count + " ";

	}
		return count;

}



	public boolean addGuess(char geussOneChar) {
		boolean isGuessCorrect = false;
		for(int i=0; i<randomwords1.length(); i++) {
			if(geussOneChar == randomwords1.charAt(i)) {
				arrayNull[i] = geussOneChar;
				System.out.println("insideLOOP : " + arrayNull[i]);
				 isGuessCorrect = true;
			}
			
			
		
	}
		System.out.println("insideloop" + isGuessCorrect);
		return isGuessCorrect;
}

	public GameService reloadNewWord() {
		GameService gameService = applicationContext.getBean(GameService.class);
		return gameService ;
	}

	
}
