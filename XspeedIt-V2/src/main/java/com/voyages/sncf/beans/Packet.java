package com.voyages.sncf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author ALONSO REMI
 *
 */
public class Packet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int maxCapacity;
	
	private List<Article> articles = new ArrayList<>();
	
	public Packet(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	/**
	 * Add an article in packet
	 * 
	 * @param article
	 */
	public void addArticle(Article article) {
		articles.add(article);
	}
	
	/**
	 * Check if the article can be added in packet
	 * 
	 * @param article
	 * @return
	 */
	public boolean isAddingArticleAuthorised(Article article) {
		return maxCapacity >= currentWeight() + article.getWeight();
	}
	
	/**
	 * Check if packet is full
	 * 
	 * @return
	 */
	public boolean isFullPacket() {
		return currentWeight() == maxCapacity;
	
	}
	
	// Get current packet weight
	private int currentWeight() {
		int currentWeight = 0;
		
		for(Article currentArticle : articles) {
			currentWeight += currentArticle.getWeight();
		}
		
		return currentWeight;
	}
	
	@Override
	public String toString() {
		
		StringBuilder articleAsString = new StringBuilder();
		
		for(Article currentArticle : articles) {
			articleAsString.append(currentArticle.getWeight());
		}
		
		return articleAsString.toString();
	}
}
