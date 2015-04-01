package in.sel.model;

public class M_WordHint {

	String word;
	String hint;
	int id;

	public M_WordHint(String word, String hint, int id) {
		super();
		this.word = word;
		this.hint = hint;
		this.id = id;
	}

	public M_WordHint(String word, String hint) {
		super();
		this.word = word;
		this.hint = hint;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
