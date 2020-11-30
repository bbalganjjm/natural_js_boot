package common.app.file;

import java.io.File;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import common.app.file.service.FileService;
import common.exception.CommonException;
import common.utils.SecurityUtils;

/**
 * File Upload / Download Controller.
 *
 * @author KIM HWANG MAN<bbalganjjm@gmail.com>
 * @since 2018.12.05
 */

@Controller
@RequestMapping("file")
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Resource(name = "fileServiceImpl")
	private FileService fileService;

	@Autowired
	private MessageSource messageSource;

	@Value("${file.not.allow.exts}")
    private String[] fileNotAllowExts;

	@Value("${file.upload.base.path}")
    private String fileUploadBasePath;

	@Value("${file.upload.max.each}")
    private int fileUploadMaxEach;

	@Value("${file.upload.max.all}")
    private int fileUploadMaxAll;

	@RequestMapping(value = "manager.view", method = RequestMethod.GET)
	public String fileView(Model model) {
		String strFileNotAllowExts = "";
		for(int i=0; i < fileNotAllowExts.length; i++) {
			strFileNotAllowExts += fileNotAllowExts[i] + ",";
		}

		model.addAttribute("fileNotAllowExts", strFileNotAllowExts);
		model.addAttribute("fileUploadMaxEach", fileUploadMaxEach);
		model.addAttribute("fileUploadMaxAll", fileUploadMaxAll);

		return "common/app/file/manager";
	}

	@RequestMapping(value = "saveFile.json", method = RequestMethod.POST)
	public Map<String, Object> saveFile(MultipartHttpServletRequest mRequest) throws Exception {
		if(fileService == null) {
			throw new Exception("FileService interface 클래스를 구현(implements) 한 fileServiceImpl 빈(Service)을 생성해 주세요.");
		}

		List<Map<String, Object>> saveFileList = new ArrayList<Map<String, Object>>();

		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		if(month.length() == 1) {
			month = "0" + month;
		}
		String fileUploadPath = fileUploadBasePath + File.separator + year + month;

		File dir = new File(fileUploadPath);
		if (!dir.isDirectory()) {
			if(!dir.mkdirs()) {
				throw new CommonException(-27);
			}
		}

		Iterator<String> files = mRequest.getFileNames();
		MultipartFile mFile;
		String fileName;
		String fileNameExt;
		String strdFileName;


		Map<String, Object> paramMap;
		int fileSizeSum = 0;
		while (files.hasNext()) {
			paramMap = new HashMap<String, Object>();
			Enumeration<String> e = mRequest.getParameterNames();
			String name;
			String[] values;
			while (e.hasMoreElements()) {
				name = e.nextElement();
				values = mRequest.getParameterValues(name);
				if (values != null) {
					if(values.length > 1) {
						int i = 0;
						for (String value : values) {
							paramMap.put(name + "[" + String.valueOf(i) + "]", SecurityUtils.cleanXSSString(value));
							i++;
						}
					} else {
						paramMap.put(name, SecurityUtils.cleanXSSString(values[0]));
					}
				}
			}

			String uploadFile = files.next();

			mFile = mRequest.getFile(uploadFile);

			fileName = mFile.getOriginalFilename();
			fileNameExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			strdFileName = UUID.randomUUID().toString();

			paramMap.put("fileName", fileName);
			paramMap.put("fileNameExt", fileNameExt);
			paramMap.put("fileSize", mFile.getSize());
			fileSizeSum += mFile.getSize();
			paramMap.put("strdFilePath", fileUploadPath);
			paramMap.put("strdFileName", strdFileName);

			// 파일 확장자 체크
			for(int i=0; i < fileNotAllowExts.length; i++) {
				if(fileNotAllowExts[i].equals(fileNameExt)) {
					this.rollbackFiles(saveFileList);
					throw new CommonException(-24, new String[] { fileNameExt, fileName });
				}
			}

			// 파일별 업로드 가능 최대 사이즈 체크
			if(mFile.getSize() > fileUploadMaxEach) {
				this.rollbackFiles(saveFileList);

				throw new CommonException(-25, new String[] { String.valueOf(Math.round((fileUploadMaxEach / 1024 / 1024) * 100) / 100.0)
						, fileName
						, String.valueOf(Math.round((mFile.getSize() / 1024 / 1024) * 100) / 100.0) });
			}

			// 전체 업로드 가능 최대 사이즈 체크
			if(fileSizeSum > fileUploadMaxAll) {
				this.rollbackFiles(saveFileList);
				throw new CommonException(-26, new String[] { String.valueOf(Math.round((fileUploadMaxAll / 1024 / 1024) * 100) / 100.0) });
			}

			if(logger.isDebugEnabled()) {
				logger.debug("File to upload : " + fileName + "(" + fileUploadPath + File.separator + strdFileName + ")");
			}
			mFile.transferTo(new File(fileUploadPath + File.separator + strdFileName));

			saveFileList.add(paramMap);
		}

		return fileService.saveFile(saveFileList);
	}

	@RequestMapping("getFileList.json")
	public List<Map<String, Object>> getFileList(@RequestBody Map<String, Object> vo) throws Exception {
		return fileService.getFileList(vo);
	}

	@RequestMapping(value = "getFile/{fileId}/{fileSeq}", method = RequestMethod.GET)
	public String getFile(@PathVariable(value = "fileId") String fileId, @PathVariable(value = "fileSeq") String fileSeq, Model model) throws Exception {
		Map<String, Object> vo = new HashMap<>();
		vo.put("fileId", fileId);
		vo.put("fileSeq", fileSeq);
		Map<String, Object> fileInfo = fileService.getFile(vo);

		model.addAttribute("strdFilePath", fileInfo.get("strdFilePath"));
	    model.addAttribute("strdFileName", fileInfo.get("strdFileName"));
	    model.addAttribute("fileName", fileInfo.get("fileName"));

	    return "fileView";
	}

	@RequestMapping("deleteFile.json")
	public void deleteFile(@RequestBody Map<String, Object> vo) throws Exception {
		Map<String, Object> fileInfo = fileService.getFile(vo);

		File uploadedFile = new File(fileInfo.get("strdFilePath") + File.separator + fileInfo.get("strdFileName"));
		if(!uploadedFile.delete()) {
			throw new CommonException(-28);
		}
		if(logger.isDebugEnabled()) {
			logger.debug("Delete file : " + fileInfo.get("fileName") + "(" + uploadedFile.getPath() + ")");
		}

		fileService.deleteFile(vo);
	}

	/**
	 * 서버에 복제된 파일들 롤백(삭제)
	 *
	 * @param saveFileList
	 */
	private void rollbackFiles(List<Map<String, Object>> saveFileList) {
		Iterator<Map<String, Object>> iter = saveFileList.iterator();
		while (iter.hasNext()) {
			Map<String, Object> fileInfo = (Map<String, Object>) iter.next();
			File uploadedFile = new File(fileInfo.get("strdFilePath") + File.separator + fileInfo.get("strdFileName"));
			if(!uploadedFile.delete()) {
				if(logger.isWarnEnabled()) {
					logger.warn(messageSource.getMessage("-29", new String[] { uploadedFile.getName() }, Locale.getDefault()));
				}
			}
			if(logger.isDebugEnabled()) {
				logger.debug("Rollback copied file : " + fileInfo.get("fileName") + "(" + uploadedFile.getPath() + ")");
			}
		}
		saveFileList.clear();
	}
}