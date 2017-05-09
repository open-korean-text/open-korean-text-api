## open-korean-text-api
RESTful web service for open-korean-text  

이 API 서비스는 **Heroku** 서버에서 제공됩니다.(Domain: https://open-korean-text.herokuapp.com/)  
현재 정규화(normalization), 토큰화(tokenization), 어근화(stemmin), 어구 추출(phrase extract) 서비스를 제공합니다.

이 서비스는 [오픈소스 한국어 처리기(Open-source Korean Text Processor)][open-korean-text]를 사용하였습니다.

[open-korean-text]:  https://github.com/open-korean-text/open-korean-text

## Deploy
먼저 이 프로젝트의 개발환경을 참고하세요.

IDE | Web framework | Java | Build tool
--- | ------------- | ---- | ----------
Eclipse neon.2 | [Spark framework][Spark framework] | 8 | Maven

[Spark framework]: http://sparkjava.com

**pom.xml** 에 기본으로 Heroku 플러그인이 설정되어있습니다.
Heroku에 배포하실 경우 생성한 앱 이름에 따라 **pom.xml** 파일에서 Heroku 플러그인 설정 부분의 **appName** 값을 수정해야 합니다.  

메이븐의 **Goal** 의 값을 `heroku:deploy` 로 설정하여 배포하며 배포 하기 전 **Heroku toolbelt** 설치 및 로그인을 권장합니다. **Heroku toolbelt** 에 대한 자세한 설명은 [Heroku CLI 문서][Heroku CLI]를 **배포** 에 대한 자세한 설명은 [Deploying Java Applications with the Heroku Maven Plugin 문서][Heroku Deploy Doc]를 참고하세요.  

Heroku가 아닌 다른 배포를 원하시는 경우, 메이븐을 통해 생성된 **jar** 파일을 간단히 실행시켜서 서비스 할 수 있습니다. 이를 위해 다음과 같은 명령을 수행합니다.

```
maven clean package
```

이클립스에서는 **Run configurations** 에서 Maven 빌드 설정을 통해 쉽게 작업이 가능합니다.
빌드가 끝나면 프로젝트의 target 폴더에 jar파일이 생성된 것을 확인할 수 있습니다.  

jar 파일을 배포할 곳에 위치시킨 후 `java -jar {File name}` 을 수행합니다.

[Heroku CLI]: https://devcenter.heroku.com/articles/heroku-cli
[Heroku Deploy Doc]:  https://devcenter.heroku.com/articles/deploying-java-applications-with-the-heroku-maven-plugin

## Access
각 서비스와 사용법은 다음과 같습니다.  
`normalize`, `tokenize`, `stem`, `extractPhrases` 가 각 서비스의 **Action** 이 되며 **Query parameter** 는 `text` 입니다.

서비스 | 사용법
---- | ----
정규화 | https://open-korean-text.herokuapp.com/normalize?text=오픈코리안텍스트
토큰화 | https://open-korean-text.herokuapp.com/tokenize?text=오픈코리안텍스트
어구 추출 | https://open-korean-text.herokuapp.com/extractPhrases?text=오픈코리안텍스트
