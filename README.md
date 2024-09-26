Natural-JS Boot
===

## 소개 

Natural-JS Boot 는 [Natural-JS](https://bbalganjjm.github.io/natural_js) 와 [Natural-TEMPLATE](https://bbalganjjm.github.io/natural_js/#html/naturaljs/template/documents/template-guide.html), Spring Boot 로 구성된 기업용 웹 애플리케이션 개발을 위한 아키텍처 프레임워크 패키지 입니다.

Natural-JS Boot 는 Front-end / Back-end 프레임워크와 더불어 기업용 웹 애플리케이션에 필요한 다양한 유틸리티와 개인정보보호, 웹 취약점 보완, 파일 관리, 엑셀 업/다운로드 등의 공통 라이브러리를 제공합니다.

## 목차

- [프로젝트 구성](#user-content-프로젝트-구성)

- [**Front-End 개발**](#user-content-front-end-개발)
	- [페이지 소스코드 작성 규칙](#user-content-페이지-소스코드-작성-규칙)

	- [Controller object(N.cont) 의 속성명 작성 규칙](#user-content-controller-objectncont-의-속성명-작성-규칙)

        - [1. "p." 으로 시작 - UI 컴포넌트 생성](#user-content-1-p-으로-시작---ui-컴포넌트-생성)
            - [1.1. N.select - 공통코드 데이터 바인딩](#user-content-11-nselect---공통코드-데이터-바인딩)
            - [1.2. N.select - 일반 목록 데이터를 선택요소(select, radio, checkbox)에 바인딩](#user-content-12-nselect---일반-목록-데이터를-선택요소select-radio-checkbox에-바인딩)
            - [1.3. N.form](#user-content-13-nform)
            - [1.4. 다른 모든 컴포넌트](#user-content-14-다른-모든-컴포넌트)
            - [1.5. 엑셀 데이터 바인딩](#user-content-15-엑셀-데이터-바인딩)
            - [1.6. 파일관리 공통 팝업](#user-content-16-파일관리-공통-팝업)

        - [2. "c." 으로 시작 - Communicator(N.comm) 선언](#user-content-2-c-으로-시작---communicatorncomm-선언)
            - [2.1. 엑셀 다운로드](#user-content-21-엑셀-다운로드)
            - [2.2. 엑셀 대용량(Streaming) 다운로드](#user-content-22-엑셀-대용량streaming-다운로드)

        - [3. "e." 으로 시작 - 이벤트 바인딩](#user-content-3-e-으로-시작---이벤트-바인딩)

        - [4. 유틸리티](#user-content-4-유틸리티)
            - [콘솔 로그 및 에러 처리](#user-content-콘솔-로그-및-에러-처리)
            - [APP.comm.utils.del](#user-content-appcommutilsdel)
            - [APP.comm.utils.save](#user-content-appcommutilssave)
            - [APP.comm.utils.selectNBind](#user-content-appcommutilsselectnbind)
            - [APP.comm.utils.createFileSummaryList](#user-content-appcommutilscreatefilesummarylist)
            - [APP.comm.utils.getFileSummaryList](#user-content-appcommutilsgetfilesummarylist)
            - [APP.comm.utils.excelDownload](#user-content-appcommutilsexceldownload)

- [**Back-End 개발**](#user-content-back-end-개발)

    - [1. 네이밍(Naming)](#user-content-1-네이밍naming)
        - [1.1. 패키지 구조](#user-content-1-네이밍naming)
        - [1.2. @RequestMapping](#user-content-12-requestmapping)

    - [2. Controller 개발](#user-content-2-controller-개발)

	- [3. ServiceImpl(Service) 개발](#user-content-3-serviceimplservice-개발)

	- [4. MyBatis Mapper XML 개발](#user-content-4-mybatis-mapper-xml-개발)
        - [4.1. DB 페이징](#user-content-41-db-페이징)

	- [5. Mapper Interface 개발](#user-content-5-mapper-interface-개발)
    - [6. 기타](#user-content-6-기타)
        - [6.1. 예외처리(Exception)](#user-content-61-예외처리exception)
        - [6.2. 마스킹](#user-content-62-마스킹)
        - [6.3. 최대 조회건수 제한](#user-content-63-최대-조회건수-제한)
        - [6.4. XSS 공격 차단](#user-content-64-xss-공격-차단)

## 프로젝트 구성

샘플 프로젝트의 기술 요소는 다음과 같습니다.

**Front-End**
- 기반기술 : HTML5, CSS3, Javascript(ECMA Script 3+), jQuery v3.7.1
- 프레임워크 : Natural-JS
- 지원브라우저 : Internet Explorer 11, Chrome 최신버전, Opera 최신버전, Firefox 최신버전, Edge 최신버전

**Back-End**
- 기반기술 : JAVA 17
- 프레임워크 : Spring Boot 3.1.5
- DB : HSQLDB(Sample DB)

## Front-End 개발

#### Front-End 개발을 위해 HTML, CSS, Javascript, [jQuery](https://jquery.com), [Natural-JS](https://bbalganjjm.github.io/natural_js) 에 대한 학습이 선행되어야 합니다.

### 페이지 소스코드 작성 규칙

기본적으로 Natural-JS 의 블록 페이지들의 소스코드는 다음과 같이 구성해야 됩니다.

```javascript
<style>
    /* View(CSS) - 생략 가능하고 이 파일의 View 에만 스타일을 적용하고 싶을 때만 추가 합니다. */
    .page-id {
        /* CSS 셀렉터를 선언할 때 .page-id #target { } 처럼 .page-id 를 먼저 추가해야 합니다. 그렇지 않으면 다른 페이지에도 영향을 미칩니다. */
    }
</style>

<article class="page-id">
    <!-- article 태그에 class 속성으로 page-id를 지정합니다. -->
</article>

<script type="text/javascript">
(function() {

    // N.cont 함수 실행시 View 의 class 속성으로 정의 된 "page-id" 값을 N 의 인수로 넣어 줍니다.
    var cont = N(".page-id").cont({
        init : function(view, request) {
            // "init" 함수는 페이지 로드가 완료되면 자동으로 실행됩니다.
        }
    });

})();
</script>
```

### Controller object(N.cont) 의 속성명 작성 규칙

Natural-TEMPLATE 의 기능은 Controller object 프로퍼티명의 명명규칙으로 실행할 수 있습니다. 컴포넌트를 초기화하거나 이벤트를 바인딩하는 등의 반복적인 작업들을 자동화해 줍니다.

#### 1. "p." 으로 시작 - UI 컴포넌트 생성

아래와 같이 선언하면 Natural-UI 의 컴포넌트를 지정한 요소에 생성하고 생성된 컴포넌트 인스턴스를 반환 해 줍니다.
컴포넌트를 선언하는 Controller object 의 속성명은 다음과 같이 조합하여 사용할 수 있습니다.

```
"p.{컴포넌트명}.{요소id}" : {
    컴포넌트 옵션
}
```

초기화가 완료되면 `p.{컴포넌트명}.{요소id}` 속성 값으로 지정한 컴포넌트 옵션 객체는 생성된 컴포넌트의 인스턴스로 대체됩니다.

> `p.select.{id}` 선언은 페이지의 view 에서 해당 id 속성 값을 갖고 있는 모든 선택 요소에 Select(N.select) 컴포넌트를 초기화하고 생성된 컴포넌트 인스턴스들을 Array 에 저장한 다음 속성 값을 대체합니다. Select 컴포넌트의 인스턴스를 사용할 때는 `cont["p.select.{id}"][1]`와 같이 사용할 인스턴스를 배열에서 꺼내어 사용해야 합니다.

> 컴포넌트 초기화 옵션은 `cont["p.{컴포넌트명}.{요소id}"].options`로 접근할 수 있으나 옵션의 직접 사용은 권장하지 않습니다.

```
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

컴포넌트의 context 옵션은 id 로 지정한 요소가 자동으로 할당되지만 id 아닌 class 셀렉터 등, 다른 셀렉터로 지정하려면 context 옵션을 직접 설정하면 됩니다.

```
...
"p.form.detail" : {
    context : ".detail",
    revert : true,
    autoUnbind : true
},
...
```

위와 같이 context 옵션을 선언하면 `N(".detail", view)`와 같이 View 내에서 찾을 수 있는 구문이 자동으로 생성됩니다.

>**N.tab 과 N.popup 컴포넌트의 `onOpen 옵션의 함수명 문자열은 반드시 onOpen으로 시작("onOpen", "onOpenABC" 등)` 해야 합니다. 그렇지 않으면 Controller object의 init 함수보다 onOpen 함수가 먼저 실행되어서 컴포넌트 인스턴스 등을 참조하지 못할 수 있습니다.**

>**N.tab 컴포넌트의 onActive 옵션은 주의해서 사용 바랍니다. init 함수의 지연 실행에 대한 대책이 아직 없어서 onActive 가 init 보다 먼저 실행됩니다. 비슷한 안정적인 기능을 원한다면 onOpen 을 사용해 주세요.**

컴포넌트 옵션은 Natural-UI의 컴포넌트별 기본 옵션 외에 해당 컴포넌트의 용도를 지정하거나 초기화 후 바로 실행할 함수 등을 지정할 수 있는 옵션이 더 추가되어 있습니다.
Natural-TEMPLATE 에서만 사용 가능한 컴포넌트 별 추가 옵션은 다음과 같습니다.

#### 1.1. N.select - 공통코드 데이터 바인딩

**이 기능을 사용하려면 공통코드 데이터를 제공하는 서비스 URL 과 공통코드 분류코드 컬럼명을 [Config(natural.config.js)](#html/naturaljs/refr/refr0102.html) 의  N.context.attr("template").codes 속성에 설정해 주어야 합니다.**

| 속성 | 옵션명 | 타입 | 필수 여부 | 속성 값 | 설명 |
| :--: | :--: | :--: | :--: | :--: | -- |
| p.select.{id} | - | - | - | - | N.select 컴포넌트를 초기화합니다. |
| - | code | string | O | 공통코드 분류코드 | 바인딩할 코드 목록의 분류 코드값을 설정합니다. |
| - | filter | function | | 데이터 필터 | 공통코드 데이터를 필터링하여 바인딩합니다. |
| - | selected | string | | 기본 선택 값 | 컴포넌트 초기화 시 기본으로 선택될 선택지의 값을 설정합니다. |

```
...
"p.select.gender" : {
    "code" : "gender",
    "selected" : "male"
},
...
```

 - p.select.{id} : [ "code", filterFunction ] 처럼 array 타입으로도 간단하게 선언할 수 있습니다. filter 가 필요 없으면 [ "code" ] 만 선언해도 됩니다.

```
...
"p.select.gender" : [ "gender" ],
...
```

 - "filter" 옵션 예제
```
...
"filter" : function (data) {
    // data(원래 데이터)를 가공하여 return 하면 가공된 데이터가 바인딩됩니다.
    return N(N.array.deduplicate(data, "age")).datasort("age"); // 중복 제거 후 정렬.
}
...
```

#### 1.2. N.select - 일반 목록 데이터를 선택요소(select, radio, checkbox)에 바인딩

| 속성 | 옵션명 | 타입 | 필수 여부 | 속성 값 | 설명 |
| :--: | :--: | :--: | :--: | :--: | -- |
| p.select.{id} | - | - | - | - | N.select 컴포넌트를 초기화합니다. |
| - | comm | string | | 목록을 조회할 Communicator(N.comm) | Controller object 에 선언한 "c.{serviceName}"을 지정합니다. |
| - | data | array[object] | | 바인딩할 데이터 | comm 옵션을 지정하지 않고 data 옵션으로 [{}, {}]와 같은 데이터를 직접 생성하여 바인딩할 수 있습니다. |
| - | key | string | O | 선택 요소의 명칭에 바인딩될 데이터 속성 명 | 조회한 데이터 객체에서 바인딩할 프로퍼티명을 설정합니다. |
| - | val | string | O | 선택 요소의 값에 바인딩될 데이터 속성 명 | 조회한 데이터 객체에서 바인딩할 프로퍼티명을 설정합니다. |
| - | filter | function | | 데이터 필터 | 공통코드 데이터를 필터링하여 바인딩합니다. |
| - | selected | string | | 기본 선택 값 | 컴포넌트 초기화 시 기본으로 선택될 선택지의 값을 설정합니다. |

```
...
"p.select.age" : {
    "comm" : "c.getSampleCodeList",
    key : "age",
    val : "age",
    "filter" : function(data) {
        return N(N.array.deduplicate(data, "age")).datasort("age");
    },
    "selected" : "22"
},
...
```

 - p.select.{id} : [ "comm", "key", "val", filterFunction ] 처럼 Array 타입으로도 간단하게 선언할 수 있습니다. filter 가 필요 없으면 [ "comm", "key", "val" ] 만 선언해도 됩니다.

```
...
"p.select.age" : [ "c.getSampleCodeList", "age", "age", function(data) {
    return N(N.array.deduplicate(data, "age")).datasort("age");
}],
...
```

#### 1.3. N.form

| 속성 | 옵션명 | 타입 | 필수 여부 | 속성 값 | 설명 |
| :--: | :--: | :--: | :--: | :--: | -- |
| p.form.{id} | - | - | - | - | N.form 컴포넌트를 초기화합니다. |
| - | usage | string or object | O | Form 의 용도 | "search-box" 문자열을 입력하면 지정한 영역을 검색 박스 Form 으로 생성해 줍니다. object 타입으로 좀 더 상세한 옵션을 지정할 수 있습니다.(하단 설명 참고). |

 - 일반 폼 예제
```
...
"p.form.detail" : { // N.form 컴포넌트의 옵션
    revert : true,
    autoUnbind : true
},
...
```


 - 검색 폼 예제

```
...
"p.form.search" : {
    "usage" : "search-box"
},
...
```

> 검색 폼에서 Enter 키 이벤트를 자동으로 처리하기 위해서는 메인 조회 버튼에 클래스 속성 값 "btn-search" 를 추가해야 합니다.

좀더 상세한 옵션을 설정하려면 다음 예제와 같이 "search-box" 옵션을 object 로 지정하면 됩니다.

> "usage" 옵션이 "search-box"로 설정된 Form 은 입력 요소에 Enter 키로 조회하는 이벤트 핸들러가 자동으로 바인딩됩니다. 이 Enter 키 이벤트 핸들러의 실행을 차단하고 다른 이벤트 핸들러를 등록하려면 "search-box" 옵션 객체의 "events" 속성에 이벤트 핸들러를 array 객체 안에 필요한 만큼 정의해 주면 됩니다.

```
...
"p.form.search" : {
    "usage" : {
        "search-box" : {
            "defaultButton" : ".btn-search", // 엔터키를 눌렀을 때 클릭될 버튼 요소를 선택하는 selector 문자열
            "events" : [{ // 엔터키 이벤트를 차단하고 입력 요소에 이벤트를 직접 지정하고 싶을 때 추가합니다.
                "event" : "focusin", // 이벤트 유형
                "target" : "#name", // 검색 박스 안의 대상 요소를 선택하는 selector 문자열
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

#### 1.4. 다른 모든 컴포넌트

| 속성 | 옵션 | 타입 | 필수 여부 | 속성 값 | 설명 |
| :--: | :--: | :--: | :--: | :--: | -- |
| p.{component}.{id} | - | - | - | - | N.{component} 컴포넌트를 초기화합니다. N.alert 을 제외한 모든 컴포넌트를 이와 같은 방법으로 초기화할 수 있습니다. |

 - Tab(N.tab) 예제

```
...
"p.tab.master" : { },
...
```

 - Popup(N.popup) 예제

```
...
"p.popup.dept" : {
    url : "html/naturaljs/template/samples/type04P0.html",
    onOpen : "onOpen",
    height: 621,
    onClose : function(onCloseData) {
        if (onCloseData) {
            cont["p.form.detail"]
            .val("deptNm", onCloseData.deptNm)
            .val("deptCd", onCloseData.deptCd);
        }
    }
},
...
```

 - Grid(N.grid) 예제

```
...
"p.grid.master" : {
    height : 200,
    select : true,
    selectScroll : false,
    onBind : function(context, data, isFirstPage, isLastPage) {
        if(isFirstPage) {
            this.select(0);
        }
    }
},
...
```

#### 1.5. 엑셀 데이터 바인딩

N.grid 의 bindExcel 메서드를 통해 엑셀 파일(Xlsx)의 데이터를 그리드에 바인딩할 수 있습니다.

```
{N.grid 인스턴스}.bindExcel([
    "key", "deptCd", "deptNm", "index", "guid", "active", "balance",
    "picture", "age", "eyeColor", "name", "gender", "company", "email",
    "phone", "address", "about", "registered", "latitude", "longitude",
    "greeting", "favoriteFruit" // 엑셀 컬럼 순서대로 JSON object의 키값을 정의
], {
    start : 1, // 데이터로 추출할 시작 엑셀 행 인덱스
    mode : "update", // insert 이면 무조건 INSERT, update 면 pk 로 지정한 행 데이터가 있으면 UPDATE, 없으면 INSERT
    pk : [ "key" ], // mode 옵션이 update 일 때의 기준 키 컬럼 명
    after : function(data) {
        N.log(data); // 엑셀 데이터 추출 완료 후 실행.
    }
});
```

bindExcel 메서드의 인수는 다음과 같습니다.
- arguments[0] : JSON Object 키값 - 엑셀 컬럼 순서대로 JSON object의 키값을 정의(필수)
- arguments[1] : 추가 옵션 오브젝트(선택)
    - start : 데이터로 추출할 시작 엑셀 행 인덱스(number)
    - mode : 데이터 바인딩 모드 - insert 면 무조건 INSERT, update 면 pk로 지정한 행 데이터가 있으면 UPDATE, 없으면 INSERT(string)
    - pk : mode 옵션이 update 일 때 INSERT, UPDATE 를 판단할 기준 키 컬럼 명(array)
    - server : JSON 데이터를 추출하기 위해 Excel 파일을 Server 에 업로드하여 처리할 것인지 브라우저에서 javascript 로 처리할 것인지 여부(boolean)
    - after : 엑셀 데이터 추출 완료 후 실행할 콜백 함수(함수의 첫 번째 인수로 추출된 JSON 타입의 엑셀 데이터가 반환 됨)
>Excel 데이터 추출을 브라우저에서 javascript 로 처리하려면 "src/main/resources/static/js/lib/xlsx.full.min.js" 파일을 임포트해야 합니다.

#### 1.6. 파일관리 공통 팝업

Controller 오브젝트에 "p.popup.file" 속성을 정의하면 파일관리 공통 팝업을 인스턴스를 생성해 줍니다.

> 파일관리 공통 팝업은 파일 업로드/다운로드 기능을 제공합니다.

> 옵션은 N.popup 컴포넌트와 동일하고 mode 옵션이 추가되어 있습니다.

| 속성명 | 추가옵션 | 변수타입 | 필수 여부 | 기능 | 설명 |
| :--: | :--: | :--: | :--: | :--: | -- |
| p.popup.file | - | - | - | - | 파일관리 공통 팝업을 생성 한다 |
| - | mode | string | | 파일관리 팝업 모드 | "download" 입력 시 다운로드 관련 항목만 표시되고 "upload" 입력 시 업로드 관련 항목만 표시되고 mode 옵션을 정의하지 않으면 모든항목이 다 표시 된다. |

> 팝업의 onClose 이벤트 옵션을 지정하면 핸들러 함수의 인수(onCloseData) 속성에 파일그룹아이디(fileId)와 다운로드파일목록(downloadList) 정보가 포함되어 반환됩니다.

> 파일관련 설정은 src/main/resources/config/common/file.properties 에서 할 수 있습니다.
> - file.not.allow.exts : 업로드 불가 확장자
> - file.upload.base.path : 파일 저장 경로
> - file.upload.max.each : 개별 파일의 최대 업로드 가능 용량
> - file.upload.max.all : 전체 파일의 최대 업로드 가능 용량

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

### 2. "c." 으로 시작 - Communicator(N.comm) 선언

서버와 통신하는 모든 [Communicator(N.comm)](#html/naturaljs/refr/refr0203.html) 를 Controller object 의 멤버 변수로 선언할 수 있습니다. Communicator 를 미리 선언해 놓으면 데이터의 흐름을 한눈에 확인할 수 있고 선언된 Communicator 에 AOP 를 적용할 수 있습니다.
Communicator 선언하는 Controller object 의 속성명은 다음과 같이 조합하여 사용할 수 있습니다.

```
"c.{서비스명}" : function() { 
    return N(params).comm({url}); 
}
```

>가능 하다면 서비스명은 호출하는 URL 의 마지막 경로의 서비스명과 동일하게 정의하고 중복되는 서비스명이 있다면 getSampleList*Json* 과 같이 확장자까지 조합하여 정의 합니다. 
>그래도 중복되는 서비스명이 있다면 목록 조회는 `get + {serviceName} + List`, 한건 조회는 `get + {serviceName}`, 다건(CUD) 저장은 `save + {serviceName} + List`, 입력은 `insert + {serviceName}`, 수정은 `update + {serviceName}`, 삭제는 `delete + {serviceName}` 으로 정의 바랍니다.

```
...
var cont = N(".page-id").cont({
    "c.PAGEID" : {
        // "sample/PAGEID.html" 페이지를 .box 요소 안에 불러오는 Communicator 정의
        return N(".box").comm("sample/PAGEID.html");
    },
    "c.getSampleList" : {
        // cont["p.form.search"]의 data를 파라미터로 사용하여 "sample/getSampleList.json" 서비스 호출
        return cont["p.form.search"].data(false).comm("sample/getSampleList.json");
    },
    init : function(view, request) {
        // Communicator를 사용하여 다른 페이지 삽입하기
        cont["c.PAGEID"]().submit();

        // Communicator를 사용하여 데이터 불러오기
        cont["c.getSampleList"]().submit(function(data) {
           cont["p.grid.master"].bind(data);
        });
    }
});
```

Communicator 선언은 직접 오브젝트나 값을 대입하는것이 아닌 실행 함수를 지정하는 방식으로, 사용 시 `cont["c.{서비스명}"]().submit` 와 같이 함수 실행 구문 `()`을 추가하는것에 유의 바랍니다.
N.comm 의 파라미터를 위 예제와 같이 N.form 이나 N.grid, N.list 등의 데이터 관련 컴포넌트의 data() 메서드에 연결해 놓으면 컴포넌트의 최신 데이터가 요청 파라미터로 자동으로 지정됩니다.

#### 2.1. 엑셀 다운로드

N.comm 의 excelDownload 메서드를 통해 서버에서 데이터를 엑셀파일로 받을 수 있습니다.

>[APP.comm.utils.excelDownload](#appcommutilsexceldownload) 함수를 사용하면 N.comm 을 사용하지 않고 파라미터와 URL 을 직접 정의하여 데이터를 Excel 파일로 다운로드할 수 있습니다.
>APP.comm.utils.excelDownload 함수는 파라미터와 URL 을 정의할 수 있는 인수가 있습니다.

```javascript
if (cont["p.form.search"].validate()) {
    cont["c.getSampleList"]().excelDownload([ "샘플 목록", cont["p.grid.master"] ]);
}
```

excelDownload 메서드의 인수는 다음과 같습니다.
- arguments[0] : 엑셀 파일 명
- arguments[1] : N.grid 인스턴스 or 컬럼명 정보 Object - N.grid 인스턴스를 넣으면 헤더의 타이틀을 엑셀파일의 타이틀로 지정해 줍니다. N.list 등 컬럼 타이틀 정보가 없는 컴포넌트를 사용할 때는 Object 타입으로 직접 지정 가능합니다.
```javascript
cont["c.getSampleList"]().excelDownload([ "샘플 목록", {
    id : "name",
    id : "name",
} ]);
```
- arguments[3] : 추가 컬럼명 정보 Object - 엑셀 파일의 컬럼 중 N.grid 의 헤더에 타이틀 정보가 없는 컬럼들의 타이틀을 추가로 정의할 수 있습니다. arguments[1]으로 지정된 컬럼정보를 extend 합니다.
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
>isXlsxRequest 변수는 xlsx 요청일때만 true 로 값이 세팅되고 일반 json 요청일 때는 변수가 생성되지 않습니다.

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

#### 2.2. 엑셀 대용량(Streaming) 다운로드

N.comm 의 excelStreaming 메서드를 통해 서버에서 대용량 데이터를 엑셀파일로 받을 수 있습니다.

사용법은 N.comm 의 excelDownload 메서드와 동일 하지만 데이터 리턴 시 Controller, Service 를 거치지 않고 MyBatis 단에서 바로 엑셀파일을 생성하여 데이터를 반환하므로 반환데이터에 대해 후 처리를할 수 없습니다.
반환데이터를 참조하여 로직을 분기하거나 데이터를 가공을 하려면  SQL 쿼리로 처리하거나 excelDownload 메서드를 사용해야 합니다.

>MyBatis 의 하단에서 처리되어 속도가 빠르고 엑셀 파일 전체를 서버 메모리에 저장 하지 않고 100 로우 마다 flush 하여 대용량 데이터 다운로드 시 Heap Memory 가 Overflow 되어 WAS 가 다운되는 현상을 방지해 줍니다.

```javascript
if (cont["p.form.search"].validate()) {
    cont["c.getSampleList"]().excelStreaming([ "샘플 목록", cont["p.grid.master"] ]);
}
```

### 3. "e." 으로 시작 - 이벤트 바인딩

view 안에 있는 요소들에 이벤트 바인딩을 Controller object 에 선언하고 이벤트 핸들러를 정의 할 수 있습니다.

>a, button, input[type=button] 요소에 이벤트를 선언하면 N.button 컴포넌트가 자동으로 적용되고 Controller object 속성 값으로 정의한 이벤트 핸들러가 N.button 의 인스턴스로 대체 됩니다.

```
"e.{요소id}.{이벤트유형}" : function(e, [idx]) {
    // 이벤트 핸들러
}
```

또는

```
"e.{이벤트구분자}.{이벤트유형}" : {
    target : "{요소 selector}",
    handler : function(e, [idx]) {
        // Event handler
    }
}
```

id 이외의 속성을 가진 요소를 선택할 때는 target 속성에 jQuery selector 문자열을 지정하면 됩니다. 이때 셀렉터의 context 를 view 요소로 지정하지 않아도 view 요소가 context 인수로 자동으로 지정됩니다.

이벤트 바인딩이 완료되면 `e.{요소id}.{이벤트유형}` 속성 값으로 정의한 이벤트 핸들러 함수는 대상요소(jQuery object)로 대체됩니다.

```
...
var cont = N(".page-id").cont({
    "e.id.click" : function(e) { // 이벤트 바인딩이 완료되면 이 이벤트 핸들러 함수는 대상 요소(jQuery object)로 대체됩니다.
        e.preventDefault();

        cont["p.popup.dept"].open();
    },
    "e.id.click" : {
        target : ".div #id",
        handler : function(e) { // 이벤트 바인딩이 완료되면 이 이벤트 핸들러 함수는 대상 요소(jQuery object)로 대체됩니다.
            e.preventDefault();

            cont["p.popup.company"].open();
        }
    },
    init : function(view, request) {
        cont["e.id.click"].trigger("click"); // DOM 로딩이 완료되면 이 이벤트를 실행합니다.
    }
});
...
```

다음과 같이 컴포넌트에서 제공하는 이벤트도 적용 가능합니다.

```
...
var cont = N(".page-id").cont({
    "e.dateInput.onSelect" : function(e, inputEle, selDate, isMonthonly, idx) { // N.datepicker의 onSelect 이벤트
        e.preventDefault();

        N.log(selDate.obj.formatDate(selDate.format)); // 선택한 날짜를 설정된 Date 포맷으로 추출하여 브라우저 콘솔에 출력.
    },
});
...
```

N.grid 나 N.list 컴포넌트 안의 요소를 지정하면 이벤트 핸들러 함수의 마지막 인수에 `해당 요소가 포함된 행의 인덱스를 반환` 해 줍니다.

>rowHandler 나 rowHandlerBeforeBind 에서 행마다 이벤트를 바인딩하면 브라우저 Heap 메모리 사용량이 이벤트 수 X 행 수만큼 늘어나 웹 애플리케이션 성능이 저하됩니다. 아래 방법(이벤트 위임 적용)을 사용하면 이벤트에 의한 메모리 사용량을 크게 줄일 수 있습니다.

```
...
var cont = N(".page-id").cont({
    "e.id.click" : function(e, idx) {
        e.preventDefault();

        N.log(cont["p.grid.id"].data()[idx]); // Print row data containing the clicked button to the browser console.
    }
});
...
```

> 아래와 같이 target 속성으로 요소를 지정하면 Selector 의 context 가 view 요소가 아닌 N.grid 의 행 요소(tbody)로 설정됩니다.

```
...
"e.col01.click" : {
    target : ".col01", // Selector의 context가 view 요소가 아닌 N.grid의 행 요소(tbody)로 설정됩니다.
    handler : function(e, idx) {
        // TODO
    }
}
...
```

>Natural-JS는 내부 데이터와 입력된 데이터를 동기화하기 위해서 select 요소는 change 이벤트를, radio, checkbox 요소는 click 이벤트를, 그 외 text 입력 요소(text, textarea, number 등)는 focusout 이벤트를 사용합니다. 
>컴포넌트의 내부 데이터를 가져올 때는 반드시 위와 같은 이벤트유형으로 바인딩해 줘야 합니다. 
>그렇지 않으면 변경되기 이전의 데이터가 반환됩니다.

```
...
var cont = N(".page-id").cont({
    "e.textInput.focusout" : function(e, idx) { // change 이벤트로 바인딩하면 변경되기 이전의 데이터가 반환됩니다.
        e.preventDefault();

        N.log(cont["p.grid.id"].val(idx, "textInput"));
    }
});
...
```

### 4. 유틸리티

샘플 프로젝트의 공통 유틸리티입니다.

#### 콘솔 로그 및 에러 처리

Natural-CORE 패키지에는 console 객체에서 제공하는 log, warn, info 등의 함수들을 Wrapping 하여 로그레벨을 중앙에서 컨트롤할 수 있는 기능을 제공합니다. 이 기능을 사용하기 위해서는 다음과 같은 명령어로 로그와 에러등을 처리해야 합니다.
- N.log : 브라우저 콘솔에 LOG(DEBUG) 메시지를 표시합니다. 사용법은 Javascript 의 console.log 와 동일하고 `N.log("디버그 메시지");` 와 같이 간단하게 사용할 수 있습니다.
- N.info : 브라우저 콘솔에 INFO 레벨의 메시지를 표시합니다. 사용법은 Javascript 의 console.info 와 동일하고 `N.warn("경고 메시지");` 와 같이 간단하게 사용할 수 있습니다.

> 브라우저에서 console.info 을 지원하지 않으면 N.log 와 같이 작동됩니다.

- N.warn : 브라우저 콘솔에 WARN 레벨의 메시지를 표시합니다. 사용법은 Javascript 의 console.warn 과 동일하고 `N.warn("경고 메시지");` 와 같이 간단하게 사용할 수 있습니다.

> 브라우저에서 console.warn 을 지원하지 않으면 N.log 와 같이 작동됩니다.
> N.warn 에 Error 객체를 넣으면 Error stack trace 를 표시해 줍니다.

- N.error : 에러객체를 생성하고 에러 메시지와 Error stack trace 를 브라우저 콘솔에 표시해 줍니다. 기본적인 사용법은 Javascript 의 console.error 와 동일 하나 N.error 함수는 에러 객체를 생성해 주므로 `throw N.error("에러 메시지");`와 같이 선언하면 에러메시지를 표시하고 에러를 발생시켜 이후 로직들이 중단됩니다.

#### APP.comm.utils.del

N.grid 나 N.list 에서 체크되거나 선택된 행들을 삭제하기 위한 함수 - 삭제 전 동의 메시지 다이얼로그 표시 후 remove 함수 호출 등의 반복적인 루틴들을 한 번에 처리해 줍니다.

- Arguments Object
    - obj.cont : N.cont object
	- obj.inst : 행을 삭제할 N.grid 인스턴스 명,
	- obj.before : 선택한 행을 삭제하기 전 실행할 함수. 핸들러 함수의 인수로 체크한 행의 index 들을 반환합니다.
	- obj.after : 선택한 행을 삭제 한 후 실행할 함수.

>.call(this) 로 함수를 호출하여 호출되는 함수의 this 에 호출하는 함수의 this 를 바인딩해 줘야 합니다.

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

- Arguments Object
    - obj.cont : N.cont object
    - obj.comm : 데이터 저장을 처리하는 N.comm 이 정의된 함수명.
    - obj.msg : 저장 확인 메시지, undefined 이면 기본 메시지가 표시됨.
    - obj.changed : 변경된 데이터 유무를 참조할 컴포넌트 인스턴스 명.
    - obj.validate : 추가/수정된 데이터의 유효성을 검증할 컴포넌트 인스턴스 명.
    - obj.before : 서버에 저장하기 전 실행할 함수.
    - obj.after : 서버에 저장 한 후 실행할 함수.

>.call(this)로 함수를 호출하여 호출되는 함수의 this 에 호출하는 함수의 this 를 바인딩해 줘야 합니다.

```
...
"e.btnSave.click" : function(e) {
    e.preventDefault();

    return APP.comm.utils.save.call(this, {
        cont : cont,
        comm : "c.saveSampleList",
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

N.grid 나 N.list 의 행을 선택했을 때(onSelect 이벤트 핸들러 함수 이용) N.form 컴포넌트에 같은 데이터를 연동하기 위한 반복적인 루틴들을 한 번에 처리해 줍니다.

- Arguments Object
    - opts.cont : N.cont object
	- opts.form : 데이터를 연동할 N.form 인스턴스 명.
    - opts.dataSync : false 를 입력하면 N.grid 나 N.list 컴포넌트의 데이터와 N.form 의 데이터 참조를 끊어 데이터가 실시간 동기화되지 않습니다.

>.call(this)로 함수를 호출하여 호출되는 함수의 this 에 호출하는 함수의 this 를 바인딩해 줘야 합니다.

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

- Arguments Object
    - fileList : 파일 목록 Array
   	- fileNameCol : 파일명 컬럼명
   	- length : 파일 목록 문자열을 자를 기준 길이
   	- fileButton : 파일팝업 버튼(입력하지 않으면 파일요약목록 문자열을 반환하고 입력하면 버튼 옆에 목록을 표시해 준다)

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

- Arguments Object
    - fileId : 파일 아이디
   	- fileNameCol : 파일명 컬럼명
   	- length : 파일 목록 문자열을 자를 기준 길이
   	- fileButton : 파일팝업 버튼(입력하지 않으면 파일요약목록 문자열을 반환하고 입력하면 버튼 옆에 목록을 표시해 준다)

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

- Arguments
    - arguments[0] : 조회 파라미터 - JSON Object 타입으로 조회 파라미터를 정의합니다.
    - arguments[1] : 조회 URL - 조회 URL 을 선언합니다. 조회 URL 의 확장자는 .xlsx 입니다. .json 으로 호출되는 서비스를 확장자만 .xlsx 로 바꿔주면 해당 서비스에서 반환되는 데이터를 엑셀파일로 받을 수 있습니다. 해당 서비스의 Controller 의 @RequestMapping 에 반드시 `@RequestMapping(value = { "getSampleList.json", "getSampleList.xlsx" })` 와 같이 .json 과 .xlsx 확장자를 가진 URL 을 두개 이상 Mapping 해줘야 합니다.
    - arguments[2] : 엑셀 파일 명
    - arguments[3] : N.grid 인스턴스 or 컬럼명 정보 Object - N.grid 인스턴스를 넣으면 헤더의 타이틀을 엑셀파일의 타이틀로 지정해 줍니다. N.list 등 컬럼 타이틀 정보가 없는 컴포넌트를 사용할 때는 Object 타입으로 직접 지정 가능합니다.
    - arguments[4] : 추가 컬럼명 정보 Object - 엑셀 파일의 컬럼 중 N.grid 의 헤더에 타이틀 정보가 없는 컬럼들의 타이틀을 추가로 정의할 수 있습니다.
        - 추가 컬럼명 정보 데이터는 arguments[3]의 데이터에 extend 됩니다. arguments[3]의 오브젝트에 정의되어 있지만 arguments[4]의 오브젝트에 없는 컬럼정보는 그대로 유지되고 arguments[4] 의 오브젝트에는 정의되어 있지만 arguments[3]의 오브젝트에 정의되어 있지 않은 컬럼정보는 arguments[3]의 오브젝트에 추가됩니다.

자세한 사용법은 [2.1. 엑셀 다운로드](#21)를 참고 바랍니다.

## Back-End 개발

#### Back-End 개발을 위해 JAVA 와 Spring MVC 에 대한 학습이 선행되어야 합니다.

### 1. 네이밍(Naming)

#### 1.1. 패키지 구조

이 예제의 JAVA 기본 패키지는 framework.naturaljs.app.sample 이고 Sample 패키지를 예를 들어 패키지 구조를 설명하면 다음과 같습니다.
 - framework.naturaljs.app.sample : Spring MVC 의 Controller 소스코드들이 담겨 있는 패키지
 - framework.naturaljs.app.sample.service.impl : 비즈니스 로직을 처리하는 Service 소스코드들이 담겨있는 패키지
 - framework.naturaljs.app.sample.mappers : SQL 쿼리가 기록되어 있는 MyBatis Mapper XML 파일과 이 파일안의 쿼리 ID 들을 JAVA 메서드로 연결해주는 Mapper JAVA 인터페이스 파일이 담겨있는 패키지
 - framework.naturaljs.app.sample.vo : VO 객체(get/set Bean)
>VO(Value Object) 객체는 get/set Bean 으로 처리하지 않고 파라미터부터 리턴되는 데이터까지 모두 `Map<String, Object>` 나 `List<Map<String, Object>>`로 처리됩니다.
>그러나 Bean 타입의 VO 객체가 필요하면 빈을 만들어서 써도 상관없습니다.

#### 1.2. @RequestMapping

Controller 의 @RequestMapping 은 Camel Case 문자열 형태로 다음과 같이 정의되어 있습니다.
 - 단건 조회 : `get` + `MethodName` + `.json`
 - 목록 조회 : `get` + `MethodName` + `List` + `.json`
 - 단건 입력 : `insert` + `MethodName` + `.json`
 - 단건 수정 : `update` + `MethodName` + `.json`
 - 단건 삭제 : `delete` + `MethodName` + `.json`
 - 다건 저장(입력/수정/삭제) : `save` + `MethodName` + `List.json`
 - 출력 : `get` + `MethodName` + `.print`
 - 엑셀 다운로드 : `get` + `MethodName` + `List` + `.xlsx`
 - 엑셀 업로드 : `save` + `MethodName` + `.xlsx`

Controller, ServiceImpl, Mapper 클래스의 메서드명이나 Mapper XML 의 id는 위 네이밍룰에서 확장자만 뺀 명칭으로 정의하면 됩니다.

>목록 조회와 엑셀 다운로드는 URL 의 확장자로만 구분하고 같은 Controller, ServiceImpl, Mapper 클래스를 사용합니다.

### 2. Controller 개발

Controller 는 기본적으로 다음 예제와 같이 구성되어 있습니다.

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

	@RequestMapping("saveSampleList.json")
	public Map<String, Object> saveSampleList(@RequestBody List<Map<String, Object>> voList) {
		return sampleService.saveSampleList(voList);
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

 1. 클래스 선언문 상단에 @Controller 어노테이션을 선언하여 Spring MVC 의 Controller 클래스로 생성합니다.
 2. 클래스 선언문 상단에 @RequestMapping 어노테이션으로 이 컨트롤러를 접근할 URL 의 기본 경로(path)를 정의합니다.
 3. public class 선언문을 통해 Controller 클래스를 생성합니다.
 4. 생성된 클래스에 사용할 서비스 클래스들을 @Resource 어노테이션을 통해 얻어옵니다.
 5. 이제 단위 URL 에 매핑될 컨트롤러 메서드를 생성해 줍니다. 먼저 @RequestMapping 어노테이션을 통해 접근할 수 있는 URL 을 정의합니다.
  >클래스 선언문 위의 @RequestMapping 의 경로와 메서드 선언문 위의 @RequestMapping 의 경로가 합해져서 해당 메서드에 접근하는 최종 URL 이 생성됩니다.

 6. 컨트롤러 메서드의 리턴타입은 `Map<String, Object>`이나 `List<Map<String, Object>>`로 정의합니다.
  >반환된 Map 이나 List 객체는 자동으로 JSON 형태의 문자열로 직렬화되고 HTTP Response Body 에 적제되어 클라이언트 브라우저로 전송됩니다. 전송된 JSON 문자열은 Javascript Object 로 인스턴스화됩니다.

  >java 의 List 객체는 클라이언트 브라우저에서 javascript array[object] 객체로 변환되고 Map 객체는 javascript object 로 변환됩니다.

 7. 파라미터 선언은 목록데이터는 `List<Map<String, Object>>` 타입으로 정의하고 단건 데이터는 `Map<String, Object>` 타입으로 선언하되 클라이언트에서 request body 에 담아 Ajax 로 전달되기 때문에 앞에 @RequestBody 어노테이션을 선언해 줍니다.
  >클라이언트 브라우저에서 파라미터로 지정한 javascript array[object] 타입의 데이터 객체나 object 타입의 데이터 객체는 JSON 문자열로 직렬화 되에 HTTP Request Body 에 적제되어 서버로 전송됩니다. 전송된 JSON 문자열은 java 의 List 나 Map 객체로 인스턴스화됩니다.

  >전달된 javascript array[object] 객체는 java 의 List 객체로 변환되고 javascript object 는 Map 객체로 변환됩니다.

  >@RequestBody 의 옵션 중 `required`는 파라미터가 필수 인지 아닌지를 결정하는 옵션입니다. `true(기본값)로 지정`하면 브라우저로부터 `전달된 데이터가 없으면 서버에서 에러가 발생`하여 요청이 중단되므로 비어있는 오브젝트({}) 라도 전달해야 합니다.
  `파라미터가 필요하지 않은 컨트롤러 메서드`라면 반드시 `required = false`를 지정해 주세요.

 8. 컨트롤러 메서드 선언이 끝났으면 service 클래스를 이용하여 데이터를 반환하는등의 컨트롤러 메서드의 로직을 채워 줍니다.

### 3. ServiceImpl(Service) 개발

Service 는 기본적으로 다음 예제와 같이 구성되어 있습니다.

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

    public Map<String, Object> saveSampleList(List<Map<String, Object>> voList) {
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

 1. 클래스 선언문 상단에 @Service 어노테이션을 선언하여 Spring MVC 의 Service 클래스로 생성합니다. `@Service 어노테이션의 value 값은 ServiceImpl 클래스의 Impl을 제거한 클래스 명칭을 기입` 합니다.
 >ServiceImpl 클래스는 @Service 어노테이션을 선언하는 것을 빼면 일반 POJO 클래스를 개발하는 방식과 같습니다.

 2. public class 선언문을 통해 ServiceImpl 클래스를 생성합니다.
 3. 생성된 클래스에 사용할 Mapper 클래스들을 @Autowired 어노테이션을 통해 얻어옵니다.
 >private SampleMapper sampleMapper;와 같이 Mapper 클래스의 변수명은 클래스명에서 첫글자만 소문자로 바꾼 형태로 정의합니다.

 4. 서비스 메서드를 생성합니다.
 > insert, update, delete 메서드는 반드시 int 형을 리턴하도록 정의해 줍니다.

 > save 메서드는 그리드(N.grid)나 리스트(N.list)에서 넘어온 다건의 입력/수정/삭제를 처리하는 메서드입니다. 위 예제의 구문을 그대로 복사하여 사용하거나 rowStatus 별로 관련 로직을 더 채워 사용하면 됩니다.

### 4. MyBatis Mapper XML 개발

MyBatis Mapper XML 파일은 기본적으로 다음 예제와 같이 구성되어 있습니다.

```sql
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="framework.naturaljs.app.sample.mappers.SampleMapper">
...
    <select id="getSample" parameterType="java.util.Map" resultType="java.util.Map">
	    /* framework.naturaljs.app.sample.mappers.SampleMapper.getSample */
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

MyBatis Mapper XML 의 개발 방법은 다음과 같습니다.
1. mapper 태그의 namespace 속성에 Mapper XML 파일이 있는 패키지 경로를 입력합니다.
> Mapper Interface 가 이 namespace 참고하여 자동으로 연결해 주므로 정확하게 입력해야 합니다.

2. select, insert, update, delete 태그를 이용하여 쿼리를 작성합니다.
> 빠른 디버깅을 위해 `반드시` 쿼리 상단에 주석`(/* */)`으로 해당 `namespace와 id를 더하여 기입` 바랍니다.

3. parameterType 타입은 기본적으로 `java.util.Map` 을 선언해 주고 데이터를 get/set Bean 에 담길 원하면 해당 클래스를 지정해 줍니다.
4. resultType 은 java.util.Map 을 선언해 줍니다.

> 쿼리 파라미터할당은 반드시 `#{}` 로 지정해야하고 `${}` 치환하는 구문은 지양해야 합니다.
`${}`로 처리하면 SQL Injection 공격에 취약하게 됩니다. 어쩔 수 없이 `${}` 를 사용해야 한다면
반드시 ServiceImpl 클래스에서 해당 파라미터에 대해서 SQL Injection 공격 문자열을 제거 바랍니다.
> MyBatis Mapper XML 의 변수할당이나 분기처리 등의 문법은
[MyBatis Mapper XML 매뉴얼](html/com/app/sample/mybatis-mapper-xml.pdf) 과
[MyBatis 동적 SQL 매뉴얼](html/com/app/sample/mybatis-dynamic-sql.pdf)을 참고 바랍니다.

#### 4.1. DB 페이징

페이징은 Natural-JS의 N.pagination 컴포넌트를 사용합니다.
N.pagination 의 사용방법은 [조회폼+그리드+DB페이징](#dHlwZTAzMDElMjQlRUMlQTElQjAlRUQlOUElOEMlRUQlOEYlQkMlMkIlRUElQjclQjglRUIlQTYlQUMlRUIlOTMlOUMlMkJEQiVFRCU4RSU5OCVFQyU5RCVCNCVFQyVBNyU5NSUyNGZhbHNl) 템플릿과 [Natural-UI API Document 의 Pagination 탭](naturaljs/index.html#refr/refr0105)을 참고 바랍니다.
N.pagination 에서 Mybatis Mapper SQL 의 변수로 다음과 같은 페이징 정보를 전달해 줍니다.

- pageNo : 선택한 페이지 번호
- countPerPage : 페이지당 행 수
- countPerPageSet : 페이지 세트당 페이지 수
- currSelPageSet : 현재 페이지세트(페이지 그룹) 번호
- pageCount : 전체 페이지 개수
- pageSetCount : 전체 페이지세트(페이지 그룹) 개수
- totalCount : 전체 행 개수
- startPage : 현재 페이지세트(페이지 그룹) 중 첫 번째 페이지 번호
- startRowIndex : 선택한 페이지의 첫 번째 행 인덱스
- startRowNum : 선택한 페이지의 첫 번째 행 번호
- endPage : 현재 페이지세트(페이지 그룹) 중 마지막 페이지 번호
- endRowIndex : 선택한 페이지의 마지막 행 인덱스
- endRowNum : 선택한 페이지의 마지막 행 번호

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

### 5. Mapper Interface 개발

Mapper Interface 는 기본적으로 다음 예제와 같이 구성되어 있습니다.

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

 1. 인터페이스 선언문 상단에 @Mapper 어노테이션을 선언하여 MyBatis 의 Mapper 인터페이스로 생성합니다.
 > 생성된 인터페이스는 mappers 패키지 안에 Mapper XML 파일과 같이 있어야 합니다.

 2. 인터페이스 메서드를 생성합니다.
 > 메서드의 명칭은 Mapper XML 파일의 연결하고자 하는 쿼리의 id와 일치해야 합니다.

 > Arguments 타입은 Service 에서 단건으로 처리되어 넘어오므로 `Map<String, Object>`로 선언합니다.

 > 리턴타입은 java.util.Map 도 Map 인터페이스를 상속하여 구현된 객체이므로 `List<Map<String, Object>>`로 선언합니다. 단건 조회도 UI 개발의 편의성을 위해 `List<Map<String, Object>>`로 선언 바랍니다(Natural-JS는 단건이든 다건이든 Array 타입으로 컴포넌트에 바인딩 함).

### 6. 기타

#### 6.1. 예외처리(Exception)

서버에서 BizException 을 던지면 화면에 N.alert 컴포넌트로 던진 메시지가 표시됩니다.

BizException 의 생성자 메서드는 다음과 표와 같이 인수의 개수 또는 타입에 따라 다음과 같은 기능을 제공합니다.

| 생성자 | 설명 |
| :-- | :-- |
| `BizException(int code)` | Message Properties 파일에서 message 로 지정한 프로퍼티의 메시지 값을 표시합니다. |
| `BizException(int code, String[] args)` | Message Properties 파일에서 message 로 지정한 프로퍼티의 메시지 값을 표시하고 args 인수로 지정한 메시지 파라미터를 메시지에 바인딩합니다. |

아래 예와 같이 서버단 java 에서 BizException 을 던지면 화면의 Natural-UI의 N.alert 컴포넌트로 메시지가 표시됩니다.

```java
if(Condition) {
    throw new BizException(-30001, ["날짜"]);
}
```

메시지 프로퍼티는 src/main/resources/messages 폴더의 message-{업무대분류코드}.properties 파일에(없으면 생성 바람) {메시지코드}={메시지}와 같은 형식으로 지정 바랍니다.

>메시지에 대한 파라미터는  BizException 생성자 함수의 두 번째 인수(Array 타입)로 지정 가능하고 메시지의 변수는 파라미터 배열의 순서에 따라 {0}, {1}, {2} 와같이 선언합니다.

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

#### 6.2. 마스킹

마스킹은 DB의 스키마나 테이블 상관없이 대상 컬럼명들을 src/main/resources/config/common/data.properties 파일의 masking.columns 속성 값으로 다음과 같이 정의해 주면 됩니다.

```
{컬럼명}|{마스킹룰명}, {컬럼명}|{마스킹룰명}
```

```
masking.columns=rrnNo|rrn, name|name, phone|phone, address|address, email|email, cardNo|card
```
>컬럼명은 카멜케이싱(camel-case)된 컬럼명으로 선언해 줘야하고 콤마(,)로 구분하여 여러 개 선언 가능합니다.
>get 으로 시작하는 Url Mapping 만 처리됩니다.

마스킹 룰은 ```name(성명), rrn(주민등록번호), frn(외국인등록번호), pn(여권번호), dln(운전면허번호), phone(전화번호, 휴대폰번호), email(이메일주소), card(카드번호), an(계좌번호), ip(IP주소)```가 있고 MaskingUtils(src/main/java/framework/utils/MaskingUtils.java)를 통해 처리됩니다.

>마스킹 룰을 추가하려면 MaskingUtils 의 maskString 메서드에 추가하고 싶은 룰의 케이스를 추가해 주고 같은 룰명으로 처리 함수를 추가해 주면 됩니다.

>마스킹은 MyBatis 의 하단에서 처리되어 마스킹에 의한 성능 저하는 최소화되어 있습니다.

마스킹 대상컬럼을 선언 했으나 특정 요청은 마스킹을하고 싶지 않으면 masking.exclude.urls 속성 값에 URL 을 다음과 같이 Ant Path 문법으로 선언해 주면 됩니다.
```
masking.exclude.urls=/**/sample/getSampleList.json, /**/sample/getSampleBigList.xlsx
```

>콤마(,)로 구분하여 여러 개 선언 가능합니다.

만약 관리화면등에서 마스킹되지 않은 데이터를 봐야 한다면 `소스파일을 분할하지 말고` Controller 의 RequestMapping 에 다음과 같이 URL 2개를 등록하고
그중 관리화면에서 사용할 URL 1개를 masking.exclude.urls 속성 값으로 등록 한 후 관리자
권한이 있는 사용자는 masking.exclude.urls 속성 값으로 등록된 URL 을 호출하도록 프로그래밍하면 됩니다.

```
@RequestMapping(value = { "getSampleList", "getAdminSampleList" })
```

#### 6.3. 최대 조회건수 제한

쾌적한 서버 운영환경을 유지하기 위해 DB 데이터의 최대 행(row) 수를 제한할 수 있습니다.
src/main/resources/config/common/data.properties 파일의 max.rows.limit 속성 값에 최대 행 수를 지정 가능하고 max.rows.exclude.urls 속성에 예외 URL 들을 등록 가능합니다.

>엑셀 다운로드 요청은 최대 조회 건수 제한에 걸리지 않고 모든 데이터를 내려 받습니다.

#### 6.4. XSS 공격 차단

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

HttpServletRequest.getParameter() 로 값을 가져올 수 있는 GET 이나 POST 요청 뿐만 아니라 Natural-JS의 N.comm 에 의해 Request Body 에 실려 넘어오는 JSON 문자열도
Controller 메서드들의 Map 이나 List 타입의 인수로 변환되면서 XSS Filter 가 적용됩니다.

변환된 문자열은 DB 단까지 전달되어 그대로 저장되고 DB 에서 데이터를 다시 조회하여 UI로 넘어왔을때 Natural-UI의 데이터 관련 여러 컴포넌트들이 HTML 컨트롤의 특성을 파악하여 자동으로 원래 문자열로 표시해 줍니다.

src/main/resources/config/common/data.properties 파일의 xss.exclude.urls 속성에 URL 들을 등록하면 등록된 URL(요청) 은 XSS 공격 문자열을 필터링하지 않습니다.

## Supports

### Support browsers

- PC : Chrome, Edge, Firefox, Safari, Opera, Internet Explorer 11(limited support)
- Mobile : iOS Safari, iOS UIWebView, Android Browser, Android Chrome, Android WebView

### Training and support

- Please contact us at <bbalganjjm@gmail.com>

### License
This software is licensed under the [Apache License v2.0](https://github.com/bbalganjjm/natural_js/blob/master/LICENSE) &copy; Goldman Kim<<bbalganjjm@gmail.com>>
