/* Work.java
 */

/* Work <- Recordクラスを継承
 */
public class Work extends Record {
	/**
	 * フィールド
	 */
	private int personID;     // 従業員ID
	private int clientID;     // 顧客ID
	private String startTime; // 稼働開始年月日
	private String endTime;	  // 稼働終了年月日
	private int price;        // 契約単価

	/** コンストラクタ Work
	 * @param int ID
	 * @param int personID
	 * @param int clientID
	 * @param String startTime
	 * @param String endTime
	 * @param int price
	 * @param boolean eraseFlag
	 */
	Work( int ID, int personID, int clientID,
	      String startTime, String endTime,
	      int price, boolean eraseFlag ) {
		super( ID, eraseFlag );
		this.personID = personID;
		this.clientID = clientID;
		this.startTime = startTime;
		this.endTime = endTime;
		this.price = price;
	}

	/** コンストラクタ Work
	 * @param String record
	 * @throws Exception
	 */
	Work( String record ) throws Exception {
		setData( record );
	}

	/** setData
	 * @param String record
	 * @throws Exception
	 */
	public void setData( String record ) throws Exception {
		String[] n = record.split( "," );    // レコードを","で分割
		try {
			if( n.length != 7 )
			 throw new ArrayIndexOutOfBoundsException
			   ( "不正なレコードを読み込みました。" );

			setID( Integer.parseInt( n[0] ) );
			personID = Integer.parseInt( n[1] );
			clientID = Integer.parseInt( n[2] );
			startTime = n[3];
			endTime = n[4];
			price = Integer.parseInt( n[5] );
			if( n[6].equals( "t" ) ) setEraseFlag( true );
			else if( n[6].equals( "f" ) ) setEraseFlag ( false );
			else throw new NumberFormatException();
		} catch( NumberFormatException e ) {
			System.out.println
			  ( "数値または削除フラグに変換できない値がレコードに含まれています。" );
			throw e;
		}
	}

	/** toString
	 * @return String
	 */
	public String toString() {
		String ts = "ID:" + getID() + " 従業員ID:" + personID
		             + " 顧客ID:" + clientID
		             + " " + startTime + "〜" + endTime
		             + " 契約単価:" + price;
		return ts;
	}

	// 抽象メソッドwriteForCSVの実装
	/** writeForCSV
	 * @return String
	 */
	public String writeForCSV() {
		String s = "" + getID() + "," + personID + "," + clientID +
		           "," + startTime + "," + endTime +
		           "," + price + "," + ( getEraseFlag() ? "t" : "f" );
		return s;
	}

	/** getPersonID
	 * @return int
	 */
	public int getPersonID() {
		return personID;
	}

	/** setPersonID
	 * @param int personID
	 */
	public void setPersonID( int personID ) {
		this.personID = personID;
	}

	/** getClientID
	 * @return int
	 */
	public int getClientID() {
		return clientID;
	}

	/** setClientID
	 * @param int clientID
	 */
	public void setClientID( int clientID ) {
		this.clientID = clientID;
	}

	/** getStartTime
	 * @return String
	 */
	public String getStartTime() {
		return startTime;
	}

	/** setStartTime
	 * @param String startTime
	 */
	public void setStartTime( String startTime ) {
		this.startTime = startTime;
	}

	/** getEndTime
	 * @return String
	 */
	public String getEndTime() {
		return endTime;
	}

	/** setEndTime
	 * @param String endTime
	 */
	public void setEndTime( String endTime ) {
		this.endTime = endTime;
	}

	/** setPrice
	 * @param int price
	 */
	public void setPrice( int price ) {
		this.price = price;
	}
}
