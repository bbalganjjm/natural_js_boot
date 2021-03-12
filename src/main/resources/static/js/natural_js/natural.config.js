/**
 * Config(natural.config.js) is a place to save Natural-JS operation environment setting, AOP setting, Communication Filter setting, UI global option value, etc.
 *
 * For more information on settings, please refer to the
 * Natural-CORE > Config( https://bbalganjjm.github.io/natural_js/#cmVmcjAxMDIlMjRDb25maWckaHRtbCUyRm5hdHVyYWxqcyUyRnJlZnIlMkZyZWZyMDEwMi5odG1s )
 * menu on the Natural-JS site.
 */
(function(N) {
    /**
     * Natural-CORE Config
     */
    N.context.attr("core", {
        /**
         * Default locale
         */
        "locale" : "ko_KR",
        /**
         * Default value when one checkbox is checked in N().vals method
         *  - Define according to the project data standards such as "Y", "1", and "on".
         */
        "sgChkdVal" : "Y",
        /**
         * Default value when one checkbox is unchecked in N().vals method
         *  - Define according to the project data standards such as "N", "0", and "off".
         */
        "sgUnChkdVal" : "N",
        /**
         * String separator used in Natural-JS
         */
        "spltSepa" : "$@^",
        /**
         * Garbage collection mode of N.gc function
         *  - minimum, full
         */
        "gcMode" : "full",
        /**
         * Set the default byte length of characters except single-byte characters such as English and numeric characters.
         *  - charByteLength is used in logic that checks the length of a string, such as N.string.byteLength function and maxbyte, minbyte, rangebyte verification rules, etc.
         */
        "charByteLength" : 3
    });

    /**
     * Natural-ARCHITECTURE Config
     */
    N.context.attr("architecture", {
        /**
         * Specify the element to insert the main content in jQuery Selector syntax.
         *  - If you use the Documents(N.docs) component, you don't have to specify it, but otherwise you must.
         *  - If it is not SPA(Single Page Application), please set it to "body".
         */
        "page" : {
            "context" : ".docs__ > .docs_contents__.visible__"
        },
        /**
         * Controller AOP related settings.
         *  - Refer to the
         *    https://bbalganjjm.github.io/natural_js/#cmVmcjAyMDIlMjRBT1AkaHRtbCUyRm5hdHVyYWxqcyUyRnJlZnIlMkZyZWZyMDIwMi5odG1s
         */
        "cont" : {
            "advisors" : [{
                "pointcut" : "^init$",
                "adviceType" : "around",
                "fn" : function(cont, fnChain, args, joinPoint) {
                    // Multilingual processing
                    APP.indx.i18n(undefined, cont.request.options.target);
                    // Natural-CODE
                    N.template.aop.codes(cont, joinPoint);
                }
            }, { // Pointcut for executing onOpen after a delayed init on a popup or tab
                "pointcut" : "^onOpen",
                "adviceType" : "around",
                "fn" : function(cont, fnChain, args, joinPoint) {
                    if(cont.onOpenDefer || (cont.caller && cont.caller.options.preload)) {
                        joinPoint.proceed();
                    } else {
                        cont.onOpenDefer = $.Deferred().done(function() {
                            joinPoint.proceed();
                        });
                    }
                }
            }]
        },
        "comm" : {
            /**
             * Communication Filter related settings.
             *  - Refer to the
             *    https://bbalganjjm.github.io/natural_js/#cmVmcjAyMDUlMjRDb21tdW5pY2F0aW9uJTIwRmlsdGVyJTI0aHRtbCUyRm5hdHVyYWxqcyUyRnJlZnIlMkZyZWZyMDIwNS5odG1s
             */
            "filters" : {
                "basicFilter" : {
                    /**
                     * This function is executed before N.comm is initialized.
                     */
                    beforeInit : function(obj) {
                    },
                    /**
                     * This function is executed after N.comm is initialized.
                     */
                    afterInit : function(request) {
                    },
                    /**
                     * This function is executed before sending a request to the server.
                     */
                    beforeSend : function(request, xhr, settings) {
                        // Display page loading image.
                        if(request.options.dataType === "html" && request.options.target !== null && request.options.append === false) {
                            request.options.target.html('<div style="text-align: center; vertical-align: middle;border: 0; border: none;width: 100%;height: 100%;"><img src="images/loading.gif" height="24"></div>');
                        }
                    },
                    /**
                     * This function is executed when the request is successful.
                     *  - If you return by modifying the data argument, you can receive the modified data as response data of all N.comm.
                     */
                    success : function(request, data, textStatus, xhr) {
                        var opts = request.options;
                        if((opts.target && N.isElement(opts.target)) || opts.dataType === "html" || opts.contentType === "text/css") {
                            if(location.hostname === "localhost" || location.hostname === "127.0.0.1") {
                             // [ Natural-CODE ] Code inspection
                                N.code.inspection.report.console(N.code.inspection.test(data), opts.url);

                                // [ Natural-CODE ] For debugging controller object, sourceURL is automatically inserted for every HTML request.
                                data = N.code.addSourceURL(data, opts.url);
                            }

                            // color theme
                            if(window.localStorage.themeColor !== "green") {
                                $(APP.indx.colorPalette.green).each(function(i, color) {
                                    data = data.replace(new RegExp(color, "gi"), APP.indx.colorPalette[window.localStorage.themeColor][i]);

                                    if(opts.contentType === "text/css") {
                                        data = data.replace(/url\(/gi, "*url(");
                                    }
                                });
                            }

                            return data;
                        }
                    },
                    /**
                     * This function is executed when an error occurs on the server.
                     */
                    error : function(request, xhr, textStatus, errorThrown) {
                        if((xhr.getResponseHeader("Content-Type") && xhr.getResponseHeader("Content-Type").indexOf("html") > -1) || request.options.dataType === "html") {
                            if(request.options.target != null && request.options.target.html !== undefined) {
                                request.options.target.html('<div style="text-align: center; margin-top: 140px;margin-bottom: 140px;">[ ' + request.options.url + ' ] 페이지나 서비스를 불러오는 도중 에러가 발생했습니다.</div>');
                            } else {
                                N(window).alert('An error occurred while loading the "' + request.options.url + '" page.').show();
                            }
                            if(request.options.target != null && request.options.target.is(".docs_contents")) {
                                request.options.target.removeClass("hidden__").addClass("visible__")
                                    .siblings(".docs_contents__").removeClass("visible__").addClass("hidden__");
                            }
                        } else {
                            var code;
                            var message;
                            if(xhr.responseJSON) {
                                if(xhr.responseJSON.error) {
                                    code = xhr.responseJSON.error.code;
                                    message = xhr.responseJSON.error.message;
                                } else {
                                    if(xhr.responseJSON.message) {
                                        message = xhr.responseJSON.message;
                                    }
                                }
                            }
                            if(xhr.status == 500 || xhr.status == 413) {
                                if(message) {
                                    N.notify({
                                        html : true
                                    }).add('<div style="white-space: pre-line;">' + message + '</div>');
                                }
                            } else if(xhr.status == 412) {
                                N(window).alert(message).show();
                            }
                        }
                    },
                    /**
                     * This function is executed when the server response is completed.
                     */
                    complete : function(request, xhr, textStatus) {
                    }
                }
            },
            "request" : {
                "options" : {
                    /**
                     * Global options of Communicator.request.
                     *  - Refer to the
                     *    https://bbalganjjm.github.io/natural_js/#cmVmcjAyMDQlMjRDb21tdW5pY2F0b3IucmVxdWVzdCUyNGh0bWwlMkZuYXR1cmFsanMlMkZyZWZyJTJGcmVmcjAyMDQuaHRtbA==
                     */
                    "type" : "POST",
                    "contentType" : "application/json; charset=utf-8",
                    "cache" : true,
                    "urlSync" : true,
                    "browserHistory" : false,
                    "append" : false
                }
            }
        }
    });

    /**
     * Natural-DATA Config
     */
    N.context.attr("data", {
        "formatter" : {
            /**
             * Custom format rules
             */
            "userRules" : {
                /*
                 * Function name becomes rule name.
                 *
                 * str : Target string of format
                 * args : Format option
                 *
                 * return : Returns a formatted string
                "userRule" : function(str, args) {
                    return str;
                }
                 */
            },
            /**
             * Specifies the date format to be used globally for the application.
             *  - Date/time format : Y : year, m : month, d : day, H : hour, i : minute, s : second
             */
            "date" : {
                /**
                 * Year, month, day separator
                 */
                "dateSepa" : "-",
                /**
                 * Hour, minute, second separator
                 */
                "timeSepa" : ":",
                /**
                 * Year, month format function, The format is specified in the return statement.
                 */
                "Ym" : function() {
                    return "Y" + this.dateSepa + "m";
                },
                /**
                 * Year, month, day Format function, The format is specified in the return statement.
                 */
                "Ymd" : function() {
                    return "Y" + this.dateSepa + "m" + this.dateSepa + "d";
                },
                /**
                 * Year, month, day, hour Format function, The format is specified in the return statement.
                 */
                "YmdH" : function() {
                    return this.Ymd() + " H";
                },
                /**
                 * Year, month, day, hour, minute Format function, The format is specified in the return statement.
                 */
                "YmdHi" : function() {
                    return this.Ymd() + " H" + this.timeSepa + "i";
                },
                /**
                 * Hour, minute, second Format function, The format is specified in the return statement.
                 */
                "YmdHis" : function() {
                    return this.Ymd() + " H" + this.timeSepa + "i" + this.timeSepa + "s";
                }
            }
        },
        "validator" : {
            /**
             * Custom validation rules
             */
            "userRules" : {
                /*
                 * Function name becomes rule name.
                 * The validation failure message is defined in the following N.context.attr("data").validator.message object with a property name same a function name for each language.
                 *
                 * str : Target string of validation
                 * args : validation options
                 *
                 * return : Returns true if validation succeeds, false if it fails.
                "userRule" : function(str, args) {
                    return true;
                }
                 */
            },
            /**
             * Validation error multilingual message
             *  - To add a language, copy the language set, specify the language set object property name as its locale string, and define the message.
             */
            "message" : {
                "ko_KR" : {
                    global : "필드 검증에 통과하지 못했습니다.",
                    required : "필수 입력 필드입니다.",
                    alphabet : "영문자만 입력할 수 있습니다.",
                    integer : "숫자(정수)만 입력할 수 있습니다.",
                    korean : "한글만 입력할 수 있습니다.",
                    alphabet_integer : "영문자와 숫자(정수)만 입력할 수 있습니다.",
                    integer_korean : "숫자(정수)와 한글만 입력할 수 있습니다.",
                    alphabet_korean : "영문자와 한글만 입력할 수 있습니다.",
                    alphabet_integer_korean : "영문자, 숫자(정수), 한글만 입력할 수 있습니다.",
                    dash_integer : "숫자(정수), 대시(-)만 입력할 수 있습니다.",
                    commas_integer : "숫자(정수), 콤마(,)만 입력할 수 있습니다.",
                    number : "숫자(+-,. 포함)만 입력할 수 있습니다.",
                    email : "e-mail 형식에 맞지 않습니다.",
                    url : "URL 형식에 맞지 않습니다.",
                    zipcode : "우편번호 형식에 맞지 않습니다.",
                    decimal : "(유한)소수만 입력할 수 있습니다.",
                    decimal_ : "(유한)소수 {0}번째 자리까지 입력할 수 있습니다.", // TODO
                    phone : "전화번호 형식이 아닙니다.",
                    rrn : "주민등록번호 형식에 맞지 않습니다.",
                    ssn : "주민등록번호 형식에 맞지 않습니다.", // Deprecated
                    frn : "외국인등록번호 형식에 맞지 않습니다.",
                    frn_rrn : "주민번호나 외국인등록번호 형식에 맞지 않습니다.", // Deprecated
                    frn_ssn : "주민번호나 외국인등록번호 형식에 맞지 않습니다.",
                    cno : "사업자등록번호 형식에 맞지 않습니다.", // Deprecated
                    kbrn : "사업자등록번호 형식에 맞지 않습니다.",
                    cpno : "법인번호 형식에 맞지 않습니다.", // Deprecated
                    kcn : "법인번호 형식에 맞지 않습니다.",
                    date : "날짜 형식에 맞지 않습니다.",
                    time : "시간 형식에 맞지 않습니다.",
                    accept : "\"{0}\" 값만 입력할 수 있습니다.",
                    match : "\"{0}\" 이(가) 포함된 값만 입력할 수 있습니다.",
                    acceptFileExt : "\"{0}\" 이(가) 포함된 확장자만 입력할 수 있습니다.",
                    notAccept : "\"{0}\" 값은 입력할 수 없습니다.",
                    notMatch : "\"{0}\" 이(가) 포함된 값은 입력할 수 없습니다.",
                    notAcceptFileExt : "\"{0}\" 이(가) 포함된 확장자는 입력할 수 없습니다.",
                    equalTo : "\"{1}\"의 값과 같아야합니다.",
                    maxlength : "{0} 글자 이하만 입력 가능합니다.",
                    minlength : "{0} 글자 이상만 입력 가능합니다.",
                    rangelength : "{0} 글자에서 {1} 글자 까지만 입력 가능합니다.",
                    maxbyte : "{0} 바이트 이하만 입력 가능합니다.<br> - 영문, 숫자 : 1 바이트<br> - 한글, 특수문자 : {1} 바이트",
                    minbyte : "{0} 바이트 이상만 입력 가능합니다.<br> - 영문, 숫자 : 1 바이트<br> - 한글, 특수문자 : {1} 바이트",
                    rangebyte : "{0} 바이트에서 {1} 바이트 까지만 입력 가능합니다.<br> - 영문, 숫자 한글자 : 1 바이트<br> - 한글, 특수문자 : {2} 바이트",
                    maxvalue : "{0} 이하의 값만 입력 가능합니다.",
                    minvalue : "{0} 이상의 값만 입력 가능합니다.",
                    rangevalue : "{0}에서 {1} 사이의 값만 입력 가능합니다.",
                    regexp : "{2}"
                },
                "en_US" : {
                    global : "It Can't pass the field verification.",
                    required : "It is a field to input obligatorily.",
                    alphabet : "Can enter only alphabetical characters.",
                    integer : "Can enter only number(integer).",
                    korean : "Can enter only Korean alphabet.",
                    alphabet_integer : "Can enter only alphabetical characters and number(integer).",
                    integer_korean : "Can enter only number(integer) and Korean alphabet.",
                    alphabet_korean : "Can enter only alphabetical characters and Korean alphabet.",
                    alphabet_integer_korean : "Can enter only alphabetical characters and number(integer) and Korean alphabet.",
                    dash_integer : "Can enter only number(integer) and dash(-).",
                    commas_integer : "Can enter only number(integer) and commas(,).",
                    number : "Can enter only number and (+-,.)",
                    email : "Don't conform to the format of E-mail.",
                    url : "Don't conform to the format of URL.",
                    zipcode : "Don't conform to the format of zip code.",
                    decimal : "Can enter only (finite)decimal",
                    decimal_ : "Can enter up to {0} places of (finite)decimal.", // TODO
                    phone : "There is no format of phone number.",
                    rrn : "Don't fit the format of the resident registration number.",
                    ssn : "Don't fit the format of the resident registration number.", // Deprecated.
                    frn : "Don't fit the format of foreign registration number.",
                    frn_rrn : "Don't fit the format of the resident registration number or foreign registration number.",
                    frn_ssn : "Don't fit the format of the resident registration number or foreign registration number.", // Deprecated.
                    cno : "Don't fit the format of registration of enterpreneur.", // Deprecated
                    kbrn : "Don't fit the format of registration of enterpreneur.",
                    cpno : "Don't fit the format of corporation number.", // Deprecated
                    kcn : "Don't fit the format of corporation number.",
                    date : "Don't fit the format of date.",
                    time : "Don't fit the format of time.",
                    accept : "Can enter only \"{0}\" value.",
                    match : "Can enter only value ​​that contains \"{0}\".",
                    acceptFileExt : "Can enter only extension that includes \"{0}\".",
                    notAccept : "Can't enter \"{0}\" value.",
                    notMatch : "Can't enter only value ​​that contains \"{0}\".",
                    notAcceptFileExt : "Can't enter only extension that includes \"{0}\".",
                    equalTo : "Must be the same as \"{1}\" value.",
                    maxlength : "Can enter only below {0} letters.",
                    minlength : "Can enter only more than {0} letters.",
                    rangelength : "It can be entered from {0} to {1} letters.",
                    maxbyte : "Can enter only below {0} bytes.",
                    minbyte : "Can enter only more than {0} bytes.",
                    rangebyte : "It can be entered from {0} to {1} bytes.",
                    maxvalue : "Can enter only below {0} value.",
                    minvalue : "Can enter only more than {0} value.",
                    rangevalue : "Can be entered value from {0} to {1}.",
                    regexp : "{2}"
                }
            }
        }
    });

    // The extend statement below should never be deleted if you have defined a custom format rule.
    $.extend(N.formatter, N.context.attr("data").formatter.userRules);
    // The extend statement below should never be deleted if you have defined a custom validation rule.
    $.extend(N.validator, N.context.attr("data").validator.userRules);

    /**
     * Natural-UI Config
     */
    N.context.attr("ui", {
        "alert" : {
            /**
             * Set the element to save the elements of N.alert and N.popup components using jQuery Selector syntax.
             * Define the same value as the value of N.context.attr("architecture").page.context unless it is a special case.
             *  - If you use the Documents(N.docs) component, you don't have to specify it, but otherwise you must.
             *  - If it is not SPA(Single Page Application), please set it to "body".
             */
            "container" : ".docs__ > .docs_contents__.visible__",
            "global" : {
                /**
                 * N.alert's OK button style
                 *  - It is specified as an option of the Button(N.button) component.
                 */
                "okBtnStyle" : {
                    color : "yellowgreen",
                    size : "medium"
                },
                /**
                 * N.alert's Cancel button style
                 *  - It is specified as an option of the Button(N.button) component.
                 */
                "cancelBtnStyle" : {
                    size : "medium"
                }
            },
            /**
             * Global draggableOverflowCorrectionAddValues option
             *  - Specifies the position to move to inside when the message dialog is dropped off the screen.
             *  - Correct the position of the message dialog  by incrementing or decrementing by 1 when a scroll bar appears on the screen because the message dialog  does not completely return to the inside.
             */
            "draggableOverflowCorrectionAddValues" : {
                top : 0,
                bottom : 0,
                left : +2,
                right : -2
            },
            /**
             * Global draggable option
             *  - If set to true, the message dialog will be draggable by the title bar.
             */
            "draggable" : true,
            /**
             * Global alwaysOnTop option
             *  - If set to true, The message dialog is always displayed at the top.
             */
            "alwaysOnTop" : true,
            "input" : {
                /**
                 * Display time of message dialog displayed when input element is specified in context option(ms)
                 */
                displayTimeout : 7000,
                /**
                 * Design of the close button of the message dialog displayed when an input element is specified in the context option(html tags can be entered)
                 */
                closeBtn : "&times;"
            },
            /**
             * Global html option
             * - If set to true, the HTML of the message is applied.
             */
            "html" : true,
            /**
             * Global saveMemory option
             *  - If set to true, save memory usage by removing unnecessary reference elements.
             */
            "saveMemory" : true,
            /**
             * Multilingual messages
             */
            "message" : {
                "ko_KR" : {
                    "confirm" : "확인",
                    "cancel" : "취소"
                },
                "en_US" : {
                    "confirm" : "OK",
                    "cancel" : "Cancel"
                }
            }
        },
        "popup" : {
            /**
             * Global draggable option
             *  - If set to true, the popup dialog will be draggable by the title bar.
             */
            "draggable" : true,
            /**
             * Global alwaysOnTop option
             *  - If set to true, The popup dialog is always displayed at the top.
             */
            "alwaysOnTop" : true,
            /**
             * Global button option
             *  - If set to false, It does not create basic button(OK/Cancel buttons) related elements.
             */
            "button" : false,
            "windowScrollLock" : false,
            /**
             * Global saveMemory option
             *  - If set to true, save memory usage by removing unnecessary reference elements.
             */
            "saveMemory" : true
        },
        "tab" : {
            "tabScrollCorrection" : {
                tabContainerWidthCorrectionPx : 1,
                tabContainerWidthReCalcDelayTime : 0
            }
        },
        "datepicker" : {
            /**
             * Multilingual messages of N.datepicker
             */
            "message" : {
                "ko_KR" : {
                    "year" : "년",
                    "month" : "월",
                    "days" : "일,월,화,수,목,금,토",
                    "yearNaN" : "연도는 서기 100년 이하는 입력할 수 없습니다.",
                    "monthNaN" : "월은 1월부터 12월까지 입력할 수 있습니다.",
                    "dayNaN" : "일은 1일부터 {0}일까지 입력할 수 있습니다.",
                    "minDate" : "\"{0}\" 이후의 날짜만 입력할 수 있습니다.",
                    "maxDate" : "\"{0}\" 이전의 날짜만 입력할 수 있습니다.",
                    "minMaxDate" : "\"{0}\" 와 \"{1}\" 사이의 날짜만 입력할 수 있습니다.",
                    "prev" : "이전",
                    "next" : "다음"
                },
                "en_US" : {
                    "year" : "Year",
                    "month" : "Month",
                    "days" : "Sun,Mon,Tue,Wed,Thu,Fri,Sat",
                    "yearNaN" : "You can not enter less AD 100 years",
                    "monthNaN" : "You can enter 1 to 12 months value",
                    "dayNaN" : "You can enter 1 to {0} days value",
                    "minDate" : "You can only input the date after \"{0}\".",
                    "maxDate" : "You can only input the date before \"{0}\".",
                    "minMaxDate" : "You can only enter dates between \"{0}\" and \"{1}\".",
                    "prev" : "Previous",
                    "next" : "Next"
                }
            },
            /**
             * Global yearsPanelPosition option.
             *  - If set to top, the year selection element is created at the top.
             *  - Set to "left" or "top".
             */
            "yearsPanelPosition" : "top",
            /**
             * Global monthsPanelPosition option.
             *  - If set to top, the month selection element is created at the top.
             *  - Set to "left" or "top".
             */
            "monthsPanelPosition" : "top",
            "monthonlyOpts" : {
                /**
                 * Global yearsPanelPosition option when monthonly option is true.
                 *  - Specifies the position of the year selection element when the monthonly option is true.
                 */
                "yearsPanelPosition" : "left",
                /**
                 * Global monthsPanelPosition option when monthonly option is true.
                 *  - Specifies the position of the month selection element when the monthonly option is true.
                 */
                "monthsPanelPosition" : "left",
            },
            /**
             * Global yearChangeInput option.
             *  - If set to true, the changed date is applied immediately to the input element when the year is changed.
             */
            "yearChangeInput" : true,
            /**
             * Global monthChangeInput option.
             *  - If set to true, the changed date is applied immediately to the input element when the month is changed.
             */
            "monthChangeInput" : true,
            /**
             * Global touchMonthChange option.
             *  - If set to true, the month changes when you touch-drag left or right.
             */
            "touchMonthChange" : true,
            /**
             * Global scrollMonthChange option.
             *  - If set to true, the month will change when the mouse wheel is scrolled.
             */
            "scrollMonthChange" : true
        },
        "select" : {
            /**
             * Global key option.
             *  -Specifies the property name of the data to be bound to the name attribute of the selection element
             */
            "key" : "key",
            /**
             * Global val option.
             *  - Specifies the property name of the data to be bound to the value attribute of the selection element.
             */
            "val" : "val",
            /**
             * Global append option.
             *  - If set to false, empty the default options in the select element and then bind the data.
             */
            "append" : true
        },
        "form" : {
            "tpBind" : true
        },
        "list" : {
            /**
             * Multilingual messages
             */
            "message" : {
                "ko_KR" : {
                    "empty" : "조회를 하지 않았거나 조회된 데이터가 없습니다."
                },
                "en_US" : {
                    "empty" : "No inquired data or no data available."
                }
            },
            /**
             * Global scrollPaging.size option.
             *  - Specifies the number of rows to bind at a time when scroll paging.
             */
            "scrollPaging" : {
                "size" : 30
            },
            /**
             * Global unselect option.
             *  - If set to false, when the select option is true, selecting the selected row again does not cancel the selection.
             */
            "unselect" : false
        },
        "grid" : {
            /**
             * Sort sort indicator when sort function is activated, You can also enter HTML tags
             */
            "sortableItem" : {
                "asc" : "▼",
                "desc" : "▲"
            },
            /**
             * Multilingual messages
             */
            "message" : {
                "ko_KR" : {
                    "empty" : "조회를 하지 않았거나 조회된 데이터가 없습니다.",
                    "search" : "검색",
                    "selectAll" : "전체선택",
                    "dFilter" : "데이터 필터",
                    "more" : "더보기",
                    "column" : "열",
                    "showHide" : "열 감추기 / 보이기",
                    "prev" : "이전",
                    "next" : "다음"
                },
                "en_US" : {
                    "empty" : "No inquired data or no data available.",
                    "search" : "Search",
                    "selectAll" : "Select all",
                    "dFilter" : "Data filter",
                    "more" : "MORE",
                    "column" : "Column",
                    "showHide" : "Hide and show columns",
                    "prev" : "Previous",
                    "next" : "Next"
                }
            },
            /**
             * Global resizable option.
             *  - If set to true, the width of the column can be resized.
             */
            "resizable" : true,
            /**
             * Global sortable option.
             *  - If set to true, data can be sorted based on the selected column.
             */
            "sortable" : true,
            /**
             * Global filter option.
             *  - If set to true, data can be filtered based on the selected column.
             */
            "filter" : true,
            /**
             * Global scrollPaging.size option.
             *  - Specifies the number of rows to bind at a time when scroll paging.
             */
            "scrollPaging" : {
                "size" : 30
            },
            /**
             * Global unselect option.
             *  - If set to false, when the select option is true, selecting the selected row again does not cancel the selection.
             */
            "unselect" : false,
            /**
             * Miscellaneous settings
             */
            "misc" : {
                /**
                 * Global misc.resizableCorrectionWidth option
                 *  - The grid body column width and grid header column width may not match when the resizable option is activated.
                 *    At this time, it is an option to correct by increasing or decreasing the value by 0.1.
                 */
                "resizableCorrectionWidth" : N.browser.is("safari") ? -6 : -7,
                /**
                 * Global misc.resizableLastCellCorrectionWidth option
                 *  - Clicking on the last column may cause other columns to be pushed when the resizable option is enabled in the header fixed grid.
                 *    At this time, it is an option to correct by increasing or decreasing the value by 0.1.
                 */
                "resizableLastCellCorrectionWidth" : 8,
                /**
                 * Global misc.resizeBarCorrectionLeft option
                 *  - The left position of the resize bar may not be centered relative to the column border when the resizable option is activated. At this time,
                 *    it is an option to correct by increasing or decreasing the value by 0.1.
                 */
                "resizeBarCorrectionLeft" : N.browser.is("firefox") ? -1 : N.browser.is("safari") ? 1 : 0,
                /**
                 * Global misc.resizeBarCorrectionHeight option
                 *  - The height of the resizing bar may not be full when the resizable option is activated.
                 *    At this time, it is an option to correct by increasing or decreasing the value by 0.1.
                 */
                "resizeBarCorrectionHeight" : 0,
                /**
                 * Global misc.fixedcolHeadMarginTop option
                 *  - The top position of the fixed header cell(th) may not match when the fixedcol option is activated.
                 *    At this time, it is an option to correct by increasing or decreasing the value by 0.1.
                 */
                "fixedcolHeadMarginTop" : N.browser.is("ie") || N.browser.is("firefox") ? 0 : 1,
                /**
                 * Global misc.fixedcolHeadMarginLeft option
                 *  - The left position of the fixed header cell(th) may not match when the fixedcol option is activated.
                 *    At this time, it is an option to correct by increasing or decreasing the value by 0.1.
                 */
                "fixedcolHeadMarginLeft" : N.browser.is("ie") || N.browser.is("firefox") ? -1 : 0,
                /**
                 * Global misc.fixedcolHeadHeight option
                 *  - The height of the fixed header cell(th) may not match when the fixedcol option is activated.
                 *    At this time, it is an option to correct by increasing or decreasing the value by 0.1..
                 */
                "fixedcolHeadHeight" : N.browser.is("ie") || N.browser.is("firefox") ? 1 : 0,
                /**
                 * Global misc.fixedcolBodyMarginTop option
                 *  - The top position of the fixed body cell(td) may not match when the fixedcol option is activated.
                 *    At this time, it is an option to correct by increasing or decreasing the value by 0.1.
                 */
                "fixedcolBodyMarginTop" : N.browser.is("ie") ? -0.5 : N.browser.is("firefox") ? -1 : 0,
                /**
                 * Global misc.fixedcolBodyMarginLeft option
                 *  - The left position of the fixed body cell(td) may not match when the fixedcol option is activated.
                 *    At this time, it is an option to correct by increasing or decreasing the value by 0.1.
                 */
                "fixedcolBodyMarginLeft" : N.browser.is("ie") || N.browser.is("firefox") ? -1 : 0,
                /**
                 * Global misc.fixedcolBodyBindHeight option
                 *  - The height of the fixed body cell(td) may not match when the fixedcol option is activated.
                 *    At this time, it is an option to correct by increasing or decreasing the value by 0.1.
                 */
                "fixedcolBodyBindHeight" : N.browser.is("ie") ? 1.3 : 1,
                /**
                 * Global misc.fixedcolBodyAddHeight option
                 *  - The height of the cell(td) of the added row may not match when the fixedcol option is activated.
                 *    At this time, it is an option to correct by increasing or decreasing the value by 0.1.
                 */
                "fixedcolBodyAddHeight" : 1,
                /**
                 * Global misc.fixedcolRootContainer option
                 *  - After creating a grid instance by applying the fixed col option, the grid shape may break when the height of the element wrapping the grid is dynamically changed.
                 *    At this time, if you specify the element whose height is changed in this option, the grid shape will not be broken.
                 *  - Specify an element with the jQuery selector string.
                 */
                "fixedcolRootContainer" : ".view_context__"
            }
        }
    });

    /**
     * Natural-UI.Shell Config
     */
    N.context.attr("ui.shell", {
        "notify" : {
            /**
             * Global alwaysOnTop option
             *  - If set to true, The message dialog is always displayed at the top.
             */
            "alwaysOnTop" : true,
            /**
             * Multilingual messages
             */
            "message" : {
                "ko_KR" : {
                    "close" : "닫기"
                },
                "en_US" : {
                    "close" : "Close"
                }
            }
        },
        "docs" : {
            /**
             * Global alwaysOnTop option
             *  - If set to true, The menu list dialog is always displayed at the top.
             */
            "alwaysOnTop" : true,
            /**
             * Global maxStateful option
             *  - If the multi option is true, the maximum number of stateful tab contents can be set to prevent the web browser from slowing down whenever additional tab contents are opened.
             */
            "maxStateful" : 20,
            /**
             * Global maxTabs option
             *  - If the multi option is true, the maximum number of tab contents can be set to prevent the web browser from slowing down whenever additional tab contents are opened.
             */
            "maxTabs" : 0,
            /**
             * Global entireLoadIndicator option
             *  - If set to true, the progress bar is displayed until all Ajax requests executed when the page is loaded are completed.
             */
            "entireLoadIndicator" : true,
            /**
             * Global entireLoadScreenBlock option
             *  - If set to true, double submission is prevented by blocking the screen until all Ajax requests executed when the page is loaded are completed.
             */
            "entireLoadScreenBlock" : true,
            /**
             * Global addLast option
             *  - If set to true, a new tab is added last when the add method is called.
             */
            "addLast" : true,
            /**
             * Global tabScroll option
             *  - If set to true, tabs can be scrolled by dragging the mouse or touching.
             */
            "tabScroll" : true,
            /**
             * Global closeAllRedirectURL option
             *  - When the "Close All" button is clicked, if the value of the closeAllRedirectURL option is null, all other tabs except the active tab are closed and if you input the url string, it  will be redirect to the url.
             */
            "closeAllRedirectURL" : "./",
            /**
             * Global entireLoadExcludeURLs option
             *  - Excludes URLs specified by entireLoadExcludeURLs from the entireLoad(entireLoadIndicator, entireLoadScreenBlock, etc.) related event or option.
             */
            "entireLoadExcludeURLs" : ["contents.html", "footer.html"],
            /**
             * Global onBeforeActive event
             *  - This event is executed before the selected tab is activated.
             */
            "onBeforeActive" : function(docId, isFromDocsTabList, isNotLoaded) {
                if(!isNotLoaded) {
                    // FIXME When the menu DB is created and the page loading service is created, remove the code below (before var hashVal).
                    var url = N(".index-lefter.view_context__ a[data-pageid='" + docId + "']").attr("href");
                    if (N.string.trim(location.hash).length === 0 || docId === "home0100") {
                        docId = "home0100";
                        url = "html/naturaljs/" + docId.substring(0, 4) + "/" + docId + ".html";
                    }

                    var hashVal = docId + "$" + this.options.docs[docId].docNm + "$" + url;
                    if(decodeURIComponent(atob(location.hash.replace("#", ""))) != hashVal) {
                        location.hash = btoa(encodeURIComponent(hashVal));
                    }
                }
            },
            /**
             * Global onActive event
             *  - This event is executed after the selected tab is activated.
             */
            "onActive" : function(docId, isFromDocsTabList, isNotLoaded) {
                if(location.hostname === "bbalganjjm.github.io") {
                    try {
                        ga('create', 'UA-58001949-2', 'auto');
                        ga('set', 'location', location.href);
                        ga('set', 'title', this.doc(docId).docNm);
                        ga('send', {
                            'hitType': 'pageview',
                            'page': location.hash
                        });
                    } catch (e) {}
                }
            },
            /**
             * Multilingual messages
             */
            "message" : {
                "ko_KR" : {
                    "closeAllTitle" : "메뉴 전체 닫기",
                    "closeAll" : "전체 닫기",
                    "closeAllQ" : "선택한 메뉴를 제외하고 열린 메뉴 전체를 닫겠습니까?",
                    "closeAllDQ" : "열려있는 메뉴 전체를 닫고 메인(홈) 화면으로 이동하겠습니까?",
                    "docListTitle" : "열린 메뉴 목록",
                    "docList" : "메뉴 목록",
                    "selDocument" : "{0} 메뉴 선택",
                    "close" : "메뉴 닫기",
                    "closeConf" : "\"{0}\" 메뉴에 편집 중인 항목이 있습니다. 무시하고 메뉴를 닫겠습니까?",
                    "maxTabs" : "최대 {0} 개의 메뉴 만 열 수 있습니다. <br>다른 메뉴 탭을 닫고 다시 시도하세요.",
                    "maxStateful" : "선택한 메뉴가 활성화되면 설정된 최대 상태 유지 메뉴 개수(최대 {1} 개)가 초과되어 마지막으로 선택된 \"{0}\" 메뉴 페이지가 다시 로딩됩니다.<br>메뉴를 선택하겠습니까?"
                },
                "en_US" : {
                    "closeAllTitle" : "Close all menus",
                    "closeAll" : "Close all",
                    "closeAllQ" : "Are you sure you want to close the all open menus except for the selected menu?",
                    "closeAllDQ" : "Are you sure you want to close the all open menus and go to home page?",
                    "docListTitle" : "Opened menu list",
                    "docList" : "Menu list",
                    "selDocument" : "Select the {0} menu",
                    "close" : "Close the menu",
                    "closeConf" : "There is an item being edited in the \"{0}\" memu. Are you sure you want to close the menu?",
                    "maxTabs" : "You can only open up to {0} menus. <br>Close other menu tabs and try again.",
                    "maxStateful" : "When the selected menu is activated, the maximum number of menu items (maximum of {1}) is exceeded and the last selected menu page \"{0}\" will be  reloaded.<br>Do you want to select the menu?"
                }
            }
        }
    });

    /**
     * Natural-TEMPLATE Config
     */
    N.context.attr("template", {
        design : {
            type : "md"
        },
        aop : {
            /**
             * Common code request information
             *
             * @codeUrl Common code request URL
             * @codeKey Property name of common code classification code
             */
            codes : {
                codeUrl : "code/getCommonCodeList.json",
                codeKey : "code"
            }
        },
        /**
         * 다국어 메시지
         */
        "message" : {
            "ko_KR" : {
                "MSG-0001" : "data 옵션을 정의해 주세요.",
                "MSG-0002" : "서버 오류가 발생하여 공통 코드 목록을 조회하지 못했습니다.",
                "MSG-0003" : "데이터 코드 목록을 조회하는 N.comm({0}) 이 없습니다.",
                "MSG-0004" : "서버 오류가 발생하여 데이터 코드 목록을 조회하지 못했습니다.",
                "MSG-0005" : "컴포넌트({0})가 잘못 지정되었습니다.",
                "MSG-0006" : "이벤트({0})가 잘못 지정되었습니다."
            },
            "en_US" : {
                "MSG-0001" : "Define the data option.",
                "MSG-0002" : "The common code list could not be queried because a server error occurred.",
                "MSG-0003" : "There is no N.comm({0}) to query the data code list.",
                "MSG-0004" : "The data code list could not be queried because a server error occurred.",
                "MSG-0005" : "Component({0}) was incorrectly specified.",
                "MSG-0006" : "Event({0}) was incorrectly specified."
            }
        }
    });

    /**
     * Natural-CODE Config
     */
    N.context.attr("code", {
        inspection : {
            /**
             * Specifies whether to stop the logic by throwing an ERROR when a code of ERROR type is detected.
             */
            abortOnError : false,
            /**
             * Defines the statements to exclude from the scan as a string.
             *
             * If the detected code contains the following string, it is excluded.
             *  ex) excludes : [ ".index-header", ".page-header", ".index-lefter", ".index-contents", ".index-footer" ]
             */
            excludes : [
                ".index-header",
                ".page-header",
                ".index-lefter",
                ".index-contents",
                ".index-footer"
            ],
            /**
             * Multilingual messages
             */
            "message" : {
                "ko_KR" : {
                    "NoContextSpecifiedInSelector" : 'Controller object의 함수 안에서 요소를 선택할 때는 반드시 $() 나 N() 함수의 두 번째 인자(context)에 view 요소를 입력하거나 view 요소에서 find해야 합니다.'
                        + 'view(context) 요소를 입력하지 않으면 다른 View의 요소까지 선택되어 의도하지 않은 오류가 발생할 수 있습니다.'
                        + '\nex) N("selector", cont.view).hide();'
                        + '\n    cont.view.find("selector").hide();',
                    "UseTheComponentsValMethod" : 'jQuery의 val 메서드로 입력 요소의 value 속성 값을 변경하면 컴포넌트에 바인딩되어 있는 데이터는 업데이트되지 않습니다.'
                        + '컴포넌트의 내부 데이터와 연동된 입력 요소들은 적용된 데이터 관련 컴포넌트(N.form, N.grid 등)에서 제공하는 val 메서드를 사용해야 합니다.'
                        + '\nex) cont["p.form.id"].val("columnName", "value")'
                        + '\n    cont["p.grid.id"].val(index, "columnName", "value")'
                        + '\n    cont["p.list.id"].val(index, "columnName", "value")'
                },
                "en_US" : {
                    "NoContextSpecifiedInSelector" : 'When selecting an element within a function of a Controller object, you must input the view element in the second argument of the $ () or N () function or find it in the view element.'
                        + "If you don't type view(context) element, you can get unintended errors as the elements of other views are also selected. "
                        + '\nex) N("selector", cont.view).hide();'
                        + '\n    cont.view.find("selector").hide();',
                    "UseTheComponentsValMethod" : "If you change the value of an input element's value attribute using jQuery's val method, the data bound to the component will not be updated. "
                        + 'Input elements linked with the internal data of the component should use the val method provided by the applied data-related components(N.form, N.grid, etc.).'
                        + '\nex) cont["p.form.id"].val("columnName", "value")'
                        + '\n    cont["p.grid.id"].val(index, "columnName", "value")'
                        + '\n    cont["p.list.id"].val(index, "columnName", "value")'
                }
            }
        }
    });

})(N);