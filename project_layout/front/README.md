# `front/` 디렉토리

전용 클라이언트가 있는 소프트웨어일 경우 여기에 UI를 포함해 클라이언트용 소스 및 문서를 관리한다.
클라이언트를 관리하는 서브모듈로 이해할 수 있다.

**예제 디렉토리 구조는 Spring Boot 기반을 웹 서비스를 기준으로 설명한다.**

필요시 이 디렉토리(`front`)를 도큐먼트 루트로 웹 서버(Nginx, Apache HTTP 등)를 

- [`front/docs/`](docs/) : 프론트 개발 문서.
만약 동일한 URL에 대해 다양한 결과가 있을 경우, 이곳에 결과 템플릿 링크가 있는 HTML을 만들고, 템플릿에 링크를 추가한다.
그래서 `front/templates/` -&gt; `front/docs` =&gt; `front/templates` 순서로 링크 한다. 
- [`front/templates/`](templates/) : Thymeleaf 템플릿 관리.
  - `front/templates/components` : 자사용 가능한 UI 요소 관리.
  - `front/templates/layout` : 특정 페이지의 HTML 레이아웃 템플릿 관리.
  재사용 가능한 UI 요소는 `components`를 사용하고, 다른 레이아웃에서 사용하지 않는다면 직접 기술한다.
- `front/css`
- `front/js`
- `front/image`