package sitesnapSimple;

import java.io.IOException;
import java.net.MalformedURLException;
import javax.servlet.http.HttpServletRequest;

import webUI.WebUI;

public class SiteSnap {

	public DataResponse getResponse(String url, HttpServletRequest request) throws MalformedURLException, IOException {
		WebUI.openRemoteBrowserHeadless();
		DataResponse dr = new DataResponse();
		//WebUI.openBrowser("Chrome");
		WebUI.navigateToURL(url);
		WebUI.delay(3);
		dr.imgName = WebUI.takescreenshot(url, request);
		WebUI.closeBrowser();
		dr.statusCode = 200;
		return dr;
	}

}
