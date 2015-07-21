/* PersonList.java
 */

import java.util.List;
import java.util.ArrayList;

/* PersonList <- RecordListインタフェースを実装
 */
public class PersonList implements RecordList {

	/**
	 * フィールド
	 */
	private List<Person> list;

	/**
	 * コンストラクタ PersonList
	 */
	PersonList() {
		this.list = new ArrayList<Person>();
	}

	/** コンストラクタ PersonList
	 * @param List<Person> al
	 */
	PersonList( List<Person> al ) {
		this.list = al;
	}

	/** size
	 * @return int
	 */
	public int size() {
		return list.size();
	}

	/** add
	 * @param Person p
	 */
	public void add( Person p ) {
		for( int idx = 0; idx < list.size(); idx++ ) {
			Person pidx = list.get( idx );
			if( pidx.getID() == p.getID() )   // 同じIDのレコードがある場合
				return;                       // 何もせず終了
			else if( pidx.getID() > p.getID() ) {
				list.add( idx, p );           // レコードを追加
				return;
			}
		}
		list.add( p );    // リスト末尾にレコードを追加
	}

	/** add
	 * @param String data
	 * @throws Exception
	 */
	public void add( String data ) throws Exception {
		Person p = new Person( data );
		add( p );
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
		int idx;
		Person p;
		if( ( idx = find( ID ) ) == -1 )
			return false;
		else {
			p = get( idx );
			p.setEraseFlag( true );
			return true;
		}
	}

	/**
	 * allDisplay
	 */
	public void allDisplay() {
		for( Person p : list ) {
			System.out.println( p.toString() );
		}
	}

	/** find
	 * @param int ID
	 * @return int
	 */
	public int find( int ID ) {
		// 引数のIDと同じIDをもつレコードの位置を検索
		for( int idx = 0; idx < list.size(); idx++ ) {
			Person pidx = list.get( idx );
			if( pidx.getID() == ID )
				return idx;
		}

		return -1;
	}

	/** get
	 * @param int ID
	 * @return Person
	 */
	public Person get( int ID ) {
		Person p;

		// 引数のIDと同じIDをもつレコードが存在するならば，
		// そのレコードを返す
		int idx;
		if( (idx = find( ID )) != -1 ) {
			p = list.get( idx );
			// 削除フラグ＝falseなら当該レコードを返す
			if( !p.getEraseFlag() )
				return p;
			else
				return null;
		}
		else
			return null;
	}

	/** searchByName
	 * @param String name
	 * @return PersonList
	 */
	public PersonList searchByName( String name ) {

		ArrayList<Person> l = new ArrayList<Person>();

		for( int idx = 0; idx < list.size(); idx++ ) {
			Person p = list.get( idx );
			// idx番目のレコードのnameに引数nameが含まれるか
			// どうかを確認する
			if( p.getName().indexOf( name ) != -1 &&
			    !p.getEraseFlag() )
				l.add( p );
		}

		return new PersonList( l );
	}

	/** searchByTypes
	 * @param String type
	 * @return PersonList
	 */
	public PersonList searchByTypes( String type ) {

		ArrayList<Person> l = new ArrayList<Person>();

		for( int idx = 0; idx < list.size(); idx++ ) {
			Person pidx = list.get( idx );
			// idx番目のレコードの職種typeが引数typeと一致するか
			// どうかを確認する
			if( pidx.getType().equals( type ) &&
			    !pidx.getEraseFlag() )
				l.add( pidx );
		}

		return new PersonList( l );
	}
}
