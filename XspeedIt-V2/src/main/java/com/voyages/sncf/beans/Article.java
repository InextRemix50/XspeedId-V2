package com.voyages.sncf.beans;

import java.io.Serializable;

/**
 * 
 * @author ALONSO REMI
 *
 */
public class Article implements Comparable<Article>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int weight;
	
	public Article(int weight) {
		this.weight = weight;
	}
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Article article) {
		return article.getWeight() - getWeight();
	}
}
