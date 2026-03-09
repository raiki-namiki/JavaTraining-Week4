・プロダクト概要（1–3行）：
Spring Bootを利用して開発した、シンプルなタスク管理Webアプリケーションです。
ログイン機能を持ち、タスクの一覧表示、新規登録、編集、削除操作が可能です。

・開発環境（JDK/IDE/ビルドツール）：
言語：Java 17
フレームワーク：Spring Boot 4.0.3
ビルドツール：Gradle
データベース：H2 Database 
テンプレートエンジン： Thymeleaf 

・セットアップ手順（コマンド例明記）：
1.リポジトリをクローンする。
2.ターミナルでプロジェクトのルートディレクトリに移動し、以下のコマンドを実行してアプリを起動する。

・Gradle：gradlew.bat bootRun

・動作確認手順：
http://localhost:8080/tasks にアクセス → Usernameとpasswordを入力


・http://localhost:8080/login → ログイン情報：
Username：testuser
Password：password

・画面：/tasks の操作手順：
新規登録：タスク一覧画面から、「新規タスクを登録する」ボタンでフォーム画面 (`/tasks/new`) に移動し、タイトルを入力して登録。
編集・削除：一覧の各行にある「編集」または「削除」ボタンから操作可能。

・API：curl 例:：
curl -u testuser:password -X GET http://localhost:8080/api/tasks
curl -u testuser:password -X POST http://localhost:8080/api/tasks -H "Content-Type: application/json" -d '{"title": "APIからのテストタスク"}'

・アーキテクチャ図（テキスト図でも可）とパッケージ構成：
com.example.taskapp
 ├── config/         Spring Securityなどの設定クラス
 ├── controller/     画面用とAPI用のコントローラー
 ├── entity/         データベースのテーブルと紐づくドメインモデル
 ├── repository/     データベースの操作(CRUD)を担うインターフェース
 └── service/        ビジネスロジックをカプセル化したサービスクラス

・既知の制約・今後の改善点（3点以上、短文でOK）
アプリを再起動すると、データが消えてしまうため、データの永続化をさせるようにすべきである。
画面がシンプルで最低限のデザインであるため、UIなどを考えながらより使いやすいデザインで作りたい。
1つのアカウントでしかログインできないので、ユーザーが一人一人アカウントを作成し、アプリを利用できるようにすることで、より本格的なアプリになる。