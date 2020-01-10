# kotlin_study
## 학습 정보
### 교재
[오준석의 안드로이드 생존코딩(코틀린편)] (http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791162241196&orderClick=LAG&Kc=)

### 목차
1. 비만도 계산기
2. 스톱워치
3. 나만의 웹 브라우저
4. 수평 측정기
5. 전자액자  
6. 지도와 GPS <--현재
7. 손전등
8. 실로폰
9. TODO list


### 오류- Fatal signal 6 (SIGABRT)

자꾸 
A/libc: Fatal signal 6 (SIGABRT), code -6 (SI_TKILL) in tid 1924 (a.myapplication), pid 1924 (a.myapplication)
해당 오류가 나며 앱이 강제종료 된다..
구글링 결과, breakpoint나 font사용 등의 문제로 인한 오류로 나오는데 나는 breakingpoint도 사용하지 않았고 폰트도 사용하지 않았다..
무슨 문제일까?
원인을 아시는 분은 skekf123@naver.com으로.. 

12-15 22:11:51.122 1924-1924/com.drj.a.myapplication D/OpenGLRenderer: Skia GL Pipeline
12-15 22:11:51.126 1924-1924/com.drj.a.myapplication D/EmergencyMode: [EmergencyManager] android createPackageContext successful
12-15 22:11:51.150 1924-1924/com.drj.a.myapplication D/InputTransport: Input channel constructed: fd=79
12-15 22:11:51.151 1924-1924/com.drj.a.myapplication D/ViewRootImpl@6e12df0[StopWatchActivity]: setView = DecorView@17fdf69[StopWatchActivity] TM=true MM=false
12-15 22:11:51.157 1924-1924/com.drj.a.myapplication V/StudioProfiler: StudioProfilers agent attached.
12-15 22:11:51.183 1924-2011/com.drj.a.myapplication V/StudioProfiler: Acquiring Application for Events
12-15 22:11:51.244 1924-1924/com.drj.a.myapplication A/libc: Fatal signal 6 (SIGABRT), code -6 (SI_TKILL) in tid 1924 (a.myapplication), pid 1924 (a.myapplication)


A: 해결됬다. ide를 껐다키니 정상 작동.
추측하기로는 

```
override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}
```

이 함수의 TODO에 주석을 걸었는데 그게 지원하지 않는 형식인 것 같다.


### sdk version 28 업그레이드 오류
compileSdkVersion 28, targetSdkVersion 28 를 28로 올렸고,
그에 해당하는 컴포넌트들 
1. implementation 'com.android.support:appcompat-v7:28.0.0'
2. implementation 'com.android.support:support-v4:28.0.0'
3. implementation 'com.android.support:design:28.0.0'
을 같이 올렸다.

에러 로그는 다음과 같다.
```
Error:Execution failed for task ':app:processDebugManifest'.
> Manifest merger failed : Attribute application@appComponentFactory value=(android.support.v4.app.CoreComponentFactory) from [com.android.support:support-compat:28.0.0] AndroidManifest.xml:22:18-91
  	is also present at [androidx.core:core:1.0.0] AndroidManifest.xml:22:18-86 value=(androidx.core.app.CoreComponentFactory).
  	Suggestion: add 'tools:replace="android:appComponentFactory"' to <application> element at AndroidManifest.xml:18:5-56:19 to override.
```
구글링하면 gradle properties에
```
android.useAndroidX=true
android.enableJetifier=true
```
을 추가하라고 하여 추가했지만 여전히 같다. 







