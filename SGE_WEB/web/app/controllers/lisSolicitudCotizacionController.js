'use strict';

define(['app'], function (app) {

    app.register.controller('lisSolicitudCotizacionController', ['$scope', '$http', function ($scope, $http) {

        $scope.click = function(){
            console.log($scope.yourName);
        };
        
    }]);
});