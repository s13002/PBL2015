/* SystemManager.java
 */

/* SystemManager
 */
public class SystemManager {
    /**
     * フィールド
     */
    private PersonList plist;  // 従業員のリスト
    private ClientList clist;  // 顧客のリスト
    private WorkList wlist;    // 稼働のリスト

    private String pfilename = "sample/src/person.csv";
    private String cfilename = "sample/src/client.csv";
    private String wfilename = "sample/src/work.csv";

    private ConsoleStatus sts1, sts2;
    private DisplayPersonStatus sts5, sts5_2;
    private DisplayPersonsByTypeStatus sts4;
    private TypeSelectionStatus sts3;
    private DisplayPersonsByNameStatus sts7;
    private NameSelectionStatus sts6;
    private AddPersonStatus sts8;
    private UpdatePersonStatus sts9;
    private DeletePersonStatus sts10;
    private AddWorkStatus sts11;
    private DeleteWorkStatus sts12;
    private ExitStatus sts13;
    private AddClientStatus sts14; // CI/CU
    private UpdateClientStatus sts15; // CI/CU

    public static void main(String[] args) {
        try {
            SystemManager manager = new SystemManager();

            manager.load();
            manager.run();
            manager.save();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * コンストラクタ SystemManager
     */
    SystemManager() {

        // 従業員，顧客，稼働の各リストを作成
        this.plist = new PersonList();
        this.clist = new ClientList();
        this.wlist = new WorkList(this.clist);

        statusSetting();
    }

    // 状態遷移の設定

    /**
     * statusSetting
     */
    public void statusSetting() {

        // システム起動時の，機能選択の状態
        sts1 = new ConsoleStatus(
                "_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/\n" +
                        "            従業員派遣管理システム\n" +
                        "                メニュー\n" +
                        "  従業員検索(S)\n" +
                        "  従業員管理(JI：追加 JU：更新 JD：削除)\n" +
                        "  顧客管理(CI：追加 CU：更新)\n" +
                        "  稼働状況管理(KI：追加 KD：削除)\n" +
                        "  終了(X)\n" +
                        "_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/\n",
                "どの機能を実行しますか？\n[S,JI,JU,JD,CI,CU,KI,KD,X]>",
                false
        );
        // 起動時から"S"入力時の状態
        sts2 = new ConsoleStatus(
                "検索方法を指定してください。\n" +
                        "N->氏名から検索      T->職種から検索\n" +
                        "E->従業員検索終了(メニューに戻る)",
                "[N,T,E]>",
                false
        );

        // 従業員IDを入力すると情報を表示する状態
        sts5 = new DisplayPersonStatus(
                "",
                "エンターキーを押すと検索結果一覧に戻ります。\n>",
                false,
                wlist
        );
        sts5_2 = new DisplayPersonStatus(
                "",
                "エンターキーを押すと検索結果一覧に戻ります。\n>",
                false,
                wlist
        );

        // 職種から得た従業者リストを表示し，従業員IDを入力する状態
        sts4 = new DisplayPersonsByTypeStatus(
                "",
                "P->前の3件 N->次の3件\n" +
                        "E->検索結果一覧終了（検索条件指定に戻る）[(従業員ID),P,N,E]>",
                false,
                plist,
                sts5
        );

        // 職種名を入力する状態
        sts3 = new TypeSelectionStatus(
                "職種名を入力してください。\n",
                "[(職種名)]>",
                false,
                sts4
        );

        // 氏名から得た従業者リストを表示し，従業員IDを入力する状態
        sts7 = new DisplayPersonsByNameStatus(
                "",
                "P->前の3件 N->次の3件\n" +
                        "E->検索結果一覧終了（検索条件指定に戻る）[(従業員ID),P,N,E]>",
                false,
                plist,
                sts5_2
        );

        // 従業員の氏名を入力する状態
        sts6 = new NameSelectionStatus(
                "氏名を入力してください。\n",
                "[(氏名)]>",
                false,
                sts7
        );

        // 従業員を追加する状態
        sts8 = new AddPersonStatus(
                "",
                "エンターキーを押すとメニューに戻ります。>",
                false,
                plist
        );

        // 従業員の情報を更新する状態
        sts9 = new UpdatePersonStatus(
                "",
                "更新しました。\nエンターキーを押すとメニューに戻ります。>",
                false,
                plist
        );

        // 従業員を削除する状態
        sts10 = new DeletePersonStatus(
                "",
                "この従業員情報を削除しますか？（Y はい  N いいえ）[Y,N]>",
                false,
                plist
        );

        // 稼働を追加する状態
        sts11 = new AddWorkStatus(
                "",
                "エンターキーを押すとメニューに戻ります。>",
                false,
                clist, wlist
        );

        // 稼働を削除する状態
        sts12 = new DeleteWorkStatus(
                "",
                "この稼働情報を削除しますか？（Y はい  N いいえ）[Y,N]>",
                false,
                plist, wlist
        );

        // システムを終了する状態
        sts13 = new ExitStatus(
                "",
                "",
                true
        );

        // 従業員を追加する状態
        sts14 = new AddClientStatus(
                "",
                "エンターキーを押すとメニューに戻ります。>",
                false,
                clist
        );

        // 従業員の情報を更新する状態
        sts15 = new UpdateClientStatus(
                "",
                "更新しました。\nエンターキーを押すとメニューに戻ります。>",
                false,
                clist
        );

        sts1.setNextStatus("S", sts2);
        sts1.setNextStatus("JI", sts8);
        sts1.setNextStatus("JU", sts9);
        sts1.setNextStatus("JD", sts10);
        sts1.setNextStatus("KI", sts11);
        sts1.setNextStatus("KD", sts12);
        sts1.setNextStatus("X", sts13);

        sts1.setNextStatus("CI", sts14); // CI/CU
        sts1.setNextStatus("CU", sts15); // CI/CU

        sts2.setNextStatus("N", sts6);
        sts2.setNextStatus("T", sts3);
        sts2.setNextStatus("E", sts1);

        sts4.setNextStatus("E", sts2);

        sts5.setNextStatus(" ", sts4);
        sts5_2.setNextStatus(" ", sts7);

        sts7.setNextStatus("E", sts2);

        sts8.setNextStatus(" ", sts1);

        sts9.setNextStatus(" ", sts1);

        sts10.setNextStatus(" ", sts1);

        sts11.setNextStatus(" ", sts1);

        sts12.setNextStatus(" ", sts1);

        sts14.setNextStatus(" ", sts1); // CI/CU

        sts15.setNextStatus(" ", sts1); // CI/CU
    }

    // システムの起動

    /**
     * run
     *
     * @throws Exception
     */
    public void run() throws Exception {
        // メインルーチン
        ConsoleStatus sts = sts1;
        String cmd;

        while (!sts.getIsEndStatus()) {
            // 最初に出力するメッセージ
            sts.displayFirstMess();
            // 次の状態に遷移することを促すためのメッセージ
            sts.displayPromptMess();
            // キー入力を受け付ける
            cmd = sts.inputMessage();
            // キー入力されたコマンドによって，
            // 次の状態に遷移する
            sts = sts.getNextStatus(cmd);
        }

        // 終了状態になったら，その旨のメッセージを出力して
        // 終了（保存）処理を行う
        sts.displayFirstMess();
    }

    // マスタファイルの読込み

    /**
     * load
     *
     * @throws Exception
     */
    public void load() throws Exception {
        // 各CSVファイルからレコードを読み取る
        FileLoader pload = new FileLoader(pfilename);
        FileLoader cload = new FileLoader(cfilename);
        FileLoader wload = new FileLoader(wfilename);

        pload.read(plist);
        cload.read(clist);
        wload.read(wlist);
    }

    // マスタファイルの保存

    /**
     * save
     *
     * @throws Exception
     */
    public void save() throws Exception {
        FileSaver psave = new FileSaver(pfilename);
        FileSaver csave = new FileSaver(cfilename);
        FileSaver wsave = new FileSaver(wfilename);

        psave.write(plist);
        csave.write(clist);
        wsave.write(wlist);
    }
}
