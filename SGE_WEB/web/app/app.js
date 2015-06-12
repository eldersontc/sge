'use strict';

define(['services/routeResolver'], function () {

    var app = angular.module('app', ['ngRoute', 'ui.bootstrap', 'routeResolverServices']);

    app.config(['$routeProvider', 'routeResolverProvider', '$controllerProvider',
        '$compileProvider', '$filterProvider', '$provide',
        function ($routeProvider, routeResolverProvider, $controllerProvider,
                $compileProvider, $filterProvider, $provide) {
            
            app.register =
                    {
                        controller: $controllerProvider.register,
                        directive: $compileProvider.directive,
                        filter: $filterProvider.register,
                        factory: $provide.factory,
                        service: $provide.service
                    };

            var route = routeResolverProvider.route;

            $routeProvider
                    .when('/inicio', route.resolve('inicio'))
                    .when('/404', route.resolve('404'))
                    .when('/lisSolicitudCotizacion', route.resolve('lisSolicitudCotizacion'))
                    .when('/lisCotizacion', route.resolve('lisCotizacion'))
                    .when('/lisPresupuesto', route.resolve('lisPresupuesto'))
                    .when('/', { redirectTo: '/inicio' })
                    .otherwise({ redirectTo: '/404' });

        }]);

    app.controller('appController', function ($scope) {
        
        $scope.menus = [
            { 
                nombre: 'VENTAS', 
                subMenus:[
                    { nombre: 'SOLICITUD DE COTIZACIÓN', path: 'lisSolicitudCotizacion' },
                    { nombre: 'COTIZACIÓN', path: 'lisCotizacion' },
                    { nombre: 'PRESUPUESTO', path: 'lisPresupuesto' }
                ]
            }
        ];
        
    });

    return app;
});