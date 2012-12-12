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
            * Добавляет функцию trim к строке
            */
            String.prototype.trim = function() { return this.replace(/^\s+|\s+$/g, ''); };
        }
    };
})
