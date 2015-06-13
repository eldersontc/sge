'use strict';
define(['app'], function (app) {
    app.factory('alertFactory',function(){
        return {
            view: function (type, message){
                var alert = $('<div class="alert alert-' + type + ' alert-floating" role="alert">');
                var close = $('<button type="button" class="close" data-dismiss="alert">&times</button>');
                alert.append(close);
                alert.append(message);
                alert.appendTo($('body')).fadeIn(300).delay(7000).fadeOut(500);
            }
        };
    });
});


