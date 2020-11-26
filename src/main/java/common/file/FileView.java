package common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class FileView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> fileMap, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		String copyFilePath = (String) fileMap.get("copyFilePath");
		String copyFileName = (String) fileMap.get("copyFileName");
		String fileName = (String) fileMap.get("fileName");

		File file = new File(copyFilePath + File.separator + copyFileName);

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
