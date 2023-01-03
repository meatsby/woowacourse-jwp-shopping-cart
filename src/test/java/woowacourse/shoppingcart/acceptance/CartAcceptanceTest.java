package woowacourse.shoppingcart.acceptance;

import static org.assertj.core.api.Assertions.assertThat;
import static woowacourse.shoppingcart.acceptance.ProductAcceptanceTest.상품_등록되어_있음;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import woowacourse.shoppingcart.dto.CartRequest;
import woowacourse.shoppingcart.dto.CartResponse;
import woowacourse.shoppingcart.dto.product.ProductResponse;

@DisplayName("장바구니 관련 기능")
public class CartAcceptanceTest extends AcceptanceTest {

    private Long productId1;
    private Long productId2;
    private String accessToken;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        회원가입(파리채);
        accessToken = 로그인_후_토큰발급(파리채토큰);

        productId1 = 상품_등록되어_있음("치킨", 10_000, "http://example.com/chicken.jpg", 100);
        productId2 = 상품_등록되어_있음("맥주", 20_000, "http://example.com/beer.jpg", 100);
    }

    @DisplayName("장바구니 아이템 추가")
    @Test
    void addCartItem() {
        final CartRequest cartRequest = new CartRequest(productId1, 1);

        ExtractableResponse<Response> response = 장바구니_아이템_추가_요청(accessToken, cartRequest);

        장바구니_아이템_추가됨(response);
    }

    @DisplayName("장바구니 아이템 목록 조회")
    @Test
    void getCartItems() {
        final CartRequest cartRequest1 = new CartRequest(productId1, 1);
        final CartRequest cartRequest2 = new CartRequest(productId2, 2);
        장바구니_아이템_추가_요청(accessToken, cartRequest1);
        장바구니_아이템_추가_요청(accessToken, cartRequest2);

        ExtractableResponse<Response> response = 장바구니_아이템_목록_조회_요청(accessToken);

        장바구니_아이템_목록_응답됨(response);
        장바구니_아이템_목록_포함됨(response, productId1, productId2);
    }

    @DisplayName("장바구니 상품 수량 변경")
    @Test
    void modifyCartQuantity() {
        final CartRequest cartRequest = new CartRequest(productId1, 1);

        장바구니_아이템_추가_요청(accessToken, cartRequest);

        final CartRequest modifyRequest = new CartRequest(productId1, 5);

        ExtractableResponse<Response> response = 장바구니_아이템_수량_변경_요청(accessToken, modifyRequest);

        장바구니_아이템_목록_응답됨(response);
        장바구니_아이템_목록_포함됨(response, productId1);
    }

    @DisplayName("장바구니 삭제")
    @Test
    void deleteCartItem() {
        final CartRequest cartRequest = new CartRequest(productId1, 1);

        장바구니_아이템_추가_요청(accessToken, cartRequest);

        ExtractableResponse<Response> response = 장바구니_삭제_요청(accessToken, productId1);

        장바구니_삭제됨(response);
        장바구니_아이템_목록_포함됨(response);
    }

    public static ExtractableResponse<Response> 장바구니_아이템_추가_요청(String accessToken, CartRequest cartRequest) {
        return RestAssured
                .given().log().all()
                .auth().oauth2(accessToken)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(cartRequest)
                .when().post("/api/carts/products")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 장바구니_아이템_목록_조회_요청(String accessToken) {
        return RestAssured
                .given().log().all()
                .auth().oauth2(accessToken)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/api/carts")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 장바구니_아이템_수량_변경_요청(String accessToken, CartRequest cartRequest) {
        return RestAssured
                .given().log().all()
                .auth().oauth2(accessToken)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(cartRequest)
                .when().patch("/api/carts/products")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 장바구니_삭제_요청(String accessToken, Long productId) {
        return RestAssured
                .given().log().all()
                .auth().oauth2(accessToken)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().delete("/api/carts/products?productId=" + productId)
                .then().log().all()
                .extract();
    }

    public static void 장바구니_아이템_추가됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    public static Long 장바구니_아이템_추가되어_있음(String userName, CartRequest cartRequest) {
        ExtractableResponse<Response> response = 장바구니_아이템_추가_요청(userName, cartRequest);
        return Long.parseLong(response.header("Location").split("/products/")[1]);
    }

    public static void 장바구니_아이템_목록_응답됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    public static void 장바구니_아이템_목록_포함됨(ExtractableResponse<Response> response, Long... productIds) {
        List<Long> resultProductIds = response.jsonPath().getList(".", CartResponse.class).stream()
                .map(CartResponse::getProductResponse)
                .map(ProductResponse::getId)
                .collect(Collectors.toList());
        assertThat(resultProductIds).contains(productIds);
    }

    public static void 장바구니_삭제됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
