package in.sel.smileword;

public class TableContract {

	/* Data Type And Separator */
	private static final String TYPE_INTEGER = " INTEGER ";
	private static final String TYPE_BOOLEAN = " BOOLEAN ";
	private static final String TYPE_TEXT = " TEXT ";
	private static final String SEP_COMMA = " , ";

	private static final String CLOSE_BRACE = " ) ";
	private static final String OPEN_BRACE = " ( ";
	private static final String SEMICOLON = " ; ";

	private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";
	private static final String AUTO_INCREMENT = " AUTOINCREMENT ";
	private static final String CREATE_TABLE = " CREATE TABLE ";
	private static final String PRIMARY_KEY = " PRIMARY KEY ";
	private static final String NOT_NULL = " NOT NULL ";

	/* Constraints */
	private static final String ON_CONFLICT_REPLACE = " ON CONFLICT REPLACE ";
	private static final String ON_CONFLICT_IGNORE = " ON CONFLICT IGNORE ";
	private static final String UNIQUE = " UNIQUE ";

	/**holds all type of Category 
	 * for e.g. Sports, Science, Scientist etc  */
	public interface Category {
		String TABLE_NAME = "Category";
		String AUTO_ID = "_id";
		String CATEGORY_NAME = "CategoryName";

		String SQL_CREATE = CREATE_TABLE + TABLE_NAME + OPEN_BRACE + AUTO_ID
				+ TYPE_INTEGER + PRIMARY_KEY + AUTO_INCREMENT + SEP_COMMA
				+ CATEGORY_NAME + TYPE_TEXT + SEP_COMMA 
				+ UNIQUE + OPEN_BRACE + CATEGORY_NAME+ CLOSE_BRACE + ON_CONFLICT_IGNORE
				+ CLOSE_BRACE;
		String SQL_DROP = DROP_TABLE + TABLE_NAME;
	}

	/** Save All Word and there Hint */
	public interface HintWord {
		String TABLE_NAME = "HintWord";
		String AUTO_ID = "_id";
		String HINT = "Hint";
		String WORD = "Word";
		String LANGUAGE_CODE = "LanguageCode";
		

		String SQL_CREATE = CREATE_TABLE + TABLE_NAME + OPEN_BRACE + AUTO_ID
				+ TYPE_INTEGER + PRIMARY_KEY + AUTO_INCREMENT + SEP_COMMA
				+ HINT + TYPE_TEXT + SEP_COMMA 
				+ WORD + TYPE_TEXT + SEP_COMMA 
				+ LANGUAGE_CODE + TYPE_INTEGER + SEP_COMMA 
				+ UNIQUE + OPEN_BRACE + HINT +SEP_COMMA + WORD + CLOSE_BRACE + ON_CONFLICT_IGNORE
				+ CLOSE_BRACE;
		String SQL_DROP = DROP_TABLE + TABLE_NAME;
	}
	
	/**For Language Option */
	public interface Language {
		String TABLE_NAME = "Language";
		String AUTO_ID = "_id";
		String LANGUAGE_NAME = "Lanuage_Name";
		

		String SQL_CREATE = CREATE_TABLE + TABLE_NAME + OPEN_BRACE + AUTO_ID
				+ TYPE_INTEGER + PRIMARY_KEY + AUTO_INCREMENT + SEP_COMMA
				+ LANGUAGE_NAME + TYPE_TEXT + SEP_COMMA 
				
				+ UNIQUE + OPEN_BRACE + LANGUAGE_NAME + CLOSE_BRACE + ON_CONFLICT_IGNORE
				+ CLOSE_BRACE;
		String SQL_DROP = DROP_TABLE + TABLE_NAME;
	}
	
}
