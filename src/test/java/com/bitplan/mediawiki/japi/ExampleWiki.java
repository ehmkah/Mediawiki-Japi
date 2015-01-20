/**
 * Copyright (C) 2015 BITPlan GmbH
 *
 * Pater-Delp-Str. 1
 * D-47877 Willich-Schiefbahn
 *
 * http://www.bitplan.com
 */
package com.bitplan.mediawiki.japi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlRootElement;

import com.bitplan.mediawiki.japi.api.General;
import com.bitplan.mediawiki.japi.api.Page;
import com.bitplan.mediawiki.japi.user.WikiUser;

/**
 * example wikis to be used for test
 * 
 * @author wf
 *
 */
@XmlRootElement(name = "examplewiki")
public class ExampleWiki  {
  /**
   * Logging may be enabled by setting debug to true
   */
  protected static java.util.logging.Logger LOGGER = java.util.logging.Logger
      .getLogger("com.bitplan.mediawiki.japi");

  // the delegate wiki to use
  MediawikiApi wiki;
  
  /**
   * get the MediaWikiJapi implementation
   * @return
   */
  public Mediawiki getMediaWikiJapi() {
    Mediawiki result=null;
    if (wiki instanceof com.bitplan.mediawiki.japi.Mediawiki) {
      result=(Mediawiki) wiki;
    } else {
      LOGGER.log(Level.SEVERE,"trying to get MediaWikiJapi implementation for "+wiki.getClass());
    }
    return result;
  }

	/**
	 * the id of the wiki
	 */
	protected String wikiId;

	/**
	 * Map of example titles
	 */
	protected Map<String, List<ExamplePage>> examplePages = new HashMap<String, List<ExamplePage>>();

	protected int expectedPages;

	private String logo;

	private WikiUser wikiuser;

	/**
	 * @return the wikiId
	 */
	public String getWikiId() {
		return wikiId;
	}

	/**
	 * @param wikiId
	 *          the wikiId to set
	 */
	public void setWikiId(String wikiId) {
		this.wikiId = wikiId;
	}

	/**
	 * @return the expectedPages
	 */
	public int getExpectedPages() {
		return expectedPages;
	}

	protected void setExpectedPages(int count) {
		expectedPages = count;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLogo() {
		return logo;
	}

	/**
	 * creates an ExampleWiki for the given wikiId,and wiki to be used
	 * to the map of example Wikis
	 * 
	 * @param wikiId
	 * @throws Exception 
	 */
	public ExampleWiki(String wikiId, MediawikiApi wiki) throws Exception {
		this.wiki=wiki;
		this.wikiId = wikiId;
	}

	/**
	 * get the WikiUser for this example Wiki
	 * 
	 * @return the wiki user
	 */
	public WikiUser getWikiUser() {
		if (wikiuser == null) {
			wikiuser = WikiUser.getUser(wikiId, wiki.getSiteurl());
			if (wiki.isDebug()) {
				LOGGER.log(Level.INFO, "user=" + wikiuser.getUsername());
			}
		}
		return wikiuser;
	}

	/**
	 * example Page
	 * 
	 * @author wf
	 *
	 */
	public class ExamplePage extends Page {
		String contentPart;
		boolean forEdit=false;

		/**
		 * construct an example Page with the given title and the given part of the
		 * content
		 * 
		 * @param title
		 * @param contentPart
		 * @param forEdit
		 */
		public ExamplePage(String title, String contentPart,boolean forEdit) {
			this.title = title;
			this.contentPart = contentPart;
			this.forEdit=forEdit;
		}
		
		public ExamplePage(String title, String contentPart) {
			this(title,contentPart,false);
		}

		public String getContentPart() {
			return contentPart;
		}

	}

	/**
	 * return the example Page for the given testId
	 * 
	 * @param testId
	 *          - the key for looking up the example page
	 * @return - the list of example pages with the given testId - or an empty list if there was nothing found
	 */
	public List<ExamplePage> getExamplePages(String testId) {
		List<ExamplePage> result = examplePages.get(testId);
		if (result==null)
			return new ArrayList<ExamplePage>();
		return result;
	}

	/**
	 * add the given page to the example pages
	 * 
	 * @param testId
	 * @param page
	 */
	public void addExamplePage(String testId, ExamplePage page) {
		if (!examplePages.containsKey(testId)) {
			List<ExamplePage> pages = new ArrayList<ExamplePage>();
			examplePages.put(testId, pages);
		}
		List<ExamplePage> pages = examplePages.get(testId);
		pages.add(page);
	}	

	

	/**
	 * login to the example wiki
	 * 
	 * @throws Exception
	 */
	public void login() throws Exception {
		WikiUser lwikiuser = this.getWikiUser();
		wiki.login(lwikiuser.getUsername(), lwikiuser.getPassword());
	}

	/**
	 * get list of titles for the given example pages
	 * @param examplePages
	 * @return
	 */
	public List<String> getTitleList(List<ExamplePage> examplePages) {
		List<String> titles=new ArrayList<String>();
		for (ExamplePage page:examplePages) {
			titles.add(page.getTitle());
		}
		return titles;
	}

	/**
	 * delegate the getSiteinfo call
	 * @return
	 * @throws Exception
	 */
  public General getSiteInfo() throws Exception {
    return wiki.getSiteInfo();
  }

}
