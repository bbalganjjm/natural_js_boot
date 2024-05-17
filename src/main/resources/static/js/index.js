(function(window, N) {

    var IndexController = {
        docs : null,
        init : function() {
            this.mobileResponsiveView();
        	this.setLocale();
            this.loadHeader();
            this.loadLefter();
            this.loadFooter();
            this.initBrowserHistorySystem();
        },
        loadHeader : function() {
            N("header").comm("header.view").submit();
        },
        loadLefter : function() {
            N(".main-nav").comm("lefter.view").submit(function () {
                APP.indx.loadBody();
            });
        },
        loadBody : function() {
            N(".page-content").comm("contents.view").submit();
        },
        loadFooter : function() {
            N("footer").comm("footer.view").submit();
        },
        initBrowserHistorySystem : function() {
            var self = this;
            $(window).on("hashchange.index", function() {
                if(self.docs) {
                    if (!N.string.isEmpty(location.hash)
                        && (N.string.endsWith(location.hash, ".html") || N.string.endsWith(location.hash, ".view"))) {
                        var url = location.hash.replace("#", "");
                        var selectedMenuEle = N(".index-lefter.view_context__ a[href='" + url + "']");
                        var docId = selectedMenuEle.find("span:last").text();
                        if(self.docs.options.order[0] !== docId) {
                            self.docs.add(selectedMenuEle.data("pageid"), docId, {
                                "url" : url
                            });
                        }
                    }
                }
            });
        },
        setLocale : function() {
    		N.locale(window.sessionStorage.locale !== undefined ? window.sessionStorage.locale : IndexController.getLocale().toLowerCase().indexOf("ko") > -1 ? "ko_KR" : "en_US");
    		N("html").attr("lang", N.locale().substring(0, 2));
    	},
        getLocale : function() {
        	if(navigator) {
	   			if (navigator.language) {
	   				return navigator.language;
	   			} else if (navigator.browserLanguage) {
	   				return navigator.browserLanguage;
	   			} else if (navigator.systemLanguage) {
	   				return navigator.systemLanguage;
	   			} else if (navigator.userLanguage) {
	   				return navigator.userLanguage;
	   			}
	   		}
	   	},
        mobileResponsiveView : function() {
            // API 문서 모바일 용 보기 처리 이벤트
            N(window).on("resize.mobile", function(e, view) {

                if (e.target == window || view) { // 모바일에서 scroll 시 resize 이벤트가 firing 되서(ios, android 동일).

                    if(!$("#methods").hasClass("visible__") && !$("#constructor").hasClass("visible__")) {
                        return false;
                    }

                    N(".agrsIndex", view).remove();
                    N(".function-desc", view).removeClass("function-desc");

                    if($(window).width() <= 751) { // 768 - 17px(?)
                        $("td:contains('N/A'):visible", view).css({
                            "visibility": "hidden",
                            "padding" : 0,
                            "margin" : 0,
                            "height" : 0,
                            "line-height" : 0
                        });

                        var idx = -1;
                        $("tr:visible", view).each(function() {
                            if($(this).find(">td:eq(0)").text().length > 0) {
                                idx = -1;
                                $(this).find(">td:eq(0)").siblings("td").addClass("function-desc");
                            }
                            $(this).find(">td:eq(1)").prepend('<strong class="agrsIndex">[' + idx + '] : </strong>');
                            idx++;
                        });
                    } else {
                        $("[id='methods'], [id='constructor']").find("tr .function-desc").removeClass("function-desc");
                        $("[id='methods'], [id='constructor']").find("tr .agrsIndex").remove();

                        $("[id='methods'], [id='constructor']").find("td:contains('N/A')").css({
                            "visibility": "",
                            "padding" : "",
                            "margin" : "",
                            "height" : "",
                            "line-height" : ""
                        });
                    }

                }

            });

        }
    };

    if (!window.APP) {
        window.APP = {};
    }
    window.APP.indx = IndexController;

})(window, N);
