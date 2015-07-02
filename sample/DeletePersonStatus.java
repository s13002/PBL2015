/* DeletePersonStatus.java
 */

/* DeletePersonStatus
 */
public class DeletePersonStatus extends ConsoleStatus {

	// フィールド
	private PersonList pl;
	private int id = -1;
	private String data;

	/**
	 * コンストラクタ DeletePersonStatus
	 * @param String firstMess
	 * @param String promptMess
	 * @param boolean IsEndStatus
	 * @param PersonList pl
	 */
	DeletePersonStatus( String firstMess, String promptMess,
	                 boolean IsEndStatus, PersonList pl ) {
		super( firstMess, promptMess, IsEndStatus );
		this.pl = pl;
		this.id = -1;
		this.data = "";
	}

	// 最初に出力するメッセージを表示する
	// このクラスでは従業員のデータの削除処理
	// のみを行う
	/** displayFirstMess
	 * @throws Exception
	 */
	public void displayFirstMess() throws Exception {
		// IDの入力
		System.out.print( "従業員IDを入力してください。\n>" );
		data = inputMessage();
		try {
			id = Integer.parseInt( data ); // 従業員ID
		} catch( NumberFormatException e ) {
			System.out.println( "数値に変換できないデータが入力されています。" );
			System.out.println( "再入力してください。" );
			displayFirstMess();
			return;
		}

		Person p = pl.get( id );
		if( p == null ) {
			System.out.println( "指定のIDの従業員は存在しません。" );
			System.out.println( "再入力してください。" );
			displayFirstMess();
			return;
		}

		// 従業員の情報の表示
		System.out.println( p.toString() + "\n" );
	}

	// Yが入力された場合指定されたレコードを削除，
	// N(またはそれ以外)の場合何もせずに
	// 初期状態に遷移するようにしている
	/** getNextStatus
	 * @param String s
	 * @return ConsoleStatus
	 */
	public ConsoleStatus getNextStatus( String s ) {
		if( s.equals( "Y" ) ) {
			System.out.println( "削除しました。" );
			pl.delete( id );
		}

		return super.getNextStatus( " " );
	}

}
