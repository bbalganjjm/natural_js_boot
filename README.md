Natural-JS Boot
===
Natural-JS Boot는 [Natural-JS](https://bbalganjjm.github.io/natural_js)(Natural-TEMPLATE)와 Spring Boot 로 구성된 MSA(Micro Service Architecture) 기반 웹 개발을 위한 최고의 Start-Up 키트 입니다.
Natural-JS Boot는 웹 어플리케이션 아키텍처 프레임워크와 더불어 개인정보보호, 웹 취약점 보완, 파일 관리, 엑셀 업/다운로드 등의 공통 기능을 제공합니다.

## 설치

[eclipse]: https://www.eclipse.org
[eclipse-download]: https://www.eclipse.org/downloads/eclipse-packages/
[jdk]: http://www.oracle.com/technetwork/java/javase/downloads/index.html

[img-0]: https://bbalganjjm.github.io/natural_js/images/gtst/gtst2000/0.png
[img-1]: https://bbalganjjm.github.io/natural_js/images/gtst/gtst2000/1.png
[img-2]: https://bbalganjjm.github.io/natural_js/images/gtst/gtst2000/2.png
[img-3]: https://bbalganjjm.github.io/natural_js/images/gtst/gtst2000/3.png
[img-4]: https://bbalganjjm.github.io/natural_js/images/gtst/gtst2000/4.png
[img-5]: https://bbalganjjm.github.io/natural_js/images/gtst/gtst2000/5.png
[img-6]: https://bbalganjjm.github.io/natural_js/images/gtst/gtst2000/6.png
[img-7]: https://bbalganjjm.github.io/natural_js/images/gtst/gtst2000/7.png
[img-8]: https://bbalganjjm.github.io/natural_js/images/gtst/gtst2000/8.png
[img-9]: https://bbalganjjm.github.io/natural_js/images/gtst/gtst2000/9.png

### 1. 이클립스 설치
[이클립스][eclipse] 사이트에서 Eclipse IDE for Java EE Developers를 [다운로드][eclipse-download]해서 압축을 해제하고 eclipse.exe 파일을 실행합니다.
>이클립스를 구동하기 위해서는 [Java SE Development Kit][jdk]이 설치되어 있어야 합니다.

이클립스 실행이 완료되면 `Spring Boot` 개발 지원을 위해 `Spring Tools` 플러그인을 다음과 같이 설치합니다.

`Help` > `Eclipse Marketplace` 메뉴를 클릭하고 Find 입력 항목에  `sts`로 검색하면
다음 그림과 같이 `Spring Tools` 플러그인이 조회됩니다. 여기에서 `install` 버튼을 클릭하면 설치가 진행됩니다.

![Spring Tools 설치][img-0]

설치가 완료되었으면 이클립스를 재시작합니다.

### 2. 프로젝트 내려받기
이클립스 실행이 완료되었으면 이클립스에서 다음 순서대로 실행합니다.

먼저 아래 URL을 선택 후 복사(Ctrl + C)합니다. 복사만 해 두면 됩니다.
```md
https://github.com/bbalganjjm/natural_js.git
```

2.1. 좌측 `Package Explorer`에서 마우스 오른 버튼을 클릭하거나 `File` 메뉴에서 `Import`를 선택합니다.

![Import 메뉴 선택][img-1]

2.2. `Import` 대화상자에서 `Git > Projets from Git`을 선택하고 `Next` 버튼을 클릭합니다.

![Projets from Git 선택][img-2]

2.3. Clone URI를 선택하고 `Next` 버튼을 클릭합니다.

![img-3][]

2.4. Natural-JS의 Source Git Repository 접속 정보를 입력하는 화면입니다. 처음에 복사해 둔 URL에 의해 값들이 자동으로 입력되어 있을 겁니다. 입력이 되어 있지 않으면
```md
https://github.com/bbalganjjm/natural_js.git
```
를 직접 입력합니다.
입력 항목 중 `User`와 `Password`는 Github 로그인 정보를 입력하면 됩니다. 인증 정보를 저장하려면 `Store in Secure Store`를 체크하고 `Finish` 버튼을 클릭합니다.

![img-4][]

2.5. Natural-JS Source Git Repository의 브랜치 목록에서 natural-js-spring-boot 브랜치 만 체크한 후 `Finish` 버튼을 클릭합니다.

![img-5][]

2.6. `Directory` 입력 항목에 소스코드를 저장할 대상 디렉토리를 지정하고 `Finish` 버튼을 클릭합니다.

![img-6][]

2.7. `Wizard for project import` 섹션에서 `import as general project` 라디오 버튼을 선택 후 `Finish` 버튼을 클릭하고 소스코드를 다운로드하여 프로젝트가 생성될 때까지 기다립니다.

![img-7][]

2.8. 프로젝트가 생성되면 생성된 프로젝트명을 마우스 오른쪽 버튼으로 클릭한 후 `Configure` > `Convert to Maven Project` 메뉴를 선택하여 Maven Project로 만들어 줍니다.

![img-8][]

Maven 프로젝트로 전환이 완료될 때까지 기다립니다.

2.9. Maven 프로젝트로 전환이 완료되었으면 프로젝트명을 마우스 오른쪽 버튼으로 클릭한 후 `Run As` > `Spring Boot App` 메뉴를 선택하여 프로그램을 구동합니다.

![img-9][]

2.10. 프로그램 구동이 완료되면 웹 브라우저를 열어 다음 주소를 입력합니다.
```md
http://localhost:8080/index.html
```
페이지가 정상적으로 표시되면 설치가 완료된 것입니다.



개발 가이드
===

목차
===
* [프로젝트 구성](#프로젝트-구성)
* [**Front-End 개발**](#front-end-개발)
    * [UI 파일 별 기본 코드 작성 규칙](#ui-파일-별-기본-코드-작성-규칙)

    * [N.cont(Controller Object) 작성 규칙](#ncontcontroller-object-작성-규칙)

        * [1. "p."으로 시작(UI 컴포넌트 초기화)](#1-p으로-시작ui-컴포넌트-초기화)
            * [1.1. N.select - 공통코드 조회 시](#11-nselect---공통코드-조회-시)
            * [1.2. N.select - 일반 목록 데이터를 선택 요소(select, radio, checkbox)에 바인딩 시](#12-nselect---일반-목록-데이터를-선택-요소select-radio-checkbox에-바인딩-시)
            * [1.3. N.form](#13-nform)
            * [1.4. 기타 모든 컴포넌트](#14-기타-모든-컴포넌트)
            * [1.5. 엑셀 데이터 바인딩](#15-엑셀-데이터-바인딩)
            * [1.6. 파일관리 공통 팝업](#16-파일관리-공통-팝업)

        * [2. "c."으로 시작(N.comm(커뮤니케이터) 정의)](#2-c으로-시작ncomm커뮤니케이터-정의)
            * [2.1. 엑셀 다운로드](#21-엑셀-다운로드)
            * [2.2. 엑셀 대용량(Streaming) 다운로드](#22-엑셀-대용량streaming-다운로드)

        * [3. "e."으로 시작(이벤트 바인딩)](#3-e으로-시작이벤트-바인딩)

        * [4. 기타 유틸리티](#4-기타-유틸리티)
            * [콘솔 로그 및 에러 처리](#콘솔-로그-및-에러-처리)
            * [APP.comm.utils.del](#appcommutilsdel)
            * [APP.comm.utils.save](#appcommutilssave)
            * [APP.comm.utils.selectNBind](#appcommutilsselectnbind)
            * [APP.comm.utils.createFileSummaryList](#appcommutilscreatefilesummarylist)
            * [APP.comm.utils.getFileSummaryList](#appcommutilsgetfilesummarylist)
            * [APP.comm.utils.excelDownload](#appcommutilsexceldownload)

* [**Back-End 개발**](#back-end-개발)

    * [1. 네이밍(Naming)](#1-네이밍naming)
        * [1.1. 패키지 구조](#11-패키지-구조)
        * [1.2. @RequestMapping](#12-requestmapping)

    * [2. Controller 개발](#2-controller-개발)

    * [3. ServiceImpl(Service) 개발](#3-serviceimplservice-개발)

    * [4. MyBatis Mapper XML 개발](#4-mybatis-mapper-xml-개발)
        * [4.1. DB 페이징](#41-db-페이징)

    * [5. Mapper Interface 개발](#5-mapper-interface-개발)
    * [6. 기타](#6-기타)
        * [6.1. 예외처리(Exception)](#61-예외처리exception)
        * [6.2. 마스킹](#62-마스킹)
        * [6.3. 최대 조회건수 제한](#63-최대-조회건수-제한)
        * [6.4. XSS 공격 차단](#64-xss-공격-차단)


# 프로젝트 구성

Natural-Js Boot의 기술 요소들은 다음과 같이 구성되어 있습니다.
* **Front-End**
     * 기반기술 : HTML5, CSS3, Javascript(ECMA4+), jQuery v1.12.4
     * 프레임워크 : Natural-JS(Natural-TEMPLATE)
     * 지원브라우저 : Internet Explorer 11, Chrome 최신버전, Opera 최신버전, Firefox 최신버전, Eage 최신버전

* **Back-End**
     * 기반기술 : JAVA 1.8
     * 프레임워크 : eGovFrame(Spring 4.2.4) v3.7
     * DB : HSQLDB(Sample DB)


# Front-End 개발
### Natrual-JS의 기본적인 사용법과 컴포넌트의 옵션은 [Natrual-JS 홈페이지의 API/DEMO](https://bbalganjjm.github.io/natural_js)를 참고 바랍니다.


## UI 파일 별 기본 코드 작성 규칙

각각의 html 파일은 다음과 같이 구성됩니다.

```javascript
<style>
    .page-id {
        /* View(CSS) - 퍼블리셔가 작성, 생략 가능하고 이 파일의 View 에만 스타일을 적용하고 싶을 때만 추가. */
        /* CSS 셀렉터를 선언할 때는 반드시 .page-id #target { } 처럼 .page-id를 맨 앞에 적어 주세요. 안그러면 다른 페이지에도 영향을 미칩니다. */
    }
</style>

<article class="page-id">
    <!-- View - 퍼블리셔가 작성 -->
    <!-- article 태그에 class 속성으로 page-id를 지정합니다. -->
</article>

<script type="text/javascript">
    (function() {

        // Controller - 업무 개발자가 작성
        // N.cont 함수를 실행 시킬 때 N의 인자로 View의 class 속성으로 정의 한 "page-id" 값을 넣어 줍니다.
        var cont = N(".page-id").cont({
            init : function(view, request) {
                // 페이지 로딩 후 init 함수가 자동으로 실행됩니다.
            }
        });

    })();
</script>
```

## N.cont(Controller Object) 작성 규칙
N.cont 함수의 인자인 컨트롤러 객체는 미리 정의된 속성명 룰들이 있습니다. 미리 정의된 속성명으로 객체 변수명을 선언하고 객체나 함수등을할당하면 AOP에 의해 N.cont 객체의 init 함수가 실행되기 전에 정의한 작업들을 처리해 줍니다.

### 1. "p."으로 시작(UI 컴포넌트 초기화)
Natural-UI의 컴포넌트 들을 자동으로 초기 화해 줍니다.
컴포넌트 초기화 속성명은 다음과 같이 조합하여 사용할 수 있습니다.

```
"p.{컴포넌트명}.{요소ID}" : { 컴포넌트 옵션 }
```

초기화가 완료되면 `p.{컴포넌트명}.{요소ID}` 속성 값으로 지정한 컴포넌트 옵션 객체는 생성된 컴포넌트의 인스턴스로 바뀝니다.

> 공통코드등을 처리하는 `p.select.{id}` 선언(N.select 컴포넌트)은 해당 view 페이지 안에 해당 id로 정의된 모든 요소들에 같은 데이터를 바인딩 한 후 Array 객체 타입으로 인스턴스들을 담아 줍니다. 생성된 N.select 인스턴스를 직접 사용할 때는 `cont["p.select.{id}"][index]`와 같이 인스턴스의 `index를 지정`해 줘야 합니다.

> 컴포넌트 초기화 옵션은 `cont["p.{컴포넌트명}.{요소ID}"].options`로 접근할 수 있으나 옵션의 직접 사용은 권장하지 않습니다. 컴포넌트의 안정성을 보장 받으려면 컴포넌트의 인스턴스에서 제공하는 메서드를 통해 기능을 사용 바랍니다.

```javascript
...
var cont = N(".page-id").cont({
    "p.select.id" : {
        // 컴포넌트 옵션
    },
    init : function(view, request) {
        N.log(cont["p.select.id"].val()); // 컴포넌트 인스턴스 사용.
    }
});
...
```

컴포넌트의 context 옵션은 {id}로 지정한 요소가 자동으로할당 되나 id 아닌 class 셀렉터등의 다른 셀렉터로 직접 지정하려면 컴포넌트 context 옵션을 선언하면 됩니다.

```javascript
...
"p.form.detail" : {
    context : ".detail",
    revert : true,
    autoUnbind : true
},
...
```

위와같이 context 옵션을 선언하면 내부에서는 `N(".detail", view)`와 같이 view 안에서 찾아주는 구문을 자동으로 생성해 줍니다.

>**N.tab과 N.popup 컴포넌트의 `onOpen 옵션의 함수명 문자열은 반드시 onOpen으로 시작("onOpen", "onOpenABC" 등)`해야 합니다. 그렇지 않으면 AOP에 의해 init이 지연 실행되기 때문에 init 보다 onOpen이 먼저 실행되어 자동으로 초기화된 컴포넌트 인스턴스들을 참조하지 못할 수 있습니다.**

>**N.tab 컴포넌트의 onActive 옵션은 주의해서 사용 바랍니다. init 지연 실행에 대한 대책이 아직 나오지 않아 onActive가 init 보다 먼저 실행됩니다. 안정적인 비슷한 기능을 원한다면 onOpen을 사용해 주세요.**

컴포넌트 옵션은 Natural-UI의 컴포넌트별 기본 옵션 외에 해당 컴포넌트의 용도를 지정하거나 초기화 후 바로 실행할 함수 등을 지정할 수 있는 옵션이 더 추가되어 있습니다.
N.cont의 컴포넌트 별 추가 옵션들은 다음과 같습니다.

### 1.1. N.select - 공통코드 조회 시
| 속성명 | 옵션명 | 변수타입 | 필수 여부 | 기능 | 설명 |
| :--: | :--: | :--: | :--: | :--: | -- |
| p.select.{id} | - | - | - | - | N.select 컴포넌트를 초기화 한다 |
| - | code | string | O | 공통코드 분류코드 | 초기화 한 N.select 컴포넌트에 입력한 공통코드를 바인딩 한다. |
| - | filter | function | | 데이터 필터 | 조회한 데이터를 가공하여 바인딩 한다. |
| - | selected | string | | 기본 선택 코드 값 | 데이터를 바인딩 한 후 기본으로 선택할 값. |
>p.select.{id} : [ "code", filterFunction ] 처럼 Array 타입으로도 간단하게 선언할 수 있습니다.. filter가 필요 없으면 [ "code" ] 만 선언해도 됩니다.

### 1.2. N.select - 일반 목록 데이터를 선택 요소(select, radio, checkbox)에 바인딩 시
| 속성명 | 옵션명 | 변수타입 | 필수 여부 | 기능 | 설명 |
| :--: | :--: | :--: | :--: | :--: | -- |
| p.select.{id} | - | - | - | - | N.select 컴포넌트를 초기화 한다 |
| - | comm | string | | 목록을 조회할 N.comm(Communicator) | N.cont 오브젝트로 선언한 "c.{actionName}"(N.comm 참고)을 입력한다. |
| - | data | array[object] | | comm 옵션을 지정하지 않고 data 옵션으로 [{}, {}]와 같이 데이터를 직접 작성하여 바인딩할 수 있다. |
| - | key | string | O | 선택 요소의 명칭에 바인딩될 데이터 컬럼 명 | 조회 데이터의 컬럼명을 입력한다. |
| - | val | string | O | 선택 요소의 값에 바인딩될 데이터 컬럼 명 | 조회 데이터의 컬럼명을 입력한다. |
| - | filter | function | | 데이터 필터 | 조회한 데이터를 가공하여 바인딩 한다. |
| - | selected | string | | 기본 선택 코드 값 | 데이터를 바인딩 한 후 기본으로 선택할 값. |
#### 1.2.1. "filter" 옵션 예제
```javascript
...
"filter" : function (data) {
    // data : 원 데이터
    // return : 가공된 데이터
    return N(N.array.deduplicate(data, "age")).datasort("age"); // 중복 제거 후 정렬
}
...
```
>p.select.{id} : [ "comm", "key", "val", filterFunction ] 처럼 Array 타입으로도 간단하게 선언할 수 있습니다. filter가 필요 없으면 [ "comm", "key", "val" ] 만 선언해도 됩니다.

### 1.3. N.form
| 속성명 | 옵션명 | 변수타입 | 필수 여부 | 기능 | 설명 |
| :--: | :--: | :--: | :--: | :--: | -- |
| p.form.{id} | - | - | - | - | N.form 컴포넌트를 초기화 한다 |
| - | usage | string or object | O | Form의 용도 | "search-box" 문자열 입력 시 지정한 영역을 검색 박스 Form으로 생성해 준다. object 타입으로 좀 더 상세한 옵션을 지정할 수 있다(하단 설명 참고). |
>엔터 키 이벤트를 자동으로 처리하기 위해서 반드시 "btn-search"(조회버튼) 라는 class 속성 값을 갖고있는 버튼요소(a 요소)를 view 안에 추가해 주어야 합니다.

 * "search-box" 옵션을 object 타입으로 지정.
```javascript
...
"p.form.search" : {
    "usage" : {
        "search-box" : {
            "defaultButton" : ".btn-search", // 엔터키를 눌렀을때 클릭될 버튼 요소 selector
            "events" : [{ // 엔터키 이벤트가 실행되지 않고 입력요소의 이벤트를 직접 지정하고 싶을 때 추가.
                "event" : "focusin", // 이벤트 명
                "target" : "#name", // 검색 박스 안의 대상 요소 selector
                "handler" : function(e) { // 이벤트 핸들러
                    N.log(e);
                }
            }, {
                "event" : "click",
                "target" : ".id",
                "handler" : function(e) {
                    N.log(e);
                }
            }]
        }
    }
},
...
```

### 1.4. 기타 모든 컴포넌트
| 속성명 | 추가옵션 | 변수타입 | 필수 여부 | 기능 | 설명 |
| :--: | :--: | :--: | :--: | :--: | -- |
| p.{component}.{id} | - | - | - | - | 지정한 컴포넌트를 초기화 한다. N.alert을 제외한 모든 컴포넌트를 이와 같은 방법으로 초기화 가능 하다. |

### 1.5. 엑셀 데이터 바인딩
N.grid의 bindExcel 메서드를 통해 엑셀 파일(Xlsx)의 데이터를 그리드에 바인딩할 수 있습니다.

```
{N.grid 인스턴스}.bindExcel([
    "key", "deptCd", "deptNm", "index", "guid", "active", "balance",
    "picture", "age", "eyeColor", "name", "gender", "company", "email",
    "phone", "address", "about", "registered", "latitude", "longitude",
    "greeting", "favoriteFruit" // 엑셀 컬럼 순서대로 JSON object의 키값을 정의
], {
    start : 1, // 데이터로 추출할 시작 엑셀 행 인덱스
    mode : "update", // insert 이면 무조건 INSERT, update 면 pk로 지정한 행 데이터가 있으면 UPDATE, 없으면 INSERT
    pk : [ "key" ], // mode 옵션이 update 일 때의 기준 키 컬럼 명
    after : function(data) {
        N.log(data); // 엑셀 데이터 추출 완료 후 실행.
    }
});
```

bindExcel 메서드의 인자는 다음과 같습니다.
* arguments[0] : JSON Object 키값 - 엑셀 컬럼 순서대로 JSON object의 키값을 정의(필수)
* arguments[1] : 추가 옵션 오브젝트(선택)
    * start : 데이터로 추출할 시작 엑셀 행 인덱스(number)
    * mode : 데이터 바인딩 모드 - insert 면 무조건 INSERT, update 면 pk로 지정한 행 데이터가 있으면 UPDATE, 없으면 INSERT(string)
    * pk : mode 옵션이 update 일 때 INSERT, UPDATE를 판단할 기준 키 컬럼 명(array)
    * server : JSON 데이터를 추출하기 위해 Excel 파일을 Server에 업로드하여 처리할 것인지 브라우저에서 javascript로 처리할 것인지 여부(boolean)
    * after : 엑셀 데이터 추출 완료 후 실행할 콜백 함수(함수의 첫 번째 인자로 추출된 JSON 타입의 엑셀 데이터가 반환 됨)
>Excel 데이터 추출을 브라우저에서 javascript로 처리하려면 "js/lib/xlsx.full.min.js" 파일을 임포트해야 합니다.

### 1.6. 파일관리 공통 팝업
Controller 오브젝트에 "p.popup.file" 속성을 정의하면 파일관리 공통 팝업을 인스턴스를 생성해 줍니다.
> 파일관리 공통 팝업은 파일다운로드/파일업로드 기능을 제공합니다.
> 옵션은 N.popup 컴포넌트와 동일하고 mode 옵션이 추가되어 있습니다.

| 속성명 | 추가옵션 | 변수타입 | 필수 여부 | 기능 | 설명 |
| :--: | :--: | :--: | :--: | :--: | -- |
| p.popup.file | - | - | - | - | 파일관리 공통 팝업을 생성 한다 |
| - | mode | string | | 파일관리 팝업 모드 | "download" 입력 시 다운로드 관련 항목만 표시되고 "upload" 입력 시 업로드 관련 항목만 표시되고 mode 옵션을 정의하지 않으면 모든항목이 다 표시 된다. |

> 팝업의 onClose 이벤트 옵션을 지정하면 핸들러 함수의 인자(onCloseData) 속성에 파일그룹아이디(fileId)와 다운로드파일목록(downloadList) 정보가 포함되어 반환됩니다.

```javascript
<article class="type0401">
    <div id="detail">
        <a id="btnFile" href="#">파일</a>
    </div>
</article>

<script type="text/javascript">
(function() {

    var cont = N(".type0401").cont({
        "p.form.detail" : {
            "action" : "add"
        },
        "p.popup.file" : {
            onClose : function(onCloseData) {
                if(onCloseData) {
                    cont["p.form.detail"].val("fileId", onCloseData.fileId);

                    // 파일 요약 목록 생성
                    APP.comm.utils.createFileSummaryList(onCloseData.downloadList, "fileName", 60, cont["e.btnFile.click"]);
                }
            }
        },
        "e.btnFile.click" : function(e) {
            e.preventDefault();

            cont["p.popup.file"].open(cont["p.form.detail"].val("fileId"));
        },
        init : function(view, request) {
            // 파일 요약 목록 생성
            if(data.length > 0) {
                APP.comm.utils.getFileSummaryList("fileId", "fileName", 60, cont["e.btnFile.click"]);
            }
        }
    });

})();
</script>

```

## 2. "c."으로 시작(N.comm(커뮤니케이터) 정의)
해당 컨트롤러 내에서 서버와 통신하는 모든 N.comm(Communicator) 들을 모두 정의합니다.
N.comm의 초기화 속성명은 다음과 같이 조합하여 사용할 수 있습니다.

```
"c.{액션명}" : function() { return N.comm; }
```

>액션명은 가능 하다면 호출하는 URL의 액션명과 맞춰 주고 불가능하면 반드시 목록 조회는 get{ActionName}List, 한건 조회는 get{ActionName}, 다건(CUD) 저장은 save{ActionName}, 입력은 insert{ActionName}, 수정은 update{ActionName}, 삭제는 delete{ActionName}으로 정의 바랍니다.
```javascript
...
var cont = N(".page-id").cont({
    "c.PAGEID" : {
        // "sample/PAGEID.html" 페이지를 .box 요소 안에 불러오는 커뮤니케이터 정의
        return N(".box").comm("sample/PAGEID.html");
    },
    "c.getSampleList" : {
        // cont["p.form.search"]의 data를 파라미터로 "sample/getSampleList.json" URL 호출
        return cont["p.form.search"].data(false).comm("sample/getSampleList.json");
    },
    init : function(view, request) {
        // 커뮤니케이터로 다른 페이지 삽입
        cont["c.PAGEID"]().submit();

        // 커뮤니케이터로 데이터 불러오기
        cont["c.getSampleList"]().submit(function(data) {
           cont["p.grid.master"].bind(data);
        });
    }
});
```

>커뮤니케이터들의 정의는 직접 오브젝트나 값을 대입하는것이 아닌 실행 함수를 지정하는 방식이므로 사용 시 `cont["c.{액션명}"]().submit`와 같이 함수 실행 구문 `()`을 추가하는것에 유의 바랍니다.
>커뮤니케이터의 파라미터를 위 예제와 같이 N.form이나 N.grid / N.list의 data() 메서드에 연결(정의)해 놓으면 커뮤케이터의 submit 메서드가 호출되는 시점의 컴포넌트 데이터를 서버로의 요청 파라미터로 쉽게 추출할 수 있습니다.

### 2.1. 엑셀 다운로드
N.comm의 excelDownload 메서드를 통해 서버에서 데이터를 엑셀파일로 받을 수 있습니다.

>[APP.comm.utils.excelDownload](#appcommutilsexceldownload) 함수를 사용하면 N.comm을 사용하지 않고 파라미터와 URL을 직접 정의하여 데이터를 Excel 파일로 다운로드할 수 있습니다.
>APP.comm.utils.excelDownload 함수는 파라미터와 URL을 정의할 수 있는 인자가 있습니다.

```javascript
if (cont["p.form.search"].validate()) {
    cont["c.getSampleList"]().excelDownload([ "샘플 목록", cont["p.grid.master"] ]);
}
```

excelDownload 메서드의 인자는 다음과 같습니다.
* arguments[0] : 엑셀 파일 명
* arguments[1] : N.grid 인스턴스 or 컬럼명 정보 Object - N.grid 인스턴스를 넣으면 헤더의 타이틀을 엑셀파일의 타이틀로 지정해 줍니다. N.list 등 컬럼 타이틀 정보가 없는 컴포넌트를 사용할 때는 Object 타입으로 직접 지정 가능합니다.
```javascript
cont["c.getSampleList"]().excelDownload([ "샘플 목록", {
    id : "name",
    id : "name",
} ]);
```
* arguments[3] : 추가 컬럼명 정보 Object - 엑셀 파일의 컬럼 중 N.grid의 헤더에 타이틀 정보가 없는 컬럼들의 타이틀을 추가로 정의할 수 있습니다. arguments[1]으로 지정된 컬럼정보를 extend합니다.
```javascript
cont["c.getSampleList"]().excelDownload([ "샘플 목록", {
    id : "name",
    id : "name",
}, {
    extId : "extName",
    extId : "extName",
} ]);
```

>N.pagination 컴포넌트를 사용하여 페이징 된 데이터를 페이징되지 않은 엑셀파일 데이터로 내려받기 위해서는 MyBatis Mapper 파일의 페이징 관련 쿼리들을 다음 예와 같이 분기해 줘야 합니다.
>isXlsxRequest 변수는 xlsx 요청일때만 true로 값이 세팅되고 일반 json 요청일 때는 변수가 생성되지 않습니다.

```xml
SELECT
<if test="isXlsxRequest == null">
    COUNT(*) OVER() AS total_count, -- ORACLE
</if>
...
<if test="isXlsxRequest == null">
    OFFSET #{startRowIndex} ROWS FETCH NEXT #{countPerPage} ROWS ONLY -- ORACLE
</if>
```

>엑셀파일의 컬럼은 쿼리에서 SELECT 한 컬럼들이 기록됩니다. 엑셀파일에만 특정 컬럼을 제외하거나 추가하고 싶을때는 isXlsxRequest 변수를 활용하여 다음과 같이 처리합니다.

```xml
SELECT
<if test="isXlsxRequest == null">
    COL01,
    COL02,
</if>
<if test="isXlsxRequest == true">
    COL03,
</if>
    COL04
FROM TABLE
```

### 2.2. 엑셀 대용량(Streaming) 다운로드
N.comm의 excelStreaming 메서드를 통해 서버에서 대용량 데이터를 엑셀파일로 받을 수 있습니다.

사용법은 N.comm의 excelDownload 메서드와 동일 하지만 데이터 리턴 시 Controller, Sevice를 거치지 않고 MyBatis 단에서 바로 엑셀파일을 생성하여 데이터를 반환하므로 반환데이터에 대해 후 처리를할 수 없습니다.
반환데이터를 참조하여 로직을 분기하거나 데이터를 가공을 하려면  SQL 쿼리로 처리하거나 excelDownload 메서드를 사용해야 합니다.

>MyBatis의 밑(low)단에서 처리되어 속도가 빠르고 엑셀 파일을 100 로우 마다 Flush하여 대용량 데이터 다운로드 시 Heap Memory가 Full되어 WAS가 다운되는 현상을 방지해 줍니다.
>WAS 단에서 처리되는 것이므로 ResultSet 자체의 메모리 사용량이 많아 Heap Memory가 Full되는 현상은 방지할 수 없습니다.

```javascript
if (cont["p.form.search"].validate()) {
    cont["c.getSampleList"]().excelStreaming([ "샘플 목록", cont["p.grid.master"] ]);
}
```


## 3. "e."으로 시작(이벤트 바인딩)
페이지(View) 안의 모든 요소들에 이벤트를 간단하게 정의할 수 있습니다.
>a, button, input[type=button] 요소에 이벤트를 정의하면 N.button 컴포넌트가 자동으로 초기화되어 버튼으로 생성됩니다.

이벤트의 초기화 속성명은 다음과 같이 조합하여 사용할 수 있습니다.

```
"e.{요소ID}.{이벤트명}" : 이벤트 핸들러
```

또는
```
"e.{이벤트구분자}.{이벤트명}" : {
    target : "{요소 Selector}",
    handler : 이벤트 핸들러
}
```


초기화가 완료되면 `e.{요소ID}.{이벤트명}` 속성 값으로 지정한 이벤트 핸들러 함수는 id 지정한 요소(jQuery Object)로 바뀝니다.

```javascript
...
var cont = N(".page-id").cont({
    "e.id.click" : function(e) { // 선언한 이벤트 핸들러는 초기화 후(init 함수가 실행되기 바로전) id로 지정한 요소(jQuery Object)로 바뀝니다.
        e.preventDefault(); // e.preventDefault()는 버튼 이벤트에는 반드시 추가해 주세요.

        cont["p.popup.dept"].open();
    },
    "e.id.click" : {
        target : ".div #id",
        handler : function(e) { // 선언한 이벤트 핸들러는 초기화 후(init 함수가 실행되기 바로전) target으로 지정한 요소(jQuery Object)로 바뀝니다.
            e.preventDefault(); // e.preventDefault()는 버튼 이벤트에는 반드시 추가해 주세요.

            cont["p.popup.company"].open();
        }
    },
    init : function(view, request) {
        cont["e.id.click"].click(); // 페이지 초기화가 완료되면 이벤트 실행.
    }
});
...
```

다음과 같이 컴포넌트에서 제공하는 이벤트도 적용 가능합니다.
```javascript
...
var cont = N(".page-id").cont({
    "e.dateInput.onSelect" : function(e, inputEle, selDate, isMonthonly, idx) { // N.datepicker의 onSelect 이벤트
        e.preventDefault();

        N.log(selDate.obj.formatDate(selDate.format)); // 선택한 날짜를 설정된 데이트포맷으로 추출하여 브라우저 콘솔에 출력.
    },
});
...
```

N.grid 나 N.list 컴포넌트 안의 요소를 지정하면 이벤트 핸들러 함수의 마지막 인자에 `해당 요소가 속한 행 인덱스를 반환`해 줍니다.
>rowHandler에서 행 요소마다 이벤트를 바인딩하면 브라우저 Heap 메모리 사용량이 이벤트 수 X 행 수만큼 늘어나 웹 어플리케이션 성능이 저하됩니다. 아래 방법(내부적으로 Event Delegation 기법 적용)을 사용하면 이벤트에 의한 메모리 사용량을 크게 줄일 수 있습니다.
```javascript
...
var cont = N(".page-id").cont({
    "e.id.click" : function(e, idx) {
        e.preventDefault(); // e.preventDefault()는 버튼 이벤트에는 반드시 추가해 주세요.

        N.log(cont["p.grid.id"].data()[idx]); // 클릭한 버튼이 속한 행 데이터를 브라우저 콘솔에 출력.
    }
});
...
```

> N.grid 나 N.list 컴포넌트 내부 요소의 id와 다른 컴포넌트(N.form 등)의 id가 중복되어 target 옵션으로 대상 요소를 직접 지정할 때는 target으로 지정한 selector의 context가 행(tbody 나 li) 요소로 자동 지정됩니다. 때문에 이를 구분하기 위해서는 아래와 같이 대상요소에 class 속성을 추가하여 ".class#id"와 같이 구분해 줘야 합니다.

```javascript
...
<div class="sampleForm">
    <input id="col01">
</div>
<table class="sampleGrid">
    <tbody>
        <tr>
            <td>
                <input class="col01" id="col01">
            </td>
        </tr>
    </tbody>
</table>
...
var cont = N(".page-id").cont({
    "e.col01.click" : {
        target : ".col01#col01", // "#grid #col01"로 지정하면 찾지 못함. 최상위 요소는 N.grid의 <tr> 이라고 생각해야 함.
        handler : function(e, idx) {
            // TODO
        }
    }
});
...
```

>Natural-JS는 내부 데이터와 입력된 데이터를 동기화하기 위해서 select 요소는 change 이벤트를, radio, checkbox 요소는 click 이벤트를, 그 외 text 나 textarea 등의 요소는 focusout 이벤트를 사용합니다.
때문에 대상 요소가 포함된 컴포넌트의 내부 데이터를 가져올 때는 같은 이벤트로 바인딩해 줘야 합니다. 그렇지 않으면 변경되기 이전의 데이터가 반환됩니다.

```javascript
...
var cont = N(".page-id").cont({
    "e.selectInput.change" : function(e, idx) {
        e.preventDefault();

        N.log(cont["p.grid.id"].val(idx, "selectInput")); // focusin 이벤트로 바인딩하면 변경되기 전 데이터가 반환됩니다.
    },
    "e.radioCheckboxInput.click" : function(e, idx) {
        e.preventDefault();

        N.log(cont["p.grid.id"].val(idx, "radioCheckboxInput")); // focusin 이벤트로 바인딩하면 변경되기 전 데이터가 반환됩니다.
    },
    "e.textInput.focusout" : function(e, idx) {
        e.preventDefault();

        N.log(cont["p.grid.id"].val(idx, "textInput")); // change 이벤트로 바인딩하면 변경되기 전 데이터가 반환됩니다.
    }
});
...
```

## 4. 기타 유틸리티
샘플 프로젝트의 공통 유틸리티입니다.

#### 콘솔 로그 및 에러 처리

Natural-CORE 패키지에는 console 객체에서 제공하는 log, warn, info 등의 함수들을 Wrapping하여 로그레벨을 중앙에서 컨트롤할 수 있는 기능을 제공합니다. 이 기능을 사용하기 위해서는 다음과 같은 명령어로 로그와 에러등을 처리해야 합니다.
* N.log : 브라우저 콘솔에 LOG(DEBUG) 메시지를 표시합니다. 사용법은 Javascript의 console.log와 동일하고 `N.log("디버그 메시지");`와 같이 간단하게 사용할 수 있습니다.
* N.info : 브라우저 콘솔에 INFO 레벨의 메시지를 표시합니다. 사용법은 Javascript의 console.info와 동일하고 `N.warn("디버그 메시지");`와 같이 간단하게 사용할 수 있습니다.

> 브라우저에서 console.info을 지원하지 않으면 N.log와 같이 작동됩니다.

* N.warn : 브라우저 콘솔에 WARN 레벨의 메시지를 표시합니다. 사용법은 Javascript의 console.warn와 동일하고 `N.warn("디버그 메시지");`와 같이 간단하게 사용할 수 있습니다.

> 브라우저에서 console.warn을 지원하지 않으면 N.log와 같이 작동됩니다.
> N.warn에 Error 객체를 넣으면 Error stack trace를 표시해 줍니다.

* N.error : 에러객체를 생성하고 에러 메시지와 Error stack trace를 브라우저 콘솔에 표시해 줍니다. 기본적인 사용법은 Javascript의 console.error와 동일 하나 N.error 함수는 에러 객체를 생성해 주므로 `throw N.error("에러 메시지");`와 같이 선언하면 에러메시지를 표시하고 에러를 발생시켜 이후 로직들이 중단됩니다.

#### APP.comm.utils.del

N.grid 나 N.list에서 체크되거나 선택된 행들을 삭제하기 위한 함수 - 삭제 전 동의 메시지 다이얼로그 표시 후 remove 함수 호출 등의 반복적인 루틴들을 한 번에 처리해 줍니다.

* Arguments Object
    * obj.cont : N.cont object
    * obj.inst : 행을 삭제할 N.grid 인스턴스 명,
    * obj.before : 선택한 행을 삭제하기 전 실행할 함수. 핸들러 함수의 인자로 체크한 행의 index 들을 반환합니다.
    * obj.after : 선택한 행을 삭제 한 후 실행할 함수.

>.call(this)로 함수를 호출하여 호출되는 함수의 this에 호출하는 함수의 this를 바인딩해 줘야 합니다.

```
...
"e.btnDelete.click" : function(e) {
    e.preventDefault();

    return APP.comm.utils.del.call(this, {
        cont : cont,
        grid : "p.grid.detail",
        before : function(checkedIndexs) {
            var cont = this; // this는 호출한 N.cont object
            // checkedIndexs : 체크한 행 index
        },
        after : function(data) { // data는 서버에서 리턴해준 데이터
            var cont = this; // this는 호출한 N.cont object
        }
    });
},
...
```

#### APP.comm.utils.save

추가, 수정, 삭제된 데이터를 저장하는 함수 - 저장 전 데이터 검증, 변경된 데이터 확인, 저장 메시지 다이얼로그 표시등 데이터 저장에 대한 반복적인 루틴들을 한 번에 처리해 줍니다.

* Arguments Object
    * obj.cont : N.cont object
    * obj.comm : 데이터 저장을 처리하는 N.comm이 정의된 함수명.
    * obj.msg : 저장 확인 메시지, undefined 이면 기본 메시지가 표시됨.
    * obj.changed : 변경된 데이터 유무를 참조할 컴포넌트 인스턴스 명.
    * obj.validate : 추가/수정된 데이터의 유효성을 검증할 컴포넌트 인스턴스 명.
    * obj.before : 서버에 저장하기 전 실행할 함수.
    * obj.after : 서버에 저장 한 후 실행할 함수.

>.call(this)로 함수를 호출하여 호출되는 함수의 this에 호출하는 함수의 this를 바인딩해 줘야 합니다.

```
...
"e.btnSave.click" : function(e) {
    e.preventDefault();

    return APP.comm.utils.save.call(this, {
        cont : cont,
        comm : "c.saveSample",
        changed : "p.grid.master",
        validate : "p.grid.master",
        before : function() {
            var cont = this; // this는 호출한 N.cont object
        },
        after : function() {
            var cont = this; // this는 호출한 N.cont object
        }
    });
},
...
```

#### APP.comm.utils.selectNBind

N.grid 나 N.list의 행을 선택했을 때(onSelect 이벤트 핸들러 함수 이용) N.form 컴포넌트에 같은 데이터를 연동하기 위한 반복적인 루틴들을 한 번에 처리해 줍니다.

* Arguments Object
    * opts.cont : N.cont object
    * opts.form : 데이터를 연동할 N.form 인스턴스 명.
    * opts.dataSync : false를 입력하면 N.grid 나 N.list 컴포넌트의 데이터와 N.form의 데이터 참조를 끊어 데이터가 실시간 동기화되지 않습니다.

>.call(this)로 함수를 호출하여 호출되는 함수의 this에 호출하는 함수의 this를 바인딩해 줘야 합니다.

```
...
"p.grid.master" : {
    onSelect : function(index, rowEle, data, beforeRow, e) {
        // TODO 전처리

        APP.comm.utils.selectNBind.call(this, {
            cont : cont,
            form : "p.form.detail"
        });

        // TODO 후처리
    }
},
...
```

#### APP.comm.utils.createFileSummaryList

파일 요약 목록을 만들어 줍니다.

>파일 팝업에서 전달받은 onCloseData.downloadList 목록을 요약 목록으로 만들 수 있습니다.

* Arguments Object
    * fileList : 파일 목록 Array
    * fileNameCol : 파일명 컬럼명
    * length : 파일 목록 문자열을 자를 기준 길이
    * fileButton : 파일팝업 버튼(입력하지 않으면 파일요약목록 문자열을 반환하고 입력하면 버튼 옆에 목록을 표시해 준다)

```
...
"p.popup.file" : {
    onClose : function(onCloseData) {
        if(onCloseData) {
            cont["p.form.detail"].val("fileId", onCloseData.fileId);

            APP.comm.utils.createFileSummaryList(onCloseData.downloadList, "fileName", 60, cont["e.btnFile.click"]);
        }
    }
},
...
```

#### APP.comm.utils.getFileSummaryList

fileId로 서버에서 업로드된 파일을 조회 후 파일 요약 목록을 만들어 줍니다.

* Arguments Object
    * fileId : 파일 아이디
    * fileNameCol : 파일명 컬럼명
    * length : 파일 목록 문자열을 자를 기준 길이
    * fileButton : 파일팝업 버튼(입력하지 않으면 파일요약목록 문자열을 반환하고 입력하면 버튼 옆에 목록을 표시해 준다)

```
...
"p.grid.master" : {
    onSelect : function(index, rowEle, data, beforeRow, e) {
        APP.comm.utils.selectNBind.call(this, {
            cont : cont,
            form : "p.form.detail"
        });

        // 파일 요약 목록 생성
        APP.comm.utils.getFileSummaryList(cont["p.form.detail"].val("fileId"), "fileName", 60, cont["e.btnFile.click"]);
    }
}
...
```

#### APP.comm.utils.excelDownload

데이터를 엑셀 파일로 다운로드합니다.

* Arguments
    * arguments[0] : 조회 파라미터 - JSON Object 타입으로 조회 파라미터를 정의합니다.
    * arguments[1] : 조회 URL - 조회 URL을 선언합니다. 조회 URL의 확장자는 .xlsx입니다. .json으로 호출되는 서비스를 확장자만 .xlsx로 바꿔주면 해당 서비스에서 반환되는 데이터를 엑셀파일로 받을 수 있습니다. 해당 서비스의 Controller의 @RequestMapping 에 반드시 `@RequestMapping(value = { "getSampleList.json", "getSampleList.xlsx" })` 와 같이 .json 과 .xlsx 확장자를 가진 URL을 두개 이상 Mapping 해줘야 합니다.
    * arguments[2] : 엑셀 파일 명
    * arguments[3] : N.grid 인스턴스 or 컬럼명 정보 Object - N.grid 인스턴스를 넣으면 헤더의 타이틀을 엑셀파일의 타이틀로 지정해 줍니다. N.list 등 컬럼 타이틀 정보가 없는 컴포넌트를 사용할 때는 Object 타입으로 직접 지정 가능합니다.
    * arguments[4] : 추가 컬럼명 정보 Object - 엑셀 파일의 컬럼 중 N.grid의 헤더에 타이틀 정보가 없는 컬럼들의 타이틀을 추가로 정의할 수 있습니다.
        * 추가 컬럼명 정보 데이터는 arguments[3]의 데이터에 extend됩니다. arguments[3]의 오브젝트에 정의되어 있지만 arguments[4]의 오브젝트에 없는 컬럼정보는 그대로 유지되고 arguments[4] 의 오브젝트에는 정의되어 있지만 arguments[3]의 오브젝트에 정의되어 있지 않은 컬럼정보는 arguments[3]의 오브젝트에 추가됩니다.

자세한 사용법은 [2.1. 엑셀 다운로드](#21)를 참고 바랍니다.


# Back-End 개발

## 1. 네이밍(Naming)
### 1.1. 패키지 구조
이 예제의 JAVA 기본 패키지는 common.app.sample이고 Sample 패키지를 예를 들어 패키지 구조를 설명하면 다음과 같습니다.
 * common.app.sample : Spring MVC의 Controller 소스코드들이 담겨 있는 패키지
 * common.app.sample.service.impl : 비즈니스 로직을 처리하는 Service 소스코드들이 담겨있는 패키지
 * common.app.sample.mappers : SQL 쿼리가 기록되어 있는 MyBatis Mapper XML 파일과 이 파일안의 쿼리 ID 들을 JAVA 메서드로 연결해주는 Mapper JAVA 인터페이스 파일이 담겨있는 패키지
 * common.app.sample.vo : VO 객체(get/set Bean)
>VO(Value Object) 객체는 get/set Bean으로 처리하지 않고 파라미터부터 리턴되는 데이터까지 모두 `Map<String, Object>` 나 `List<Map<String, Object>>`로 처리됩니다.
>그러나 Bean 타입의 VO 객체가 필요하면 빈을 만들어서 써도 상관없습니다.

### 1.2. @RequestMapping
Controller의 @RequestMapping은 Camel Case 문자열 형태로 다음과 같이 정의되어 있습니다.
 * 단건 조회 : `get` + `MethodName` + `.json`
 * 목록 조회 : `get` + `MethodName` + `List` + `.json`
 * 단건 입력 : `insert` + `MethodName` + `.json`
 * 단건 수정 : `update` + `MethodName` + `.json`
 * 단건 삭제 : `delete` + `MethodName` + `.json`
 * 다건 저장(입력/수정/삭제) : `save` + `MethodName` + `.json`
 * 출력 : `get` + `MethodName` + `.print`
 * 엑셀 다운로드 : `get` + `MethodName` + `List` + `.xlsx`
 * 엑셀 업로드 : `save` + `MethodName` + `.xlsx`

Controller, ServiceImpl, Mapper 클래스의 메서드명이나 Mapper XML의 id는 위 네이밍룰에서 확장자만 뺀 명칭으로 정의하면 됩니다.

>목록 조회와 엑셀 다운로드는 URL의 확장자로만 구분하고 같은 Controller, ServiceImpl, Mapper 클래스를 사용합니다.

## 2. Controller 개발
Controller는 기본적으로 다음 예제와 같이 구성되어 있습니다.

```java
...
@Controller
@RequestMapping("sample")
public class SampleController {

    @Resource(name = "sampleService")
    SampleServiceImpl sampleService;

    @RequestMapping("getSampleList.json")
    public List<Map<String, Object>> getSampleList(@RequestBody(required = false) Map<String, Object> vo) {
        return sampleService.getSampleList(vo);
    }

    @RequestMapping("getSample.json")
    public List<Map<String, Object>> getSample(@RequestBody Map<String, Object> vo) {
        return sampleService.getSample(vo);
    }

    @RequestMapping("saveSample.json")
    public Map<String, Object> saveSample(@RequestBody List<Map<String, Object>> voList) {
        return sampleService.saveSample(voList);
    }

    @RequestMapping("insertSample.json")
    public int insertSample(@RequestBody Map<String, Object> vo) {
        return sampleService.insertSample(vo);
    }

    @RequestMapping("updateSample.json")
    public int updateSample(@RequestBody Map<String, Object> vo) {
        return sampleService.updateSample(vo);
    }

    @RequestMapping("deleteSample.json")
    public int deleteSample(@RequestBody Map<String, Object> vo) {
        return sampleService.deleteSample(vo);
    }

}
```

Controller 클래스 개발 방법은 다음과 같습니다.

 1. 클래스 선언문 상단에 @Controller 어노테이션을 선언하여 Spring MVC의 Controller 클래스로 생성합니다.
 2. 클래스 선언문 상단에 @RequestMapping 어노테이션으로 이 컨트롤러를 접근할 URL의 기본 경로(path)를 정의합니다.
 3. public class 선언문을 통해 Controller 클래스를 생성합니다.
 4. 생성된 클래스에 사용할 서비스 클래스들을 @Resource 어노테이션을 통해 얻어옵니다.
 5. 이제 단위 URL에 매핑될 컨트롤러 메서드를 생성해 줍니다. 먼저 @RequestMapping 어노테이션을 통해 접근할 수 있는 URL을 정의합니다.
  >클래스 선언문 위의 @RequestMapping의 경로와 메서드 선언문 위의 @RequestMapping의 경로가 합해져서 해당 메서드에 접근하는 최종 URL이 생성됩니다.

 6. 컨트롤러 메서드의 리턴타입은 `Map<String, Object>`이나 `List<Map<String, Object>>`로 정의합니다.
  >반환된 Map이나 List 객체는 자동으로 JSON 형태의 문자열로 직렬화되고 HTTP Respose Body에 적제되어 클라이언트 브라우저로 전송됩니다. 전송된 JSON 문자열은 Javascript Object로 인스턴스화됩니다.

  >java의 List 객체는 클라이언트 브라우저에서 javascript array[object] 객체로 변환되고 Map 객체는 javascript object로 변환됩니다.

 7. 파라미터 선언은 목록데이터는 `List<Map<String, Object>>` 타입으로 정의하고 단건 데이터는 `Map<String, Object>` 타입으로 선언하되 클라이언트에서 request body에 담아 Ajax로 전달되기 때문에 앞에 @RequestBody 어노테이션을 선언해 줍니다.
  >클라이언트 브라우저에서 파라미터로 지정한 javascript array[object] 타입의 데이터 객체나 object 타입의 데이터 객체는 JSON 문자열로 직렬화 되에 HTTP Request Body에 적제되어 서버로 전송됩니다. 전송된 JSON 문자열은 java의 List 나 Map 객체로 인스턴스화됩니다.

  >전달된 javascript array[object] 객체는 java의 List 객체로 변환되고 javascript object는 Map 객체로 변환됩니다.

  >@RequestBody의 옵션 중 `required`는 파라미터가 필수 인지 아닌지를 결정하는 옵션입니다. `true(기본값)로 지정`하면 브라우저로부터 `전달된 데이터가 없으면 서버에서 에러가 발생`하여 요청이 중단되므로 비어있는 오브젝트({}) 라도 전달해야 합니다.
  `파라미터가 필요하지 않은 컨트롤러 메서드`라면 반드시 `required = false`를 지정해 주세요.

 8. 컨트롤러 메서드 선언이 끝났으면 service 클래스를 이용하여 데이터를 반환하는등의 컨트롤러 메서드의 로직을 채워 줍니다.

## 3. ServiceImpl(Service) 개발
Service는 기본적으로 다음 예제와 같이 구성되어 있습니다.

```java
...
@Service("sampleService")
public class SampleServiceImpl {

    @Autowired
    private SampleMapper sampleMapper;

    public List<Map<String, Object>> getSampleList(Map<String, Object> vo) {
        return sampleMapper.getSampleList(vo);
    }

    public List<Map<String, Object>> getSample(Map<String, Object> vo) {
        return sampleMapper.getSample(vo);
    }

    public Map<String, Object> saveSample(List<Map<String, Object>> voList) {
        Iterator<Map<String, Object>> iter = voList.iterator();

        Map<String, Object> vo;
        int insert = 0;
        int update = 0;
        int delete = 0;
        while (iter.hasNext()) {
            vo = (Map<String, Object>) iter.next();
            if ("insert".equals((String) vo.get("rowStatus"))) {
                // TODO
                insert += this.insertSample(vo);
            } else if ("update".equals((String) vo.get("rowStatus"))) {
                // TODO
                update += this.updateSample(vo);
            } else if ("delete".equals((String) vo.get("rowStatus"))) {
                // TODO
                delete += this.deleteSample(vo);
            }
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("insert", insert);
        resultMap.put("update", update);
        resultMap.put("delete", delete);

        return resultMap;
    }

    public int insertSample(Map<String, Object> vo) {
        return sampleMapper.insertSample(vo);
    }

    public int updateSample(Map<String, Object> vo) {
        return sampleMapper.updateSample(vo);
    }

    public int deleteSample(Map<String, Object> vo) {
        return sampleMapper.deleteSample(vo);
    }

}
```

ServiceImpl 클래스의 개발 방법은 다음과 같습니다.

 1. 클래스 선언문 상단에 @Service 어노테이션을 선언하여 Spring MVC의 Service 클래스로 생성합니다. `@Service 어노테이션의 value 값은 ServiceImpl 클래스의 Impl을 제거한 클래스 명칭을 기입`합니다.
 >ServiceImpl 클래스는 @Service 어노테이션을 선언하는 것을 빼면 일반 POJO 클래스를 개발하는 방식과 같습니다.

 2. public class 선언문을 통해 ServiceImpl 클래스를 생성합니다.
 3. 생성된 클래스에 사용할 Mapper 클래스들을 @Autowired 어노테이션을 통해 얻어옵니다.
 >private SampleMapper sampleMapper;와 같이 Mapper 클래스의 변수명은 클래스명에서 첫글자만 소문자로 바꾼 형태로 정의합니다.

 4. 서비스 메서드를 생성합니다.
 > insert, update, delete 메서드는 반드시 int 형을 리턴하도록 정의해 줍니다.

 > save 메서드는 그리드(N.grid)나 리스트(N.list)에서 넘어온 다건의 입력/수정/삭제를 처리하는 메서드입니다. 위 예제의 구문을 그대로 복사하여 사용하거나 rowStatus 별로 관련 로직을 더 채워 사용하면 됩니다.

## 4. MyBatis Mapper XML 개발
MyBatis Mapper XML 파일은 기본적으로 다음 예제와 같이 구성되어 있습니다.

```sql
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="common.app.sample.mappers.SampleMapper">
...
    <select id="getSample" parameterType="java.util.Map" resultType="java.util.Map">
        /* common.app.sample.mappers.SampleMapper.getSample */
        SELECT key,
               dept_cd,
               (SELECT dept_nm FROM sample_dept WHERE dept_cd = a.dept_cd) dept_nm,
               index,
               guid,
               is_active,
               balance,
               picture,
               age,
               eye_color,
               name,
               gender,
               company,
               email,
               phone,
               address,
               about,
               to_char(registered, 'YYYYMMDD') registered,
               latitude,
               longitude,
               greeting,
               favorite_fruit
          FROM sample
         WHERE KEY = #{key}
    </select>
...
</mapper>
```

MyBatis Mapper XML의 개발 방법은 다음과 같습니다.
1. mapper 태그의 namespace 속성에 Mapper XML 파일이 있는 패키지 경로를 입력합니다.
> Mapper Interface가 이 namespace 참고하여 자동으로 연결해 주므로 정확하게 입력해야 합니다.

2. select, insert, update, delete 태그를 이용하여 쿼리를 작성합니다.
> 빠른 디버깅을 위해 `반드시` 쿼리 상단에 주석`(/* */)`으로 해당 `namespace와 id를 더하여 기입` 바랍니다.

3. parameterType 타입은 기본적으로 `java.util.Map`을 선언해 주고 데이터를 get/set Bean에 담길 원하면 해당 클래스를 지정해 줍니다.
4. resultType 은 java.util.Map을 선언해 줍니다.

> 쿼리 파라미터할당은 반드시 `#{}`로 지정해야하고 `${}` 치환하는 구문은 지양해야 합니다.
`${}`로 처리하면 SQL Injection 공격에 취약하게 됩니다. 어쩔 수 없이 `${}`를 사용해야 한다면
반드시 ServiceImpl 클래스에서 해당 파라미터에 대해서 SQL Injection 공격 문자열을 제거 바랍니다.
> MyBatis Mapper XML의 변수할당이나 분기처리 등의 문법은
[MyBatis Mapper XML 매뉴얼](html/com/app/sample/mybatis-mapper-xml.pdf) 과
[MyBatis 동적 SQL 매뉴얼](html/com/app/sample/mybatis-dynamic-sql.pdf)을 참고 바랍니다.

### 4.1. DB 페이징
페이징은 Natural-JS의 N.pagination 컴포넌트를 사용합니다.
N.pagination의 사용방법은 [조회폼+그리드+DB페이징](#dHlwZTAzMDElMjQlRUMlQTElQjAlRUQlOUElOEMlRUQlOEYlQkMlMkIlRUElQjclQjglRUIlQTYlQUMlRUIlOTMlOUMlMkJEQiVFRCU4RSU5OCVFQyU5RCVCNCVFQyVBNyU5NSUyNGZhbHNl) 템플릿과 [Natural-UI API Document의 Pagination 탭](naturaljs/index.html#refr/refr0105)을 참고 바랍니다.
N.pagination에서 Mybatis Mapper SQL의 변수로 다음과 같은 페이징 정보를 전달해 줍니다.

* pageNo : 선택한 페이지 번호
* countPerPage : 페이지당 행 수
* countPerPageSet : 페이지 세트당 페이지 수
* currSelPageSet : 현재 페이지세트(페이지 그룹) 번호
* pageCount : 전체 페이지 개수
* pageSetCount : 전체 페이지세트(페이지 그룹) 개수
* totalCount : 전체 행 개수
* startPage : 현재 페이지세트(페이지 그룹) 중 첫 번째 페이지 번호
* startRowIndex : 선택한 페이지의 첫 번째 행 인덱스
* startRowNum : 선택한 페이지의 첫 번째 행 번호
* endPage : 현재 페이지세트(페이지 그룹) 중 마지막 페이지 번호
* endRowIndex : 선택한 페이지의 마지막 행 인덱스
* endRowNum : 선택한 페이지의 마지막 행 번호

위 정보를 참고하여 다음 예와 같이 쿼리를 작성하기 바랍니다.
```sql
-- Oracle 12c 이상 예
SELECT
       COUNT(*) OVER() AS TOTAL_COUNT, -- 페이징 시 필수 컬럼(조회된 데이터의 ROW COUNT)
       KEY,
       DEPT_CD
  FROM SAMPLE
OFFSET #{startRowIndex} ROWS FETCH NEXT #{countPerPage} ROWS ONLY -- 페이징 처리 구문

```

## 5. Mapper Interface 개발
Mapper Interface는 기본적으로 다음 예제와 같이 구성되어 있습니다.

```java
...
@Mapper
public interface SampleMapper {

    public List<Map<String, Object>> getSampleList(Map<String, Object> vo);

    public List<Map<String, Object>> getSampleBigList(Map<String, Object> vo);

    public List<Map<String, Object>> getSample(Map<String, Object> vo);

    public int insertSample(Map<String, Object> vo);

    public int updateSample(Map<String, Object> vo);

    public int deleteSample(Map<String, Object> vo);

}
```

Mapper 인터페이스의 개발 방법은 다음과 같습니다.

 1. 인터페이스 선언문 상단에 @Mapper 어노테이션을 선언하여 MyBatis의 Mapper 인터페이스로 생성합니다.
 > 생성된 인터페이스는 mappers 패키지 안에 Mapper XML 파일과 같이 있어야 합니다.

 2. 인터페이스 메서드를 생성합니다.
 > 메서드의 명칭은 Mapper XML 파일의 연결하고자 하는 쿼리의 id와 일치해야 합니다.

 > Arguments 타입은 Service에서 단건으로 처리되어 넘어오므로 `Map<String, Object>`로 선언합니다.

 > 리턴타입은 java.util.Map 도 Map 인터페이스를 상속하여 구현된 객체이므로 `List<Map<String, Object>>`로 선언합니다. 단건 조회도 UI 개발의 편의성을 위해 `List<Map<String, Object>>`로 선언 바랍니다(Natural-JS는 단건이든 다건이든 Array 타입으로 컴포넌트에 바인딩 함).

## 6. 기타
### 6.1. 예외처리(Exception)
서버에서 BizException을 던지면 화면에 N.alert 컴포넌트로 던진 메시지가 표시됩니다.

BizException의 생성자 메서드는 다음과 표와 같이 인자의 개수 또는 타입에 따라 다음과 같은 기능을 제공합니다.

| 생성자 | 설명 |
| :-- | :-- |
| `BizException(int code)` | Message Properties 파일에서 message로 지정한 프로퍼티의 메시지 값을 표시합니다. |
| `BizException(int code, String[] args)` | Message Properties 파일에서 message로 지정한 프로퍼티의 메시지 값을 표시하고 args 인자로 지정한 메시지 파라미터를 메시지에 바인딩합니다. |

아래 예와 같이 서버단 java에서 BizException을 던지면 화면의 Natural-UI의 N.alert 컴포넌트로 메시지가 표시됩니다.

```java
if(Condition) {
    throw new BizException(-30001, ["날짜"]);
}
```

메시지 프로퍼티는 src/main/resources/messages 폴더의 message-{업무대분류코드}.properties 파일에 {메시지코드}={메시지}와 같은 형식으로 지정 바랍니다.

> src/main/resources/messages/message-{업무대분류코드}.properties 파일이 없으면 application.yml 파일의 messages 속성의 basename 속성값에 ", message-{업무대분류코드}"를 추가하고 파일을 생성해야 합니다.
>메시지에 대한 파라미터는  BizException 생성자 함수의 두 번째 인자(Array 타입)로 지정 가능하고 메시지의 변수는 파라미터 배열의 순서에 따라 {0}, {1}, {2} 와같이 선언합니다.

```text
-30001={0} 형식이 잘못 됐습니다.
```

결과
```
"날짜 형식이 잘못 됐습니다."
```
메시지 프로퍼티 파일의 메시지 키값은 -3으로 시작하는 문자열을 지정해 줍니다.
> -1 은 시스템 에레 코드, -2는 프레임워크 에러 코드이고 -3은 응용프로그램 에러 코드입니다.

```
-30001={0}를 입력해 주세요.
-30002={0}와 {1}가 같지 않습니다.
-30003={0}는 {1} 보다 이전 날짜를 지정해 주세요.
```

### 6.2. 마스킹

마스킹은 DB의 스키마나 테이블 상관없이 대상 컬럼명들을 src/main/resources/config/common/data.properties 파일의 masking.columns 속성 값으로 다음과 같이 정의해 주면 됩니다.

```
{컬럼명}|{마스킹룰명}, {컬럼명}|{마스킹룰명}
```

```
masking.columns=rrnNo|rrn, name|name, phone|phone, address|address, email|email, cardNo|card
```
>컬럼명은 카멜케이싱(camel-case)된 컬럼명으로 선언해 줘야하고 콤마(,)로 구분하여 여러 개 선언 가능합니다.
>get으로 시작하는 Url Mapping 만 처리됩니다.

마스킹 룰은 ```name(성명), rrn(주민등록번호), frn(외국인등록번호), pn(여권번호), dln(운전면허번호), phone(전화번호, 휴대폰번호), email(이메일주소), card(카드번호), an(계좌번호), ip(IP주소)```가 있고 MaskingUtils(src/main/java/common/utils/MaskingUtils.java)를 통해 처리됩니다.

>마스킹 룰을 추가하려면 MaskingUtils의 maskString 메서드에 추가하고 싶은 룰의 케이스를 추가해 주고 같은 룰명으로 처리 함수를 추가해 주면 됩니다.

>마스킹은 MyBatis의 밑(low)단에서 처리되어 마스킹에 의한 성능 저하는 최소화되어 있습니다.

마스킹 대상컬럼을 선언 했으나 특정 요청은 마스킹을하고 싶지 않으면 masking.exclude.urls 속성 값에 URL을 다음과 같이 Ant Path 문법으로 선언해 주면 됩니다.
```
masking.exclude.urls=/**/sample/getSampleList.json, /**/sample/getSampleBigList.xlsx
```

>콤마(,)로 구분하여 여러 개 선언 가능합니다.

만약 관리화면등에서 마스킹되지 않은 데이터를 봐야 한다면 `소스파일을 분할하지 말고` Controller의 RequestMapping에 다음과 같이 URL 2개를 등록하고
그중 관리화면에서 사용할 URL 1개를 masking.exclude.urls 속성 값으로 등록 한 후 관리자
권한이 있는 사용자는 masking.exclude.urls 속성 값으로 등록된 URL을 호출하도록 프로그래밍하면 됩니다.

```
@RequestMapping(value = { "getSampleList", "getAdminSampleList" })
```

### 6.3. 최대 조회건수 제한
쾌적한 서버 운영환경을 유지하기 위해 DB 데이터의 최대 행(row) 수를 제한할 수 있습니다.
src/main/resources/config/common/data.properties 파일의 max.rows.limit 속성 값에 최대 행 수를 지정 가능하고 max.rows.exclude.urls 속성에 예외 URL 들을 등록 가능합니다.

>엑셀 다운로드 요청은 최대 조회 건수 제한에 걸리지 않고 모든 데이터를 내려 받습니다.

### 6.4. XSS 공격 차단
Cross Site Scripting(XSS) 공격을 차단하기 위해 서버에는 XSS 공격 문자열을 HTML 특수문자 코드로 변환해 주는 필터가 걸려 있습니다.

변환되는 문자열은 다음과 같습니다.

| 문자 | HTML 특수문자 코드 |
| :--: | :--: |
| `&` | `&amp;` |
| `/` | `&#x2F;` |
| `<` | `&lt;` |
| `>` | `&gt;` |
| `'` | `&#x27;` |
| `\` | `&quot;` |

HttpServletRequest.getParametor()로 값을 가져올 수 있는 GET이나 POST 요청 뿐만 아니라 Natural-JS의 N.comm에 의해 Request Body에 실려 넘어오는 JSON 문자열도
Controller 메서드들의 Map이나 List 타입의 인자로 변환되면서 XSS Filter가 적용됩니다.

변환된 문자열은 DB 단까지 전달되어 그대로 저장되고 DB에서 데이터를 다시 조회하여 UI로 넘어왔을때 Natrual-UI의 데이터 관련 여러 컴포넌트들이 HTML 컨트롤의 특성을 파악하여 자동으로 원래 문자열로 표시해 줍니다.

src/main/resources/config/common/data.properties 파일의 xss.exclude.urls 속성에 URL 들을 등록하면 등록된 URL(요청) 은 XSS 공격 문자열을 필터링하지 않습니다.


src/main/resources/config/common/data.properties 파일의 xss.exclude.urls 속성에 URL 들을 등록하면 등록된 URL(요청) 은 XSS 공격 문자열을 필터링하지 않습니다.



Supports
===

### Support browsers

* PC : Internet Explorer 8 or later(Optimized for Internet Explorer 9 or later), Chrome, Firefox, Safari(OSX), Opera latest version
* Mobile : iOS Safari, iOS UIWebView, Android Browser, Android Chrome, Android WebView

### Training and support

* Please contact us at <bbalganjjm@gmail.com>

### License
This software is licensed under the [LGPL v2.1](https://github.com/bbalganjjm/natural_js/blob/master/LICENSE) &copy; KIM HWANG MAN&lt;<bbalganjjm@gmail.com>&gt;