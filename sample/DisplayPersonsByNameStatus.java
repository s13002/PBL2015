/* DisplayPersonsByNameStatus.java
 */

/* DisplayPersonsByNameStatus
 */
public class DisplayPersonsByNameStatus extends ConsoleStatus {

	// フィールド
	private String name;
	private PersonList plist;
	private PersonList selectedList;
	private DisplayPersonStatus next;

	/**
	 * コンストラクタ DisplayPersonsByNameStatus
	 * @param String firstMess
	 * @param String promptMess
	 * @param boolean IsEndStatus
	 * @param PersonList plist
	 * @param DisplayPersonStatus next
	 */
	DisplayPersonsByNameStatus( String firstMess, String promptMess,
	                     boolean IsEndStatus,
	                     PersonList plist, DisplayPersonStatus next ) {
		super( firstMess, promptMess, IsEndStatus );
		this.name = "";
		this.plist = plist;
		this.selectedList = null;
		this.next = next;
	}

	// 最初に出力するメッセージを表示する
	/** displayFirstMess
	 * @throws Exception
	 */
	public void displayFirstMess() throws Exception {
		displayList();
		super.displayFirstMess();
	}

	// 検索する氏名を登録する
	/** setName
	 * @param String name
	 */
	public void setName( String name ) {
		this.name = name;
	}

	// 入力された氏名の文字列を氏名に含む従業員のレコードだけを
	// 取り出す処理
	/**
	 * displayList
	 */
	public void displayList() {
		// 入力された氏名に一致または氏名を含む従業員のレコードだけを
		// selectedListに取り出す
		selectedList = plist.searchByName( name );
		// selectedListの件数＝0ならば当該職種をもつ
		// 従業員はいないと表示
		if( selectedList.size() <= 0 )
			System.out.println( "従業員が存在しません。" );
		else
			selectedList.allDisplay();
	}

	// 次の状態に遷移することを促すためのメッセージの表示
	/** getNextStatus
	 * @param String s
	 * @return ConsoleStatus
	 */
	public ConsoleStatus getNextStatus( String s ) {
		// 数値が入力された場合，その数値と同じIDをもつ
		// レコードがselectedListにあるかどうか判定し，
		// あればそれを次の状態DisplayPersonStatusに渡す
		try {
			int i = Integer.parseInt( s );
			Person p = selectedList.get( i );
			if( p == null )
				return this;
			else {
				next.setPersonRecord( p );
				return next;
			}
		} catch( NumberFormatException e ) {
			return super.getNextStatus( s );
		}
	}
}
