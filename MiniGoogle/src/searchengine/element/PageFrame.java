package searchengine.element;

/** A text Iframe in a web page.
 */
public class PageFrame implements PageElementInterface {

	public PageFrame (String w) {
		frame = w;
	}

	public String toString () {
		return frame;
	}

	private String frame;
}
