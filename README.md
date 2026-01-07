	•	プロジェクト概要（1〜2行）:
	Spring Boot を利用したWebアプリケーション。基本的な「Hello World」の表示と、タスクデータの保存・取得ができる機能を作成。

	•	開発環境（JDK / IDE / ビルドツールのバージョン）：
	Java: JDK 17
	IDE:VS Cord
	Build Tool: Spring Boot 4.0.1 , Gradle
	•	セットアップ手順
	1.パソコン環境の問題により、リポジトリをSpring Intializrからダウンロードし、解凍することで作成。
	2.プロジェクトのルートディレクトリに移動。
	3.コマンドでアプリケーションを起動。

	•	Gradle: gradlew.bat bootRun
	
	•	動作確認手順（/hello と /api/tasks の例）：/
	・hello：gradlew.bat bootRun → curl http://localhost:8080/hello
	・/api/tasks：gradlew.bat bootRun → curl http://localhost:8080/api/tasks (確認) → $ curl -X POST -H "Content-Type: application/json" -d "{\"title\":\"new task\"}" http://localhost:8080/api/tasks

	•	エラーが出た場合の対処（自分が踏んだ内容を1〜3行で記載）：
	まずはエラー文を読み、エラー内容を確認したうえで該当の場所を探した。解決方法が分からない場合は調べたりAIを利用したりして、エラー原因と解決方法の理解に努めた。