package common.views;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class FileView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> fileMap, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		String strdFilePath = (String) fileMap.get("strdFilePath");
		String strdFileName = (String) fileMap.get("strdFileName");
		String fileName = (String) fileMap.get("fileName");

		File file = new File(strdFilePath + File.separator + strdFileName);

		int contentLength = (int) file.length();

		if (request.getHeader("User-Agent").indexOf("Trident") > -1 || request.getHeader("User-Agent").indexOf("MSIE") > -1) {
			fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
		} else {
			fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		}

		response.setContentType(response.getContentType());
		response.setContentLength(contentLength);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		OutputStream out = response.getOutputStream();
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
			out.flush();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException ioe) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException ioe) {
				}
			}
		}

	}

}
