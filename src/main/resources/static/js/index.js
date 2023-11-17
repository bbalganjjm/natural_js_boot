(function(window, N) {

    var IndexController = {
        docs : null,
        init : function() {
            this.mobileResponsiveView();
        	this.setLocale();
            this.loadHeader();
            this.loadLefter();
            this.loadBody();
            this.loadFooter();
            this.initBrowserHistorySystem();
        },
        loadHeader : function() {
            N("header").comm("header.view").submit();
        },
        loadLefter : function() {
            N(".main-nav").comm("lefter.view").submit();
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
                var docId, docNm, url;
                if (N.string.trim(location.hash).length === 0) {
                    docId = "documents";
                    docNm = "개발가이드";

                    url = "html/com/app/sample/" + docId + ".html";
                }

                if ((docId === "documents" || N.string.trim(location.hash).length > 35) && !N.string.endsWith(location.href, "#")) {
                    var menuInfoStr = "";
                    var menuInfo = "";
                    try {
                        menuInfoStr = location.hash.replace("#", "");
                        menuInfo = decodeURIComponent(atob(menuInfoStr)).split("$");
                    } catch(e) {
                        N.warn(e);
                    };

                    if (menuInfo.length > 1) {
                        if(!N.string.isEmpty(menuInfo[0])) {
                            docId = menuInfo[0];
                        }
                        if(!N.string.isEmpty(menuInfo[1])) {
                            docNm = menuInfo[1];
                        }

                        // FIXME 메뉴 DB 만들어 지고 페이지 불러오는 서비스 만들어지면 아래 url 수정해서 살리고 url = menuInfo[2];는 제거 바람.
                        // url = "html/com/app/sample/" + docId + ".html";
                        if(menuInfo[2]) {
                            url = menuInfo[2];
                        }
                    }

                    if(self.docs) {
                        if(self.docs.options.order[0] !== docId) {
                            // N.docs MDI 탭 닫을 때 페이지에서 사용된 라이브러리 제거
                            var onRemove;
                            if(docId === "documents") {
                                onRemove = function(docId) {
                                    showdown = undefined;
                                }
                            }

                            self.docs.add(docId, docNm, {
                                "url" : url,
                                "onRemove" : onRemove
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
        i18n : function(locale, view) {
    		if(locale === undefined) {
    			locale = N.locale();
    		}
    		if(locale === "ko_KR") {
        		$("[lang='ko_KR']", view).show();
    			$("[lang='en_US']", view).remove();
    		} else {
    			$("[lang='en_US']", view).show();
    			$("[lang='ko_KR']", view).remove();
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
