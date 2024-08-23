package com.guess.the.word.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.guess.the.word.gameutil.GameUtill;
import com.guess.the.word.service.GameService;

@Controller
//@RestController
public class GameController {
	
	@Autowired
	GameService gameServiceObj;
	
	@Autowired
	GameUtill gameutilObj;
	
	@GetMapping("gamehome")
	public String showGamePage(@RequestParam(value = "guessedChar1", required = false) String guessedChar1, Model model) {
	    
	    String word = gameServiceObj.getRandomWord();
	    System.out.println("type word :" + guessedChar1);
	
	    model.addAttribute("wordDisplay", word);
	   try {
	    if(guessedChar1 != null) {
	    	System.out.println("inside condi : " + guessedChar1);
	    	boolean isGuessCorrect = gameServiceObj.addGuess(guessedChar1.charAt(0));
	    	System.out.println("Maincheck" + isGuessCorrect);
	    		if(isGuessCorrect == true) {
	    			model.addAttribute("correct", "CorrectGuess");
	    			System.out.println("insidecheck : " + isGuessCorrect);
	    	    	word = gameServiceObj.getRandomWord();

	    		}
	    		else  {
		    		model.addAttribute("wrong", "Oopss");
		    		System.out.println("inside condition-----");
		    		gameutilObj.reduceTry();
  		
	    	}
	    }
	    
	    }
//   	System.out.println("rightorwrong : " + isGuessCorrect);
//   	word = gameServiceObj.getRandomWord();
//   		if(isGuessCorrect == false) {
//   		model.addAttribute("wrong", "Oopss");
//   		System.out.println("inside condition-----");
// 
	   catch (StringIndexOutOfBoundsException e) {
		   model.addAttribute("error", "character must be contained!!!");
           System.out.println("Exception caught: " + e);}
	   
	    System.out.println("tries remaining :" + gameutilObj.youHaveTry());
	    model.addAttribute("wordDisplay", word);
	    model.addAttribute("triesleft", gameutilObj.youHaveTry());


	    return "Game-Home-Page";
	
	}

	@GetMapping("reload")
	public String reloadMethod() {
		
		gameServiceObj = gameServiceObj.reloadNewWord();
		gameutilObj.resttries();
		return "redirect:gamehome";
	}
}
