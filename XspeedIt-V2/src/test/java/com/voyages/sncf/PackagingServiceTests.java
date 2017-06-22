package com.voyages.sncf;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.voyages.sncf.beans.Article;
import com.voyages.sncf.beans.Packet;
import com.voyages.sncf.services.PackagingService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PackagingServiceTests {

	@Autowired
	PackagingService packagingService;
	
	@Test
	public void testOptimizedPackaging() {
		
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
        assertThat(packets.size()).isEqualTo(8);
    }
}
