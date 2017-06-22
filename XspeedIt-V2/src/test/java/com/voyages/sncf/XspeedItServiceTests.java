package com.voyages.sncf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.voyages.sncf.exceptions.MalformatedArticleListException;
import com.voyages.sncf.services.XspeedItService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = XspeedItV2Application.class)
public class XspeedItServiceTests {

	@Autowired
	XspeedItService xspeedItService;
	
	@Test
	public void testProceedPackaging() throws MalformatedArticleListException {
		
		// The article test list : 163841689525773
		String articlesEntered = "163841689525773";
		
		String result = xspeedItService.proceedPackaging(articlesEntered);

        // assert statements
		assertEquals("Bad result found", "91/82/81/73/73/64/6/55", result);
    }
	
	@Test
	public void testBadArticlesEntered() throws MalformatedArticleListException {
		
		try {
			// Bad entered list
			String articlesEntered = "abc";
			
			xspeedItService.proceedPackaging(articlesEntered);
			fail("MalformatedArticleListException must be throw");
		} catch(MalformatedArticleListException e) {
			
		}
    }
	
	@Test
	public void testEmptyArticlesEntered() throws MalformatedArticleListException {
		
		try {
			// Bad entered list
			String articlesEntered = "4786453 48786746";
			
			xspeedItService.proceedPackaging(articlesEntered);
			fail("MalformatedArticleListException must be throw");
		} catch(MalformatedArticleListException e) {
			
		}
    }
}
