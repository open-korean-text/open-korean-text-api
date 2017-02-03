## open-korean-text-api
RESTful web service for open-korean-text  

이 API 서비스는 **Heroku** 서버에서 제공됩니다.(Domain: https://open-korean-text.herokuapp.com/)  
현재 정규화(normalization), 토큰화(tokenization), 어근화(stemmin), 어구 추출(phrase extract) 서비스를 제공합니다.

이 서비스는 [오픈소스 한국어 처리기(Open-source Korean Text Processor)][open-korean-text]를 사용하였습니다.

[open-korean-text]:  https://github.com/open-korean-text/open-korean-text

## Access Information
각 서비스와 사용법은 다음과 같습니다.  
`normalize`, `tokenize`, `stem`, `extractPhrases` 가 각 서비스의 **Action** 이 되며 **Query parameter** 는 `text` 입니다.

서비스 | 사용법
---- | ----
정규화 | https://open-korean-text.herokuapp.com/normalize?text=오픈코리안텍스트
토큰화 | https://open-korean-text.herokuapp.com/tokenize?text=오픈코리안텍스트
어근화 | https://open-korean-text.herokuapp.com/stem?text=오픈코리안텍스트
어구 추출 | https://open-korean-text.herokuapp.com/extractPhrases?text=오픈코리안텍스트
