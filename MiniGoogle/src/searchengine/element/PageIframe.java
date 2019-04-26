package searchengine.element;

/** A text Iframe in a web page.
 */
public class PageIframe implements PageElementInterface {

	public PageIframe (String w) {
		Iframe = w;
	}

	public String toString () {
		return Iframe;
	}

	private String Iframe;
}
