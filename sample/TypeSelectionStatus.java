/* TypeSelectionStatus.java
 */

import java.io.IOException;

/* TypeSelectionStatus
 */
public class TypeSelectionStatus extends ConsoleStatus {

	// フィールド
	private DisplayPersonsByTypeStatus next;

	/**
	 * コンストラクタ TypeSelectionStatus
	 * @param String firstMess
	 * @param String promptMess
	 * @param boolean IsEndStatus
	 * @param DisplayPersonsByTypeStatus next
	 */
	TypeSelectionStatus( String firstMess, String promptMess,
	                     boolean IsEndStatus, 
	                     DisplayPersonsByTypeStatus next ) {
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
		next.setWork( mess );

		return mess;
	}

	// このクラスは，次にDisplayPersonsByTypeStatusを呼ぶと
	// 決まっているため，何が入力されても
	// DisplayPersonsByTypeStatusに遷移するようにしている
	/** getNextStatus
	 * @param String s
	 * @return ConsoleStatus
	 */
	public ConsoleStatus getNextStatus( String s ) {
		return next;
	}
}
