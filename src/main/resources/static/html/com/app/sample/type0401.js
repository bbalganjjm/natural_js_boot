(function() {

    var cont = N(".type0401").cont({
        init : function(view, request) {
            cont["e.btnSearch.click"].click();
        },
        "e.btnSearch.click" : function (e) {
            e.preventDefault();

            if (cont["p.form.search"].validate()) {
                cont["c.getSampleListJson"]().submit(function(data) {
                    // N.grid bind
                    cont["p.grid.master"].bind(data);
                });
            }
        },
        "c.getSampleListJson" : function() {
            return cont["p.form.search"].data(false).comm("/sample/getSampleList.json");
        },
        "c.getCommonCodeList" : function() {
            return N.comm("sample/getSampleList.json");
        },
        "p.form.search" : {
            usage: "search-box"
        },
        "p.grid.master" : {
            height: 500
        },
        "p.select.gender" : {
            code: "gender"
        },
        "p.select.eyeColor" : {
            code: "eyeColor"
        },
        "p.form.detail" : {
            onBeforeBindValue: function(ele, val, action) {
                // this : N.form 인스턴스
                // ele : 바인딩될 요소
                // val : 바인딩될 값
                // action : N.form 인스턴스의 메서드 명 - "bind" | "val"


                return val; // 반드시 처리된 값을 다시 리턴해야 합니다.
            }
        },
    });

})();
//# sourceURL=html/com/app/sample/type0401.js

