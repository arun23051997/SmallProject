package com.guess.the.word.gameutil;

import org.springframework.stereotype.Component;

@Component
public class GameUtill {
	
//	@Autowired
//	ConfigurableApplicationContext applicationContext;
	
	private int maxtry = 5;
	
	public int reduceTry() {
		maxtry = maxtry - 1;
		return maxtry;
		
	}
	public int youHaveTry() {
		return maxtry;
		
	}
	
	public int resttries() {
		return maxtry = 5;
	}
	
//	public GameService reloadNewWord() {
//		GameService gameService = applicationContext.getBean(GameService.class);
//		return gameService ;
//	}
}
