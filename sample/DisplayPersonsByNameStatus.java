/* DisplayPersonsByNameStatus.java
 */

import java.util.stream.IntStream;

/* DisplayPersonsByNameStatus
 */
public class DisplayPersonsByNameStatus extends ConsoleStatus {

	// フィールド
	private String name;
	private PersonList plist;
	private PersonList selectedList;
	private DisplayPersonStatus next;
	private int idx;
	private static final int PAGE = 3;
	private boolean isInitDisp;


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
		displayList("");
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
		// 入力された職種をもつ従業員のレコードだけを
		// selectedListに取り出す
		selectedList = plist.searchByName(name);
		// selectedListの件数＝0ならば当該職種をもつ
		// 従業員はいないと表示
		if (selectedList.size() <= 0 && code.isEmpty()) {
			System.out.println("従業員が存在しません。");
			return;
		}

		if (isInitDisp || selectedList.size() <= 3) {
			IntStream.range(0, selectedList.size())
					.mapToObj(selectedList::getRecord)
					.limit(PAGE)
					.forEach(System.out::println);
			return;
		}

		if (!code.isEmpty()) {
			if (code.equals("P")) {
				if (idx - PAGE == -3) {
					idx = selectedList.size() - PAGE;
				} else if (idx - PAGE >= 0) {
					idx -= PAGE;
				} else {
					IntStream.range(0, idx)
							.mapToObj(selectedList::getRecord)
							.forEach(System.out::println);
					idx = 0;
					return;
				}
			}

			if (code.equals("N")) {
				if (idx + PAGE >= selectedList.size()) {
					idx = 0;
				} else {
					idx += PAGE;
				}
			}

			IntStream.range(idx, selectedList.size())
					.mapToObj(selectedList::getRecord)
					.limit(PAGE)
					.forEach(System.out::println);
		}
	}

	// 次の状態に遷移することを促すためのメッセージの表示
	/** getNextStatus
	 * @param String s
	 * @return ConsoleStatus
	 */
	public ConsoleStatus getNextStatus(String s) {
		isInitDisp = false;
		if ((s.equals("P") || s.equals("N")) && selectedList.size() > 3) {
			displayList(s);
			return this;
		} else if(s.equals("E")) {
			isInitDisp = true;
			this.idx = 0;
			return super.getNextStatus(s);
		} else {
			// 数値が入力された場合，その数値と同じIDをもつ
			// レコードがselectedListにあるかどうか判定し，
			// あればそれを次の状態DisplayPersonStatusに渡す
			try {
				int i = Integer.parseInt(s);
				Person p = selectedList.get(i);
				if (p == null)
					return this;
				else {
					next.setPersonRecord(p);
					return next;
				}
			} catch (NumberFormatException e) {
				return super.getNextStatus(s);
			}
		}
	}}
