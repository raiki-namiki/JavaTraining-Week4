目的と機能概要（1〜2段落）：
Spring BootとThymeleafを使用した、タスク管理アプリケーションです。タスクの登録・表示・編集・削除機能が備わっており、例外エラーにも対応しています。

起動手順（JDK/ビルドツール、bootRunなど）：
Java 17, Thymeleaf, Gradle
実行：./gradlew bootRun

画面遷移（URL一覧：/tasks, /tasks/new, /tasks/{id}/edit など）：
/tasks	            GET	    タスク一覧表示（編集・削除ボタンあり）
/tasks/new	        GET	    新規タスク作成フォームの表示
/tasks	            POST	新規タスクの登録処理
/tasks/{id}/edit	GET	    既存タスクの編集フォーム表示
/tasks/{id}	        POST	タスクの更新処理
/tasks/{id}/delete	POST	タスクの削除処理

バリデーション・例外ハンドリングの説明
・バリデーション
@NotBlank：タイトルの入力を必須にする
@Size (Max = 50)：入力される文字数を50字以内にする

・例外ハンドリング
存在しないタスクIDを指定して画面にアクセスした場合、エラーページを表示する（ResponseStatusExceptionからerror/404.htmlを表示）