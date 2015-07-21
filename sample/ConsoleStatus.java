/* ConsoleStatus.java
 */

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/* ConsoleStatus
 */
public class ConsoleStatus {
	/**
	 * フィールド
	 */
	// 次に遷移する状態を記録するHashMap
	private HashMap<String, ConsoleStatus> nextStatus;
	// 終了状態であるかどうかを表す変数
	private boolean IsEndStatus;
	// 状態遷移後，最初に出力するメッセージ
	private String firstMess;
	// 次の状態に遷移することを促すためのメッセージ
	private String promptMess;

	/**
	 * コンストラクタ ConsoleStatus
	 * @param String firstMess
	 * @param String promptMess
	 * @param boolean IsEndStatus
	 */
	ConsoleStatus( String firstMess, String promptMess, boolean IsEndStatus ) {
		this.nextStatus = new HashMap<String, ConsoleStatus>();
		this.firstMess = firstMess;
		this.promptMess = promptMess;
		this.IsEndStatus = IsEndStatus;
	}

	// 終了状態かどうかをチェックする
	/** getIsEndStatus
	 * @return boolean
	 */
	public boolean getIsEndStatus() {
		return IsEndStatus;
	}

	// 次の状態をセットする
	/** setNextStatus
	 * @param String s
	 * @param ConsoleStatus c
	 */
	public void setNextStatus( String s, ConsoleStatus c ) {
		nextStatus.put( s, c );
	}

	// 次の状態を得る
	/** getNextStatus
	 * @param String s
	 * @return ConsoleStatus
	 */
	public ConsoleStatus getNextStatus( String s ) {
		// 入力された文字列に対応付けられた次の状態が
		// あるかどうかを判定し，あれば次の状態を返す
		// なければthis(現在の状態)を返す
		ConsoleStatus cs;
		if( (cs = nextStatus.get( s )) != null )
			return cs;
		else
			return this;
	}

	// 最初に出力するメッセージを表示する
	/** displayFirstMess
	 * @throws Exception
	 */
	public void displayFirstMess() throws Exception {
		System.out.println( firstMess );
	}

	// 次の状態に遷移することを促すためのメッセージの表示
	/**
	 * displayPromptMess
	 */
	public void displayPromptMess() {
		System.out.print( promptMess );
	}

	// 操作者からのキー入力を受け付ける
	/** inputMessage
	 * @throws IOException
	 * @return String
	 */
	public String inputMessage() throws IOException {
		String s = null;
		try {

			BufferedReader input
			 = new BufferedReader( new InputStreamReader( System.in ) );
			s = input.readLine();

		} catch( IOException e ) {
			System.out.println( "入力中にエラーが発生しました。" );
			throw e;
		}

		return s;
	}
}
