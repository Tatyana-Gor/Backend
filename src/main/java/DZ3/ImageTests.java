package DZ3;

import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import static tests.Endpoints.UPLOAD_IMAGE;
import static tests.Endpoints.FAVORITE_IMAGE;
import dto.PostImageResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

public class ImageTests extends BaseTest {
    static String encodedFile;
    String uploadedImageId;
    String imageHash;
    MultiPartSpecification base64MultiPartSpec;
    MultiPartSpecification multiPartSpecWithFile;
    static RequestSpecification requestSpecificationWithAuthAndMultipartImage;
    static RequestSpecification requestSpecificationWithAuthWithBase64;
    PostImageResponse PostImageResponse;

    @BeforeEach
    void beforeTest() {
        byte[] byteArray = getFileContent("src/test/resources/s-l300.jpg");
        encodedFile = Base64.getEncoder().encodeToString(byteArray);
    }

    @Test
    void getNonExistingImageTest() {
        @Test
        void uploadFileTest() {
            uploadedImageId = given()
                    .headers("Authorization", token)
                    .multiPart("image", encodedFile)
                    .formParam("title", "ImageTitle")
                    .expect()
                    .body("success", is(true))
                    .body("data.id", is(notNullValue()))
                    .when()
                    .post("https://api.imgur.com/3/image")
            uploadedImageId = given(requestSpecificationWithAuthWithBase64,positiveResponseSpecification)
                    .post(UPLOAD_IMAGE)
                    .prettyPeek()
                    .then()
                    .extract();
    }
        @Test
        void uploadFileImageTest() {
            uploadedImageId = given()
                    .headers("Authorization", token)
                    .multiPart("image", new File("src/test/resources/s-l300.jpg"))
            PostImageResponse = given(requestSpecificationWithAuthAndMultipartImage)
                    .expect()
                    .statusCode(200)
                    .when()
                    .post("https://api.imgur.com/3/upload")
                    .post(UPLOAD_IMAGE)
                    .prettyPeek()
                    .then()
                    .extract()
                    .body()
                    .as(PostImageResponse.class);

            uploadedImageId = PostImageResponse.getData().getDeletehash();
        }

        @Test
        void uploadWithMultiPart() {
            uploadedImageId = given(requestSpecificationWithAuthAndMultipartImage)
                    .post("https://api.imgur.com/3/image")
                    .prettyPeek()
                    .then()
                    .extract()
                    .response()
                    .jsonPath()
                    .getString("data.deletehash");
        }

        @Test
        void uploadJPEGFormatTest() {
            String encodedFile;
            byte[] byteArray = getFileContent("src/test/resources/jake.jpeg");
            encodedFile = Base64.getEncoder().encodeToString(byteArray);
            uploadedImageId = given()
                    .headers("Authorization", token)
                    .multiPart("image", encodedFile)
                    .expect()
                    .body("success", is(true))
                    .body("data.id", is(notNullValue()))
                    .statusCode(200)
                    .when()
                    .post("https://api.imgur.com/3/image")
            beforeTest("src/test/resources/jake.jpeg", "jpeg");
            uploadedImageId = given(requestSpecificationWithAuthWithBase64, positiveResponseSpecification)
                    .post(UPLOAD_IMAGE)
                    .prettyPeek()
                    .then()
                    .extract();
            @Test
            void uploadGIFFormatTest() {
                String encodedFile;
                byte[] byteArray = getFileContent("src/test/resources/adventure-time-jake.gif");
                encodedFile = Base64.getEncoder().encodeToString(byteArray);
                uploadedImageId = given()
                        .headers("Authorization", token)
                        .multiPart("image", encodedFile)
                        .expect()
                        .body("success", is(true))
                        .body("data.id", is(notNullValue()))
                        .body("data.type", equalTo("image/gif"))
                        .when()
                        .post("https://api.imgur.com/3/image")
                beforeTest("src/test/resources/adventure-time-jake.gif", "gif");
                uploadedImageId = given(requestSpecificationWithAuthWithBase64, positiveResponseSpecification)
                        .post(UPLOAD_IMAGE)
                        .prettyPeek()
                        .then()
                        .extract();
                @Test
                void uploadPNGFormatTest() {
                    String encodedFile;
                    byte[] byteArray = getFileContent("src/test/resources/jake.png");
                    encodedFile = Base64.getEncoder().encodeToString(byteArray);
                    uploadedImageId = given()
                            .headers("Authorization", token)
                            .multiPart("image", encodedFile)
                            .expect()
                            .body("success", is(true))
                            .body("data.id", is(notNullValue()))
                            .body("data.type", equalTo("image/png"))
                            .when()
                            .post("https://api.imgur.com/3/image")
                    beforeTest("src/test/resources/jake.png", "png");
                    uploadedImageId = given(requestSpecificationWithAuthWithBase64, positiveResponseSpecification)
                            .post(UPLOAD_IMAGE)
                            .prettyPeek()
                            .then()
                            .extract();
                    @Test
                    void uploadFile1x1pixelTest() {
                        String encodedFile;
                        byte[] byteArray = getFileContent("src/test/resources/1x1-0000ff7f.png");
                        encodedFile = Base64.getEncoder().encodeToString(byteArray);
                        uploadedImageId = given()
                                .headers("Authorization", token)
                                .multiPart("image", encodedFile)
                                .expect()
                                .body("success", is(true))
                                .body("data.id", is(notNullValue()))
                                .body("data.type", equalTo("image/jpeg"))
                                .when()
                                .post("https://api.imgur.com/3/image")
                        beforeTest("src/test/resources/1x1-0000ff7f.png", "png");
                        uploadedImageId = given(requestSpecificationWithAuthWithBase64, positiveResponseSpecification)
                                .post(UPLOAD_IMAGE)
                                .prettyPeek()
                                .then()
                                .extract();
                        @Test
                        void uploadBMPFormatTest() {
                            String encodedFile;
                            byte[] byteArray = getFileContent("src/test/resources/jake.bmp");
                            encodedFile = Base64.getEncoder().encodeToString(byteArray);
                            uploadedImageId = given()
                                    .headers("Authorization", token)
                                    .multiPart("image", encodedFile)
                                    .expect()
                                    .body("success", is(true))
                                    .body("data.id", is(notNullValue()))
                                    .body("data.type", equalTo("image/png")) //Imgur transforms .bmp to .png
                                    .when()
                                    .post("https://api.imgur.com/3/image")
                            beforeTest("src/test/resources/jake.bmp", "bmp");
                            uploadedImageId = given(requestSpecificationWithAuthWithBase64, positiveResponseSpecification)
                                    .post(UPLOAD_IMAGE)
                                    .prettyPeek()
                                    .then()
                                    .extract();
                        }
                        @Test
                        void uploadAndFavoriteFileTest() {
                            String encodedFile;
                            byte[] byteArray = getFileContent("src/test/resources/jake.jpeg");
                            encodedFile = Base64.getEncoder().encodeToString(byteArray);
                            String imageHash = given()
                                    .headers("Authorization", token)
                                    .multiPart("image", encodedFile)
                                    .expect()
                                    .body("success", is(true))
                                    .body("data.id", is(notNullValue()))
                                    .when()
                                    .post("https://api.imgur.com/3/image")

                            beforeTest("src/test/resources/jake.jpeg", "jpeg");
                            imageHash = given(requestSpecificationWithAuthWithBase64, positiveResponseSpecification)
                                    .post(UPLOAD_IMAGE)
                                    .prettyPeek()
                                    .then()
                                    .extract()
                                    .response()
                                    .jsonPath()
                                    .getString("data.id");

                            given()
                                    .headers("Authorization", token)
                                    .when()
                                    .post("https://api.imgur.com/3/image/{imageHash}/favorite", imageHash)
                                    .prettyPeek()
                                    .then()
                                    .statusCode(200);
                            given(requestSpecificationWithAuthWithBase64, positiveResponseSpecification)
                                    .post(FAVORITE_IMAGE, imageHash);
                        }

                        @AfterEach
                        void tearDown() {
                            given()
                                    .then()
                                    .statusCode(200);
                        }
                        private byte[] getFileContent(String PATH_TO_IMAGE) {

                            void beforeTest(String PATH_TO_IMAGE, String TYPE_OF_FILE) {

                                byte[] byteArray = getFileContent(PATH_TO_IMAGE);
                                encodedFile = Base64.getEncoder().encodeToString(byteArray);
                                base64MultiPartSpec = new MultiPartSpecBuilder(encodedFile)
                                        .controlName("image")
                                        .build();

                                multiPartSpecWithFile = new MultiPartSpecBuilder(new File(PATH_TO_IMAGE))
                                        .controlName("image")
                                        .build();

                                requestSpecificationWithAuthAndMultipartImage = new RequestSpecBuilder()
                                        .addHeader("Authorization", token)
                                        .addFormParam("title", "Picture")
                                        .addFormParam("type", TYPE_OF_FILE)
                                        .addMultiPart(multiPartSpecWithFile)
                                        .build();

                                requestSpecificationWithAuthWithBase64 = new RequestSpecBuilder()
                                        .addHeader("Authorization", token)
                                        .addMultiPart(base64MultiPartSpec)
                                        .build();
                            }

                            private byte[] getFileContent(String PATH_TO_IMAGE) {
                                byte[] byteArray = new byte[0];
                                try {
                                    byteArray = FileUtils.readFileToByteArray(new File(PATH_TO_IMAGE));
                                }
                                return byteArray;
                            }
                        }

                    }