package com.voyages.sncf.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voyages.sncf.beans.Article;
import com.voyages.sncf.beans.Packet;
import com.voyages.sncf.constants.XspeedItConstants;
import com.voyages.sncf.logger.XspeedItLogger;

/**
 * 
 * @author ALONSO REMI
 *
 */
@Service
public class PackagingService {

	@Autowired
	XspeedItLogger logger;

	/**
	 * 
	 * 
	 * @param articles
	 * @return
	 */
	public List<Packet> packageArticles(List<Article> articles) {

		logger.info("Packaging Article");

		// List<Packet> packets = new ArrayList<>();

		// Sort article by weight descending
		Collections.sort(articles);

		// Add all heavy article in separated packet (weight > max capacity / 2)
		List<Packet> packets = articles.stream()
				.filter(x -> x.getWeight() > (XspeedItConstants.MAX_WEIGHT / 2))
				.map(x -> {
					Packet packet = new Packet(XspeedItConstants.MAX_WEIGHT);
					packet.addArticle(new Article(x.getWeight()));
					return packet;
				}).collect(Collectors.toList());

		// Add all lightweight article in existed packet (or create others)
		List<Article> lightWeightPacket = articles.stream().filter(
				x -> x.getWeight() <= (XspeedItConstants.MAX_WEIGHT / 2))
				.collect(Collectors.toList());

		for (Article article : lightWeightPacket) {

			// If article is not added in one packet of the list, then create
			// new packet
			if (!addArticleInPackets(packets, article)) {
				Packet aNewPacket = new Packet(XspeedItConstants.MAX_WEIGHT);
				aNewPacket.addArticle(article);
				packets.add(aNewPacket);
			}
		}

		return packets;
	}

	// Return true if packet is added in on packet, false otherwise
	private boolean addArticleInPackets(List<Packet> packets, Article article) {

		for (Packet packet : packets) {
			if (packet.isAddingArticleAuthorised(article)) {
				packet.addArticle(article);

				return true;
			}
		}

		return false;
	}
}
