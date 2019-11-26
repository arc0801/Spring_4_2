package com.naver.s4.model;

import java.util.List;

public class BoardQnaVO extends BoardVO {
	private int ref;
	private int step;
	private int depth;
	private List<FilesVO> files;
	
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public List<FilesVO> getFiles() {
		return files;
	}
	public void setFiles(List<FilesVO> files) {
		this.files = files;
	}

	
	
}
