/* ExitStatus.java
 */

/* ExitStatus
 */
public class ExitStatus extends ConsoleStatus {

	/* コンストラクタ ExitStatus
	 * @param String firstMess
	 * @param String promptMess
	 * @param boolean IsEndStatus
	 */
	ExitStatus( String firstMess, String promptMess, boolean IsEndStatus ) {
		super( firstMess, promptMess, IsEndStatus );
	}

	// システムを終了する特別なメッセージ
	/**
	 * displayFirstMess
	 */
	public void displayFirstMess() {
		System.out.println( "システムを終了します。" );
	}
}
