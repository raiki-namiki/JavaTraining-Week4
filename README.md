プロジェクト概要:
Spring Bootを使って作成した、タスク（ToDo）を管理するアプリケーションです。データベースにはH2 Databaseを使用しています。

環境構築手順（DB設定を含む）:
Javaのバージョン:Java 17
データベース:H2 Database
設定ファイル；`src/main/resources/application.yml` に記載

実行・確認手順（curl例など）:
./gradlew bootRun → Cntr C で動作終了

・タスクの登録；curl -X POST -H "Content-Type: application/json" -d "{\"title\":\"テストタスク\",\"completed\":false}" http://localhost:8080/tasks

例外ハンドリングの動作例（400/404の例）:
・タイトルを空にして送信：
curl -v -X POST -H "Content-Type: application/json" -d "{\"title\":\"\"}" http://localhost:8080/tasks
・データがなしにして送信：
curl -v http://localhost:8080/tasks/999

苦労した点：H2コンソールが出ない問題が起こり、様々な方法を試みましたが、中々直らず苦戦しました。結果として、application.yml と build.gradle を調整し、ブラウザからデータベースを操作できるように設定したことで表示することができました。