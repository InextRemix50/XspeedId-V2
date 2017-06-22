package com.voyages.sncf.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
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
	public String proceedPackaging(String articlesEntered)
			throws MalformatedArticleListException {

		logger.info(
				"Proceed packaging for articles : ".concat(articlesEntered));

		List<Article> articles = createArticle(articlesEntered);

		List<Packet> packets = packagingService.packageArticles(articles);

		return createResult(packets);
	}

	// Check if all articles are available (mean represent a valid integer)
	// And create list of articles
	private List<Article> createArticle(String articlesEntered)
			throws MalformatedArticleListException {

		logger.info("Check and create articles");

		List<String> articlesAsString = Arrays.asList(
				articlesEntered.split(XspeedItConstants.ARTICLE_SEPARATOR));
		List<Article> articles;
		try {
			articles = articlesAsString.stream()
					.map(x -> new Article(Integer.parseInt(x)))
					.collect(Collectors.toList());
		} catch (NumberFormatException e) {
			throw new MalformatedArticleListException(
					"Entered articles list is not valid");
		}

		return articles;
	}

	// Create a result (as String) that represent all packets and articles
	// (separating by /)
	private String createResult(List<Packet> packets) {

		logger.info("Create result from created packets");

		List<String> result = packets.stream().map(x -> x.toString())
				.collect(Collectors.toList());

		return StringUtils.join(result, XspeedItConstants.PACKET_SEPARATOR);
	}
}
