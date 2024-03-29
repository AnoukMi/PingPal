package fr.mightycode.cpoo.server;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWebTestClient
class ServerApplicationTests {

  @Autowired
  private MockMvc mvc;
  @Autowired
  private WebTestClient webClient;
  @Autowired
  private HttpSession httpSession;

//  @Test
//  void testSignUpSignInSignOut() throws Exception {
//
//    // Signing up a non-existing account should succeed
//    mvc.perform(post("/user/signup")
//        .contentType(APPLICATION_JSON)
//        .content("""
//          {"login": "test",
//            "password": "test",
//            "remember":false,
//            "icon":1,
//            "firstname":"Admin",
//            "lastname":"Adminadm",
//            "birthday":"2000-05-05",
//            "address":"admin@pingpal"
//          }"""))
//      .andExpect(status().isOk());
//
//    // Signing up an existing account should fail with CONFLICT
//    mvc.perform(post("/user/signup")
//        .contentType(APPLICATION_JSON)
//        .content("""
//          {
//            "login": "test",
//            "password": "test",
//            "remember":false,
//            "icon":1,
//            "firstname":"Admin",
//            "lastname":"Adminadm",
//            "birthday":"2000-05-05",
//            "address":"admin@pingpal"
//          }"""))
//      .andExpect(status().isConflict());
//
//    // Signing in with invalid credentials should fail with UNAUTHORIZED
//    httpSession.invalidate();
//    webClient.post()
//      .uri("/user/signin")
//      .contentType(APPLICATION_JSON)
//      .bodyValue("""
//        {
//          "login": "user",
//          "password": "invalid",
//          "remember": false
//        }""")
//      .exchange()
//      .expectStatus().isUnauthorized();
//
//    // Signing in a fresh account should succeed
//    webClient.post()
//      .uri("/user/signin")
//      .contentType(APPLICATION_JSON)
//      .bodyValue("""
//        {
//          "login": "test",
//          "password": "test",
//          "remember":false
//        }""")
//      .exchange()
//      .expectStatus().isOk();
//
//    // Signing out a signed in account should succeed
//    // FIXME: session cookie is not returned
////    webClient.post()
////      .uri("/user/signout")
////      .exchange()
////      .expectStatus().isOk();
//  }
}
