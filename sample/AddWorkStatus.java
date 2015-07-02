/* AddWorkStatus.java
 */

/* AddWorkStatus
 */
public class AddWorkStatus extends ConsoleStatus {

	// フィールド
	private ClientList cl;
	private WorkList wl;

	private String[] messages = {
		"従業員IDを入力してください。\n>",
		"顧客IDを入力してください。\n>",
		"稼働開始年月日を入力してください。\n>",
		"稼働終了年月日を入力してください。\n>",
		"単価を入力してください。\n>"
	};
	private String[] data = new String[ 5 ];

	/**
	 * コンストラクタ AddWorkStatus
	 * @param String firstMess
	 * @param String promptMess
	 * @param boolean IsEndStatus
	 * @param ClientList cl
	 * @param WorkList wl
	 */
	AddWorkStatus( String firstMess, String promptMess,
	                 boolean IsEndStatus, ClientList cl,
	                 WorkList wl ) {
		super( firstMess, promptMess, IsEndStatus );
		this.cl = cl;
		this.wl = wl;
	}

	// 最初に出力するメッセージを表示する
	// このクラスでは稼働のデータの入力処理
	// のみを行う
	/** displayFirstMess
	 * @throws Exception
	 */
	public void displayFirstMess() throws Exception {
		// messagesの各文字列を順に表示しながら
		// キーボードから氏名，住所などを得ていく
		System.out.print( messages[ 0 ] );
		data[ 0 ] = inputMessage();
		cl.allDisplay();
		System.out.print( messages[ 1 ] );
		data[ 1 ] = inputMessage();
		for( int idx = 2; idx < messages.length; idx++ ) {
			System.out.print( messages[ idx ] );
			data[ idx ] = inputMessage();
		}

		try {
			int pid = Integer.parseInt( data[ 0 ] );  // 従業員ID
			int cid = Integer.parseInt( data[ 1 ] );  // 顧客ID
			int pr = Integer.parseInt( data[ 4 ] );   // 契約単価

			Work new_w = new Work(
			  wl.size(), // 現在のWorkListのレコード数を
			             // 新しいレコードのIDとする
			  pid, cid, data[ 2 ], data[ 3 ], 
			  pr, false
			);

			// 新しいレコードを追加
			wl.add( new_w );
			System.out.println( "ID:" + new_w.getID() + "で登録されました。" );
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
