/* NameSelectionStatus.java
 */

import java.io.IOException;

/* NameSelectionStatus
 */
public class NameSelectionStatus extends ConsoleStatus {

	// フィールド
	private DisplayPersonsByNameStatus next;

	/**
	 * コンストラクタ NameSelectionStatus
	 * @param String firstMess
	 * @param String promptMess
	 * @param boolean IsEndStatus
	 * @param DisplayPersonsByNameStatus next
	 */
	NameSelectionStatus( String firstMess, String promptMess,
	                     boolean IsEndStatus, 
	                     DisplayPersonsByNameStatus next ) {
		super( firstMess, promptMess, IsEndStatus );
		this.next = next;
	}

	// 次の状態に遷移することを促すためのメッセージの表示
	/** inputMessage
	 * @throws IOException
	 * @return String
	 */
	public String inputMessage() throws IOException {
		String mess = super.inputMessage();
		next.setName( mess );

		return mess;
	}

	// このクラスは，次にDisplayPersonsByNameStatusを呼ぶと
	// 決まっているため，何が入力されても
	// DisplayPersonsByNameStatusに遷移するようにしている
	/** getNextStatus
	 * @param String s
	 * @return ConsoleStatus
	 */
	public ConsoleStatus getNextStatus( String s ) {
		return next;
	}
}
