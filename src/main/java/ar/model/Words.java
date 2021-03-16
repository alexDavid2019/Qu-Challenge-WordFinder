package ar.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Words {

	Vector<String> _toFind;
	List<String> _matrix;
	
	public Words() {
		this._matrix = new ArrayList<String>();
		this._toFind = new Vector<String>();
	}
	
	public Vector<String> getWordsToFind() {
		return _toFind;
	}

	public void setWordsToFind(Vector<String> val) {
		this._toFind = val;
	}

	public List<String> getMatrix() {
		return _matrix;
	}

	public void setMatrix(List<String> val) {
		this._matrix = val;
	}

	public Boolean IsCompleted() {
		return this._matrix.size() > 0 && this._toFind.size() > 0;
	}
	
}
