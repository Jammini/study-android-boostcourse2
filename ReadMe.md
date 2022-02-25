# Android Cinema

---

![Untitled](https://user-images.githubusercontent.com/59176149/155660786-2a398624-4e9b-4d02-a996-4b2e30e7c5b2.png)

## **# 개요**

---

영화 앱 만들기 토이 프로젝트

- Project1. 상세화면 구성하기
- Project2. 좋아요와 한 줄 평 리스트
- Project3. 화면 전환하기
- Project4. 목록과 바로가기 메뉴 만들기
- Project5. 서버에서 정보 가져오기
- Project6. 정보를 단말기에 저장하기
- Project7. 이미지와 동영상 재생
- Project8. 애니메이션 효과 적용하기

## **# 소개**

---

- 안드로이드 개인 토이프로젝트
- 개발언어 : Android(Java)
- 소스코드 : [Github](https://github.com/Jammini/study-android-boostcourse2) (현재 private 상태)

## **# 주요기능**

---

- [영화 목록 보기](https://www.notion.so/Android-Cinema-18f24062400c466eb226d9b7def2ef0e) - 앱의 메인화면으로, 서버로부터 영화 목록 데이터를 받아 보여줍니다.
- [영화 상세 보기](https://www.notion.so/Android-Cinema-18f24062400c466eb226d9b7def2ef0e) - 서버로부터 영화 상세정보 데이터를 받아 보여줍니다.
- [한줄평 보기](https://www.notion.so/Android-Cinema-18f24062400c466eb226d9b7def2ef0e) - 선택한 영화에 대한 한줄평을 작성할 수 있고, 작성된 한줄평을 모두 볼 수 있습니다.

## **# SDK 버전 및 라이브러리**

---

- minSdkVersion : 16
- targetSdkVersion : 30

```
dependencies {

    implementation 'androidx.appcompat:appcompat:1.2.0'
    implementation 'com.google.android.material:material:1.3.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.3.1'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1'
    implementation 'androidx.navigation:navigation-fragment:2.3.5'
    implementation 'androidx.navigation:navigation-ui:2.3.5'
    implementation 'androidx.recyclerview:recyclerview:1.2.0'
    implementation 'de.hdodenhof:circleimageview:3.1.0'
    compileOnly 'org.projectlombok:lombok:1.18.18'
    annotationProcessor 'org.projectlombok:lombok:1.18.18'
    implementation 'androidx.room:room-runtime:2.3.0'
    annotationProcessor 'androidx.room:room-compiler:2.3.0'
    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.2'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'

    implementation 'com.android.volley:volley:1.2.0'
    implementation 'com.google.code.gson:gson:2.8.6'
    implementation 'com.github.bumptech.glide:glide:4.11.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'

    implementation 'com.github.chrisbanes:PhotoView:2.3.0'
}
```

## **# 개발 기능 설명**

---

### **1. 영화 목록 보기**

|                         First Image                          |                         Second Image                         |                         Third Image                          |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
| ![1994E752-25C0-4641-ACD0-F4203312DF2E](https://user-images.githubusercontent.com/59176149/155660867-fb374b3a-90e7-45e0-82d3-559de7e1cae1.png) | ![8554EE56-B7D3-4C18-9923-84869430C4C0](https://user-images.githubusercontent.com/59176149/155660935-7f8092c5-35db-4e88-b779-f3a2d7ee0656.png) | ![E7C1498D-B49C-4861-9B13-1285EB2A0C6B](https://user-images.githubusercontent.com/59176149/155661121-5fe6f02a-8c06-4da6-8604-dc99510a3d55.png) |

 앱의 메인화면으로, 서버로부터 영화 목록 데이터를 받아 화면에 보여줍니다. **인터넷이 연결되어있지 않은 경우에는 DB로부터 데이터**를 불러옵니다. 액션바의 옵션메뉴를 통해 정렬 기준을 변경할 수 있습니다.

- 웹서버로부터 영화 목록 데이터를 받아 오기전 초기화면으로 1초간 Init화면을 생성하였습니다.
- 목록화면을 만들고 ViewPager를 이용하여 포스터를 좌우 스크롤하여 볼 수 있도록 하였습니다.
- 바로가기 메뉴를 추가하고 위쪽에는 사용자 프로필, 아래쪽에는 메뉴가 보이도록 하였습니다.
- 영화목록으로부터 영화 상세화면으로의 전환은 Fragment 간에 전환됩니다.
- 인터넷이 연결되어 있을 경우, 영화 목록을 DB에 저장하도록 ViewModel 클래스에 메서드를 구현
- 영화 정렬 팝업메뉴를 구현하였고 translate 애니메이션 적용하였습니다.

## 2. 영화 상세보기

|                         First Image                          |                         Second Image                         |                         Third Image                          |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
| ![9C317155-7BC1-4F16-9187-D708CFF70B69](https://user-images.githubusercontent.com/59176149/155661984-3e5072b5-381c-49d8-b8a1-4522fda2b78f.png) | ![A1169953-3C5D-435E-93CE-216D981A6EAD](https://user-images.githubusercontent.com/59176149/155661279-08892c56-5d13-4459-ae12-b43beeb9dc0f.png) | ![1B42CDB6-CB45-484C-A2F9-C81C2D7F9F47](https://user-images.githubusercontent.com/59176149/155661795-71e0b1b4-68e6-4a33-8551-8ba975e35baa.png) |

 서버로 부터 영화 상세정보 데이터를 받아 화면에 보여줍니다. 해당 영화의 이미지, 추천수, 줄거리, 갤러리 등 가장 최근에 작성된 한줄평을 보여줍니다. 

인터넷이 연결되어 있지 않는 경우에는 DB로부터 데이터를 불러옵니다.

- 각 화면은 fragment로 구성하였고 ConstraintLayout와 LinearLayout 등을 이용하였습니다.
- 영화 상세 fragment로 교체되면 인터넷 연결을 확인한 후 서버에 상세정보를 요청하여 DB에 저장합니다.
- 인터넷 연결이 되지 않고 해당 상세화면을 한 번도 사용한 적이 없다면 기본 베이스 화면을 뛰어줍니다.
- 갤러리는 RecyclerView를 이용해 구성하였고 사진을 누르면 Zoom In, Zoom  Out이 가능한 Activity가 띄워지며 동영상을 누르면 유튜브 동영상이 재생됩니다.
- 한줄평 미리보기 화면은 RecyclerView를 이용해 구성하였고 가장 최근의 등록된 리뷰에 대해 2건만 보여주도록 하였습니다.

## 3. 한줄평 보기

|                         First Image                          |                         Second Image                         |                         Third Image                          |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
| ![BB62FB2A-5D83-4E46-AF63-BB530D0883F4](https://user-images.githubusercontent.com/59176149/155661581-2c414521-6f79-4733-8f27-804f30f364be.png) | ![109CE823-F615-4804-B268-8811E6E5E7FC](https://user-images.githubusercontent.com/59176149/155661846-d28746b3-5b9f-42d8-bdd9-227e90e5f0b3.png) | ![EE408D59-0303-4969-B116-1334609EDD44](https://user-images.githubusercontent.com/59176149/155661480-bdb14044-eeba-47c4-b76c-5b72225e8ea9.png) |

서버에 저장된 리뷰 목록을 최근 리뷰 작성순으로 조회할 수 있도록 하였습니다. 인터넷이 연결되어 있지 않을 경우에는 DB로부터 데이터를 불러오고, 리뷰를 남길 수 없습니다.

- RecyclerView를 이용해 리뷰 전체보기를 구성하였습니다.
- startActivityForResult, onActivityResult를 이용하여 한줄평을 작성하였을 경우 서버 요청, 화면 갱신을 수행합니다.
- Timestamp를 이용하여 각각의 리뷰작성된 아이템을 계산하여 1분전에는 '방금전', 분, 시간, 일, 달, 년 단위로 나누어 화면에 나타냈습니다.
- 작성된 리뷰는 날짜, 시간, 초단위로 가장 최근의 작성된 리뷰가 맨 위에 보이도록 내림차순으로 정렬하였습니다.
- RecyclerView 어댑터의 데이터가 효율적으로 변경되도록 개선 필요

## 4. 기타

- ListView 보다 RecyclerView를 많이 사용함.
- 같은 위젯 등을 사용할 때 상속을 이용하면 편리하다
    - 상속받은 것 중 추가, 삭제 등 업데이트 내용이 있다면 잘 동작하는지 체크 필요
- 문자열 리소스의 경우 res/values/strings.xml 에 지정하여 참조하는 것이 좋다. 그렇게 하면 유지 보수 및 다국어 지원에 유리하다.
- HttpURLConnection이 가장 기본적이고 단순한 방법이지만 코드양이 많아지고 불편하기에 Volley라는 라이브러리를 자주 사용함.
- 패키지 구성을 도메인 중심으로 할지, 레이어 중심으로 할지 결정해볼 것.
- Lombok 사용을 하여 코드 간결화
- 단말기내 DB를 다루는 것은 Room 라이브러리를 이용해 편리하게 사용

## 5. 알아두기

- WebView → Native 접근 (카메라, 오디오, 위치 등)
    - [https://coding-food-court.tistory.com/37](https://coding-food-court.tistory.com/37)
- Push 알림
    - [https://firebase.google.com/docs/cloud-messaging/android/client?hl=ko](https://firebase.google.com/docs/cloud-messaging/android/client?hl=ko)
    - [https://game-happy-world.tistory.com/8](https://game-happy-world.tistory.com/8)
- 권한 허용 및 설정
    - [https://systemdata.tistory.com/6?category=1017254](https://systemdata.tistory.com/6?category=1017254)
- 배포할때
    - Build → BuildAPK → debug, keystore 임시서명 배포 X 개발용으로만
    - Generate Siigned (Password, Alias)  분실해서는 안됨.
- Logcat
    - VERBOS
    - DEBUG
    - WARN - catch에 잡히는 로그
    - ERR - 앱이 죽은 로그
    - ASSERT - 치명적인 로그
- 배포하거나 전달할때 Project Clean해서 전달해야함.
- distributeUri 버전 체크
- targetSDKVersion → 매년 11월 마다 바뀌는 정책, 버전 체크
- aar, jar 자바 라이브러리 확장명, SO파일은 C로구성되어 있음
- 보통 빌드가 안되는 경우 [Proguard-rules.pro](http://proguard-rules.pro) (다른 외부와 프로젝트시) 체크
- Terminal → ADB 자주 사용 체크해보기
- 해상도 별로 디자인을 요청한다.
    - mipmap-hdpi
    - mipmap-mdpi
    - mipmap-xhdpi
    - mipmap-xxhdpi
    - mipmap-xxxhdpi
    - drawable
- Firebase
    - Analytics
    - Cloud Messaging → push message
    - Crashlytics
- WebView 통신
  
    ```java
    private WebView wv;
    wv = findViewById(R.id.wv);
    wv.addJavascriptInterface(new javascriptMethod(), "androidTest");
    
    public class javascriptMethod {
            @JavascriptInterface
            public void testA() {
                Toast.makeText(MainActivity.this, "teest", Toast.LENGTH_SHORT).show();
            }
        }
    ```
    
    ```html
    <a href="javascript:window.androidTest.testA();">확인</a>
    ```
    
    ```java
    //2가지 반드시 해야할 설정
            //1. 웹문서가 열릴때 기본적으로 내 WebView가 아니라 새로운 웹뷰를 열어주는 방식을 사용함.
            //그래서 현재의 WebView안에 웹문서가 열리도록 설정
            wv.setWebViewClient(new WebViewClient(){
    
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                }
    
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                }
    
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    return super.shouldOverrideUrlLoading(view, request);
                }
    
                @Nullable
                @Override
                public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                    return super.shouldInterceptRequest(view, request);
                }
    
                @Override
                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(wv.getContext());
                    builder.setMessage("이 사이트의 보안 인증서는 신뢰하는 보안 인증서가 아닙니다. 계속하시겠습니까?");
                    builder.setPositiveButton("계속하기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            handler.proceed();
                        }
                    });
                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            handler.cancel();
                        }
                    });
                    final AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
            //2. alert(), comfirm() 같은 팝업기능의 JS코드가 사용가능하도록하는 코드 필요
            wv.setWebChromeClient(new WebChromeClient() {
    
                @Override
                public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
    
                    return !super.onJsAlert(view, url, "message", result);
                }
    
                @Override
                public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
    
                    mFilePathCallback = filePathCallback;
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
    
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
    
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
                    return true;
                }
            });
    ```
