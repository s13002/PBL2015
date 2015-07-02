/* AddPersonStatus.java
 */

/* AddPersonStatus
 */
public class AddPersonStatus extends ConsoleStatus {

	// フィールド
	private PersonList pl;

	private String[] messages = {
		"氏名を入力してください。>",
		"住所を入力してください。>",
		"電話番号を入力してください。>",
		"職種を入力してください。>",
		"勤続年数を入力してください。>",
		"単価を入力してください。>"
	};
	private String[] data = new String[ 6 ];

	/**
	 * コンストラクタ AddPersonStatus
	 * @param String firstMess
	 * @param String promptMess
	 * @param boolean IsEndStatus
	 * @param PersonList pl
	 */
	AddPersonStatus( String firstMess, String promptMess,
	                 boolean IsEndStatus, PersonList pl ) {
		super( firstMess, promptMess, IsEndStatus );
		this.pl = pl;
	}

	// 最初に出力するメッセージを表示する
	// このクラスでは従業員のデータの入力処理
	// のみを行う
	/** displayFirstMess
	 * @throws Exception
	 */
	public void displayFirstMess() throws Exception {
		// messagesの各文字列を順に表示しながら
		// キーボードから氏名，住所などを得ていく
		for( int idx = 0; idx < messages.length; idx++ ) {
			System.out.print( messages[ idx ] );
			data[ idx ] = inputMessage();
		}

		try {
			int wy = Integer.parseInt( data[ 4 ] );  // 勤続年数
			int pr = Integer.parseInt( data[ 5 ] );  // 単価

			Person new_p = new Person(
			  pl.size(), // 現在のPersonListのレコード数を
	                     // 新しいレコードのIDとする
			  data[ 0 ], data[ 1 ], data[ 2 ], data[ 3 ], 
			  wy, pr, false
			);

			// 新しいレコードを追加
			pl.add( new_p );
			System.out.println( "ID:" + new_p.getID() + "で登録されました。" );
		} catch( NumberFormatException e ) {
			System.out.println( "数値に変換できないデータが入力されています。" );
			System.out.println( "再入力してください。" );
			displayFirstMess();
			return;
		}
	}

	// 次の状態に遷移することを促すためのメッセージの表示
	// このクラスは，初期状態に戻ると決まっているため，何が
	// 入力されても初期状態に遷移するようにしている
	/** getNextStatus
	 * @param String s
	 * @return ConsoleStatus
	 */
	public ConsoleStatus getNextStatus( String s ) {
		return super.getNextStatus( " " );
	}
}
