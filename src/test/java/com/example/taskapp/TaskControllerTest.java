package com.example.taskapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

// 便利なテスト用コマンドのインポート
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc; // テスト用ロボットを用意

    @Test
    void 認証なしアクセスはログインページへリダイレクト() throws Exception {
        // 未ログイン状態で "/tasks" にアクセスしたときのテスト
        mockMvc.perform(get("/tasks"))
               .andExpect(status().is3xxRedirection()) // 300番台（リダイレクト）になること
               .andExpect(redirectedUrlPattern("**/login")); // loginページに飛ばされること
    }

    @Test
    @WithMockUser(username="testuser", roles={"USER"}) // "testuser"としてログインしたふりをする！
    void 認証ありなら一覧ページが表示される() throws Exception {
        // ログイン状態で "/tasks" にアクセスしたときのテスト
        mockMvc.perform(get("/tasks"))
               .andExpect(status().isOk()) // 200 OK（成功）になること
               .andExpect(view().name("tasks/index")); // tasks/index の画面が表示されること
    }
}