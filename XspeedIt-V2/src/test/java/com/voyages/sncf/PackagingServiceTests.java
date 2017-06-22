package com.voyages.sncf;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.voyages.sncf.beans.Article;
import com.voyages.sncf.beans.Packet;
import com.voyages.sncf.logger.XspeedItLogger;
import com.voyages.sncf.services.PackagingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = XspeedItV2Application.class)
public class PackagingServiceTests {

	@Autowired
	PackagingService packagingService;
	
	@Autowired
	XspeedItLogger logger;
	
	@Test
	public void testOptimizedPackaging() {
		
		logger.info("Proceed testOptimizedPackaging");
		
		// The article test list : 163841689525773
		List<Article> articles = new ArrayList<>();
		articles.add(new Article(1));
		articles.add(new Article(6));
		articles.add(new Article(3));
		articles.add(new Article(8));
		articles.add(new Article(4));
		articles.add(new Article(1));
		articles.add(new Article(6));
		articles.add(new Article(8));
		articles.add(new Article(9));
		articles.add(new Article(5));
		articles.add(new Article(2));
		articles.add(new Article(5));
		articles.add(new Article(7));
		articles.add(new Article(7));
		articles.add(new Article(3));
		
		List<Packet> packets = packagingService.packageArticles(articles);

        // assert statements
		assertEquals("Bad packets list size found", 8, packets.size());
    }
	
	@Test
	public void testEmptyPackaging() {
		
		logger.info("Proceed testEmptyPackaging");
		
		// The empty article test list
		List<Article> articles = new ArrayList<>();
		
		List<Packet> packets = packagingService.packageArticles(articles);

        // assert statements
		assertEquals("Bad packets list size found", 0, packets.size());
    }
}
