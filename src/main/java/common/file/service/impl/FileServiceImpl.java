package common.file.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.file.mappers.FileMapper;
import common.file.service.FileService;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2019.03.21
 */

@Service("fileServiceImpl")
public class FileServiceImpl implements FileService {

	@Autowired
	private FileMapper fileMapper;

	@Override
	public Map<String, Object> saveFile(List<Map<String, Object>> saveFileList) {
		Iterator<Map<String, Object>> iter = saveFileList.iterator();
		int i=0;
		Map<String, Object> vo = null;
		String fileId = UUID.randomUUID().toString();
		while (iter.hasNext()) {
			vo = (Map<String, Object>) iter.next();
			if(vo.get("fileId") == null) { // update
				vo.put("fileId", fileId);				
			}
			fileMapper.insertFile(vo);
			i++;
			vo.put("uploadFileCnt", i);
		}
		vo.remove("copyFileName");
		vo.remove("copyFilePath");
		vo.remove("fileName");
		vo.remove("fileNameExt");
		vo.remove("fileSize");
		vo.remove("fileSeq");
		return vo;
	}

	@Override
	public List<Map<String, Object>> getFileList(Map<String, Object> vo) {
		return fileMapper.getFileList(vo);
	}

	@Override
	public Map<String, Object> getFile(Map<String, Object> vo) {
		return fileMapper.getFile(vo);
	}

	@Override
	public int deleteFile(Map<String, Object> vo) {
		return fileMapper.deleteFile(vo);
	}

}