/* UpdateClientStatus.java
 */

import java.io.IOException;

/* UpdateClientStatus
 */
public class UpdateClientStatus extends ConsoleStatus {

	// フィールド
	private ClientList cl;

	private String[] messages = {
		"1.氏名\t\t\t2.住所\n",
		"3.電話番号\n"
	};
	private String data;

	/**
	 * コンストラクタ UpdateClientStatus
	 * @param String firstMess
	 * @param String promptMess
	 * @param boolean IsEndStatus
	 * @param ClientList cl
	 */
	UpdateClientStatus( String firstMess, String promptMess,
	                 boolean IsEndStatus, ClientList cl ) {
		super( firstMess, promptMess, IsEndStatus );
		this.cl = cl;
		this.data = "";
	}

	// 最初に出力するメッセージを表示する
	// このクラスでは従業員のデータの更新処理
	// のみを行う
	/** displayFirstMess
	 * @throws IOException
	 */
	public void displayFirstMess() throws IOException {
		int id, no, num;

		// IDの入力
		System.out.print( "顧客IDを入力してください。\n>" );
		data = inputMessage();
		try {
			id = Integer.parseInt( data ); // 従業員ID
		} catch( NumberFormatException e ) {
			System.out.println( "数値に変換できないデータが入力されています。" );
			System.out.println( "再入力してください。" );
			displayFirstMess();
			return;
		}

		Client c = cl.get( id );
		if( c == null ) {
			System.out.println( "指定のIDの顧客は存在しません。" );
			System.out.println( "再入力してください。" );
			displayFirstMess();
			return;
		}

		// 顧客の情報の出力
		System.out.println( c.toString() );
		
		System.out.println( "\n更新したい項目を入力してください。" );
		// messagesの各文字列を順に表示する
		for( int idx = 0; idx < messages.length; idx++ )
			System.out.print( messages[ idx ] );

		// 更新する項目の番号の入力
		System.out.print( "\n更新する項目の番号を入力してください。\n>" );
		data = inputMessage();

		try {
			no = Integer.parseInt( data ); // 更新する項目の番号

			// 更新する値の入力
			System.out.print( "\n更新後の値を入力してください。\n>" );
			data = inputMessage();

            switch( no ) {
                case 1:
                    c.setName( data );   break;
                case 2:
                    c.setAddress( data );   break;
                case 3:
                    c.setTel( data );   break;
                default:
                    break;
            }
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
