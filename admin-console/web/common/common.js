var DDSurok = ( function () {
    return {
        init: function() {
            /*
            * Добавляет функцию отключения всех дочерних елементов для объекта.
            * Отключаются все элементы типов:
            *   - INPUT
            *   - BUTTON
            *   - SELECT
            *   - RADIO
            */
            Object.prototype.disabledElement = function() {
                var all = this.childNodes;
                var inp, i=0;
                while(inp=all[i++]) {
                    if ((inp != null)&&(inp.textContent.trim() != "")) {
                        if ((inp.tagName == "INPUT")
                            ||(inp.tagName == "BUTTON")
                            ||(inp.tagName == "SELECT")
                            ||(inp.tagName == "RADIO")) {
                            inp.disabled=true;
                        } else {
                            inp.disabledElement();
                        }
                    }
                }
            };
            
            /*
             *
             */
            Object.prototype.enabledElement = function() {
                var all = this.childNodes;
                var inp, i=0;
                while(inp=all[i++]) {
                    if ((inp != null)&&(inp.textContent.trim() != "")) {
                        if ((inp.tagName == "INPUT")
                            ||(inp.tagName == "BUTTON")
                            ||(inp.tagName == "SELECT")
                            ||(inp.tagName == "RADIO")) {
                            inp.disabled=false;
                        } else {
                            inp.enabledElement();
                        }
                    }
                }
            }
            
            /*
            * Добавляет функцию trim к строке
            */
            String.prototype.trim = function() {return this.replace(/^\s+|\s+$/g, '');};
            
            /*
             * Добавить классы
             */
            Element.prototype.addClass = function(newclass) {
                this.className = this.className + " " + newclass;
            };
            
            /*
             * Удалить классы
             */
            Element.prototype.removeClass = function(remClass) {
                var ec = ' ' + this.className.replace(/^s*|s*$/g,'') + ' ';
                var nc = ec;
                remClass = remClass.replace(/^s*|s*$/g,'');
                if (ec.indexOf(' '+remClass+' ') != -1) {
                    nc = ec.replace(' ' + remClass.replace(/^s*|s*$/g,'') + ' ',' ');
                }
                this.className = nc.replace(/^s*|s*$/g,'');
            }
        }
    };
})
