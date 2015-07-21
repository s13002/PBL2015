/* WorkList.java
 */

import java.util.List;
import java.util.ArrayList;

/* WorkList <- RecordListインタフェースを実装
 */
public class WorkList implements RecordList {

	/**
	 * フィールド
	 */
	private List<Work> list;
	private ClientList c_list;

	/** コンストラクタ WorkList
	 * @param ClientList c_list
	 */
	WorkList( ClientList c_list ) {
		this.list = new ArrayList<Work>();
		this.c_list = c_list;
	}

	/** コンストラクタ WorkList
	 * @param List<Work> al
	 * @param ClientList c_list
	 */
	WorkList( List<Work> al, ClientList c_list ) {
		this.list = al;
		this.c_list = c_list;
	}

	/** size
	 * @return int
	 */
	public int size() {
		return list.size();
	}

	/** add
	 * @param Work w
	 */
	public void add( Work w ) {
		for( int idx = 0; idx < list.size(); idx++ ) {
			Work widx = list.get( idx );
			if( widx.getID() == w.getID() )   // 同じIDのレコードがある場合
				return;                       // 何もせず終了
			else if( widx.getID() > w.getID() ) {
				list.add( idx, w );           // レコードを追加
				return;
			}
		}
		list.add( w );    // リスト末尾にレコードを追加
	}

	/** add
	 * @param String data
	 * @throws Exception
	 */
	public void add( String data ) throws Exception {
		Work w = new Work( data );
		add( w );
	}

	/** getRecord
	 * @param int idx
	 * @return Record
	 */
	public Record getRecord( int idx ) {
		if( idx >= list.size() )
			return null;
		else
			return list.get( idx );
	}

	/** delete
	 * @param int ID
	 * @return boolean
	 */
	public boolean delete( int ID ) {
		Work w;
		if( find( ID ) == -1 )
			return false;
		else {
			w = get( ID );
			w.setEraseFlag( true );
			return true;
		}
	}

	/**
	 * allDisplay
	 */
	public void allDisplay() {
		for( Work w : list ) {
			System.out.println( w.toString() );
		}
	}

	// 顧客名とともにID，稼働開始年月日，稼働終了年月日を出力する
	/**
	 * displayWithClient
	 */
	public void displayWithClient() {
		Client c;

		for( Work w : list ) {
			if( !w.getEraseFlag() ) {
				System.out.print( w.getID() + "\t" );
				System.out.print( w.getStartTime() + "〜" );
				System.out.print( w.getEndTime() + "\t" );

				c = c_list.get( w.getClientID() );
				if( c != null )
					System.out.println( c.getName() );
				else
					System.out.println( "(顧客名が存在しません。)" );
			}
		}
	}

	/** find
	 * @param int ID
	 * @return int
	 */
	public int find( int ID ) {
		// 引数のIDと同じIDをもつレコードの位置を検索
		for( int idx = 0; idx < list.size(); idx++ ) {
			Work widx = list.get( idx );
			if( widx.getID() == ID )
				return idx;
		}

		return -1;
	}

	/** get
	 * @param int ID
	 * @return Work
	 */
	public Work get( int ID ) {
		Work w;

		// 引数のIDと同じIDをもつレコードが存在するならば，
		// そのレコードを返す
		int idx;
		if( (idx = find( ID )) != -1 ) {
			w = list.get( idx );
			// 削除フラグ=falseなら当該レコードを返す
			if( !w.getEraseFlag() )
				return w;
			else
				return null;
		}
		else
			return null;
	}

	/** searchByPersonID
	 * @param int pID
	 * @return WorkList
	 */
	public WorkList searchByPersonID( int pID ) {

		ArrayList<Work> l = new ArrayList<Work>();

		for( int idx = 0; idx < list.size(); idx++ ) {
			Work w = list.get( idx );
			// idx番目のレコードのpersonID(従業員ID)が引数pIDと一致するか
			// どうかを確認する
			if( w.getPersonID() == pID &&
			    !w.getEraseFlag() )
				l.add( w );
		}

		return new WorkList( l, c_list );
	}
}
