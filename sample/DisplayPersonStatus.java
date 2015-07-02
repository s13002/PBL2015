/* DisplayPersonStatus.java
 */

/* DisplayPersonStatus
 */
public class DisplayPersonStatus extends ConsoleStatus {

	// フィールド
	private Person pe;
	private WorkList workList;	// 稼働状況を得るために用いる稼働のリスト

	/**
	 * コンストラクタ DisplayPersonStatus
	 * @param String firstMess
	 * @param String promptMess
	 * @param boolean IsEndStatus
	 * @param WorkList workList
	 */
	DisplayPersonStatus( String firstMess, String promptMess,
	                     boolean IsEndStatus, WorkList workList ) {
		super( firstMess, promptMess, IsEndStatus );
		this.pe = null;
		this.workList = workList;
	}

	/** setPersonRecord
	 * @param Person pe
	 */
	public void setPersonRecord( Person pe ) {
		this.pe = pe;
	}

	/**
	 * displayFirstMess
	 */
	public void displayFirstMess() {
		displayPerson();
	}

	// 選択された従業員のID，氏名などを
	// 表示する処理
	/**
	 * displayPerson
	 */
	public void displayPerson() {
		// 従業員のレコードの内容を出力
		System.out.println( pe.toString() );

		System.out.println( "稼働状況-------------------" );
		// 当該従業員が割り当てられている
		// 稼働（Work）を表示
		WorkList wl = workList.searchByPersonID( pe.getID() );
		wl.allDisplay();
	}

	// 入力内容が何であろうと，
	// 前の状態に戻る
	/** getNextStatus
	 * @param String s
	 * @return ConsoleStatus
	 */
	public ConsoleStatus getNextStatus( String s ) {
		return super.getNextStatus( " " );
	}

}
