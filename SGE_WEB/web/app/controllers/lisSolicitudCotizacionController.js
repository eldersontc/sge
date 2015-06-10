'use strict';

define(['app'], function (app) {

    app.register.controller('lisSolicitudCotizacionController', ['$scope', '$http', function ($scope, $http) {

            $scope.solicitudes = [];
            $scope.verPreload = false;

            $scope.obtenerSolicitudes = function (){
                $scope.verPreload = true;
                $http.post('http://localhost:8084/SGE_REST/Servicios/SolicitudCotizacionSRV/ObtenerSolicitudesCotizacion', angular.toJson('')).
                    success(function (data, status, headers, config) {
                        if(angular.isDefined(data)){
                            if(data[0] === 'true'){
                                $scope.solicitudes = angular.fromJson(data[1]);
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
            
            $scope.obtenerSolicitudes();

        }]);
});