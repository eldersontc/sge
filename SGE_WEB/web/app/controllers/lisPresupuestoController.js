'use strict';

define(['app'], function (app) {

    app.register.controller('lisPresupuestoController', ['$scope', '$http', function ($scope, $http) {

            var urlObtenerPresupuestos = URL_BASE + 'PresupuestoSRV/ObtenerPresupuestos',
                urlDescargarPdf = URL_BASE + 'ReporteSRV/GenerarPdfConEntidad';
            
            $scope.presupuestos = [];
            $scope.verPreload = false;

            $scope.obtenerPresupuestos = function (){
                $scope.verPreload = true;
                $http.post(urlObtenerPresupuestos, angular.toJson('')).
                    success(function (data, status, headers, config) {
                        if(angular.isDefined(data)){
                            if(data[0] === 'true'){
                                $scope.presupuestos = angular.fromJson(data[1]);
                            }
                        }
                        $scope.verPreload = false;
                    }).
                    error(function (data, status, headers, config) {
                        $scope.verPreload = false;
                    });
            }

            $scope.descargar = function (presupuesto) {
                debugger;
                $.fileDownload(urlDescargarPdf, { httpMethod: "POST", data: {r:7, i:7} })
                .done(function () { 
                    console.log('done');
                })
                .fail(function () {
                    console.log('fail');
                });
            };
            
            $scope.obtenerPresupuestos();

        }]);
});