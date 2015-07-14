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
	private int next_disp_id = 0;

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
		displayList(" ");
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
	public void displayList(String code) {
		// 入力された氏名に一致または氏名を含む従業員のレコードだけを
		// selectedListに取り出す
		selectedList = plist.searchByName( name );
		// selectedListの件数＝0ならば当該職種をもつ
		// 従業員はいないと表示
		if( selectedList.size() <= 0 )
			System.out.println( "従業員が存在しません。" );
		else{
			if(code.equals(" ") && next_disp_id == 0){
				System.out.println("最初のページを表示");
				int rows = selectedList.size() >= 3 ? 3 : selectedList.size();
				for(int i=0; i<rows; i++){
					System.out.println( selectedList.getRecord(i).toString() );
				}
				next_disp_id = rows;
			}else if(code.equals("N")){
				if(selectedList.size()>next_disp_id){
					System.out.println("次のページを表示");
					int rows = selectedList.size()-next_disp_id >= 3 ? 3 : selectedList.size()-next_disp_id;
					for(int i=next_disp_id; i<next_disp_id+rows; i++){
						System.out.println( selectedList.getRecord(i).toString() );
					}
					next_disp_id += rows;
				}else{
					System.out.println("最後まで表示して頭に戻りました");
					int rows = selectedList.size() >= 3 ? 3 : selectedList.size();
					for(int i=0; i<rows; i++){
						System.out.println( selectedList.getRecord(i).toString() );
					}
					next_disp_id = rows;
				}
			}/*else if(code.equals("P")){
				System.out.println("現在のnext_disp_id: " + next_disp_id);
				if((next_disp_id-6 >= 0){
					System.out.println("前のページを表示");
					next_disp_id  -= 6;
					for(int i=next_disp_id; i<next_disp_id+3; i++){
						System.out.println( selectedList.getRecord(i).toString() );
					}
					next_disp_id += 3;
				}else{
					System.out.println("末尾の３件を表示");
					int  rows =  selectedList.size() >= 3 ? 3 : selectedList.size();
					for(int i=selectedList.size()-rows; i<selectedList.size(); i++){
						System.out.println( selectedList.getRecord(i).toString() );
					}
					next_disp_id = selectedList.size();
				}
			}*/
		}
	}

	// 次の状態に遷移することを促すためのメッセージの表示
	/** getNextStatus
	 * @param String s
	 * @return ConsoleStatus
	 */
	public ConsoleStatus getNextStatus( String s ) {
		if(s.equals("N") || s.equals("P")){
			// N -> 次の３件、P -> 前の３件
			displayList(s);
			return this;
		}else{
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
}
