# kotlin_study

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
