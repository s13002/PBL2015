/* Record.java
 */

/* Record
 */
abstract public class Record {

	/**
	 * フィールド
	 */
	private int ID;
	private boolean eraseFlag;

	/**
	 * コンストラクタ Record
	 */
	Record() {
		this.ID = -1;
		this.eraseFlag = false;
	}

	/** コンストラクタ Record
	 * @param int ID
	 * @param boolean eraseFlag
	 */
	Record( int ID, boolean eraseFlag ) {
		this.ID = ID;
		this.eraseFlag = eraseFlag;
	}

	/** getID
	 * @return int
	 */
	public int getID() {
		return ID;
	}

	/** setID
	 * @param int ID
	 */
	public void setID( int ID ) {
		this.ID = ID;
	}

	/** getEraseFlag
	 * @return boolean
	 */
	public boolean getEraseFlag() {
		return eraseFlag;
	}

	/** setEraseFlag
	 * @param boolean eraseFlag
	 */
	public void setEraseFlag( boolean eraseFlag ) {
		this.eraseFlag = eraseFlag;
	}

	// 抽象メソッド
	/** writeForCSV
	 * 実装内容：CSVファイルに出力する値を返す
	 * @return String
	 */
	public abstract String writeForCSV();
}
