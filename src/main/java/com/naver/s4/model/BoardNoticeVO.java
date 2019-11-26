package com.naver.s4.model;

import java.util.List;

public class BoardNoticeVO extends BoardVO {
	
	private List<FilesVO> files;

	public List<FilesVO> getFiles() {
		return files;
	}

	public void setFiles(List<FilesVO> files) {
		this.files = files;
	}
	
	

}
