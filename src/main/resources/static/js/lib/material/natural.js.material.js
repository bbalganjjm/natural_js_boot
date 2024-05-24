/**
 * Material Design 2
 */
var nmd2 = {
    init : function(cont, isAfterCompEventInited) {
        if(isAfterCompEventInited) {
            this.textfield(cont);
            this.select(cont);
            this.pagination(cont);
        } else {
            this.list(cont);
        }
    },
    textfield : function(cont) { // textarea, input
        N('.mdc-text-field', cont.view).each(function(i, el) {
            el = $(el);
            var id = el.attr("id");
            var label = el.data("label");
            var uid = String(Math.random()).replace("0.", "");
            var isNoLabel = el.data("nolabel") ? el.data("nolabel") : false;

            var mdcTextField;
            if(el.is("textarea")) {
                el.addClass("mdc-text-field__input").removeClass("mdc-text-field");
                el.attr("aria-label", label);

                if(el.data("type") === "outlined") { // filled | outlined
                    mdcTextField = el.wrap('<span class="mdc-text-field__resizer"></span>').parent();
                    mdcTextField = mdcTextField.wrap('<label class="mdc-text-field mdc-text-field--outlined mdc-text-field--textarea' + (isNoLabel ? ' mdc-text-field--no-label' : '') + '"></label>').parent();
                    mdcTextField.prepend('<span class="mdc-notched-outline">'
                        + '<span class="mdc-notched-outline__leading"></span>'
                        + '<span class="mdc-notched-outline__notch">'
                        + (!isNoLabel ? '<span class="mdc-floating-label" id="' + id + '-label-' + uid + '">' + label +'</span>' : '')
                        + '</span>'
                        + '<span class="mdc-notched-outline__trailing"></span>'
                        + '</span>');
                } else {
                    mdcTextField = el.wrap('<span class="mdc-text-field__resizer"></span>').parent();
                    mdcTextField = mdcTextField.wrap('<label class="mdc-text-field mdc-text-field--filled mdc-text-field--textarea' + (isNoLabel ? ' mdc-text-field--no-label' : '') + '"></label>').parent();
                    mdcTextField.prepend('<span class="mdc-floating-label" id="' + id + '-label-' + uid + '">' + label +'</span>');
                    mdcTextField.prepend('<span class="mdc-text-field__ripple"></span>');
                    mdcTextField.append('<span class="mdc-line-ripple"></span>');
                }
            } else {
                el.css({
                    "padding": "0",
                    "border": "none"
                });

                el.addClass("mdc-text-field__input").removeClass("mdc-text-field");
                el.attr("aria-labelledby", id + "-label-" + uid);

                if(el.data("type") === "outlined") { // filled | outlined
                    mdcTextField = el.wrap('<label class="mdc-text-field mdc-text-field--outlined' + (isNoLabel ? ' mdc-text-field--no-label' : '') + '"></label>').parent();
                    mdcTextField.prepend('<span class="mdc-notched-outline">'
                        + '<span class="mdc-notched-outline__leading"></span>'
                        + '<span class="mdc-notched-outline__notch">'
                        + (!isNoLabel ? '<span class="mdc-floating-label" id="' + id + '-label-' + uid + '">' + label +'</span>' : '')
                        + '</span>'
                        + '<span class="mdc-notched-outline__trailing"></span>'
                        + '</span>');
                } else {
                    mdcTextField = el.wrap('<label class="mdc-text-field mdc-text-field--filled' + (isNoLabel ? ' mdc-text-field--no-label' : '') + '"></label>').parent();
                    if(!isNoLabel) {
                        mdcTextField.prepend('<span class="mdc-floating-label" id="' + id + '-label-' + uid + '">' + label +'</span>');
                    }
                    mdcTextField.prepend('<span class="mdc-text-field__ripple"></span>');
                    mdcTextField.append('<span class="mdc-line-ripple"></span>');
                }
                var btnId = el.data("btnid");
                var btnIdAttr = "";
                if(btnId) {
                    btnIdAttr = ' id="' + btnId + '"';
                }
                if(el.data("trailingicon")) {
                    mdcTextField.addClass("mdc-text-field--with-trailing-icon");
                    el.after('<i' + btnIdAttr + ' class="material-icons mdc-text-field__icon mdc-text-field__icon--leading" tabindex="0" role="button">' + el.data("trailingicon") + '</i>');
                } else if(el.data("format") && el.data("format")[0] && el.data("format")[0][0] === "date") {
                    mdcTextField.addClass("mdc-text-field--with-trailing-icon");
                    var btn = N('<i class="material-icons mdc-text-field__icon mdc-text-field__icon--leading" tabindex="0" role="button">date_range</i>');
                    btn.on("click.material.textfield.trailing.icon", function(e) {
                        e.preventDefault();
                        var dpInst = el.instance("datepicker");
                        el.trigger("focusin.datepicker");
                    });
                    el.after(btn);
                }
                if(el.data("leadingicon")) {
                    mdcTextField.addClass("mdc-text-field--with-leading-icon");
                    el.before('<i' + btnIdAttr + ' class="material-icons mdc-text-field__icon mdc-text-field__icon--trailing" tabindex="0" role="button">' + el.data("leadingicon") + '</i>');
                }
                if(btnId) {
                    cont["e." + btnId + ".click"] = N("#" + btnId, cont.view).on("click", cont["e." + btnId + ".click"]);
                }
            }
            el.data("md_textfield_inst", mdc.textField.MDCTextField.attachTo(mdcTextField.get(0)));
        });
    },
    alert : {
        onBeforeShow : function(msgContext, msgContents) { // MD2
            if(this.options.isInput) {
                return false;
            }
            if(this.options.title !== undefined) {
                msgContents.find(".msg_box__").css("padding-top", 0);
            }
            msgContents.find(".buttonBox__ .button__").each(function(i, el) {
                el.className = "mdc-button " + N.string.trimToEmpty(el.className.replace(/btn_.*?__/g, ""));
                var icon = el.getAttribute('data-icon');
                if(icon) {
                    icon = '<span class="material-icons mdc-icon-button__icon">' + icon + '</span>&nbsp;';
                } else {
                    icon = "";
                }
                el.innerHTML = '<div class="mdc-button__ripple"></div>' + icon + '<span class="mdc-button__label">' + el.innerText + '</span>';

                mdc.ripple.MDCRipple.attachTo(el);
            });
        }
    },
    button : {
        onBeforeCreate: function(context, opts) { // MD2
            if (context.hasClass("mdc-button")) {
                var label = context.html();
                if(!N.string.isEmpty(label)) {
                    context.empty();
                }
                context.append('<div class="mdc-button__ripple"></div>');
                if(context.data("icon")) {
                    context.append('<span class="material-icons mdc-icon-button__icon">' + context.data("icon") + '</span>&nbsp;');
                }
                if(!N.string.isEmpty(label)) {
                    context.append('<span class="mdc-button__label">' + label + '</span>');
                }
            } else if (context.hasClass("mdc-icon-button")) {
                context.addClass("material-icons mdc-ripple-upgraded--unbounded mdc-ripple-upgraded");
            }
        },
        onCreate: function(context, opts) { // MD2
            var mdcRipple = mdc.ripple.MDCRipple.attachTo(context.get(0));
            if (context.hasClass("mdc-icon-button")) {
                mdcRipple.unbounded = true;
            }
        }
    },
    select : function(cont) {
        N('.mdc-njs-select', cont.view).each(function(i, el) {
            el = N(el);
            el.attr("required", true);
            var mdcNjsSelect = el.wrap('<div class="mdc-njs-select-wrap"></div>').parent();
            mdcNjsSelect.append('<span class="mdc-njs-select-highlight"></span>');
            mdcNjsSelect.append('<span class="mdc-njs-select-bar"></span>');
            mdcNjsSelect.append('<label class="mdc-njs-select-label">' + el.data("label") + '</label>');
        });
    },
    list : function(cont) {
        N('.mdc-list', cont.view).each(function(i, el) {
            el = N(el);
            if(el.is("ul")) {
                var liEl = el.find("li").addClass("mdc-list-item");
                liEl.prepend('<span class="mdc-list-item__ripple"></span>');
                liEl.find("[id]").addClass("mdc-list-item__text");
                // mdc.list.MDCList.attachTo(this);
                // mdc.ripple.MDCRipple.attachTo(liEl.get(0)); // Clone된 요소는 ripple 적용 안됨.
            }
        });
    },
    pagination : function(cont) {
        cont.view.find(".pagination__ .mdc-icon-button").button();
    }
}