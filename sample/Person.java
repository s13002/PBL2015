/* Person.java
 */

/* Person <- Recordクラスを継承
 */

public class Person extends Record {
	/**
	 * フィールド
	 */
	private String name;		// 氏名
	private String address;		// 住所
	private String tel;		// 電話番号
	private String type;		// 職種
	private int workingYears;	// 勤続年数
	private int price;		// 単価

	/** コンストラクタ Person
	 * @param int ID
	 * @param String name 
	 * @param String address 
	 * @param String tel 
	 * @param String type 
	 * @param int workingYears
	 * @param int price
	 * @param boolean eraseFlag
	 */
	Person( int ID, String name, String address, String tel,
	 String type, int workingYears, int price, boolean eraseFlag ) {
		super( ID, eraseFlag );
	 	this.name = name;
	 	this.address = address;
	 	this.tel = tel;
	 	this.type = type;
	 	this.workingYears = workingYears;
	 	this.price = price;
	}

	/** コンストラクタ Person
	 * @param String record
	 * @throws Exception
	 */
	Person( String record ) throws Exception {
		setData( record );
	}

	/** setData
	 * @param String record
	 * @throws Exception
	 */
	public void setData( String record ) throws Exception {
		String[] n = record.split( "," );	// レコードを","で分割
		try {
			if( n.length != 8 )
			 throw new ArrayIndexOutOfBoundsException
			   ( "不正なレコードを読み込みました。" );

			setID( Integer.parseInt( n[0] ) );
			name = n[1];
			address = n[2];
			tel = n[3];
			type = n[4];
			workingYears = Integer.parseInt( n[5] );
			price = Integer.parseInt( n[6] );
			if( n[7].equals( "t" ) ) setEraseFlag( true );
			else if( n[7].equals( "f" ) ) setEraseFlag ( false );
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
		String ts = "ID:" + getID() + " 氏名:" + name
		           + "\n住所:" + address + " 電話番号:" + tel
		           + "\n職種:" + type
		           + " 勤続年数:" + workingYears + "年 "
		           + "単価:" + price + "円";
		return ts;
	}

	// 抽象メソッドwriteForCSVの実装
	/** writeForCSV
	 * @return String
	 */
	public String writeForCSV() {
		String s = "" + getID() + "," + name + "," + address +
		           "," + tel + "," + type + "," + workingYears +
		           "," + price + "," + ( getEraseFlag() ? "t" : "f" );
		return s;
	}

	/** getName
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/** getType
	 * @return String
	 */
	public String getType() {
		return type;
	}

	/** setName
	 * @param String name
	 */
	public void setName( String name ) {
		this.name = name;
	}

	/** setAddress
	 * @param String address
	 */
	public void setAddress( String address ) {
		this.address = address;
	}

	/** setTel
	 * @param String tel
	 */
	public void setTel( String tel ) {
		this.tel = tel;
	}

	/** setType
	 * @param String type
	 */
	public void setType( String type ) {
		this.type = type;
	}

	/** setWorkingYears
	 * @param int workingYears
	 */
	public void setWorkingYears( int workingYears ) {
		this.workingYears = workingYears;
	}

	/** setPrice
	 * @param int price
	 */
	public void setPrice( int price ) {
		this.price = price;
	}

}
