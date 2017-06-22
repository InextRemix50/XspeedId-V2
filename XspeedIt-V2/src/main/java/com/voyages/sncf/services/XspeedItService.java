package com.voyages.sncf.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voyages.sncf.beans.Article;
import com.voyages.sncf.beans.Packet;
import com.voyages.sncf.constants.XspeedItConstants;
import com.voyages.sncf.exceptions.MalformatedArticleListException;
import com.voyages.sncf.logger.XspeedItLogger;

/**
 * 
 * @author ALONSO REMI
 *
 */
@Service
public class XspeedItService {
	
	@Autowired
	PackagingService packagingService;
	
	@Autowired
	XspeedItLogger logger;
	
	/**
	 * Proceed packaging of article in argument
	 * 
	 * @param articlesEntered
	 * @return
	 * @throws MalformatedArticleListException
	 */
	public String proceedPackaging(String articlesEntered) throws MalformatedArticleListException {
		
		logger.info("Proceed packaging for articles : ".concat(articlesEntered));
		
		List<Article> articles = createArticle(articlesEntered);
		
		List<Packet> packets = packagingService.packageArticles(articles);
		
		return createResult(packets);
	}
	
	// Check if all articles are available (mean represent a valid integer)
	// And create list of articles
	private List<Article> createArticle(String articlesEntered) throws MalformatedArticleListException {
		
		logger.info("Check and create articles");
		
		String[] articlesAsString = articlesEntered.split(XspeedItConstants.ARTICLE_SEPARATOR);
		List<Article> articles = new ArrayList<>();
		
		for(String articleAsString : articlesAsString) {
			try {
				articles.add(new Article(Integer.parseInt(articleAsString)));
			} catch(NumberFormatException e) {
				throw new MalformatedArticleListException("Entered articles list is not valid");
			}
		}
		
		return articles;
	}
	
	// Create a result (as String) that represent all packets and articles (separating by /)
	private String createResult(List<Packet> packets) {
		
		logger.info("Create result from created packets");
		
		StringBuilder result = new StringBuilder();
		
		for(Packet packet : packets) {
			result.append(packet.toString());
			result.append(XspeedItConstants.PACKET_SEPARATOR);
		}
		
		// Remove last PACKET_SEPARATOR
		result.setLength(Math.max(result.length() - 1, 0));
		
		return result.toString();
	}
}
