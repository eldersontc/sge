'use strict';

define(['app'], function (app) {

    app.register.controller('lisCotizacionController', ['$scope', '$http', function ($scope, $http) {

            var urlObtenerCotizaciones = URL_BASE + 'CotizacionSRV/ObtenerCotizaciones';
            
            $scope.cotizaciones = [];
            $scope.verPreload = false;

            $scope.obtenerCotizaciones = function (){
                $scope.verPreload = true;
                $http.post(urlObtenerCotizaciones, angular.toJson('')).
                    success(function (data, status, headers, config) {
                        if(angular.isDefined(data)){
                            if(data[0] === 'true'){
                                $scope.cotizaciones = angular.fromJson(data[1]);
                            }
                        }
                        $scope.verPreload = false;
                    }).
                    error(function (data, status, headers, config) {
                        $scope.verPreload = false;
                    });
            }

            $scope.descargar = function (solicitud) {
                
            };
            
            $scope.obtenerCotizaciones();

        }]);
});