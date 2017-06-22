package com.voyages.sncf.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
		
		List<Packet> packets = new ArrayList<>();
		
		// Sort article by weight descending
		Collections.sort(articles);
		
		for(Article article : articles) {
			
			boolean isArticleAddingInpacket = addArticleInPackets(packets, article);
			
			// If article is not added in one packet of the list, then create new packet
			if(!isArticleAddingInpacket) {
				Packet aNewPacket = new Packet(XspeedItConstants.MAX_WEIGHT);
				aNewPacket.addArticle(article);
				packets.add(aNewPacket);
			}
		}
		
		return packets;
	}
	
	// Return true if packet is added in on packet, false otherwise
	private boolean addArticleInPackets(List<Packet> packets, Article article) {
		for(Packet packet : packets) {
			if(packet.isAddingArticleAuthorised(article)) {
				packet.addArticle(article);
				
				return true;
			}
		}
		
		return false;
	}
}
