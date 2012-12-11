<%-- 
    Document   : index
    Created on : 06.12.2012, 14:00:55
    Author     : d.duritskij
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <script src="http://api-maps.yandex.ru/2.0-stable/?load=package.standard&lang=ru-RU" type="text/javascript"></script>
        <script type="text/javascript">
            var myMap;

            // Дождёмся загрузки API и готовности DOM.
            ymaps.ready(init);

            function init () {
                // Создание экземпляра карты и его привязка к контейнеру с
                // заданным id ("map").
                myMap = new ymaps.Map('map', {
                    // При инициализации карты обязательно нужно указать
                    // её центр и коэффициент масштабирования.
                    center:[55.76, 37.64], // Москва
                    zoom:10
                });

                document.getElementById('destroyButton').onclick = function () {
                    // Для уничтожения используется метод destroy.
                    myMap.destroy();
                };

            }
        </script>
        <title>Карта</title>
    </head>
    <body>
        <div id="map" style="width:400px; height:300px"></div>
        <input type="button" id="destroyButton" value="Удалить карту"/>
        <br />
        <a href="admin-console/Users.jsp">Админка</a>
    </body>
</html>
