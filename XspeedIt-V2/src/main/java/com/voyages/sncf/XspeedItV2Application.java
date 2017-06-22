package com.voyages.sncf;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.voyages.sncf.exceptions.MalformatedArticleListException;
import com.voyages.sncf.logger.XspeedItLogger;
import com.voyages.sncf.services.XspeedItService;

@SpringBootApplication
public class XspeedItV2Application implements CommandLineRunner {

	@Autowired
	XspeedItService xspeedItService;
	
	@Autowired
	XspeedItLogger logger;
	
	public static void main(String[] args) {
		SpringApplication.run(XspeedItV2Application.class, args);
	}
    
    @Override
	public void run(String... args) throws MalformatedArticleListException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your list of articles to package :");
		String articlesEntered = scanner.nextLine();
		
		String result = xspeedItService.proceedPackaging(articlesEntered);
		
		logger.info("Packaged articles  :".concat(result));
	}
}
