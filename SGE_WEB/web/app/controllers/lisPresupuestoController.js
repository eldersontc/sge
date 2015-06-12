'use strict';

define(['app'], function (app) {

    app.register.controller('lisPresupuestoController', ['$scope', '$http', '$modal', function ($scope, $http, $modal) {

            var urlObtenerPresupuestos = URL_BASE + 'PresupuestoSRV/ObtenerPresupuestos',
                urlObtenerReportes = URL_BASE + 'ReporteSRV/ObtenerReportesPorEntidad',
                urlCambiarEstado = URL_BASE + 'PresupuestoSRV/CambiarEstadoPresupuesto',
                urlDescargarPdf = URL_BASE + 'ReporteSRV/GenerarPdfConEntidad';

            $scope.estados = [
                { id: 1, nombre: 'APROBAR' },
                { id: 2, nombre: 'DESAPROBAR' },
                { id: 3, nombre: 'ENVIAR AL CLIENTE' },
                { id: 4, nombre: 'ACEPTAR' },
                { id: 5, nombre: 'RECHAZAR' }
            ];
            $scope.alerta = { ver: false, tipo: 'info', mensaje: '' };
            $scope.presupuestos = [];
            $scope.reportes = [];
            $scope.idPresupuesto = 0;
            $scope.idEstado = 1;
            $scope.idReporte = 0;
            $scope.verPreload = false;
            $scope.verTabla = false;
            
            var modalEstados,
                modalReportes;
            
            var verModalEstados = function (){
                $scope.idEstado = 1;
                modalEstados = $modal.open({
                  animation: true,
                  templateUrl: 'modalEstados',
                  scope: $scope,
                  size: 'sm'
              });  
            };
            
            var verModalReportes = function (){
                $scope.idEstado = 1;
                modalReportes = $modal.open({
                  animation: true,
                  templateUrl: 'modalReportes',
                  scope: $scope,
                  size: 'sm'
              });  
            };
            
            $scope.cerrarModalEstados = function () {
                modalEstados.close();
            };
            
            $scope.cerrarModalReportes = function () {
                modalReportes.close();
            };
            
            $scope.obtenerPresupuestos = function () {
                $scope.verPreload = true;
                $scope.verTabla = false;
                $http.post(urlObtenerPresupuestos, angular.toJson('')).
                    success(function (data, status, headers, config) {
                        if (angular.isDefined(data)) {
                            if (data[0] === 'true') {
                                $scope.presupuestos = angular.fromJson(data[1]);
                                if ($scope.presupuestos.length === 0) {
                                    $scope.alerta = { ver: true, tipo: 'info', mensaje: 'No se encontraron registros.' };
                                } else {
                                    $scope.verTabla = true;
                                }
                            }
                        }
                        $scope.verPreload = false;
                    }).
                    error(function (data, status, headers, config) {
                        $scope.verPreload = false;
                        $scope.alerta = { ver: true, tipo: 'danger', mensaje: 'Ocurrió un error en el servidor.' };
                    });
            };
            
            $scope.verModalEstados = function (presupuesto){
                $scope.idPresupuesto = presupuesto.idPresupuesto;
                verModalEstados();
            }
            
            $scope.cambiarEstado = function () {
                $http.post(urlCambiarEstado, angular.toJson([$scope.idPresupuesto, $scope.idEstado])).
                    success(function (data, status, headers, config) {
                        if (angular.isDefined(data)) {
                            if (data[0] === 'true') {
                                $scope.cerrarModalEstados();
                                $scope.obtenerPresupuestos();
                            } else {
                                alert(angular.fromJson(data[1]));
                            }
                        }
                    }).
                    error(function (data, status, headers, config) {
                        console.log('error');
                    });
            };

            $scope.obtenerReportes = function (presupuesto) {
                $scope.idPresupuesto = presupuesto.idPresupuesto;
                $http.post(urlObtenerReportes, angular.toJson(5)).
                    success(function (data, status, headers, config) {
                        if (angular.isDefined(data)) {
                            if (data[0] === 'true') {
                                $scope.reportes = angular.fromJson(data[1]);
                                if ($scope.reportes.length === 0){
                                    
                                } else if ($scope.reportes.length === 1) {
                                    $scope.idReporte = $scope.reportes[0].idReporte;
                                    $scope.descargar();
                                } else {
                                    $scope.idReporte = $scope.reportes[0].idReporte;
                                    verModalReportes();
                                }
                            }
                        }
                    }).
                    error(function (data, status, headers, config) {
                        
                    });
            };
                
            $scope.descargar = function (){
                $.fileDownload(urlDescargarPdf, { httpMethod: "POST", data: { r: $scope.idReporte, i: $scope.idPresupuesto } })
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